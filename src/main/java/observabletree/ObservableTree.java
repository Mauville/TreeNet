package observabletree;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ObservableTree<E extends Comparable<E>> implements Collection<E>, Observable {

    private Node<E> root;

    private ArrayList<E> orderingarray = new ArrayList<>();

    public boolean isEmpty() {
        return root == null;
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

    public int size() {
        int size = 0;
        return size;
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
                    root.setValue( predecessor.getValue());
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
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
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


    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }
}
