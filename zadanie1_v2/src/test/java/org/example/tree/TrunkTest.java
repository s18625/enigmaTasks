package org.example.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class TrunkTest {

    private Tree<Foliable> mockTree;
    private Trunk<Foliable> trunk;

    @BeforeEach
    void setUp() {
        // Mock the Tree object
        mockTree = mock(Tree.class);

        // When the tree's getTrunk method is called, it returns the mock trunk
        when(mockTree.getTrunk()).thenReturn(new Trunk<>(mockTree));

        // Initialize the trunk with the mock tree
        trunk = new Trunk<>(mockTree);
    }

    @Test
    void testInitialHeightIsOne() {
        // Test that the initial height of the trunk is 1
        assertEquals(1, trunk.getHeight(), "Initial trunk height should be 1.");
    }


    @Test
    void testToStringContainsHeightAndBranches() {
        // Call toString
        String result = trunk.toString();

        // Verify that the string contains trunk height and branches
        assertTrue(result.contains("height="), "String representation should contain 'height='.");
        assertTrue(result.contains("branches="), "String representation should contain 'branches='.");
    }
}
