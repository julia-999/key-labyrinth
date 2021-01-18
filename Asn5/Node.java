// Julia Anantchenko
// CS2210A 
// Student number: 251097696
// Dec 10th, 2020

// Represents a Node object which hold information about a room
public class Node {

	// variables
	private int n; // the room number
	private boolean m; // whether the room is marked or not
	
	// constructor, makes a node object with the specified room number
	public Node(int name) {
		n = name;
	}
	
	// marks/unmarks it
	public void setMark(boolean mark) {
		m = mark;
	}
	
	// returns the mark
	public boolean getMark() {
		return m;
	}
	
	// returns the name aka room number
	public int getName() {
		return n;
	}
	
}
