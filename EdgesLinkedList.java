package nz.ac.auckland.softeng281.a4;

/**
 * The Linked List Class has only one head pointer to the start edge (head) Edges are
 * indexed starting from 0. The list goes from 0 to size-1. Note that the List does not
 * have a maximum size.
 *
 * @author Partha Roop
 */
public class EdgesLinkedList {
    // the head of the linked list
    private Edge head;

    public EdgesLinkedList() {
        head = null;
    }


    /**
     * This method adds an edge as the start edge of the list
     *
     * @param edge to prepend
     */
    public void prepend(Edge edge) {
        edge.setNext(this.head);
        this.head = edge;
    }

    /**
     * This method adds an edge as the end edge of the list
     *
     * @param edge to append
     */
    public void append(Edge edge) {
    	
    	if (this.head == null) {
    		prepend(edge);
    		return;
    	}

        Edge currentEdge = this.head;
        while (currentEdge.getNext() != null) {
            currentEdge = currentEdge.getNext();
        }

        currentEdge.setNext(edge);
        edge.setNext(null);
    }

    /**
     * This method gets the edge at a given position
     *
     * @param pos: an integer, which is the position
     * @return the Edge at the position pos
     */
    public Edge get(int pos) throws InvalidPositionException {
    	if (this.head == null) {
    		return null;
    	}
    	
        if (pos < 0 || pos > size() - 1) {
            throw new InvalidPositionException("Position " + pos + " outside the list boundary");
        } 
        
        Edge current = this.head;
        
        for (int i = 0; i < pos; i++) {
        	current = current.getNext();
        }
        
        return current;
    }

    /**
     * This method adds an edge at a given position in the List
     *
     * @param pos:  an integer, which is the position
     * @param edge: the edge to add
     * @throws InvalidPositionException
     */
    public void insert(int pos, Edge edge) throws InvalidPositionException {
    	if (pos < 0 || pos > size() - 1) {
            throw new InvalidPositionException("Position " + pos + " outside the list boundary");
        }
     
        Edge edgeBefore = this.get(pos-1);
        Edge edgeAfter = edgeBefore.getNext();
        
        edgeBefore.setNext(edge);
        edge.setNext(edgeAfter);
    }

    /**
     * This method removes an edge at a given position
     *
     * @param pos: an integer, which is the position
     */
    public void remove(int pos) throws InvalidPositionException {
        if (pos < 0 || pos > size() - 1) {
            throw new InvalidPositionException("Position " + pos + " outside the list boundary");
        }
        
        if (pos == 0) {
        	this.head = this.head.getNext();
        	return;
        }
        
        Edge edgeA = this.get(pos-1);
        Edge edgeB = this.get(pos+1);

        edgeA.setNext(edgeB);
    }

    /**
     * This method returns the size of the Linked list
     *
     * @return the size of the list
     */

    public int size() {
        int pos = 0;
        Edge edge = this.head;

        while (edge != null) {
            edge = edge.getNext();
            ++pos;
        }
        return pos;
    }

    /**
     * This method is used for printing the data in the list from head till the last
     * node
     */
    public void print() {
        Edge edge = head;
        while (edge != null) {
            System.out.println(edge);
            edge = edge.getNext();
        }
    }
}