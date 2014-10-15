package vv.tp3;


import vv.tp3.factory.Factory;


public class LazyBiMap<K,V> extends BiMap<K,V> {

    private Factory<K, V> keyFactory;
    private Factory<V, K> valueFactory;

    public LazyBiMap(Factory<K, V> keyFactory, Factory<V, K> valueFactory) {
        this.keyFactory = keyFactory;
        this.valueFactory = valueFactory;
    }

    public V get(Object key) {
        if (containsKey(key)) {
            return super.get(key);
        } else {
            V value = valueFactory.transform((K) key);
            return put((K) key, value);
        }
    }

    public K getByValue(Object value) {
        if (containsValue(value)) {
            return  super.getByValue(value);
        } else {
            K key = keyFactory.transform((V) value);
            put(key, (V) value);
            return key;
        }
    }

}
