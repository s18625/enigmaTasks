package org.example.tree;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

/**
 * The {@code Branch} class represents a branch on a tree, which can grow over time and hold {@link Foliable} elements such as leaves.
 * The branch's growth is dependent on its associated {@link Trunk} and it can accumulate {@link Foliable} objects as it grows.
 *
 * @param <T> the type of {@link Foliable} that this branch can hold
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
class Branch<T extends Foliable> implements Growable {

    /**
     * The {@link Trunk} that this branch is connected to.
     */
    final Trunk<T> trunk;

    /**
     * The length of the branch, which increases as it grows.
     */
    double length;

    /**
     * The list of {@link Foliable} objects that this branch holds, such as leaves.
     */
    List<Foliage<T>> foliables = new ArrayList<>();

    /**
     * Grows the branch by increasing its length randomly and adding a new {@link Foliable},
     * if it doesn't fall off immediately, to the branch.
     * Any foliables that have fallen off will be removed.
     */
    @Override
    public void grow() {
        for (int i = 0; i < foliables.size();) {
            Foliage<T> current = foliables.get(i);
            if (current.shouldFall()) {
                foliables.remove(i);
                current.setBranch(null);
            }else {
                i++;
            }
        }
        length += new Random().nextDouble();
        final Foliage<T> freshFoliable = trunk.getTree().produceFoliage(this);
        if (length != 0 && !freshFoliable.shouldFall()) {
            foliables.add(freshFoliable);
            foliables = foliables.stream()
                    .filter(not(Foliable::shouldFall))
                    .collect(Collectors.toCollection(ArrayList::new));

            length += new Random().nextDouble();
        }

        foliables.forEach(Foliable::grow);
    }

    /**
     * Provides a string representation of the branch including its length and the foliables.
     *
     * @return a string representing the branch
     */
    @Override
    public String toString() {
        return "Branch{" +
                "length=" + length +
                ", foliables=" + foliables +
                '}';
    }
}
