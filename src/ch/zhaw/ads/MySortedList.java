/**
 * MySortedList -- ADS
 *
 * @author K. Rege 1.8.2021
 */
package ch.zhaw.ads;

import java.util.AbstractList;
import java.util.Comparator;

public class MySortedList extends AbstractList {

    private SortedListNode head;

    public MySortedList() {
        head = new SortedListNode(null);
        head.next = head;
        head.previous = head;
    }

    class SortedListNode implements Comparator<Object> {
        Object data;
        SortedListNode next;
        SortedListNode previous;

        SortedListNode(Object o) {
            data = o;
        }


        @Override
        public int compare(Object o1, Object o2) {
            if (o1 != head.data && o2 != head.data) {
                return o1.toString().compareTo(o2.toString());
            } else {
                return 1;
            }

        }
    }



    public boolean add(Object o) {
        SortedListNode newNode = new SortedListNode(o);
        if (size() == 0) {
            newNode.next = head;
            newNode.previous = head.previous;
            head.previous.next = newNode;
            head.previous = newNode;
            return false;
        }
        SortedListNode current = head.next;
        while (current.compare(current.data, newNode.data) < 1) {
            current = current.next;
        }
        newNode.next = current;
        newNode.previous = current.previous;
        current.previous.next = newNode;
        current.previous = newNode;
        return false;
    }

    public boolean remove(Object o) {
        SortedListNode current = head.next;
        while (current != head) {
            if (current.data.equals(o)) {
                current.previous.next = current.next;
                current.next.previous = current.previous;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public Object get(int pos) {
        SortedListNode node = head.next;
        while (pos > 0) {
            node = node.next;
            pos--;
        }
        return node.data;
    }

    @Override
    public int size() {
        SortedListNode current = head;
        int size = 0;
        while (current.next != head) {
            current = current.next;
            size++;
        }
        return size;
    }

    public boolean isEmpty() {
        SortedListNode current = head;
        if (current.next == head) {
            return true;
        } else {
            return false;
        }
    }

    public void clear() {
        head.next = head;
        head.previous = head;
    }
}
