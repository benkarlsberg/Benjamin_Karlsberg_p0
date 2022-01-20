package util;

import java.lang.reflect.InvocationTargetException;

public class LinkedList<T> {
    private Node<T> head;
    private int numItems;

    // constructor
    public LinkedList() {
        this.numItems = 0;
        this.head = null;
    }

    // getter numItems
    public int getNumItems() {
        return this.numItems;
    }

    public boolean isEmpty() {
        return this.numItems == 0;
    }

    public Node<T> find(int index) {
        Node<T> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
        }
        return curr;
    }

    public T findByUsername(String username) {

        Node<T> iterator = head;

        for(int i = 0; i < numItems; i++) {
            T element = iterator.getObject();
            try {
                if (element.getClass().getMethod("getUserName").invoke(element) == username) {
                    return element;
                }
            } catch (NoSuchMethodException e) {
                continue;
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
            iterator = iterator.getNext();
        }
        return null;
    }

    // inserts node at front if no index
    public void insert(T object) {
        Node<T> newNode = new Node<T>(object, head);
        head = newNode;
        numItems++;
    }

    // inserts node at given index
    public void insert(int index, T object) {
        if (index >= 0 && index < numItems+1) {
            if (index == 0) {
                // insert the new node containing item at beginning of list
                Node<T> newNode = new Node<T>(object, head);
                head = newNode;
            }
            else {
                Node<T> prev = find(index-1);
                // insert the new node containing item after the node that prev references
                Node<T> newNode = new Node<T>(object, prev.getNext());
                prev.setNext(newNode);
            }
            numItems++;
        }
        else {
            System.out.println("List index out of bounds on add");
        }
    }

    //removes node at given index
    public void delete(int index) {
        if (index >= 0 && index < numItems) {
            if (index == 0) {
                // delete the first node from the list
                head = head.getNext();
            }
            else {
                Node<T> prev = find(index-1);
                // delete the node after the node that prev
                // references, save reference to node
                Node<T> curr = prev.getNext();
                prev.setNext(curr.getNext());
            }
            numItems--;
        }
        else {
            System.out.println("List index out of bounds on delete");
        }
    }

    public String toString() {

        String result = "(";

        Node<T> curr = head;

        while(curr != null) {
            result += curr.getObject();

            if(curr.getNext() != null) {
                result += ", ";
            }
            curr = curr.getNext();
        }
        result += ")";
        return result;
    }
}
