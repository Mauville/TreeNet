package observabletree;

import observabletree.AVLTree;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

class AVLTreeTest {

    @Test
    void testAdd() {
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            a.add(ThreadLocalRandom.current().nextInt(-100, +100 + 1));
        }

        AVLTree<Integer> t = new AVLTree<>();
        for (int j = 0; j < 9; j++) {
            t.add(a.get(j));
            String tmega = t.toString();
            System.out.println(tmega);
        }
    }
}
