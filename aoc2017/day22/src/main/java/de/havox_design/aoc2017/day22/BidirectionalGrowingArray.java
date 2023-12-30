package de.havox_design.aoc2017.day22;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.IntFunction;

public class BidirectionalGrowingArray<T> implements Iterable<T> {

    private final IntFunction<T[]> arrayProducer;

    T[] array;

    private int offset;

    public BidirectionalGrowingArray(final IntFunction<T[]> arrayProducer) {
        this(arrayProducer, 0);
    }

    public BidirectionalGrowingArray(final IntFunction<T[]> arrayProducer, final int startIndex) {
        this.arrayProducer = arrayProducer;
        this.array = arrayProducer.apply(0);
        this.offset = startIndex;
    }

    public T get(final int index) {
        final int internalIndex = mapIndex(index);

        return (internalIndex < 0 || internalIndex >= array.length) ? null : array[internalIndex];
    }

    public T put(final int index, final T newValue) {
        int internalIndex = mapIndex(index);

        internalIndex = checkSize(internalIndex);

        final T result = array[internalIndex];

        array[internalIndex] = newValue;

        return result;
    }

    private int checkSize(final int neededInternalIndex) {
        final int delta = neededInternalIndex < 0 ? -neededInternalIndex : neededInternalIndex - array.length + 1;

        if (delta > 0) {
            final T[] newArray = arrayProducer.apply(array.length + delta);

            if (neededInternalIndex < 0) {
                System.arraycopy(array, 0, newArray, delta, array.length);
            } else {
                System.arraycopy(array, 0, newArray, 0, array.length);
            }

            array = newArray;

            if (neededInternalIndex < 0) {
                offset -= delta;
                return 0;
            }
        }

        return neededInternalIndex;
    }

    private int mapIndex(final int index) {
        return index - offset;
    }

    public int[] getRange() throws ArrayIndexOutOfBoundsException {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return new int[] { offset, offset + array.length - 1 };
    }

    private boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return array.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int position;

            @Override
            public boolean hasNext() {
                return position < array.length;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    return array[position++];
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
