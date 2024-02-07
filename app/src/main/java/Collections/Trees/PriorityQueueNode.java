package Collections.Trees;

public class PriorityQueueNode<T> implements Comparable<PriorityQueueNode<T>> {
    private static int nextorder = 0;
    private int priority;
    private int order;
    private T element;

    /**
     * Creates a new PriorityQueueNode with the specified data.
     *
     * @param element the element of the new priority queue node
     * @param priority the integer priority of the new queue node
     */
    public PriorityQueueNode(T element, int priority) {
        this.element = element;
        this.priority = priority;
        this.order = nextorder;
        nextorder++;
    }

    /**
     * Returns the element of this priority queue node.
     *
     * @return the element of this priority queue node
     */
    public T getElement() {
        return element;
    }

    /**
     * Returns the priority of this priority queue node.
     *
     * @return the priority of this priority queue node
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Returns the order of this priority queue node.
     *
     * @return the order of this priority queue node
     */
    public int getOrder() {
        return order;
    }


    public String toString() {
        return (element.toString() + priority + order);
    }

    /**
     * Compares this priority queue node with the specified priority queue node for order.
     * Returns a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     *
     * @param other the priority queue node to be compared
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object
     */
    @Override
    public int compareTo(PriorityQueueNode other) {
        int result;
        PriorityQueueNode<T> temp = other;
        if (priority > temp.getPriority())
            result = 1;
        else if (priority < temp.getPriority())
            result = -1;
        else if (order > temp.getOrder())
            result = 1;
        else
            result = -1;
        return result;
    }
}
