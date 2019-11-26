package observabletree;

public class Node<E extends Comparable<E>> implements Comparable<Node<E>> {
    private Node<E> left;
    private Node<E> right;
    private E val;
    private int height;

    @Override
    public int compareTo(Node<E> other) {
        return this.val.compareTo(other.getValue());
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Node(E val) {
        this.val = val;
    }

    public Node() {
    }

    public boolean hasRight() {
        return (right != null);
    }

    public boolean hasLeft() {
        return (left != null);
    }

    public boolean isLeaf() {
        return (this.left == null && this.right == null);
    }

    public boolean isEmpty() {
        return (this.val == null);
    }

    public Node<E> getLeft() {
        return left;
    }

    public void setLeft(Node<E> left) {
        this.left = left;
    }

    public Node<E> getRight() {
        return right;
    }

    public void setRight(Node<E> right) {
        this.right = right;
    }

    public E getValue() {
        return val;
    }

    void setValue(E val) {
        this.val = val;
    }

    void updateHeight() {
        int heightLeft = left != null ? left.getHeight() : 0;
        int heightRight = right != null ? right.getHeight() : 0;
        height = Math.max(heightLeft, heightRight) + 1;
    }

}
