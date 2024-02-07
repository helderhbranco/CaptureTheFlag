import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import capturetheflag.Map;
public class MapTest {

    @Test
    void testCreateMap() {
        Map map = new Map(10, true, 0.5);

        assertEquals(10, map.getNumLocations());
        assertTrue(map.isBidirectional());
        assertEquals(0.5, map.getDensity());
    }
    @Test
    void testInvalidArguments() {
        assertThrows(IllegalArgumentException.class, () -> new Map(3, true, 0.5));
        assertThrows(IllegalArgumentException.class, () -> new Map(10, true, 1.5));
        assertThrows(IllegalArgumentException.class, () -> new Map(10, true, -0.5));
    }
}