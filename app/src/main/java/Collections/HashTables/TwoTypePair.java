package Collections.HashTables;

/**
 *  TwoTypePair defines a pair of two different types.
 */
public class TwoTypePair<K, V> {
    
    private K key;
    private V value;
    
    public TwoTypePair() {
        this.key = null;
        this.value = null;
    }

    public TwoTypePair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "\nTwoTypePair {" + "key=" + key + ", value=" + value + '}';
    }
}
