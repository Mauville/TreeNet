package observabletree;

public class Node<E extends Comparable<E>> implements Comparable<E> {
    private Node<E> left;
    private Node<E> right;
    private E val;

    public Node(E val) {
        this.val = val;
    }
//    public boolean isLeaf(){
//       return(left && right);
//    }

    @Override
    public int compareTo(E other) {
        return val.compareTo(other);
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

    public E getVal() {
        return val;
    }

    public void setVal(E val) {
        this.val = val;
    }

}
