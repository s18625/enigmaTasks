package org.example.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for {@link ConiferousTree}.
 */
class ConiferousTreeTest {

    // Mocking the Branch class
    @Mock
    Branch<Needle> mockBranch;

    // Instance of ConiferousTree for testing
    ConiferousTree coniferousTree;

    @BeforeEach
    void setUp() {
        // Initialize mocks and create a new ConiferousTree instance
        MockitoAnnotations.openMocks(this);
        coniferousTree = new ConiferousTree();
    }

    @Test
    void testProduceFoliage() {
        // Act: Call produceFoliage method
        Foliage<Needle> needleFoliage = coniferousTree.produceFoliage(mockBranch);

        // Assert: Verify that the produced foliage is a Needle and is not null
        assertNotNull(needleFoliage, "Produced foliage should not be null");
        assertTrue(needleFoliage instanceof Needle, "Produced foliage should be an instance of Needle");
    }

    @Test
    void testGrowTree() {
        // Act: Call grow method to simulate tree growth
        coniferousTree.grow();

        // Assert: Verify that the tree has a trunk and the trunk's height has grown
        assertNotNull(coniferousTree.getTrunk(), "Trunk should be initialized");
        assertTrue(coniferousTree.getTrunk().getHeight() > 1, "Trunk height should increase after growing");
    }

    @Test
    void testToString() {
        // Act: Convert the ConiferousTree to a string
        String treeString = coniferousTree.toString();

        // Assert: Ensure that the toString method contains the necessary information
        assertNotNull(treeString, "toString result should not be null");
        assertTrue(treeString.contains("trunk"), "toString should include information about the trunk");
    }
}
