package org.usb4java;

import org.jetbrains.annotations.ApiStatus.AvailableSince;
import org.jetbrains.annotations.Contract;

import java.io.Serial;
import java.util.Map;

/**
 * A short description of the item
 * <p>
 * Created on 2024-04-26
 *
 * @author umberto.manzoli
 * @since usb4java - 4.1.2
 */
@AvailableSince("4.1.2")
final class ImmutablePair<L, R> extends Pair<L, R> {

    /**
     * An immutable pair of nulls.
     */
    // This is not defined with generics to avoid warnings in call sites.
    @SuppressWarnings("rawtypes")
    private static final ImmutablePair NULL = new ImmutablePair<>(null, null);

    /**
     * Serialization version
     */
    @Serial
    private static final long serialVersionUID = 4954918890077093841L;
    /**
     * Left object
     */
    public final L left;
    /**
     * Right object
     */
    public final R right;

    /**
     * Create a new pair instance.
     *
     * @param left  the left value, may be null
     * @param right the right value, may be null
     */
    public ImmutablePair(final L left, final R right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Returns an immutable pair of nulls.
     *
     * @param <L> the left element of this pair. Value is {@code null}.
     * @param <R> the right element of this pair. Value is {@code null}.
     * @return an immutable pair of nulls.
     * @since 3.6
     */
    @Contract(pure = true)
    @SuppressWarnings("unchecked")
    public static <L, R> ImmutablePair<L, R> nullPair() {
        return NULL;
    }

    /**
     * Creates an immutable pair of two objects inferring the generic types.
     *
     * <p>This factory allows the pair to be created using inference to
     * obtain the generic types.</p>
     *
     * @param <L>   the left element type
     * @param <R>   the right element type
     * @param left  the left element, may be null
     * @param right the right element, may be null
     * @return a pair formed from the two parameters, not null
     */
    @Contract("!null, _ -> new; null, !null -> new")
    public static <L, R> ImmutablePair<L, R> of(final L left, final R right) {
        return left != null || right != null ? new ImmutablePair<>(left, right) : nullPair();
    }

    /**
     * Creates an immutable pair from a map entry.
     *
     * <p>This factory allows the pair to be created using inference to
     * obtain the generic types.</p>
     *
     * @param <L>  the left element type
     * @param <R>  the right element type
     * @param pair the existing map entry.
     * @return a pair formed from the map entry
     * @since 3.10
     */
    public static <L, R> ImmutablePair<L, R> of(final Map.Entry<L, R> pair) {
        return pair != null ? new ImmutablePair<>(pair.getKey(), pair.getValue()) : nullPair();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public L getLeft() {
        return left;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public R getRight() {
        return right;
    }

    /**
     * Throws {@link UnsupportedOperationException}.
     *
     * <p>This pair is immutable, so this operation is not supported.</p>
     *
     * @param value the value to set
     * @return never
     * @throws UnsupportedOperationException as this operation is not supported
     */
    @Override
    public R setValue(final R value) {
        throw new UnsupportedOperationException();
    }
}
