package org.example.tree;

/**
 * The {@code DeciduousTree} class represents a specific type of tree that produces leaves as foliage.
 * This class extends the abstract {@link Tree} class and implements the {@link #produceFoliage(Branch)} method
 * to generate {@link Leaf} objects as foliage.
 */
public class DeciduousTree extends Tree<Leaf> {

    /**
     * Produces new foliage for the given branch in the form of a {@link Leaf}.
     *
     * @param branch the branch for which the leaf (foliage) is produced
     * @return a new instance of {@link Leaf} attached to the specified branch
     */
    @Override
    Foliage<Leaf> produceFoliage(Branch<Leaf> branch) {
        return new Leaf(branch);
    }
}
