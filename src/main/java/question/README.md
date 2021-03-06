# 每日一题

## 2019.12.10 【数据类型】【装箱/拆箱】【中等】

Q:为什么下列代码能够实现`1+1=4`？

```
Field valueField = Integer.class.getDeclaredField("value");
valueField.setAccessible(true);
valueField.setInt(1, 2);

Integer a = 1, b = 1;
System.out.println("1+1=" + (a + b));
```

A: 因为自动装/拆箱和缓存的存在。

**必须强调，这个例子是出于学习的目的，绝对绝对不要在工作中写这样的代码！**

如果你有兴趣，你可以观察一下上述代码的字节码，它等价于：

```
Field valueField = Integer.class.getDeclaredField("value");
valueField.setAccessible(true);
valueField.setInt(Integer.valueOf(1), 2);

Integer a = Integer.valueOf(1), b = Integer.valueOf(1);
System.out.println("1+1=" + (a.intValue() + b.intValue()));
```

其中编译器偷偷摸摸帮你插入的`Integer.valueOf`/`intValue()`就是我们耳熟能详的“自动装箱/拆箱机制”。

如果你对字节码不熟悉，也没关系，只要在对应的方法里打一个断点调试即可。

因此，你看到的`Integer a = 1`，实际上是`Integer.valueOf(1)`对象。那么，请你打开这个方法，阅读一下它的代码，会发现，它对-128~127范围内的整数，内部维护了一个缓存，这是[Java语言规范](https://docs.oracle.com/javase/specs/jls/se8/html/jls-5.html#jls-5.1.7)所要求的：

> If the value p being boxed is an integer literal of type int between -128 and 127 inclusive (§3.10.1), or the boolean literal true or false (§3.10.3), or a character literal between '\u0000' and '\u007f' inclusive (§3.10.4), then let a and b be the results of any two boxing conversions of p. It is always the case that a == b.

所以，看起来我们在操作数字1，实际上我们只是在操作`Integer.valueOf(1)`返回的不知道是什么鬼的对象。这个对象内部的数据，被反射篡改了：

```
Field valueField = Integer.class.getDeclaredField("value");
valueField.setAccessible(true);
valueField.setInt(Integer.valueOf(1), 2);
```

因此，你看到的`a+b`实际上是`a.intValue()+b.intValue()`，对这两个对象调用方法获取一个（被篡改后的）值，那么自然也就会输出令人惊讶的结果了。


## 2019.12.11 【集合】【搜索技巧】【guava】【简单】

给定两个List，如`[黑桃, 红心, 梅花, 方块]`和`[A, 2, 3, 4, 5, 6, ..., J, Q, K]`

返回一个新的52个元素的List，包含各种花色和数字的组合，即`[[黑桃, A], [红心, A], [梅花, A], [方块, A], [黑桃, 2], [红心, 2], [梅花, 2], [方块, 2], ..., [黑桃, K], [红心, K], [梅花, K], [方块, K]]`。

我相信你能自己写出来。我的问题是，你能不能通过搜索，找到一个第三方库，完成上述功能？这样，你就不用自己实现一遍，直接调用别人写好的方法就行了。

锻炼一下自己的搜索技能吧！

A: 在数学上，这种操作叫做`笛卡尔积`，对应的英文是`Cartesian product`，用这些关键词去搜索，就可以很容易地找到Guava中有一个`Sets/Lists.cartesianProduct`方法可以直接拿来用。

下次，当想要自己实现一个东西时，先按捺一下自己激动的心，去搜索一下有没有成熟的、久经考验的第三方库可以使用！

## 2019.12.13 【Java基础】【方法调用】【值传递】【简单】

小明想要用下列代码找出一个字符串中，大写字母、小写字母和数字分别有多少个，但是结果很明显不对。请帮小明找到问题并修复程序中的bug。

```
    public static void main(String[] args) {
        int upperCase = 0, lowerCase = 0, digit = 0;
        count("AbC1DefG230", upperCase, lowerCase, digit);
        System.out.println("upperCase: " + upperCase + ", lowerCase: " + lowerCase + ", digit: " + digit);
    }

    // 统计字符串中的大写字符、小写字符、数字出现的个数
    public static void count(String s, int upperCase, int lowerCase, int digit) {
        for (char ch : s.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                upperCase++;
            } else if (Character.isLowerCase(ch)) {
                lowerCase++;
            } else if (Character.isDigit(ch)) {
                digit++;
            }
        }
    }
```

A：Java中，方法调用的参数传递永远是【值传递】，因此，方法里拿到的参数是一个【副本】，更改这个参数的值不会引起调用者的参数发生更改（因为根本就是两份数据）。这个问题的本质是，如何使用方法返回多个值。你可以有很多种选择：

- 返回一个对象。例如自己定义一个对象，用于返回数据。
- 传递`AtomicInteger`参数。方法里调用`set()`方法对对象的修改是可以被方法调用者读取到的。

## 2019.12.15 【Java基础】【数据类型】【数组】【中等】

Q：Java中的数组是什么类型，是基本数据类型还是引用数据类型？为什么我从来没有见过数组的定义？

A：Java中的数组是引用数据类型，是JVM原生支持的一种特殊数据类型，由虚拟机通过专用的指令完成创建（有兴趣可以去翻一下虚拟机指令，有一部分是专门处理数组的）。你可以简单把数据理解成（虽然这样的定义并不存在）：

```
class int[] {
    public int length;
    private [][][][][][][][][][] data; // 存储每个数据
    public int [](int i) {
        return 第i个数据
    }
}
```

## 2019.12.17 【Java基础】【枚举】【中等】

Q：现在有如下代码：

```
class Message {
    public String getMessageType() { ... }
}

class MyService {
    public create(Object data) { ... }
    public update(Object data) { ... }
    public delete(Object data) { ... }
}

public void handleMessage(MyService service, Message message, Object data){
    switch(message.getMessageType()) {
        case "create": service.create(data); break;
        case "update": service.update(data); break;
        case "delete": service.delete(data); break;
        default: throw new RuntimeException();
    }
}
```

你能否进行你能否使用枚举对代码进行一下优化，使得代码不需要冗长的switch语句？

A: （首先必须指出，这个答案不能算是「标准答案」，只能算作一种参考，你可以自由地选择自己认为好的写法）

```
class Message {
    public String getMessageType() { ... }
}

class MyService {
    public create(Object data) { ... }
    public update(Object data) { ... }
    public delete(Object data) { ... }
}

public void handleMessage(MyService service, Message message, Object data){
    switch(message.getMessageType()) {
        case "create": service.create(data); break;
        case "update": service.update(data); break;
        case "delete": service.delete(data); break;
        default: throw new RuntimeException();
    }
}
```

通常，在我们的心中，「枚举」这种东西似乎就是一个个的小球，只能用来进行比较或者switch操作。但是，实际上，枚举类也是类，可以拥有方法、实现多态。我们可以利用多态来省掉多余的switch操作。

```
    enum MessageType {
        CREATE {
            @Override
            public void handleMessage(MyService service, Object data) {
                service.create(data);
            }
        },
        UPDATE {
            @Override
            public void handleMessage(MyService service, Object data) {
                service.update(data);
            }
        },
        DELETE {
            @Override
            public void handleMessage(MyService service, Object data) {
                service.delete(data);
            }
        };

        public abstract void handleMessage(MyService service, Object data);
    }

    public void handleMessage(MyService service, MessageType messageType, Object data){
        messageType.handleMessage(service, data);
    }
```

发现了么？你首先声明了一个带抽象方法的`MessageType`枚举类型，然后声明了多个枚举值，每个枚举值可以覆盖该抽象方法，这样，枚举不再仅仅是存储数据的对象，它还可以调用方法，而`switch`消失了——Java的多态机制会自动帮我们处理方法分派。

如果你嫌这种覆盖的写法太啰嗦，Java 8引入的函数式写法可以让代码更精炼——代价是更难读懂。

```
    enum MessageType {
        CREATE(MyService::create),
        UPDATE(MyService::update),
        DELETE(MyService::delete);

        private BiConsumer<MyService, Object> function;

        MessageType(BiConsumer<MyService, Object> function) {
            this.function = function;
        }

        public void handleMessage(MyService service, Object data) {
            function.accept(service, data);
        }
    }
```

请仔细思考一下为什么可以这么写？

## 2019.12.18 【Java基础】【范型】【反序列化】【中等】

给定如下代码

```
    class User {
        private Integer id;
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
```

请编写一个`List<User> parse(String jsonList) { }`方法，使得能将

```
[{"id":1,"name":"张三"},{"id":2,"name":"李四"}]
```

这样的JSON字符串转换成`List<User>`。

这个题看上去简单，实际上暗藏杀机，试试看吧。
