package vv.tp3;

/**
 * @authors Hacault Robin - Lhomme Anthony
 */
public class BiMap<K,V> extends SimpleMap<K,V> {

    /**
     * Get the key which represent the object value in the BiMap
     * @param value The object used to retrieve the ket
     * @return The key which refers to value, or null if it doesn't match
     */
    public K getByValue(Object value) {
        for (K key : super.keySet()) {
            if (super.get(key).equals(value)) {
                return key;
            }
        }
        return null;
    }

    /**
     * Removes the entry from the BiMap which has the value "value"
     * @param value The value of the entry to remove
     * @return The key of the entry removed, or null if no element was removed
     */
    public K removeValue(Object value) {
        for (K key : super.keySet()) {
            if (super.get(key).equals(value)) {
                super.remove(key);
                return key;
            }
        }
        return null;
    }


    @Override
    public V put(K key, V value) {
       if (super.containsValue(value)) {
            removeValue(value);
       }
       return super.put(key, value);
    }

}