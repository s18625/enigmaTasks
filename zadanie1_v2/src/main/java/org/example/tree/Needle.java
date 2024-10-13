package org.example.tree;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Random;

/**
 * Represents a needle in a tree structure, which is a specific type of foliage.
 * This class implements {@link Foliable}, meaning it has growth behavior but
 * does not fall off during the seasons (e.g., evergreen trees).
 *
 * @param <Needle> The type of foliage this class represents.
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
class Needle extends Foliage<Needle> implements Foliable {

    /**
     * Length of the needle in arbitrary units. It increases when the needle grows.
     */
    double length;

    /**
     * Constructor for creating a {@code Needle} object associated with a given branch.
     *
     * @param branch The branch to which this needle belongs.
     */
    public Needle(Branch<Needle> branch) {
        super(branch);
    }

    /**
     * Simulates the growth of the needle. Each time this method is called,
     * the length of the needle increases by a random value.
     */
    @Override
    public void grow() {
        length += new Random().nextDouble();
    }

    /**
     * Indicates whether the needle should fall off the tree. Needles do not fall off,
     * so this method always returns {@code false}.
     *
     * @return {@code false} as needles do not fall off.
     */
    @Override
    public boolean shouldFall() {
        return false;
    }

    /**
     * Provides a string representation of the {@code Needle} object, including its length.
     *
     * @return A string describing the needle and its length.
     */
    @Override
    public String toString() {
        return "Needle{" +
                "length=" + length +
                '}';
    }
}
