/**
 * MyList --- ADS
 *
 * @author K. Rege 1.8.2021
 */
 
package ch.zhaw.ads;

import java.util.*;

public class MyList extends AbstractList {
	protected int size;
	protected ListNode head;
	
	protected class ListNode {
    	Object value;
    	ListNode next, prev;
        
    	ListNode(Object value) {
    		this.value =  value;
    	}
    }
	
	protected boolean insertBefore(Object val, ListNode p) {
		ListNode n = new ListNode(val);
		n.next = p;
		n.prev = p.prev;
		p.prev.next = n;
		p.prev = n;	  	
		size++;
		return true;
	}
    
	protected boolean removeAt(ListNode p) {
		p.prev.next = p.next;
		p.next.prev = p.prev;
		size--;
		return true;   	
	}
	
	protected ListNode getAt(int pos) {
        ListNode p = head.next;
        for (; pos > 0; pos--) p = p.next;
        return p;
    }
    
	// ============== public ===================    
	public MyList() {
		head = new ListNode(null);
		clear();
	}
	
	@Override
	public void clear() {
		head.next = head;
		head.prev = head;
		size = 0;	
	}

	@Override
	public boolean add(Object val) {
		return insertBefore(val, head);	
	}
	
	@Override
	public boolean remove(Object val) {
		for (ListNode p = head.next; p != head; p = p.next) {
			if (p.value.equals(val)) {
				return removeAt(p);		
			}
		}
		return false;
	}
    
	@Override   
	public Object get(int pos) {
	    if (pos < 0 || pos >= size) {throw new IndexOutOfBoundsException();}
		return getAt(pos).value;
	}
    
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
    
}
