package org.example.tree;

/**
 * The {@code Foliable} interface represents a type of foliage that can grow and has the potential to fall off.
 * Classes that implement this interface must define behavior for both growing and determining whether the foliage
 * should fall.
 * <p>
 * This interface extends {@link Growable}, meaning that any class implementing {@code Foliable} must also provide
 * an implementation for growth-related behavior.
 * </p>
 *
 * <p>Methods:</p>
 * <ul>
 *   <li>{@link #shouldFall()}: Determines whether the foliage should fall.</li>
 * </ul>
 */
interface Foliable extends Growable {

    /**
     * Determines whether the foliage should fall off.
     *
     * @return {@code true} if the foliage should fall off, {@code false} otherwise
     */
    boolean shouldFall();
}
