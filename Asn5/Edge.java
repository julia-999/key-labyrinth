// Julia Anantchenko
// CS2210A 
// Student number: 251097696
// Dec 10th, 2020

// This class represents an Edge object between two nodes
public class Edge {
	
	// variables
	private Node e1, e2; // the two nodes it's connecting
	private int t; // the type
	private String l; // the label
	
	// constructor, makes an edge object and initializes variables
	public Edge(Node u, Node v, int type) {
		e1 = u;
		e2 = v;
		t = type;
	}
	
	// constructor, makes an edge object and initializes variables (including the label)
	public Edge(Node u, Node v, int type, String label) {
		e1 = u;
		e2 = v;
		t = type;
		l = label;
	}
	
	// returns the first endpoint
	public Node firstEndpoint() {
		return e1;
	}
	
	// returns the second endpoint
	public Node secondEndpoint() {
		return e2;
	}
	
	// returns the type
	public int getType() {
		return t;
	}
	
	// sets the type
	public void setType(int newType) {
		t = newType;
	}
	
	// returns the label
	public String getLabel() {
		return l;
	}
	
	// sets the label
	public void setLabel(String newLabel) {
		l = newLabel;
	}

}
