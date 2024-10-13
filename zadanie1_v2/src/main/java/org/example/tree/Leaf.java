package org.example.tree;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.awt.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.Random;

/**
 * The {@code Leaf} class represents a leaf object in a tree, which grows and changes color.
 * It implements the {@code Foliable} interface, allowing it to determine when it should fall.
 *
 * <p>Each {@code Leaf} belongs to a {@code Branch}, and it has attributes such as surface size
 * and color, which change over time, especially in relation to seasons.</p>
 *
 * @param <Leaf> Type of foliage the leaf represents.
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
class Leaf extends Foliage<Leaf> implements Foliable {

    /**
     * The surface area size of the leaf, which increases when the leaf grows.
     */
    double surfaceSize;

    /**
     * The color of the leaf, which starts as white by default.
     */
    Color color = Color.WHITE;

    /**
     * The random generator for simulating growth variability.
     */
    final Random random;

    /**
     * Constructs a new {@code Leaf} and attaches it to the given {@code Branch}.
     *
     * @param branch The branch to which this leaf is attached.
     */
    public Leaf(Branch<Leaf> branch) {
        super(branch);
        this.random = new Random();
    }

    /**
     * Simulates the growth of the leaf, increasing its surface size by 10% and darkening its color.
     */
    @Override
    public void grow() {
        surfaceSize = (surfaceSize + random.nextDouble()) * 1.1;
        color = color.darker();
    }

    /**
     * Determines whether the leaf should fall. Leaves should fall during fall or winter seasons.
     *
     * @return {@code true} if the current season is fall or winter, otherwise {@code false}.
     */
    @Override
    public boolean shouldFall() {
        return isFallOrWinter();
    }

    /**
     * Checks if the current date is in fall or winter based on the month of the year.
     *
     * @return {@code true} if the current month is October, November, December, January, or February.
     */
    private boolean isFallOrWinter() {
        final int currentMonth = LocalDate.now().getMonth().getValue();
        return currentMonth > Month.SEPTEMBER.getValue() || currentMonth < Month.FEBRUARY.getValue();
    }

    /**
     * Returns a string representation of the {@code Leaf}, including its surface size and color.
     *
     * @return a string representing the leaf's properties.
     */
    @Override
    public String toString() {
        return "Leaf{" +
                "surfaceSize=" + surfaceSize +
                ", color=" + color +
                '}';
    }
}
