package operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BranchTest {
    @Test
    public void testBranchNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Branch(null);
        });

        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            new Branch("");
        });
    }
}