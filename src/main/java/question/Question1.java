package question;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

public class Question1 {
    /*
    给定两个List，如[黑桃, 红心, 梅花, 方块]和[A, 2, 3, 4, 5, 6, ..., J, Q, K]

返回一个新的52个元素的List，包含各种花色和数字的组合，即
[[黑桃, A], [红心, A], [梅花, A], [方块, A], [黑桃, 2], [红心, 2], [梅花, 2], [方块, 2], ..., [黑桃, K], [红心, K], [梅花, K], [方块, K]]。
     */


    public static void main(String[] args) {
        ImmutableSet<String> charList = ImmutableSet.of("黑桃", "红心", "梅花", "方块");
        ImmutableSet<Object> charList2 = ImmutableSet.of("A", 2, 3, 4, 5, 6, 7, 8, 9, 10, "J", "Q", "K");
        Set<List<Object>> set = Sets.cartesianProduct(ImmutableList.of(charList, charList2));
        set.forEach(System.out::println);
        System.out.println("set.size() = " + set.size());
    }
}
