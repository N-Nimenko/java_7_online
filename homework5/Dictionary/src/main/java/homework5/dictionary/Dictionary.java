package homework5.dictionary;

import java.util.Hashtable;
import java.util.Collection;
import java.util.Set;

public class Dictionary<K, V> {
    private final Hashtable<K, V> hashtable;

    public Dictionary() {
        hashtable = new Hashtable<>();
    }

    public boolean isEmpty() {
        return hashtable.isEmpty();
    }

    public boolean containsKey(K key) {
        return hashtable.containsKey(key);
    }

    public boolean containsValue(V value) {
        return hashtable.containsValue(value);
    }

    public V get(K key) {
        return hashtable.get(key);
    }

    public boolean put(K key, V value) {
        if (!hashtable.containsKey(key)) {
            hashtable.put(key, value);
            return true;
        }
        return false;
    }

    public boolean remove(K key) {
        if (hashtable.containsKey(key)) {
            hashtable.remove(key);
            return true;
        }
        return false;
    }

    public int size() {
        return hashtable.size();
    }

    public boolean clear() {
        hashtable.clear();
        return true;
    }

    public boolean putAll(Dictionary<K, V> dictionary) {
        for (K key : dictionary.keySet()) {
            V value = dictionary.get(key);
            hashtable.put(key, value);
        }
        return true;
    }

    public Set<K> keySet() {
        return hashtable.keySet();
    }

    public Collection<V> values() {
        return hashtable.values();
    }
}