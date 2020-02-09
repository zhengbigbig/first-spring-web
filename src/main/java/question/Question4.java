package question;

// 使用多态
public class Question4 {
    public int calculate(int x, int y, Operation operation) {
        return operation.calculate(x, y);
    }
}

interface Operation {
    int calculate(int x, int y);
}

class Add implements Operation {
    @Override
    public int calculate(int x, int y) {
        return x + y;
    }
}

class Minus implements Operation {
    @Override
    public int calculate(int x, int y) {
        return x - y;
    }
}

// 使用枚举
enum Op {
    ADD {
        @Override
        int calculate(int x, int y) {
            return x + y;
        }
    }, MINUS {
        @Override
        int calculate(int x, int y) {
            return x - y;
        }
    };

    abstract int calculate(int x, int y);
    }