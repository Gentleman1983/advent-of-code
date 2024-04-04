package de.havox_design.aoc2019.day18;

import java.util.Objects;

public class IncomparablePair<K, V> {
    private final K k;
    private final V v;

    public IncomparablePair(K k, V v) {
        this.k = k;
        this.v = v;
    }

    public K k() {
        return k;
    }

    public V v() {
        return v;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IncomparablePair)) {
            return false;
        }
        @SuppressWarnings("unchecked")
        IncomparablePair<K, V> that = (IncomparablePair<K, V>) o;

        return this.k.equals(that.k) && this.v.equals(that.v);
    }

    @Override
    public int hashCode() {
        return Objects.hash(k, v);
    }

    @Override
    public String toString() {
        return "Key: " + k.toString() + ", Value: " + v.toString();
    }

}
