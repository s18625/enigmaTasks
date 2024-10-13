package org.example.tree;

/**
 * The {@code Growable} interface represents an entity that can grow.
 * Classes that implement this interface are expected to provide their own logic for how growth is defined and implemented
 * by overriding the {@link #grow()} method.
 *
 * <p>This interface is typically implemented by objects that change over time, such as trees, branches, and foliage.</p>
 */
interface Growable {

    /**
     * Triggers the growth process of the implementing object.
     * The specific implementation of this method should define how the object grows over time.
     */
    void grow();
}
