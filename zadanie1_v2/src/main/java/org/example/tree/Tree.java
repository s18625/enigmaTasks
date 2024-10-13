package org.example.tree;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * The {@code Tree} class is an abstract class that represents a tree with a trunk and the ability to grow.
 * Each tree can produce foliage (leaves, needles, etc.) through the abstract method {@link #produceFoliage(Branch)}.
 *
 * @param <T> the type of {@link Foliable} this tree produces (e.g., leaves, needles)
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
abstract class Tree<T extends Foliable> implements Growable {

    /**
     * The trunk of the tree, which holds branches and grows over time.
     */
    Trunk<T> trunk = new Trunk<>(this);

    /**
     * Abstract method to produce foliage (e.g., leaves or needles) for a given branch.
     *
     * @param branch the branch for which foliage is to be produced
     * @return a new instance of {@link Foliage} specific to the tree implementation
     */
    abstract Foliage<T> produceFoliage(Branch<T> branch);

    /**
     * Grows the tree by growing its trunk and branches.
     */
    public final void grow() {
        trunk.grow();
    }

    /**
     * Provides a string representation of the tree, including its trunk and its branches.
     *
     * @return a string representing the tree
     */
    @Override
    public String toString() {
        return "Tree{" +
                "trunk=" + trunk +
                '}';
    }
}
