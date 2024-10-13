package org.example.tree;

/**
 * The {@code ConiferousTree} class represents a specific type of tree that produces needles as foliage.
 * This class extends the abstract {@link Tree} class and implements the {@link #produceFoliage(Branch)} method
 * to generate {@link Needle} objects as foliage.
 */
public class ConiferousTree extends Tree<Needle> {

    /**
     * Produces new foliage for the given branch in the form of a {@link Needle}.
     *
     * @param branch the branch for which the needle (foliage) is produced
     * @return a new instance of {@link Needle} attached to the specified branch
     */
    @Override
    Foliage<Needle> produceFoliage(Branch<Needle> branch) {
        return new Needle(branch);
    }
}
