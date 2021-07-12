package nz.ac.auckland.softeng281.a4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public class GraphTest {

    public static class GraphUnitTest {
        Graph graph;

        @Before
        public void setUp() throws Exception {
            List<String> edges = Arrays.asList("1,2", "2,3", "2,4", "4,2");
            List<String> weights = Arrays.asList("10", "20", "30", "20");
            graph = new Graph(edges, weights);
        }
        
        @Test
        public void testFindNode() {
            assertTrue(graph.isNodeInGraph(new Node("1")));
            assertTrue(graph.isNodeInGraph(new Node("2")));
            assertTrue(graph.isNodeInGraph(new Node("3")));
            assertTrue(graph.isNodeInGraph(new Node("4")));
            assertFalse(graph.isNodeInGraph(new Node("5")));
        }

        @Test
        public void testSearchEdgeByWeight() {
        	Edge testEdge = graph.searchEdgeByWeight(10);
            Edge answer = new Edge(new Node("1"), new Node("2"), 10);
            
            assertTrue(testEdge.equals(answer));
        }
        
        @Test
        public void testShortestPath() {
            List<String> edges = Arrays.asList("1,2", "2,3", "3,4", "4,5");
            List<String> weights = Arrays.asList("10", "20", "30", "20");
            graph = new Graph(edges, weights);
            Path path = new Path(80, new Node("1"), new Node("2"), new Node("3"), new Node("4"), new Node("5"));
            assertTrue(path.equals(graph.computeShortestPath(new Node("1"), new Node("5"))));
        }
        
        
        
    }

    public static class GraphSystemTest {

        ByteArrayOutputStream myOut;
        PrintStream origOut;

        @Before
        public void setUp() {
            origOut = System.out;
            myOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(myOut)); 
        }

        @After
        public void tearDown() {
            System.setOut(origOut);
            if (myOut.toString().length() > 1) {
                System.out.println(System.lineSeparator() + "the System.out.print was :" + System.lineSeparator()
                        + myOut.toString());
            }
        }

        private void runTest(String fileName, String command) {
            GraphUI.scanner = new Scanner("open " + fileName + System.getProperty("line.separator") + command
                    + System.getProperty("line.separator") + "exit" + System.getProperty("line.separator"));
            GraphControl controller = new GraphControl();
            controller.execute();
        }

        @Test
        public void testSearchWeightA() {
            runTest("a.txt", "search 1 3");
            assertTrue(myOut.toString().contains("Given the edge from source 1 target 3 has weight: 5"));
        }

        @Test
        public void testSearchEdgeByWeightA() {
            runTest("a.txt", "search 5");
            assertTrue(myOut.toString().contains("The edge searched having weight 5 is: 1-->3"));
        }

        @Test
        public void testShortestPathA() {
            runTest("a.txt", "path 5 1");
            assertTrue(myOut.toString().contains("The shortest path is: 5 -> 4 -> 1 cost: 4"));
        }


    }
    
    public static class OwnTestsGraph1 {
    	Graph graph;
    	Graph graph1;
    	
    	@Before
        public void setUp() throws Exception {
    		List<String> edges = Arrays.asList("1,2","1,3", "2,3", "3,4","3,5", "4,5", "5,3");
            List<String> weights = Arrays.asList("10","40", "20", "30","50", "25",  "60");
            graph = new Graph(edges, weights);
            
        }
        
        @Test
        public void testFindNode2() {
        	List<String> edges = Arrays.asList("1,2", "2,3", "2,4", "4,2", "2,5");
            List<String> weights = Arrays.asList("10", "20", "30", "20", "15");
            graph = new Graph(edges, weights);
            
            assertTrue(graph.isNodeInGraph(new Node("1")));
            assertTrue(graph.isNodeInGraph(new Node("2")));
            assertTrue(graph.isNodeInGraph(new Node("3")));
            assertTrue(graph.isNodeInGraph(new Node("4")));
            assertTrue(graph.isNodeInGraph(new Node("5")));
        }
        
        @Test
        public void testFindNode3() {
        	List<String> edges = Arrays.asList("1,2", "3,2", "4,5", "4,2", "2,5");
            List<String> weights = Arrays.asList("10", "20", "30", "20", "15");
            graph = new Graph(edges, weights);
            
            assertTrue(graph.isNodeInGraph(new Node("1")));
            assertTrue(graph.isNodeInGraph(new Node("2")));
            assertTrue(graph.isNodeInGraph(new Node("3")));
            assertTrue(graph.isNodeInGraph(new Node("4")));
            assertTrue(graph.isNodeInGraph(new Node("5")));
        }

        @Test
        public void testShortestPath1() {
            List<String> edges = Arrays.asList("1,2", "2,3", "1,4", "4,3", "3,5", "4,5");
            List<String> weights = Arrays.asList("10", "50", "20", "30",  "10", "30");
            graph = new Graph(edges, weights);
            Path path = new Path(50, new Node("1"), new Node("4"), new Node("5"));
            assertTrue(path.equals(graph.computeShortestPath(new Node("1"), new Node("5"))));
        }
        
        @Test
        public void testShortestPathTwo() {
            List<String> edges = Arrays.asList("1,2", "2,3", "3,4", "4,5", "1,3", "3,5", "5,3");
            List<String> weights = Arrays.asList("10", "20", "30", "20", "40", "50", "20");
            Graph graph1 = new Graph(edges, weights);
            Path path = new Path(80, new Node("1"), new Node("2"), new Node("3"), new Node("5"));
            assertTrue(path.equals(graph1.computeShortestPath(new Node("1"), new Node("5"))));
        }
        
        @Test
        public void testShortestPath3() {
            List<String> edges = Arrays.asList("1,2", "2,1", "6,2", "6,5", "5,6", "5,3", "3,2", "4,5", "3,4", "1,3");
            List<String> weights = Arrays.asList("50", "20", "30", "10", "50", "40", "60", "10", "20", "10");
            Graph graph1 = new Graph(edges, weights);
            Path path = new Path(50, new Node("6"), new Node("5"), new Node("3"));
            assertTrue(path.equals(graph1.computeShortestPath(new Node("6"), new Node("3"))));
        }
        
        @Test
        public void testShortestPath4() {
            List<String> edges = Arrays.asList("1,2", "2,1", "6,2", "6,5", "5,6", "5,3", "3,2", "4,5", "3,4", "1,3");
            List<String> weights = Arrays.asList("50", "20", "30", "10", "50", "40", "60", "10", "20", "10");
            Graph graph1 = new Graph(edges, weights);
            Path path = new Path(0, new Node("1"), new Node("1"));
            assertTrue(path.equals(graph1.computeShortestPath(new Node("1"), new Node("1"))));
        }
        
        
    }
    
    public static class OwnTestsGraph2 {
    	Graph graph;
    	
    	@Before
        public void setUp() throws Exception {
    		List<String> edges = Arrays.asList("1,2","1,3", "2,3", "3,4","3,5", "4,5", "5,3");
            List<String> weights = Arrays.asList("10","40", "20", "30","50", "25",  "60");
            graph = new Graph(edges, weights);
            
        }

    	@Test
    	 public void testSearchEdgeByWeightA() {
    		Edge testEdge = graph.searchEdgeByWeight(20);
            Edge answer = new Edge(new Node("2"), new Node("3"), 20);
            
            assertTrue(testEdge.equals(answer));
    	}

        @Test
        public void testSearchWeightByEdge() {
            
            Node nodeA = new Node("1");
        	Node nodeB = new Node("4");
        	int answer = graph.searchWeightByEdge(nodeA, nodeB);
            
            assertEquals(answer, -1);
        }
        
        @Test
        public void testSearchWeightByEdge3() {
        	Node nodeD = new Node("1");
        	Node nodeA = new Node("2");
        	Node nodeB = new Node("3");
        	Node nodeC = new Node("4");
        	int testEdge = graph.searchWeightByEdge(nodeA, nodeC);
    
        	assertEquals(testEdge, -1);
        }	
        
        @Test
        public void testGetNodes() {
        	List<Node> answer = Arrays.asList(new Node("1"),new Node("2"), new Node("3"), new Node("4"),new Node("5"));
        	
        	List<Node> list = graph.getNodeList();
        	
        	assertTrue(list.equals(answer));
        }
 
    }
    
    public static class OwnTestsGraph3 {
    	Graph graph;
    	
    	@Before
        public void setUp() throws Exception {
        	List<String> edges = Arrays.asList("1,2", "3,2", "4,5", "4,2", "2,5", "1,3", "3,1", "2,1", "3,4", "4,3");
            List<String> weights = Arrays.asList("10", "20", "30", "20", "15", "15", "20", "30", "10", "40");
            graph = new Graph(edges, weights);
        }
    	
    	@Test
    	public void testSearchEdgeByWeight2() {

            
            Edge testEdge = graph.searchEdgeByWeight(15);
            Edge answerv1 = new Edge(new Node("2"), new Node("5"), 15);
            Edge answerv2 = new Edge(new Node("1"), new Node("3"), 15);
            
            assertTrue(testEdge.equals(answerv1) || testEdge.equals(answerv2));
        }
    	
    	@Test
        public void testSearchEdgeByWeight3() {

            Edge testEdge = graph.searchEdgeByWeight(30);
            Edge answer1 = new Edge(new Node("4"), new Node("5"), 30);
            Edge answer2 = new Edge(new Node("2"), new Node("1"), 30);
            
            assertTrue(testEdge.equals(answer1)||testEdge.equals(answer2));
        }
    	
        @Test
    	public void testSearchWeightByEdge2() {
            
        	Node nodeA = new Node("3");
        	Node nodeB = new Node("4");
        	int answer = graph.searchWeightByEdge(nodeA, nodeB);
            
            assertEquals(answer, 10);
        }
    }


}