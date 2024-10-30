package memory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MemoryFinderTest {

    @Test
    void get() {
        MemoryFinder finder = new MemoryFinder();
        Memory memory = finder.get();
        assertNotNull(memory);
    }

}
