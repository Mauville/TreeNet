import observabletree.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestNode {

    private Node<Integer> a = new Node<>(10);
    private Node<Integer> b = new Node<>(20);
    private Node<String> c = new Node<>("Mike");
    private Node<String> d = new Node<>("Mike");
    private Node<Double> e = new Node<>();

    @Test
    void testNodeLeaf() {
        assertTrue(a.isLeaf());
        assertTrue(c.isLeaf());
    }
    @Test
    void testNodeEmpty() {
        assertFalse(a.isEmpty());
        assertTrue(e.isEmpty());
        assertTrue(e.isEmpty());
    }
    @Test
    void testNodeEqualness() {
        assertEquals(a, a);
        assertNotEquals(b, a);
        assertNotEquals(c, d);
    }
}