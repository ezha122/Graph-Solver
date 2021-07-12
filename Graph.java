package nz.ac.auckland.softeng281.a4;

import java.util.*;

/**
 * You cannot add new fields.
 */
public class Graph {

    /**
     * Each node maps to a list of all the outgoing edges from that node
     */
    private HashMap<Node, EdgesLinkedList> adjacencyMap;

    /**
     * root of the graph, to know where to start the DFS or BFS
     */
    private Node root;

    /**
     * !!!!!! You cannot change this method !!!!!!!
     */
    public Graph(List<String> edges, List<String> weights) {
        if (edges.isEmpty() || weights.isEmpty()) {
            throw new IllegalArgumentException("edges and weights are empty");
        }
        adjacencyMap = new HashMap<>();
        int i = 0;
        for (String edge : edges) {
            String[] split = edge.split(",");
            Node source = new Node(split[0]);
            Node target = new Node(split[1]);
            Edge edgeObject = new Edge(source, target, Integer.parseInt(weights.get(i)));
            if (!adjacencyMap.containsKey(source)) {
                adjacencyMap.put(source, new EdgesLinkedList());
            }
            adjacencyMap.get(source).append(edgeObject);
            if (i == 0) {
                root = source;
            }
            i++;
        } 
    }


    /**
     * find a particular node, note that a node might not have outgoing edges but only ingoing edges
     * so you need to check also the target nodes of the edges
     *
     * @param node
     * @return true if adjacencyMap contains the node, false otherwise.
     */
    public boolean isNodeInGraph(Node node) {
    	
    	for (EdgesLinkedList edge : this.adjacencyMap.values()) {
    		int i = 0;
    		while (i < edge.size()) {
    			//get source and target from the edges in case a node has no outgoing edges 
    			Node source = edge.get(i).getSource();
        		Node target = edge.get(i).getTarget();
        		if (source.equals(node) || target.equals(node)) {
        			return true;
        		}
    			i++;
    		}
    	}
        return false; 
    }


    /**
     * This method finds an edge with a specific weight, if there are more
     * than one you need to return the first you encounter.
     * You must use Breath First Search (BFS) strategy starting from the root.
     * <p>
     * You can create a data structure to keep track of the visited nodes
     * Set<Node> visited = new HashSet<>();
     * If you don't keep track of the visited nodes the method will run forever!
     * <p>
     * <p>
     * In addition to the data structure visited you can only create new
     * data structures of type EdgesLinkedList and NodesStackAndQue
     *
     * @param weight
     * @return the Edge with the specific weight, null if no edge with the specif weight exists in teh graph
     */
    public Edge searchEdgeByWeight(int weight) {

        Set<Node> visited = new HashSet<>();
        NodesStackAndQueue queue = new NodesStackAndQueue();
        int edgeWeight;

    	queue.append(root);
    	visited.add(root);
    	
        //when queue is empty, all nodes have been visited and checked
    	while (!queue.isEmpty()) {
    		Node node = queue.pop();
    		EdgesLinkedList edgesList = this.adjacencyMap.get(node);
    		int length = getNumberofChildren(edgesList);
    		
    		// get all children of current node and check if the weight matches the argument 
    		for (int i = 0; i < length; i++) {
    			Edge edge = edgesList.get(i);
    			edgeWeight = edge.getWeight();
    			
    			Node child = edgesList.get(i).getTarget();
    			if (!visited.contains(child)) {
        			queue.append(child);
        			visited.add(child);
        		}

    			if (edgeWeight == weight) {
    				return edge;
    			}	
    		}
    	}
        return null;
    }


    /**
     * Returns the weight of the Edeg with Node source and Node target if the
     * given Edge is inside the graph.
     * If there is no edge with the specified source and target, the method returns -1
     * You must use Depth First Search (DFS) strategy starting from the root.
     * <p>
     * RULES
     * You can create a data structure to keep track of the visited nodes
     * Set<Node> visited = new HashSet<>();
     * If you don't keep track of the visited nodes the method will run forever!
     * <p>
     * In addition to the data structure visited you can only create new data structures of type
     * <p>
     * NodesStackAndQueue and EdgesLinkedList
     *
     * @param source
     * @param target
     * @return the weight of the first encountered edge with source and target,
     * -1 if no edge with the given source and target exists
     */
    public int searchWeightByEdge(Node source, Node target) {

        
        Set<Node> visited = new HashSet<>();
        NodesStackAndQueue stack = new NodesStackAndQueue();
        
        stack.push(root);
        
        //when the stack is empty, all nodes have been visited and checked
        while (!stack.isEmpty()) {
        	Node node = stack.pop();
        	visited.add(node);
        	
        	EdgesLinkedList edgeList = this.adjacencyMap.get(node);
    		Node child;
    		int length = getNumberofChildren(edgeList);
    		
    		for (int j=0; j<length; j++) {
    			child = edgeList.get(j).getTarget();
    			
//    			return weight if the parent and child node match 
//    			the given source and target
    			if (source.equals(node) && target.equals(child)) {
    				return edgeList.get(j).getWeight();
    			}
    			
    			if (!visited.contains(child)) {
        			stack.push(child);
        		}
    		}
        }
        return -1;
    }

    /**
     * Given a source Node and a target Node it returns the shortest path
     * between source and target
     * <p>
     * A Path is represented as an ordered sequence of nodes, together with the
     * total weight of the path. (see Path.java class)
     *
     * @param source
     * @param target
     * @return the shortest path between source and target
     */
    public Path computeShortestPath(Node source, Node target) {
    	
    	HashMap<Node, Node> nodeAndPrevNode = new HashMap<>(); 
    	HashMap<Node, Integer> nodeAndDistance = new HashMap<>(); 
    	List<Node> unvisitedNodes = new ArrayList<>();
    	List<Node> finalPath = new ArrayList<>();
    	int totalWeight=0;
    	
    	if (source.equals(target)) {
    		finalPath.add(source);
    		finalPath.add(target);
    		Path path = new Path(totalWeight, finalPath);
    		return path;
    	}
    	
    	unvisitedNodes = getNodeList();
    	
    	//set up HashMaps so initial weights for all nodes are infinity and 
    	//all initial previous nodes are null 
    	for (Node node : unvisitedNodes) {
    		nodeAndPrevNode.put(node, null);
    		nodeAndDistance.put(node, Integer.MAX_VALUE);
    	}
    	
    	nodeAndDistance.replace(source, 0);
    	
    	while (!unvisitedNodes.isEmpty()) {
    		Node currentNode = getMinDistance(nodeAndDistance, unvisitedNodes); 
    		int currentWeight = nodeAndDistance.get(currentNode);
    		int newWeight;
    		
    		EdgesLinkedList edgeList = this.adjacencyMap.get(currentNode);
    		int length = getNumberofChildren(edgeList);
    		
    		for (int i = 0; i < length; i++) {
    			Node child = edgeList.get(i).getTarget();
    			
    			//find the weight of the edge between current node and child 
    			//and calculate the new weight for the path to child node 
    			int childWeight = this.searchWeightByEdge(currentNode, child);
    			newWeight = currentWeight + childWeight;
    			
    			int distanceToChild = nodeAndDistance.get(child);
    			
    			// compare weights, replace with new weight found if it is 
    			// less than the current weight 
    			if (newWeight < distanceToChild) {
    				nodeAndDistance.replace(child, newWeight);
    				nodeAndPrevNode.replace(child, currentNode);
    			}
    		}
    		unvisitedNodes.remove(currentNode);
    	}
    	
    	totalWeight = nodeAndDistance.get(target);
    	finalPath = getShortestPath(target, nodeAndPrevNode);
    	
    	Path shortestPath = new Path(totalWeight, finalPath);
    	return shortestPath;
    	
    }
    
    /**
     * Given a HashMap of the nodes and the current distance saved for each node and
     * a list of all the unvisited nodes, the node with the minimum distance is found 
     * in order to use for the next iteration of Dijkstra's algorithm
     * 
     * @param hm 
     * @param nodes (unvisited)
     * @return the unvisited node that is associated with the shortest distance
     */
    private Node getMinDistance(HashMap<Node, Integer> hm, List<Node> nodes) {
    	int min = Integer.MAX_VALUE;
    	Node node = nodes.get(0);
    	int length = nodes.size();
    	Node currentNode;
    	
    	for (int i=0; i<length; i++) {
    		currentNode = nodes.get(i);
    		int distance = hm.get(currentNode);
    		if (min > distance) {
    			min = distance;
    			node = currentNode;
    		}
    	}
    	
    	return node;
    }
    /**
     * Given a HashMap of the adjacency list (adjacencyMap), all the nodes that make up the graph
     * are found
     * 
     * @param hm 
     * @return List of nodes that make up the graph
     */
    public List<Node> getNodeList() {
		List<Node> nodesList = new ArrayList<>();
    	Node node;
    	
    	for (EdgesLinkedList edge : this.adjacencyMap.values()) {
    		int i = 0;
    		while (i < edge.size()) {
    			Node source = edge.get(i).getSource();
        		Node target = edge.get(i).getTarget();
        		if (!nodesList.contains(source)) {
        			nodesList.add(source);
        		}
        		
        		if (!nodesList.contains(target)) {
        			nodesList.add(target);
        		}
    			i++;
    		}
    	}
    	
    	return nodesList;
    }
    
    /**
     * Given a HashMap containing each node and it's previous node after the shortest path
     * has been calculated, a list of nodes that make up the shortest path from source to
     * target node is found 
     * 
     * @param target 
     * @param nodeAndPrevNode
     *
     * @return List of nodes that make up the shortest path 
     */
    private List<Node> getShortestPath(Node target, HashMap<Node, Node> nodeAndPrevNode) {
    	List<Node> path = new ArrayList<Node>();
    	
    	Node currentChildNode = target;
    	Node previousNode = nodeAndPrevNode.get(target);
    	path.add(target);

    	while (previousNode != null) {
    		path.add(0, previousNode);
    		
    		currentChildNode = previousNode;
    		previousNode = nodeAndPrevNode.get(currentChildNode);
    	}
    	
    	return path;
    }
    
    /**
     * Given a list of edges for a parent node, the number of children this node has
     * is returned
     * 
     * @param target 
     * @param nodeAndPrevNode
     *
     * @return number of outgoing edges a node has 
     */
    
    private int getNumberofChildren(EdgesLinkedList edgesList) {
    	int num = 0;
    	//if edgesList is null, then there are no children to
    	//loop through
    	if (edgesList != null) {
    		num = edgesList.size();
    	}
    	return num;
    }


}