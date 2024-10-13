package org.example.tree;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The {@code Trunk} class represents the trunk of a tree, which grows in height and supports {@link Branch} objects.
 * The trunk can grow and generate branches as it increases in size.
 *
 * @param <T> the type of {@link Foliable} that the trunk's branches can hold.
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
class Trunk<T extends Foliable> implements Growable {

    /**
     * The {@link Tree} to which this trunk belongs.
     */
    final Tree<T> tree;

    /**
     * The height of the trunk, which increases as it grows.
     * The initial height is set to 1.
     */
    double height = 1;

    /**
     * A list of branches that grow from the trunk. New branches are added as the trunk grows.
     */
    List<Branch<T>> branches = new ArrayList<>();

    /**
     * Grows the trunk by increasing its height randomly and adding new branches
     * as the trunk grows. The number of branches is limited by the height of the trunk.
     */
    @Override
    public final void grow() {
        height *= 1 + new Random().nextDouble();
        if (branches.size() < 2 * height) {
            branches.add(new Branch<T>(tree.getTrunk()));
            branches.forEach(Branch::grow);
        }
    }

    /**
     * Provides a string representation of the trunk, including its height and branches.
     *
     * @return a string representing the trunk
     */
    @Override
    public String toString() {
        return "Trunk{" +
                "height=" + height +
                ", branches=" + branches +
                '}';
    }
}
