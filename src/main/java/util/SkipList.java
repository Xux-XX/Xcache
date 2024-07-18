package util;

import java.util.Iterator;
import java.util.Objects;
import java.util.Random;

/**
 * 跳表
 * @author xux
 * @since 0.0.1
 * @since 2024/7/17 下午3:18
 */
public class SkipList<V> implements Iterable<SkipList.Node<V>> {


    /**
     * head 链表头节点<p>
     * length 跳表长度<p>
     * maxLevel 跳表最大高度
     */
    private final Node<V> head;
    private int length;
    private final int MAX_LEVEL = 32;
    private final Random random;

    public SkipList() {
        this.random = new Random();
        head = new Node<>(null, 0.0, MAX_LEVEL);
        length = 0;
    }

    /**
     * 新增数据
     * @param value 值
     * @param score 分数
     * @return Iterator<V> 以新增节点为起点的迭代器
     */
    public Iterator<Node<V>> add(V value, double score){
        int level = randomLevel();
        Node<V> newNode = new Node<>(value, score, level);
        Node<V> current = head;
        int[] rank = new int[level];
        Node[] update = new Node[level];
        for (int i = level - 1; i >= 0; i--){
            rank[i] = (i == level - 1 ? 0 : rank[i+1]);
            while (current.next[i] != null && current.next[i].score <= score){
                current = current.next[i];
            }
            update[i] = current;
        }

        length ++;
        return new ListIterator(newNode);
    }

    /**
     * 获取指定分数的下限，返回的迭代器指向大于等于分数的最小迭代器
     * @param score 指定的分数
     * @return Iterator<V> 大于等于指定分数的最小值
     */
    public Iterator<Node<V>> lowerBoundScore(double score){
        Node<V> current = head;
        for (int i = MAX_LEVEL - 1; i >= 0; i--){
            while (current.next[i] != null && current.next[i].score < score) current = current.next[i];
        }
        return new ListIterator(current);
    }

    /**
     * 获取指定分数的上限
     * @param score 指定的分数
     * @return Iterator<V> 大于指定分数的最小值
     */
    public Iterator<Node<V>> upperBoundScore(double score){
        Node<V> current = head;
        for (int i = MAX_LEVEL - 1; i >= 0; i--){
            while (current.next[i] != null && current.next[i].score <= score) current = current.next[i];
        }
        return new ListIterator(current);
    }

    /**
     * 随机生成高度,高度为h的概率为(1/2)^(h-1)
     * @return level [1, MAX_LEVEL]
     */
    private int randomLevel(){
        int level = 1;
        while (random.nextBoolean()) level++;
        return Math.min(level, MAX_LEVEL);
    }

    @Override
    public Iterator<Node<V>> iterator() {
        return new ListIterator(head);
    }

    @Override
    public String toString() {
        Iterator<Node<V>> it = iterator();
        StringBuilder buffer = new StringBuilder();
        buffer.append('[');
        boolean isBegin = true;
        while (it.hasNext()){
            Node<V> next = it.next();
            if(isBegin){
                isBegin = false;
                buffer.append(next.toString());
            }
            buffer.append(", ").append(next.toString());
        }
        buffer.append(']');
        return buffer.toString();
    }

    public static class Node<V> {
        /**
         * value 存储的值<p>
         * score 评分,跳表内按评分排序<p>
         * next[i] 第i层的下一个节点<p>
         * span 跨度，表示当前节点到下一个节点间有多少节点，用于快速计算rank
         */
        V value;
        double score;
        Node<V>[] next;
        int[] span;

        public Node(V value, double score, int level) {
            this.score = score;
            this.value = value;
            next = new Node [level];
            span = new int[level];
        }

        public void setValue(V value) {
            this.value = value;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public V getValue() {
            return value;
        }

        public double getScore() {
            return score;
        }

        @Override
        public String toString() {
            return "{" + "score=" + score + ", value=" + value + '}';
        }
    }

    public class ListIterator implements Iterator<Node<V>> {
        private Node<V> cursor;

        public ListIterator(Node<V> cursor) {
            this.cursor = cursor;
        }

        @Override
        public boolean hasNext() {
            return cursor.next[0] != null;
        }

        @Override
        public Node<V> next() {
            return cursor = cursor.next[0];
        }

        @Override
        public void remove() {
            Node<V> next = cursor.next[0];
            for (int i=0; i < MAX_LEVEL && cursor.next[i] == next; i++){
                cursor.next[i] = next.next[i];
            }
            length--;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof SkipList<?>.ListIterator)) return false;
            return cursor == ((ListIterator) o).cursor;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(cursor);
        }
    }
}
