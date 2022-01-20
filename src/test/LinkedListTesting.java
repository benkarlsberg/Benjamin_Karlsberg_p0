package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.LinkedList;

public class LinkedListTesting {

    LinkedList<String> linkedList;

    @BeforeEach
    public void before() {
       linkedList = new LinkedList<>();

        linkedList.insert(0, "Hello");
        linkedList.insert(1, "Hi");
        linkedList.insert(2, "Bye");
    }

    @Test
    public void insert() {

        int length = linkedList.getNumItems();

        assertEquals(3, length);
    }

    @Test
    public void delete() {

        linkedList.delete(0);
        int length = linkedList.getNumItems();

        assertEquals(2, length);
    }

    @Test
    public void find() {

        String hi = linkedList.find(1).getObject();

        assertEquals("Hi", hi);
    }
}
