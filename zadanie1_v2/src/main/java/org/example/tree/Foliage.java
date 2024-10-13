package org.example.tree;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The {@code Foliage} class represents the abstract concept of foliage, such as leaves or needles, that grows on a {@link Branch}.
 * It serves as a base class for specific types of foliage and implements the {@link Foliable} interface, meaning it supports
 * both growth and the potential to fall off.
 *
 * <p>
 * The {@code Foliage} class is generic and can be extended by any type that implements the {@link Foliable} interface.
 * It holds a reference to the {@link Branch} it is attached to, allowing the foliage to be aware of the branch it grows on.
 * </p>
 *
 * @param <T> the type of foliage, which must extend {@link Foliable}
 */
@Data
@AllArgsConstructor
abstract class Foliage<T extends Foliable> implements Foliable {

    /**
     * The {@link Branch} to which this foliage is attached.
     */
    Branch<T> branch;
}
