package ch.zhaw.ads;

import java.util.*;

public class MyHashtable<K, V> implements java.util.Map<K, V> {
    private K[] hashKeys;
    private V[] hashValues;
    private int numberOfElements;
    private Object isDeteletedMarker;

    private int hash(Object hashKey) {
        int hash = Math.abs(hashKey.hashCode());
        return hash % hashKeys.length;
    }

    public MyHashtable(int size) {
        hashKeys = (K[]) new Object[size];
        hashValues = (V[]) new Object[size];
        numberOfElements = 0;
        isDeteletedMarker = new Object();
    }

    //  Removes all mappings from this map (optional operation).
    public void clear() {
        // to be done
        Arrays.fill(hashKeys, null);
        Arrays.fill(hashValues, null);
        numberOfElements = 0;
    }

    //  Associates the specified value with the specified key in this map (optional operation).
    public V put(K hashKey, V hashValue) {
        if (numberOfElements >= hashKeys.length) {
            extendHashTable();
        }
        int hashIndex;
        try {
            hashIndex = findPos(hashKey);
        } catch (NoSuchElementException e) {
            hashIndex = hash(hashKey);
            while (hashKeys[hashIndex] != null && hashKeys[hashIndex] != isDeteletedMarker) {
                hashIndex++;
                hashIndex %= hashKeys.length;
            }
            numberOfElements++;
        }
        hashKeys[hashIndex] = hashKey;
        hashValues[hashIndex] = hashValue;
        return hashValue;
    }

    private void extendHashTable() {
        K[] oldKeys = hashKeys.clone();
        V[] oldValues = hashValues.clone();
        clear();
        hashKeys = (K[]) new Object[oldKeys.length * 2];
        hashValues = (V[]) new Object[oldKeys.length * 2];
        for (int i = 0; i < oldKeys.length; i++) {
            put(oldKeys[i], oldValues[i]);
        }
    }

    //  Returns the value to which this map maps the specified key.
    public V get(Object key) {
        try {
            return hashValues[findPos(key)];
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    //  Returns true if this map contains no key-value mappings.
    public boolean isEmpty() {
        if (numberOfElements == 0) {
            return true;
        } else {
            return false;
        }
    }

    //  Removes the mapping for this key from this map if present (optional operation).
    public V remove(Object key) {
        try {
            int hash = findPos(key);
            V hashValue = hashValues[hash];
            hashValues[hash] = null;
            hashKeys[hash] = (K) isDeteletedMarker;
            numberOfElements = numberOfElements - 1;
            return hashValue;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    //  Returns the number of key-value mappings in this map.
    public int size() {
        return numberOfElements;
    }

    // =======================================================================
    //  Returns a set view of the keys contained in this map.
    public Set keySet() {
        throw new UnsupportedOperationException();
    }

    //  Copies all of the mappings from the specified map to this map (optional operation).
    public void putAll(Map t) {
        throw new UnsupportedOperationException();
    }

    //  Returns a collection view of the values contained in this map.
    public Collection values() {
        throw new UnsupportedOperationException();
    }

    //  Returns true if this map contains a mapping for the specified key.
    public boolean containsKey(Object key) {
        throw new UnsupportedOperationException();
    }

    //  Returns true if this map maps one or more keys to the specified value.
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException();
    }

    //  Returns a set view of the mappings contained in this map.
    public Set entrySet() {
        throw new UnsupportedOperationException();
    }

    //  Compares the specified object with this map for equality.
    public boolean equals(Object o) {
        throw new UnsupportedOperationException();
    }

    //  Returns the hash code value for this map.
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

    private int findPos(Object hashKey) throws NoSuchElementException {
        int hash = hash(hashKey);
        int hashKeyLength = 0;
        while (hashKeys[hash] != hashKey && hashKeys[hash] != null) {
            if (hashKeyLength == hashKeys.length)
                throw new NoSuchElementException();
            hash = hash + 1;
            hash %= hashKeys.length;
            hashKeyLength = hashKeyLength + 1;
        }
        if (hashKeys[hash] == null) throw new NoSuchElementException();
        return hash;
    }
}
