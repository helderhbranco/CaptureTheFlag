import Collections.Exceptions.EmptyCollectionException;
import Collections.Trees.LinkedHeap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedMaxHeapTest {

    @Test
    void testAddElement() {
        LinkedHeap<Integer> linkedHeap = new LinkedHeap<>();
        int expected = 1;

        linkedHeap.addElement(2);
        linkedHeap.addElement(1);

        assertEquals(expected,linkedHeap.getRootNode().getElement());
    }

    @Test
    void testRemove(){
        LinkedHeap<Integer> linkedHeap = new LinkedHeap<>();
        int expected = 1;

        linkedHeap.addElement(2);
        linkedHeap.addElement(1);

        assertEquals(expected,linkedHeap.removeMin());
    }

    @Test
    void testRemoveAllElements() {
        LinkedHeap<Integer> linkedHeap = new LinkedHeap<>();

        linkedHeap.addElement(3);
        linkedHeap.addElement(2);
        linkedHeap.addElement(1);

        linkedHeap.removeAllElements();

        assertThrows(EmptyCollectionException.class,() -> linkedHeap.getRootNode());
        assertNull(linkedHeap.getLastNode());
        assertEquals(0, linkedHeap.size());
        assertTrue(linkedHeap.isEmpty());
    }
}
