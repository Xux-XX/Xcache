package util;

import java.util.Iterator;

/**
 * 跳表
 * @author xux
 * @since 0.0.1
 * @since 2024/7/17 下午3:18
 */
public class SkipList<V> {


    /**
     * head 链表头节点<p>
     * length 跳表长度<p>
     * maxLevel 跳表最大高度
     */
    private Node<V> head;
    private int length;
    private final int maxLevel = 32;

    /**
     * 新增数据
     * @param value 值
     * @param score 分数
     * @return Iterator<V> 以新增节点为起点的迭代器
     */
    Iterator<V> add(V value, double score){
        return null;
    }

    /**
     * 移除对应的值
     * @param value 需要移除的值
     * @return this
     */
    SkipList<V> remove(V value){
        return this;
    }

    /**
     * 获取值对应的的分数
     * @param value 查询的值
     * @return double 分数
     */
    double getScore(V value){
        return 0.0;
    }

    /**
     * 返回分数在指定范围内的数量
     * @param minScore 最小分数
     * @param maxScore 最大分数
     * @return int 分数在指定范围内的数量
     */
    int count(double minScore, double maxScore){
        return 0;
    }







    private static class Node<V> {
        /**
         * value 存储的值<p>
         * score 评分,跳表内按评分排序<p>
         * next 同一层下一个节点<p>
         * level 一个数组
         */
        V value;
        double score;
        Node<V> next;
        Node<V>[] level;
    }

    private class Itr implements Iterator<V> {
        Node<V> cursor;
        public Itr(Node<V> start) {
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public V next() {
            return null;
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }
    }
}
