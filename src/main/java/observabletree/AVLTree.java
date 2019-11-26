package observabletree;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class AVLTree<E extends Comparable<E>> extends Observable {

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
        // Notify the Observers of a Change
        setChanged();
        notifyObservers();
        temp.setRight(e);
        e.setHeight(max(height(e.getLeft()), height(e.getRight())) + 1);
        temp.setHeight(max(height(temp.getLeft()), height(temp.getRight()) + 1));
        return temp;
    }

    private Node<E> rotateLeft(Node<E> e) {
        Node<E> temp = e.getRight();
        e.setRight(temp.getLeft());
        // Notify the Observers of a Change
        setChanged();
        notifyObservers();
        temp.setLeft(e);
        temp.setLeft(e);
        e.setHeight(max(height(e.getLeft()), height(e.getRight())) + 1);
        temp.setHeight(max(height(temp.getRight()), height(e)) + 1);
        return temp;
    }

    private Node<E> rotateDoubleLeft(Node<E> e) {
        e.setRight(rotateRight(e.getRight()));
        // Notify the Observers of a Change
        setChanged();
        notifyObservers();
        return rotateLeft(e);
    }

    private Node<E> rotateDoubleRight(Node<E> e) {
        e.setLeft(rotateLeft(e.getLeft()));
        // Notify the Observers of a Change
        setChanged();
        notifyObservers();
        return rotateRight(e);
    }

    public boolean add(E e) {
        return insert(e, this.root) != null;
    }

    private Node<E> insert(E e, Node<E> root) {
        if (root == null) {
            root = new Node<E>(e);
        } else {
            if (e.compareTo(root.getValue()) > 0) {
                root.setRight((insert(e, root.getRight())));
                setChanged();
                notifyObservers();
                if (height((root.getLeft())) - height(root.getRight()) == -2) {
                    if (e.compareTo(root.getRight().getValue()) > 0) {
                        root = rotateLeft(root);
                    } else {
                        root = rotateDoubleLeft(root);
                    }
                }
            }
            if (e.compareTo(root.getValue()) < 0) {
                root.setLeft((insert(e, root.getLeft())));
                setChanged();
                notifyObservers();
                if (height(root.getLeft()) - height(root.getRight()) == 2) {
                    if (e.compareTo(root.getLeft().getValue()) < 0) {
                        root = rotateRight(root);
                    } else {
                        root = rotateDoubleRight(root);
                    }
                }
            }
        }
        int height = max(height(root.getLeft()), height(root.getRight()));
        root.setHeight(height + 1);
        return root;
    }

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

    public void clear() {
        root = null;
        setChanged();
        notifyObservers();
    }

    public int size() {
        return inOrder().size();
    }

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

    public boolean contains(Object o) {
        return inOrder().contains(o);
    }


    public boolean remove(E e) {
        return delete(e, this.root) != null;
    }

    private Node<E> delete(E e, Node<E> root) {
        int comparison = e.compareTo(root.getValue());

        if (comparison < 0) {
            root.setRight(delete(e, root.getRight()));
            setChanged();
            notifyObservers();
        }
        else if (comparison > 0) {
            root.setLeft(delete(e, root.getLeft()));
            setChanged();
            notifyObservers();
        }
        else {
            if (!root.isLeaf()) {
                Node<E> l = root.getLeft();
                Node<E> r = root.getLeft();
                if (root.hasLeft() && !root.hasRight()) {
                    root = l;
                    setChanged();
                    notifyObservers();
                } else if (!root.hasLeft() && root.hasRight()) {
                    root = r;
                    setChanged();
                    notifyObservers();

                } else if (root.hasLeft() && root.hasRight()) {
                    Node<E> predecessor = getMaximumNode(root.getLeft());
                    root.setValue(predecessor.getValue());
                    root.setLeft(delete(predecessor.getValue(), root.getLeft()));
                    setChanged();
                    notifyObservers();
                }

            } else {
                return null;
            }
        }
        //Get rebalance in order
//        int balance = getBalance(node);
//        if (balance > 1 && getBalance(node.getLeft()) >= 0)
//            return simpleRightRotation(node);
//        if (balance < -1 && getBalance(node.getRight()) <= 0)
//            return simpleLeftRotation(node);
//        if (balance > 1 && getBalance(node.getLeft()) < 0)
//            return doubleRightRotation(node);
//        if (balance < -1 && getBalance(node.getRight()) > 0)
//            return doubleLeftRotation(node);
        return root;
    }




}

