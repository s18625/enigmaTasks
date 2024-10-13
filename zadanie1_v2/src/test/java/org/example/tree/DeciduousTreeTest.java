package org.example.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for {@link DeciduousTree}.
 */
class DeciduousTreeTest {

    // Mocking the Branch class
    @Mock
    Branch<Leaf> mockBranch;

    // Instance of DeciduousTree for testing
    DeciduousTree deciduousTree;

    @BeforeEach
    void setUp() {
        // Initialize mocks and create a new DeciduousTree instance
        MockitoAnnotations.openMocks(this);
        deciduousTree = new DeciduousTree();
    }

    @Test
    void testProduceFoliage() {
        // Act: Call produceFoliage method
        Foliage<Leaf> leafFoliage = deciduousTree.produceFoliage(mockBranch);

        // Assert: Verify that the produced foliage is a Leaf and is not null
        assertNotNull(leafFoliage, "Produced foliage should not be null");
        assertTrue(leafFoliage instanceof Leaf, "Produced foliage should be an instance of Leaf");
    }

    @Test
    void testGrowTree() {
        // Act: Call grow method to simulate tree growth
        deciduousTree.grow();

        // Assert: Verify that the tree has a trunk and the trunk's height has grown
        assertNotNull(deciduousTree.getTrunk(), "Trunk should be initialized");
        assertTrue(deciduousTree.getTrunk().getHeight() > 1, "Trunk height should increase after growing");
    }

    @Test
    void testToString() {
        // Act: Convert the DeciduousTree to a string
        String treeString = deciduousTree.toString();

        // Assert: Ensure that the toString method contains the necessary information
        assertNotNull(treeString, "toString result should not be null");
        assertTrue(treeString.contains("trunk"), "toString should include information about the trunk");
    }
}
