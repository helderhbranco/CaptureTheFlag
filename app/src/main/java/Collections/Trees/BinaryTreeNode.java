package Collections.Trees;

/**
 * BinaryTreeNode represents a node in a binary tree with a left and right child.
 */
public class BinaryTreeNode<T> {
    protected T element;
    protected BinaryTreeNode<T> left, right;

    /**
     * Creates a new binary tree node with the specified element.
     *
     * @param element the element that will become a part of the new tree node
     */
    public BinaryTreeNode(T element) {
        this.element = element;
        this.left = null;
        this.right = null;
    }

    /**
     * Creates a new binary tree node with the specified element and children.
     *
     * @param element the element that will become a part of the new tree node
     * @param left the left child of the new tree node
     * @param right the right child of the new tree node
     */
    public BinaryTreeNode(T element, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    /**
     * Returns the element stored in this node.
     *
     * @return T the element stored in this node
     */
    public T getElement() {
        return element;
    }

    /**
     * Sets the element stored in this node.
     *
     * @param element the element to be stored in this node
     */
    public void setElement(T element) {
        this.element = element;
    }

    /**
     * Returns the left child of this node or null if it does not have one.
     *
     * @return BinaryTreeNode the left child of this node
     */
    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    /**
     * Sets the left child of this node.
     *
     * @param left the node to be set as the left child of this node
     */
    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    /**
     * Returns the right child of this node or null if it does not have one.
     *
     * @return BinaryTreeNode the right child of this node
     */
    public BinaryTreeNode<T> getRight() {
        return right;
    }

    /**
     * Sets the right child of this node.
     *
     * @param right the node to be set as the right child of this node
     */
    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    /**
     * Returns the number of non-null children of this node.
     *
     * @return the integer number of non-null children of this node
     */
    public int numChildren() {
        int children = 0;

        if (left != null) {
            children = 1 + left.numChildren();
        }
        if (right != null) {
            children = children + 1 + right.numChildren();
        }
        return children;
    }

    public int getBalanceFactor() {
        return left.numChildren() - right.numChildren();
    }
}
