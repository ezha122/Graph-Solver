package nz.ac.auckland.softeng281.a4;

import org.junit.Before;
import java.util.ArrayList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
 
public class NodesStackAndQueueTest {

    NodesStackAndQueue stack;
    NodesStackAndQueue result;

    @Before
    public void setUp() {
        stack = new NodesStackAndQueue();
        result = new NodesStackAndQueue();
    }
    

	@After
	public void tearDown() {

	}

    @Test
    public void isEmptyEmptyStack() {
        assertTrue(stack.isEmpty());
    }

    @Test
    public void isEmptyNotEmpty() {
        stack.append(new Node("4"));
        assertFalse(stack.isEmpty());
    }
    
    @Test
    public void isEmptyNotEmpty2() {
    	stack.append(new Node("a")); 
    	stack.append(new Node("b"));
    	
    	assertFalse(stack.isEmpty());
    }
    
    @Test
    public void peekTest1() {
    	stack.append(new Node("a")); 
    	stack.append(new Node("b"));
    	
    	Node answer = new Node("a");
    	assertEquals(answer, stack.peek());
    }
    
    @Test 
    public void peekTest2() {
    	stack.append(new Node("a")); 
    	stack.append(new Node("b"));
    	stack.push(new Node("c"));
    	Node answer = new Node("c");
    	
    	assertEquals(answer, stack.peek());
    }
    
    @Test
    public void popTest1() {
    	Node nodeA = new Node("a");
    	Node nodeB = new Node("b");
    	Node nodeC = new Node("c");
    	
    	stack.append(nodeA);
    	stack.append(nodeB);
    	stack.push(nodeC);
    	Node removedNode = stack.pop();
    	
    	
    	assertTrue(nodeA.equals(stack.peek()));
    	assertEquals(stack.getSize(), 2);
    	assertTrue(nodeC.equals(removedNode));
    
    }
    
    @Test 
    public void popTest2() {
    	Node nodeA = new Node("a");
    	Node nodeB = new Node("b");
    	Node nodeC = new Node("c");
    	Node nodeD = new Node("d");
    	
    	stack.push(nodeA);
    	stack.push(nodeB);
    	stack.push(nodeD);
    	stack.push(nodeC);
    	
    	Node node1 = stack.pop();
    	Node node2 = stack.pop();
    	
    	assertEquals(stack.getSize(),2);
    	assertTrue(stack.peek().equals(nodeB));
    	assertTrue(nodeC.equals(node1));
    	assertTrue(nodeD.equals(node2));
    	
    }
    
    @Test 
    public void appendTest1() {
    	Node nodeA = new Node("a");
    	Node nodeB = new Node("b");
    	Node nodeC = new Node("c");
    	Node nodeD = new Node("d");
    	
    	stack.append(nodeD);
    	stack.append(nodeC);
    	stack.append(nodeA);
    	stack.append(nodeB);
    	
    	assertTrue(nodeD.equals(stack.peek()));
    	assertEquals(stack.getSize(), 4);
    }
    
    @Test 
    public void appendTest2() {
    	Node nodeA = new Node("a");
    	Node nodeB = new Node("b");
    	Node nodeC = new Node("c");
    	Node nodeD = new Node("d");
    	
    	stack.push(nodeD);
    	stack.push(nodeC);
    	stack.append(nodeA);
    	
    	assertTrue(nodeC.equals(stack.peek()));
    	assertEquals(stack.getSize(),3);
    	
    }
    
    @Test
    public void pushTest1() {
    	Node nodeA = new Node("a");
    	Node nodeB = new Node("b");
    	Node nodeC = new Node("c");
    	Node nodeD = new Node("d");
    	
    	stack.append(nodeD);
    	stack.append(nodeB);
    	stack.push(nodeA);
    	
    	assertTrue(nodeA.equals(stack.peek()));
    	assertEquals(stack.getSize(),3);
    	
    }
    
    @Test
    public void pushTest2() {
    	Node nodeA = new Node("a");
    	Node nodeB = new Node("b");
    	Node nodeC = new Node("c");
    	Node nodeD = new Node("d");
    	
    	stack.push(nodeD);
    	stack.push(nodeB);
    	stack.push(nodeA);
    	
    	assertTrue(nodeA.equals(stack.peek()));
    	assertEquals(stack.getSize(), 3);
    }


}
   