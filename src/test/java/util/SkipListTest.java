package util;


import org.junit.jupiter.api.Test;

/**
 * @author xux
 * @since 0.0.1
 * @since 2024/7/17 下午5:57
 */

public class SkipListTest {
    @Test
    public void doTest(){
        SkipList<String> list = new SkipList<>();
        list.add("alice", 1.0);
        list.add("bob", 2.0);
        list.add("?", -1.0);
        System.out.println(list);
    }
}
