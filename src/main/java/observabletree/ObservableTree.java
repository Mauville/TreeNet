package observabletree;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.Collection;
import java.util.Iterator;

public class ObservableTree<E extends Comparable<E>> implements Collection<E>, Observable {

    private Node<E> root;

    public boolean isEmpty() {
        return root == null;
    }

    public E getRoot() {
        if (root.getVal() == null) {
            throw new NullPointerException("No root value");
        } else {
            return root.getVal();
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
            root.setVal(e);
            return true;
        } else {
            Node<E> next;
            if (e.compareTo(root.getVal()) < 0) {
                next = root.getLeft();
            } else {
                next = root.getRight();
            }
            insert(e, next);
        }
        return true;
    }

    public boolean remove(E e) {
        return delete(e, this.root);
    }

    public boolean delete(E e, Node<E> root) {
        int comparison = e.compareTo(root.getVal());
        if (comparison < 0) {
            delete(e, root.getLeft());
        } else if (comparison > 0) {
            delete(e, root.getRight());
        } else {
            if (!root.isLeaf()) {
                Node<E> l = root.getLeft();
                Node<E> r = root.getLeft();
                if (root.hasLeft() && !root.hasRight()){
                    root = l;
                }
                else if (!root.hasLeft() && root.hasRight()){
                    root = r;
                }
                else if (root.hasLeft() && root.hasRight()){
                    //todo
                }

            } else {
                root = null;
            }
        }
        //TODO rebalance();
        return true;
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
