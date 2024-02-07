package Collections.Trees;

public class HeapNode<T> extends BinaryTreeNode<T> {
    protected HeapNode<T> parent;

    public HeapNode(T element) {
        super(element);
        parent = null;
    }

    public HeapNode(T element, HeapNode<T> left, HeapNode<T> right) {
        super(element, left, right);
        parent = null;
    }

    public HeapNode(T element, HeapNode<T> parent) {
        super(element);
        this.parent = parent;
    }

    public HeapNode(T element, HeapNode<T> left, HeapNode<T> right, HeapNode<T> parent) {
        super(element, left, right);
        this.parent = parent;
    }

    public HeapNode<T> getParent() {
        return parent;
    }

    public void setParent(HeapNode<T> parent) {
        this.parent = parent;
    }
}
