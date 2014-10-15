package vv.tp3;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class SimpleMap<K,V> implements Map<K,V> {

    private List<K> keys;
    private List<V> values;

    public SimpleMap() {
        keys = new ArrayList<K>();
        values = new ArrayList<V>();
    }

    @Override
    public int size() {
        return keys.size();
    }

    @Override
    public boolean isEmpty() {
        return keys.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return keys.contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return values.contains(value);
    }

    @Override
    public V get(Object key) {
        int index = keys.indexOf(key);
        return values.get(index);
    }

    @Override
    public V put(K key, V value) {
        keys.add(key);
        values.add(value);
        return value;
    }

    @Override
    public V remove(Object key) {
        int index = keys.indexOf(key);

        keys.remove(index);
        return values.remove(index);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for(K key : m.keySet()) {
            V value = m.get(key);

            keys.add(key);
            values.add(value);
        }
    }

    @Override
    public void clear() {
        keys.clear();
        values.clear();
    }

    @Override
    public Set<K> keySet() {
        return new HashSet<K>(keys);
    }

    @Override
    public Collection<V> values() {
        return values;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new NotImplementedException();
    }
}
