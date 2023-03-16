package study;

import java.util.HashMap;

/**
 * @author luhua8
 */
public class HashMapTest {

    public static void main(String[] args) {
        int expectedSize = 5;
        // HashMap的initialCapacity的初始值为16， 负载因子为 0.75
        // rehash过程比较耗时，初始化容量要设置成expectedSize/0.75 + 1 的话，可以有效地减少冲突
        HashMap<Integer, String> a = new HashMap<>(expectedSize * 4 / 3 + 1);
        a.put(1, "a");

        a.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);
            System.out.println("\n");
        });
    }
}
