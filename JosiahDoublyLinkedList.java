/* Josiah Mo
 * Programming 3 Period 7
 * Doubly Linked List - Linked List with each node having a backwards connection
 */

import java.util.NoSuchElementException;

public class JosiahDoublyLinkedList<E> {
    private ListNode front;
    private ListNode end;
    private int numElements;

    public class ListNode {
        private E data;
        private ListNode next;
        private ListNode prev;
    }
    
    // Checks if DLL is empty
    public boolean isEmpty() {
        return numElements == 0;
    }
    
    // Adds element to front of DLL
    public void addFront(E data) {
        ListNode toAdd = new ListNode(null, data, front);
        
        if (isEmpty()) {
            front = toAdd;
            end = toAdd;
        } else {
            front.prev = toAdd;
            front = toAdd;
        }
        numElements++;
    }
    
    // Adds element after certain index to DLL
    public void addAfter(int index, E data) {
        if (index < 0 || index >= numElements) 
            throw new IndexOutOfBoundsException("Index out of bounds!");
        
        
        ListNode cur = front;

        for (int i = 0; i < index; i++) 
            cur = cur.next;
        
        
        ListNode toAdd = new ListNode(cur, data, cur.next);
        // Make sure next exists!!
        if (cur.next != null)
            cur.next.prev = toAdd;
     
        
        cur.next = toAdd;
        toAdd.prev = cur;
        
        if (cur == end) 
            end = toAdd;
        

        numElements++;
    }
    
    // Append element to end of DLL
    public void addLast(E data) {
        if (isEmpty()) {
            addFront(data);
        } else {
            ListNode toAdd = new ListNode(end, data, null);
            end.next = toAdd;
            end = toAdd;
            numElements++;
        }
    }
    
    // Removes first occurence of element in DLL
    public void remove(E data) {
        if (isEmpty()) 
            throw new NoSuchElementException("DLL is empty!");
 

        ListNode cur = front;

        while (cur != null) {
            if (cur.data.equals(data)) {
                if (cur == front) {
                    front = cur.next;
                    
                    // Sever connection
                    cur.next.prev = null;
                } else 
                    cur.prev.next = cur.next;
                

                if (cur == end) {
                    end = cur.prev;
                    
                    // Sever connection
                	cur.prev.next = null;
                } else 
                    cur.next.prev = cur.prev;
                
                
             	// Sever outgoing connections from now deleted node!
            	cur.prev = null;
            	cur.next = null;
            
                numElements--;
                return;
            }
            cur = cur.next;
        }

        throw new NoSuchElementException("Element doesn't exist in DLL!");
    }
    
    // Removes first element in DLL
    public void removeFirst() {
        if (isEmpty()) 
            throw new NoSuchElementException("DLL is empty!");
        

        if (front == end) {
            front = null;
            end = null;
        } else {
            front = front.next;
            front.prev = null;
        }

        numElements--;
    }
    
    // Removes last element in DLL
    public void removeLast() {
        if (isEmpty()) 
            throw new NoSuchElementException("DLL is empty!");
        

        if (front == end) {
            front = null;
            end = null;
        } else {
        	// Sever connections
        	end.prev.next = null;
   
        	
            end = end.prev;
            end.next = null;
        }

        numElements--;
    }
    
    // Returns size of DLL
    public int size() {
        return numElements;
    }
    
    // Gets element at certain index in DLL
    public E get(int index) {
        if (index < 0 || index >= numElements) 
            throw new IndexOutOfBoundsException("No such index!");
        

        ListNode cur = front;
        for (int i = 0; i < index; i++) 
            cur = cur.next;
       

        return cur.data;
    }
    
    // Sets element at certain index in DLL
    public E set(int index, E value) {
    	E toReturn;
        if (index < 0 || index >= numElements) 
            throw new IndexOutOfBoundsException("No such index!");
        
    
        // Iterate up to target index
        ListNode cur = front;
        for (int i = 0; i < index; i++) 
            cur = cur.next;
        
        toReturn = cur.data;
        cur.data = value;
        
        return toReturn;
    }
    
    // To-string for DLL
    public String toString() {
        String res = "";

        ListNode cur = front;
        while (cur != null) {
            res += cur.data + "\n";
            cur = cur.next;
        }

        return res;
    }
}
