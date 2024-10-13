package org.example.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BranchTest {

    private Trunk<Foliable> mockTrunk;
    private Tree<Foliable> mockTree;
    private Branch<Foliable> branch;

    @BeforeEach
    void setUp() {
        // Mock the dependencies: Trunk and Tree
        mockTrunk = mock(Trunk.class);
        mockTree = mock(Tree.class);

        // When the trunk's getTree method is called, it returns the mock tree
        when(mockTrunk.getTree()).thenReturn(mockTree);

        // Initialize the branch with the mock trunk
        branch = new Branch<>(mockTrunk);
    }

    @Test
    void testInitialLengthIsZero() {
        // Test that when the branch is created, its initial length is 0
        assertEquals(0, branch.getLength(), "Initial branch length should be 0.");
    }

    @Test
    void testBranchGrows() {
        // Mock the foliage returned by the tree's produceFoliage method
        Foliage<Foliable> mockFoliage = mock(Foliage.class);
        when(mockFoliage.shouldFall()).thenReturn(false);
        when(mockTree.produceFoliage(any())).thenReturn(mockFoliage);

        // Capture the initial length
        double initialLength = branch.getLength();

        // Perform the grow operation
        branch.grow();

        // Verify that the branch length increased
        assertTrue(branch.getLength() > initialLength, "Branch length should increase after growing.");

        // Verify that the mock foliage was added to the branch
        List<Foliage<Foliable>> foliables = branch.getFoliables();
        assertFalse(foliables.isEmpty(), "Branch should contain foliables after growing.");
    }

    @Test
    void testFoliableFallsOffDuringGrowth() {
        // Mock the foliage returned by the tree's produceFoliage method, making it fall immediately
        Foliage<Foliable> mockFoliage = mock(Foliage.class);
        when(mockFoliage.shouldFall()).thenReturn(true);
        when(mockTree.produceFoliage(any())).thenReturn(mockFoliage);

        // Capture the initial foliables list size
        int initialSize = branch.getFoliables().size();

        // Perform the grow operation
        branch.grow();

        // Verify that no new foliables were added to the branch
        assertEquals(initialSize, branch.getFoliables().size(), "No foliables should be added if they fall immediately.");
    }

    @Test
    void testFoliablesGrow() {
        // Mock the foliage returned by the tree's produceFoliage method
        Foliage<Foliable> mockFoliage = mock(Foliage.class);
        when(mockFoliage.shouldFall()).thenReturn(false);
        when(mockTree.produceFoliage(any())).thenReturn(mockFoliage);

        // Add mock foliage to the branch
        branch.getFoliables().add(mockFoliage);

        // Perform the grow operation
        branch.grow();

        // Verify that grow was called on the mock foliage
        verify(mockFoliage, times(2)).grow();
    }

    @Test
    void testToStringContainsLengthAndFoliables() {
        // Create a mock foliage for testing toString
        Foliage<Foliable> mockFoliage = mock(Foliage.class);
        branch.getFoliables().add(mockFoliage);

        // Call toString
        String result = branch.toString();

        // Verify that the string contains branch length and foliables
        assertTrue(result.contains("length="), "String representation should contain 'length='.");
        assertTrue(result.contains("foliables="), "String representation should contain 'foliables='.");
    }
}
