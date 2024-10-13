package org.example.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for {@code Leaf} class
 */
@ExtendWith(MockitoExtension.class)
class LeafTest {

    // Mock the dependencies
    @Mock
    private Branch<Leaf> mockBranch;

    @Mock
    private Random mockRandom;

    // Inject the mocks into the Leaf instance
    @InjectMocks
    private Leaf leaf;

    @BeforeEach
    void setUp() {
        // Ensure the random is injected as the mock
//        when(mockRandom.nextDouble()).thenReturn(0.5);
    }

    @Test
    void testGrowIncreasesSurfaceSizeAndDarkensColor() {
        // Initial conditions
        double initialSurfaceSize = leaf.getSurfaceSize();
        Color initialColor = leaf.getColor();

        // Grow the leaf
        leaf.grow();

        // Verify surface size increased
        assertTrue(leaf.getSurfaceSize() > initialSurfaceSize, "Surface size should increase after growth.");

        // Verify color has darkened
        assertNotEquals(initialColor, leaf.getColor(), "Color should darken after growth.");
        assertTrue(leaf.getColor().getRed() < initialColor.getRed(), "Color should darken (Red value should decrease).");
    }


    @Test
    void testToStringMethod() {
        leaf.grow();
        String leafString = leaf.toString();
        assertTrue(leafString.contains("surfaceSize="), "Leaf string representation should contain surfaceSize.");
        assertTrue(leafString.contains("color="), "Leaf string representation should contain color.");
    }
}
