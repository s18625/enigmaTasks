package org.example.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TreeTest {

    // Mocking the Foliage and Branch classes
    @Mock
    Foliage<Foliable> mockFoliage;

    @Mock
    Branch<Foliable> mockBranch;

    // Creating a concrete implementation of Tree for testing
    Tree<Foliable> testTree;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create a concrete implementation of Tree for testing
        testTree = new Tree<>() {
            @Override
            Foliage<Foliable> produceFoliage(Branch<Foliable> branch) {
                return mockFoliage;  // Returning the mocked Foliage
            }
        };

        // Mock behavior of the Foliage
        when(mockFoliage.shouldFall()).thenReturn(false);
    }

    @Test
    void testGrowTree() {
        // Verifying initial state of the tree
        assertNotNull(testTree.getTrunk(), "Trunk should be initialized");
        assertEquals(1, testTree.getTrunk().getHeight(), 0.01, "Initial height should be 1");

        // Call the grow method to simulate tree growth
        testTree.grow();

        // Verify that the trunk has grown
        assertTrue(testTree.getTrunk().getHeight() > 1, "Trunk height should have increased after growing");
    }


    @Test
    void testToString() {
        // Verify that the toString method contains "Tree" and "trunk"
        String treeString = testTree.toString();
        assertTrue(treeString.contains("Tree"), "toString should contain 'Tree'");
        assertTrue(treeString.contains("trunk"), "toString should contain 'trunk'");
    }
}
