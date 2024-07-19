package util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author xux
 * @since 0.0.1
 * @since 2024/7/18 下午2:25
 */
public class ZSet<V> implements Iterable<SkipList.Node<V>>{
    Map<V, Iterator<SkipList.Node<V>>> map;
    SkipList<V> sortedList;

    public void add(V value, double score){

    }

    public double getScore(V value){
        return 0.0;
    }

    public int size(){
        return map.size();
    }

    public int count(double minScore, double maxScore){
        return 0;
    }

    public List<V> range(int minRank, int maxRank){
        return null;
    }

    public int rank(V value){
        return 0;
    }

    public boolean remove(V value){
        return false;
    }

    public int remove(Collection<V> values){
        return 0;
    }

    public boolean removeByRank(int rank){
        return false;
    }

    public int removeByRank(int minRank, int maxRank){
        return 0;
    }

    @Override
    public Iterator<SkipList.Node<V>> iterator() {
        return sortedList.iterator();
    }
}
