package nz.ac.auckland.softeng281.a4;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;




public class EdgesLinkedListTest {

    EdgesLinkedList list;

    @Before
    public void setUp() {
        list = new EdgesLinkedList();
    }
    
    @After
	public void tearDown() {

	}

    @Test
    public void testPrependEmptyList() {
        list.prepend(new Edge(new Node("1"), new Node("2"), 1));
        assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(0));
    }
    
    
    @Test
    public void testPrependNonEmptyList() {
    	Node nodeA = new Node("a");
    	Node nodeB = new Node("b");
    	Node nodeC = new Node("c");
    	Node nodeD = new Node("d");
    	Edge edgeA = new Edge(nodeA, nodeB);
    	Edge edgeB = new Edge(nodeC, nodeB);
    	
    	
    	list.prepend(edgeA);
    	list.prepend(edgeB);
    	
    	assertTrue(list.get(0).equals(edgeB));
    	assertTrue(list.get(1).equals(edgeA));
    	assertEquals(list.size(), 2);
    }
    
    public void testPrependNonEmptyList2() {
    	Node nodeA = new Node("1");
    	Node nodeB = new Node("2");

    	Edge edgeA = new Edge(nodeA, nodeB);

    	list.prepend(edgeA);

    	assertTrue(list.get(1).equals(edgeA));
    	assertEquals(list.size(), 1);
    }
    
    @Test
    public void getEmptyList() {
    	Edge edgeA = list.get(0);
    	
    	assertTrue(edgeA == null);
    }
    
    @Test 
    public void getTest() {
    	Node nodeA = new Node("a");
    	Node nodeB = new Node("b");
    	Node nodeC = new Node("c");
    	Node nodeD = new Node("d");
    	
    	Edge edgeA = new Edge(nodeA, nodeB);
    	Edge edgeB = new Edge(nodeC, nodeA);
    	Edge edgeC = new Edge(nodeB, nodeA);
    	
    	list.prepend(edgeC);
    	list.append(edgeB);
    	list.append(edgeA);
    	
    	Edge edge1 = list.get(0);
    	Edge edge2 = list.get(1);
    	Edge edge3 = list.get(2);
    	
    	assertTrue(edge1.equals(edgeC));
    	assertTrue(edge2.equals(edgeB));
    	assertTrue(edge3.equals(edgeA));
    	
    }
    
    @Test 
    public void getTest2() {
    	Node nodeA = new Node("a");
    	Node nodeB = new Node("b");
    	Node nodeC = new Node("c");
    	Node nodeD = new Node("d");
    	
    	Edge edgeA = new Edge(nodeA, nodeB);
    	Edge edgeB = new Edge(nodeC, nodeA);
    	Edge edgeC = new Edge(nodeB, nodeA);
    	
    	list.prepend(edgeC);
    	list.append(edgeB);
    	list.append(edgeA);
    	
    	Edge edge1 = list.get(0);
    	Edge edge2 = list.get(1);
    	Edge edge3 = list.get(2);

    	
    	assertTrue(edge1.equals(edgeC));
    	assertTrue(edge2.equals(edgeB));
    	assertTrue(edge3.equals(edgeA));
    	
    }
    @Test 
    public void appendTestEmptyList() {
    	Node nodeA = new Node("a");
    	Node nodeB = new Node("b");
    	Node nodeC = new Node("c");
    	Node nodeD = new Node("d");
    	
    	Edge edgeA = new Edge(nodeA, nodeB);
    	Edge edgeB = new Edge(nodeC, nodeA);
    	Edge edgeC = new Edge(nodeB, nodeA);
    	
    	list.append(edgeC);
    	list.append(edgeA);
    	list.append(edgeB);
    	
    	assertTrue(edgeC.equals(list.get(0)));
    	assertTrue(edgeA.equals(list.get(1)));
    	assertTrue(edgeB.equals(list.get(2)));
    	assertEquals(list.size(), 3);
    	
    }
    
    @Test 
    public void appendTest() {
    	Node nodeA = new Node("a");
    	Node nodeB = new Node("b");
    	Node nodeC = new Node("c");
    	Node nodeD = new Node("d");
    	
    	Edge edgeA = new Edge(nodeA, nodeB);
    	Edge edgeB = new Edge(nodeC, nodeA);
    	Edge edgeC = new Edge(nodeB, nodeA);
    	
    	list.prepend(edgeB);
    	list.append(edgeC);
    	list.append(edgeA);
    	
    	assertTrue(edgeB.equals(list.get(0)));
    	assertTrue(edgeC.equals(list.get(1)));
    	assertTrue(edgeA.equals(list.get(2)));
    	
    }
    
    @Test
    public void insertTest() {
    	Node nodeA = new Node("a");
    	Node nodeB = new Node("b");
    	Node nodeC = new Node("c");
    	Node nodeD = new Node("d");
    	
    	Edge edgeA = new Edge(nodeA, nodeB);
    	Edge edgeB = new Edge(nodeC, nodeA);
    	Edge edgeC = new Edge(nodeB, nodeA);
    	
    	list.prepend(edgeA);
    	list.append(edgeB);
    	list.insert(1, edgeC);
    	
    	assertTrue(list.get(0).equals(edgeA));
    	assertTrue(list.get(1).equals(edgeC));
    	assertTrue(list.get(2).equals(edgeB));
    	assertEquals(list.size(), 3);
    	
    }
    
    public void insertTest2() {
    	Node nodeA = new Node("a");
    	Node nodeB = new Node("b");
    	Node nodeC = new Node("c");
    	Node nodeD = new Node("d");
    	
    	Edge edgeA = new Edge(nodeC, nodeB);
    	Edge edgeB = new Edge(nodeC, nodeA);
    	Edge edgeC = new Edge(nodeB, nodeA);
    	
    	list.prepend(edgeA);
    	list.append(edgeB);
    	list.insert(1, edgeC);
    	
    	assertTrue(list.get(0).equals(edgeA));
    	assertTrue(list.get(1).equals(edgeC));
    	assertTrue(list.get(2).equals(edgeB));
    	assertEquals(list.size(), 3);
    	
    }
    
    @Test 
    public void removeTest() {
    	Node nodeA = new Node("a");
    	Node nodeB = new Node("b");
    	Node nodeC = new Node("c");
    	Node nodeD = new Node("d");
    	
    	Edge edgeA = new Edge(nodeA, nodeB);
    	Edge edgeB = new Edge(nodeC, nodeA);
    	Edge edgeC = new Edge(nodeB, nodeA);
    	Edge edgeD = new Edge(nodeD, nodeB);
    	
    	list.prepend(edgeA);
    	list.append(edgeB);
    	list.append(edgeC);
    	list.append(edgeD);
    	
    	list.remove(2);
    	
    	assertTrue(list.get(0).equals(edgeA));
    	assertTrue(list.get(1).equals(edgeB));
    	assertTrue(list.get(2).equals(edgeD));
    	assertEquals(list.size(), 3);
    }
    
    @Test
    public void removeOneElementList() {
    	Node nodeA = new Node("a");
    	Node nodeB = new Node("b");
    	Node nodeC = new Node("c");
    	Node nodeD = new Node("d");
    	
    	Edge edgeA = new Edge(nodeA, nodeB);
    	Edge edgeB = new Edge(nodeC, nodeA);
    	Edge edgeC = new Edge(nodeB, nodeA);
    	Edge edgeD = new Edge(nodeD, nodeB);
    	
    	list.prepend(edgeA);
    	
    	list.remove(0);
    	
    	assertTrue(list.get(0) == null);
    } 
    
    @Test 
    public void removeFirstEdgeTest() {
    	Node nodeA = new Node("a");
    	Node nodeB = new Node("b");
    	Node nodeC = new Node("c");
    	Node nodeD = new Node("d");
    	
    	Edge edgeA = new Edge(nodeA, nodeB);
    	Edge edgeB = new Edge(nodeC, nodeA);
    	Edge edgeC = new Edge(nodeB, nodeA);
    	Edge edgeD = new Edge(nodeD, nodeB);
    	
    	list.prepend(edgeA);
    	list.append(edgeB);
    	list.append(edgeC);
    	list.append(edgeD);
    	
    	list.remove(0);
    	
    	assertTrue(list.get(0).equals(edgeB));
    	assertEquals(list.size(),3);
    	
    }
    
    @Test
    public void sizeEmptyList() {	
    	assertEquals(list.size(),0);
    }
    
    @Test 
    public void sizeTest1() {
    	Node nodeA = new Node("a");
    	Node nodeB = new Node("b");
    	Node nodeC = new Node("c");
    	Node nodeD = new Node("d");
    	
    	Edge edgeA = new Edge(nodeA, nodeB);
    	Edge edgeB = new Edge(nodeC, nodeA);
    	Edge edgeC = new Edge(nodeB, nodeA);
    	Edge edgeD = new Edge(nodeD, nodeB);
    	
    	list.append(edgeD);
    	list.append(edgeC);
    	list.append(edgeA);
    	list.append(edgeB);
    	
    	assertEquals(list.size(),4);
    }
    
    @Test 
    public void sizeTest2() {
    	Node nodeA = new Node("a");
    	Node nodeB = new Node("b");
    	Node nodeC = new Node("c");
    	Node nodeD = new Node("d");
    	
    	Edge edgeA = new Edge(nodeA, nodeB);
    	Edge edgeB = new Edge(nodeC, nodeA);
    	Edge edgeC = new Edge(nodeB, nodeA);
    	Edge edgeD = new Edge(nodeD, nodeB);
    	
    	list.append(edgeD);
    	list.append(edgeC);
    	
    	assertEquals(list.size(),2);
    	
    }
    
    
}