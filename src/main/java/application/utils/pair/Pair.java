package application.utils.pair;

/**
 * The record <b>Pair</b> contains 2 comparable objects, the first one will be smaller than the second one.
 *
 * @param <E1> the type of the objects, should extend Comparable
 */
public class Pair<E1, E2> {
    private final E1 first;
    private final E2 second;

    public Pair (E1 first, E2 second) {
        this.first = first;
        this.second = second;
    }

    public E1 first() {
        return first;
    }

    public E2 second() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;

        return first.equals(pair.first) && second.equals(pair.second);
    }
}