package homework5.dictionary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Dictionary<K, V> {
    private final ArrayList<Entry<K, V>> entries;

    public Dictionary() {
        entries = new ArrayList<>();
    }

    public boolean isEmpty() {
        return entries.isEmpty();
    }

    public boolean containsKey(K key) {
        return findEntryByKey(key) != null;
    }

    public boolean containsValue(V value) {
        for (Entry<K, V> entry : entries) {
            if (entry.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    public V get(K key) {
        Entry<K, V> entry = findEntryByKey(key);
        return entry != null ? entry.getValue() : null;
    }

    public boolean put(K key, V value) {
        if (!containsKey(key)) {
            Entry<K, V> newEntry = new Entry<>(key, value);
            entries.add(newEntry);
            return true;
        }
        return false;
    }

    public boolean remove(K key) {
        Entry<K, V> entry = findEntryByKey(key);
        if (entry != null) {
            entries.remove(entry);
            return true;
        }
        return false;
    }

    public int size() {
        return entries.size();
    }

    public boolean clear() {
        entries.clear();
        return true;
    }

    public boolean putAll(Dictionary<K, V> dictionary) {
        for (K key : dictionary.keySet()) {
            V value = dictionary.get(key);
            put(key, value);
        }
        return true;
    }

    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (Entry<K, V> entry : entries) {
            keySet.add(entry.getKey());
        }
        return keySet;
    }

    public Collection<V> values() {
        Collection<V> valueCollection = new ArrayList<>();
        for (Entry<K, V> entry : entries) {
            valueCollection.add(entry.getValue());
        }
        return valueCollection;
    }

    private Entry<K, V> findEntryByKey(K key) {
        for (Entry<K, V> entry : entries) {
            if (entry.getKey().equals(key)) {
                return entry;
            }
        }
        return null;
    }

    private static class Entry<K, V> {
        private final K key;
        private final V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}