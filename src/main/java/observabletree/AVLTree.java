package observabletree;

import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class AVLTree<E extends Comparable<E>> implements Collection<E> {
    private Node<E> root;

    private ArrayList<E> orderingarray = new ArrayList<>();

    public int height(Node<E> e) {
        if (e == null) {
            return -1;
        } else {
            return e.getHeight();
        }
    }

    public int max(int a, int b) {
        if (a > b) {
            return a;
        } else
            return b;
    }

    private Node<E> rotateRight(Node<E> e) {
        Node<E> temp = e.getLeft();
        e.setLeft(temp.getRight());
        //TODO notify
        temp.setRight(e);
        //TODO notify
        e.setHeight(max(height(e.getLeft()), height(e.getRight())) + 1);
        temp.setHeight(max(height(temp.getLeft()), height(temp.getRight()) + 1));
        return temp;
    }

    private Node<E> rotateLeft(Node<E> e) {
        Node<E> temp = e.getRight();
        e.setRight(temp.getLeft());
        //TODO Notify
        temp.setLeft(e);
        //TODO Notify
        temp.setLeft(e);
        e.setHeight(max(height(e.getLeft()), height(e.getRight())) + 1);
        temp.setHeight(max(height(temp.getRight()), height(e)) + 1);
        return temp;
    }

    private Node<E> rotateDoubleLeft(Node<E> e) {
        //TODO Notify
        e.setRight(rotateRight(e.getRight()));
        return rotateLeft(e);
    }

    private Node<E> rotateDoubleRight(Node<E> e) {
        //TODO Notify
        e.setLeft(rotateLeft(e.getLeft()));
        return rotateRight(e);
    }


    @Override
    public boolean isEmpty() {
        return root.isEmpty();
    }

    public E getRoot() {
        if (root.getValue() == null) {
            throw new NullPointerException("No root value");
        } else {
            return root.getValue();
        }
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public int size() {
        return inOrder().size();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        List<List<String>> lines = new ArrayList<List<String>>();
        List<Node<E>> level = new ArrayList<Node<E>>();
        List<Node<E>> next = new ArrayList<Node<E>>();
        level.add(root);
        int nn = 1;
        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<String>();

            nn = 0;

            for (Node<E> n : level) {
                if (n == null) {
                    line.add(null);
                    next.add(null);
                    next.add(null);
                } else {
                    String aa = n.getValue().toString();
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();

                    next.add(n.getLeft());
                    next.add(n.getRight());

                    if (n.getLeft() != null) nn++;
                    if (n.getRight() != null) nn++;
                }
            }

            if (widest % 2 == 1) widest++;

            lines.add(line);

            List<Node<E>> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {

                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (j < line.size() && line.get(j) != null) c = '└';
                        }
                    }
                    s.append(c);

                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            s.append(" ");
                        }
                    } else {

                        for (int k = 0; k < hpw; k++) {
                            s.append(j % 2 == 0 ? " " : "─");
                        }
                        s.append(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            s.append(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                s.append("\n");
            }

            // print line of numbers
            for (String f : line) {

                if (f == null) f = "";
                int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                // a number
                for (int k = 0; k < gap1; k++) {
                    s.append(" ");
                }
                s.append(f);
                for (int k = 0; k < gap2; k++) {
                    s.append(" ");
                }
            }
            s.append("\n");

            perpiece /= 2;
        }
        return s.toString();
    }

    @Override
    public boolean add(E e) {
        return insert(e, this.root);
    }

    private boolean insert(E e, Node<E> root) {
        if (root.isEmpty()) {
            root.setValue(e);
            return true;
        } else {
            Node<E> next;
            if (e.compareTo(root.getValue()) < 0) {
                next = root.getLeft();
            } else {
                next = root.getRight();
            }
            insert(e, next);

        }
        return true;
    }

    public void remove(E e) {
        delete(e, this.root);
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    private Node<E> delete(E e, Node<E> root) {
        int comparison = e.compareTo(root.getValue());
        if (comparison < 0) {
            delete(e, root.getLeft());
        } else if (comparison > 0) {
            delete(e, root.getRight());
        } else {
            if (!root.isLeaf()) {
                Node<E> l = root.getLeft();
                Node<E> r = root.getLeft();
                if (root.hasLeft() && !root.hasRight()) {
                    root = l;
                } else if (!root.hasLeft() && root.hasRight()) {
                    root = r;
                } else if (root.hasLeft() && root.hasRight()) {
                    Node<E> predecessor = getMaximumNode(root.getLeft());
                    root.setValue(predecessor.getValue());
                    root.setLeft(delete(predecessor.getValue(), root.getLeft()));
                }

            } else {
                return null;
            }
        }
        return root;
        //TODO rebalance();
    }


    private ArrayList<E> inOrder() {
        orderingarray.clear();
        inOrderRec(this.root);
        return orderingarray;
    }

    private void inOrderRec(Node<E> e) {
        if (e != null) {
            inOrderRec(e.getLeft());
            orderingarray.add(e.getValue());
            inOrderRec(e.getRight());
        }
    }

    private ArrayList<E> preOrder() {
        orderingarray.clear();
        preOrderRec(this.root);
        return orderingarray;
    }

    private void preOrderRec(Node<E> e) {
        if (e != null) {
            orderingarray.add(e.getValue());
            inOrderRec(e.getLeft());
            inOrderRec(e.getRight());
        }
    }

    public E getMinimum() {
        Node<E> e = getMinimumNode(root);
        return e.getValue();
    }

    private Node<E> getMinimumNode(Node<E> e) {
        while (e.hasLeft()) {
            e = e.getLeft();
        }
        return e;
    }

    public E getMaximum() {
        Node<E> e = getMaximumNode(root);
        return e.getValue();
    }

    private Node<E> getMaximumNode(Node<E> e) {
        while (e.hasRight()) {
            e = e.getRight();
        }
        return e;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return inOrder().containsAll(c);
    }

    @Override
    public boolean contains(Object o) {
        return inOrder().contains(o);
    }


    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }
}

