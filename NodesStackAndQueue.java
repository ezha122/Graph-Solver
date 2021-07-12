package nz.ac.auckland.softeng281.a4;

import java.util.ArrayList;
import java.util.EmptyStackException;


/**
 * @author GGPC
 *
 */
public class NodesStackAndQueue {
	
	private ArrayList<Node> data = new ArrayList<>();
	
    public NodesStackAndQueue() {
    	
    }

    public boolean isEmpty() {
    	return (this.getSize() == 0);
        
    
    }

    /**
     * Push operation refers to inserting an element on the Top of the stack.
     *
     * @param node
     */
    public void push(Node node) {
    	this.data.add(node);
    }

    /**
     * pop an element from the top of the stack (removes and returns the top element)
     *
     * @return
     */
    public Node pop() {
    	int pos = this.getSize()-1;
    	
    	if (this.data.isEmpty()) {
        	throw new EmptyStackException();
        }
    	
    	Node top = this.data.get(pos);
    	this.data.remove(pos);
    	
    	return top;
    	
    }

    /**
     * get the element from the top of the stack without removing it
     *
     * @return
     */
    public Node peek() throws EmptyStackException {
        
        if (this.data.isEmpty()) {
        	throw new EmptyStackException();
        }
        return this.data.get(this.getSize() - 1);
    	
    }

    /**
     * append an element at the end of the stack
     *
     * @param node
     */
    public void append(Node node) {
    	this.data.add(0, node);
    	}
    
    /**
    * returns the size of the stack/queue
    *
    * @return
    */		
    public int getSize() {
    	return this.data.size();
    }
    
}
