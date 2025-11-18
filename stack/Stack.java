// Georgios Kefalas, A.M: 5252
// Dimitrios Papadopoulos, A.M: 5321
// Group 3, ID: 3

import java.io.*;

// implementation of a pushdown stack
public class Stack<Item> {
    private Node head;

    private class Node { Item item; Node next;
        Node(Item item, Node next) { this.item = item; this.next = next; }
    }

    Stack() {
        head = null;
    }

    boolean isEmpty() {
        return head == null;
    }

    void push(Item item) {
        head = new Node(item, head);
    }

    Item pop() {
        Item item = head.item;
        Node t = head.next;
        head = t;
        return item;
    }
}