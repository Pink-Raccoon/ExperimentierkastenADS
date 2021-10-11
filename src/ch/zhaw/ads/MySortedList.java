/**
 * MySortedList -- ADS
 *
 * @author K. Rege 1.8.2021
 */
 
package ch.zhaw.ads;
import java.util.*;
 
public class MySortedList extends MyList {
    
    @Override
    public boolean add(Object val){
        ListNode p = head.next;
        while(p != head && ((Comparable)p.value).compareTo(val) < 0){
            p = p.next;
        }
		return insertBefore(val,p);
    }
    
}