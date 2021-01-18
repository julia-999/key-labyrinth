// Julia Anantchenko
// CS2210A 
// Student number: 251097696
// Dec 10th, 2020

// imports
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Stack;

public class Labyrinth {

	// variables
	private int width, length; // the length and width of the map
	private Node start, end; // the start and end rooms
	private Graph graph; // the graph holding the room and edge info
	private int keys[]; // the array holding info about keys
	private char map[][]; // the map of the labyrinth
	private Stack<Node> P; // the stack holding the path

	// constructor, opens the file and initializes all the data structures holding information about the map
	public Labyrinth(String inputFile) throws LabyrinthException {

		// reads the file
		try {
			
			// opens file
			BufferedReader inFile = new BufferedReader(new FileReader(inputFile)); 

			// skips the first line
			inFile.readLine();

			// gets the length and width
			width = Integer.parseInt(inFile.readLine());
			length = Integer.parseInt(inFile.readLine());

			// creates the graph, key array and map
			graph = new Graph(width * length);
			keys = new int[10];
			map = new char[length*2-1][width*2-1];

			// prepares to read keys by splitting the line
			String[] key = inFile.readLine().split(" ");

			// checks there are 10 keys
			if (key.length != 10)
				throw new LabyrinthException("Keys are wrong.");

			// puts key info into array
			for (int i = 0; i < 10; i++) {
				keys[i] = Integer.parseInt(key[i]);
			}

			// puts the appropriate char into the map double array
			for (int i = 0; i < length*2-1; i++) {
				String line = inFile.readLine();
				for (int j = 0; j < width*2-1; j++)
					map[i][j] = line.charAt(j);
			}

			// for loop for getting the start and end nodes as well as making all the edges
			for (int i = 0; i < length*2-1; i++) {
				for (int j = 0; j < width*2-1; j++) {
					
					// if it is a room (based on index)
					if ((i*(width*2-1)+j) % 2 == 0) {
						
						// if the char is s, it is the start
						if (map[i][j] == 's')
							start = graph.getNode((i/2)*width+(j/2));
						
						// if the char is x, it is the end
						if (map[i][j] == 'x')
							end = graph.getNode((i/2)*width+(j/2));
						
					}
					
					// if it is an edge based on index (skips walls)
					else if (map[i][j] != 'w') {
						
						// initializes two nodes
						Node first = null, second = null;
						
						// if it is a vertical connection, sets the nodes to appropriate values
						if (i%2 == 1 && i-1 >= 0 && i+1 < length*2-1) {
							first = graph.getNode(((i-1)/2)*width+(j/2));
							second = graph.getNode(((i+1)/2)*width+(j/2));

						}
						
						// if it is a horizontal connection, sets the nodes to appropriate values
						if (i%2 == 0 && j-1 >= 0 && j+1 < width*2-1) {
							first = graph.getNode((i/2)*width+((j-1)/2));
							second = graph.getNode((i/2)*width+((j+1)/2));
						}
						// inserts the edge into the graph, making the door number an int if necessary
						try {
							if (map[i][j] != 'c')
								graph.insertEdge(first, second, Integer.parseInt(Character.toString((map[i][j]))), Character.toString(map[i][j]));
							else
								graph.insertEdge(first, second, -1, Character.toString(map[i][j]));
						} catch (GraphException e) {
							e.printStackTrace();
						}
					}
				}
			}

		} catch (Exception e) {
			throw new LabyrinthException("Error with input file.");
		}
	}

	// returns the graph
	public Graph getGraph() {
		return graph;
	}

	// solves the labyrinth
	public Iterator solve() {
		
		// creates a stack for the path
		P = new Stack<Node>();

		// if the path function returns true, returns the path iterator
		if (path(start, end)) {
			return P.iterator();
		}

		// otherwise returns null
		return null;

	}

	// recursive method for finding the path, returns true if one is found. Modified DFS traversal.
	public boolean path(Node s, Node t) {

		// marks the current node true and pushes it onto the stack
		s.setMark(true);
		P.push(s);

		try {

			// gets the iterator for all the edges of the current node
			Iterator<Edge> it = graph.incidentEdges(s);
			Edge e = null;
			
			// variables to keep track of the door
			boolean isDoor = false;
			int currentKey = -1;

			// if it is the destination returns true
			if (s.getName() == t.getName()) return true;
			
			// otherwise recursively checks the other paths from this node
			else {

				// for all the edges
				while (it.hasNext()) {
					e = (Edge) it.next();

					// checks if it is a door it can enter
					if (e.getType() < 0 || keys[e.getType()] > 0) {

						// reduces key number if necessary and sets variables to keep track
						if (e.getType() >= 0) {
							keys[e.getType()]--;
							isDoor = true;
							currentKey = e.getType();
						}

						// gets the other node
						Node next = e.secondEndpoint();

						// if the "second node" is the original, gets the other
						if (s.getName() == next.getName())
							next = e.firstEndpoint();

						// if unmarked
						if (!next.getMark()) {
							
							// runs the method again but with the next node
							if (path(next, t)) {

								// if the path is found, unmarks the path and returns true
								next.setMark(false);
								return true;
							}
						}
					}

					// if it entered a door to check but left through the door, gives back the key
					if (isDoor)
						keys[currentKey]++;
					isDoor = false;
					
				}
				
				// if the path way wrong pops the top node off the stack
				P.pop().setMark(false);;
				return false;

			}

		} catch (GraphException ex) {
			ex.printStackTrace();
		}
		
		// returns false if no path was found
		return false;
	}

}
