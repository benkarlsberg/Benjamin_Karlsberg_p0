public class Node<T>{
    private Node<T> next;
    private T object;

    // default head constructor
    public Node (T object) {
        this.object = object;
        this.next = null;
    }

    // default node constructor
    public Node (T object, Node<T> nextNode) {
        this.object = object;
        this.next = nextNode;
    }

    // next node getter
    public Node<T> getNext() {
        return this.next;
    }

    // next node setter
    public void setNext(Node<T> newNext) {
        this.next = newNext;
    }

    // object getter
    public T getObject() {
        return this.object;
    }

    // object setter
    public void setObject(T object) {
        this.object = object;
    }
}
