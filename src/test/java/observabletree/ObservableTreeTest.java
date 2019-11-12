package observabletree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObservableTreeTest {

    ObservableTree<Integer> a = new ObservableTree<>();
    ObservableTree<Integer> b = new ObservableTree<>();

    @Test
    void testInsertion() {
        a.add(1);
        a.add(2);
        a.add(3);
    }
}