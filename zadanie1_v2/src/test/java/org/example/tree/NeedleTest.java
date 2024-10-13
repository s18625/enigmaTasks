package org.example.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NeedleTest {

    private Branch<Needle> mockBranch;
    private Needle needle;

    @BeforeEach
    void setUp() {
        // Mock the Branch<Needle> object as it's required in the constructor.
        mockBranch = Mockito.mock(Branch.class);
        needle = new Needle(mockBranch);
    }

    @Test
    void testGrowIncreasesLength() {
        // Given
        double initialLength = needle.getLength();

        // When
        needle.grow();

        // Then
        assertTrue(needle.getLength() > initialLength, "Needle length should increase after growth.");
    }

    @Test
    void testShouldFallReturnsFalse() {
        // Given a needle

        // When & Then
        assertFalse(needle.shouldFall(), "Needle should never fall.");
    }

    @Test
    void testToStringContainsLength() {
        // When
        String result = needle.toString();

        // Then
        assertTrue(result.contains("length="), "String representation should contain 'length='.");
    }
}
