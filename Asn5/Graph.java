// Julia Anantchenko
// CS2210A 
// Student number: 251097696
// Dec 10th, 2020

// imports
import java.util.ArrayList;
import java.util.Iterator;

// represents a graph object that holds nodes and edges using an adjacency matrix
public class Graph implements GraphADT {
	
	// variables
	private Node nodes[]; // array for the nodes
	private Edge matrix[][]; // array for the edges
	
	// constructor, makes a graph
	public Graph(int n) {
		
		// arrays
		nodes = new Node[n];
		matrix = new Edge[n][n];
		
		// makes nodes for each room number
		for (int i = 0; i < n; i++) {
			nodes[i] = new Node(i);
		}
		
	}

	// inserts an edge between two nodes
	@Override
	public void insertEdge(Node nodeu, Node nodev, int type, String label) throws GraphException {
		
		// checks the nodes are in range, otherwise throws error
		if (nodeu.getName() < 0 || nodeu.getName() > nodes.length-1 || nodev.getName() < 0 || nodev.getName() > nodes.length-1)
			throw new GraphException("Error inserting edge. Node does not exist.");
		
		// checks nodes are not null
		if (nodeu == null || nodev == null)
			throw new GraphException("Error inserting edge. Node does not exist.");
		
		// checks if the edge already exists
		if (matrix[nodeu.getName()][nodev.getName()] != null)
			throw new GraphException("Error inserting edge. Edge already exists.");
		
		// inserts the edge into the matrix (a,b) and (b,a)
		matrix[nodeu.getName()][nodev.getName()] = new Edge(nodeu, nodev, type, label);
		matrix[nodev.getName()][nodeu.getName()] = new Edge(nodeu, nodev, type, label);
		
	}

	// inserts an edge between two nodes
	@Override
	public void insertEdge(Node nodeu, Node nodev, int type) throws GraphException {
		
		// checks the nodes are in range, otherwise throws error
		if (nodeu.getName() < 0 || nodeu.getName() > nodes.length-1 || nodev.getName() < 0 || nodev.getName() > nodes.length-1)
			throw new GraphException("Error inserting edge. Node does not exist.");
		
		// checks nodes are not null
		if (nodeu == null || nodev == null)
			throw new GraphException("Error inserting edge. Node does not exist.");
		
		// checks if the edge already exists
		if (matrix[nodeu.getName()][nodev.getName()] != null)
			throw new GraphException("Error inserting edge. Edge already exists.");

		// inserts the edge into the matrix (a,b) and (b,a)
		matrix[nodeu.getName()][nodev.getName()] = new Edge(nodeu, nodev, type);
		matrix[nodev.getName()][nodeu.getName()] = new Edge(nodeu, nodev, type);
		
	}

	// returns the node at specified room number
	@Override
	public Node getNode(int u) throws GraphException {
		 
		// checks the number is in range
		if (u < 0 || u > nodes.length-1)
			throw new GraphException("Error getting node.");

		return nodes[u];
		
	}

	// returns an iterator of all the edges of a node
	@Override
	public Iterator incidentEdges(Node u) throws GraphException {
		
		// checks if it is in range
		if (u.getName() < 0 || u.getName() > nodes.length-1)
			throw new GraphException("Error getting node.");

		// makes an arraylist
		ArrayList<Edge> list = new ArrayList<Edge>();
		
		// goes through the row of the matrix where the node u is, adding the values to the arraylist
		for (int i = 0; i < nodes.length; i++) {
			if (matrix[u.getName()][i] != null)
				list.add(matrix[u.getName()][i]);
		}
		
		// if the arraylist isn't empty, returns the iterator of it
		if (list.size() != 0)
			return list.iterator();
		
		return null;
		
	}

	// returns the edge of two nodes
	@Override
	public Edge getEdge(Node u, Node v) throws GraphException {
		
		// checks if the nodes exist
		if (u == null || v == null)
			throw new GraphException("Error getting edge. Node does not exist.");
		
		// checks if the edge exists
		if (matrix[u.getName()][v.getName()] == null)
			throw new GraphException("Error getting edge. Edge does not exist.");

		// returns the edge
		return matrix[u.getName()][v.getName()];
		
	}

	// checks if two nodes are adjacent
	@Override
	public boolean areAdjacent(Node u, Node v) throws GraphException {

		// checks if the nodes exist
		if (u == null || v == null)
			throw new GraphException("Error getting edge. Node does not exist.");
		
		// checks if there is an edge
		if (matrix[u.getName()][v.getName()] == null)
			return false;
		
		// returns true if they are adjacent
		return true;
		
	}
	


}
