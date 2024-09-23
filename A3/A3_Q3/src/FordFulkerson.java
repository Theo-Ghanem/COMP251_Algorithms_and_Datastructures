//no collaborators
import java.util.*;
import java.io.File;

public class FordFulkerson {

	/**
	 * finds a path via Depth First Search (DFS) between the nodes “source” and “destination” in the “graph”.
	 *
	 * @param source
	 * @param destination
	 * @param graph
	 * @return an ArrayList of Integers with the list of unique nodes belonging to the path found by the DFS.
	 * The first element in the list must correspond to the “source” node, the second element in the list must
	 * be the second node in the path, and so on until the last element (i.e., the “destination” node) is stored.
	 */
	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
		ArrayList<Integer> path = new ArrayList<Integer>();
		/* YOUR CODE GOES HERE*/

		ArrayList<Edge> edges = graph.getEdges();
		ArrayList<Integer> possiblePath = new ArrayList<Integer>();

		Stack<Integer> stack = new Stack<Integer>();
		stack.push(graph.getSource());
		while(stack.size()!=0){
			int currentNode = stack.pop();
			possiblePath.add(currentNode);
			if(currentNode == graph.getDestination())
				break;
			boolean stuck = true;
			for(Edge edge: edges){
				if(edge.nodes[0] == currentNode && edge.weight>0) {
					stack.push(edge.nodes[1]);
					stuck = false;
				}
			}
			if(stuck){
				possiblePath.remove(possiblePath.size()-1);
			}
		}


		//remove the nodes that shouldn't be in the path
		for (int i=0; i<possiblePath.size(); i++) {
			for (Edge edge : edges) {
				if (edge.nodes[0] == possiblePath.get(i) && edge.nodes[1] == possiblePath.get(i+1)) {
					path.add(possiblePath.get(i));
				}
			}
		}
		path.add(graph.getDestination());

		return path;
	}

	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph residualGraph, WGraph originalGraph) {
		ArrayList<Integer> path = new ArrayList<Integer>();
		/* YOUR CODE GOES HERE*/

		ArrayList<Edge> edges = residualGraph.getEdges();
		ArrayList<Integer> possiblePath = new ArrayList<Integer>();
		Boolean sinkReached = false;
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(residualGraph.getSource());
		while(stack.size()!=0){
			int currentNode = stack.pop();
			possiblePath.add(currentNode);
			if(currentNode == residualGraph.getDestination()) {
				sinkReached = true;
				break;
			}
			boolean stuck = true;
			for(Edge edge: edges){
				Edge originalEdge = originalGraph.getEdge(edge.nodes[0], edge.nodes[1]);
				if(edge.nodes[0] == currentNode && originalEdge.weight - edge.weight>0) {
					stack.push(edge.nodes[1]);
					stuck = false;
				}
			}
			if(stuck){
				possiblePath.remove(possiblePath.size()-1);
			}

		}

			//remove the nodes that shouldn't be in the path
			for (int i = 0; i < possiblePath.size()-1; i++) {
				for (Edge edge : edges) {
					if (edge.nodes[0] == possiblePath.get(i) && edge.nodes[1] == possiblePath.get(i + 1)) {
						path.add(possiblePath.get(i));
					}
				}
			}
			if(sinkReached)
			path.add(residualGraph.getDestination());

			return path;

	}




	public static String fordfulkerson( WGraph graph){
		String answer="";
		int maxFlow = 0;
		
		/* YOUR CODE GOES HERE		*/

		//initialize all flows to 0
		WGraph residualGraph = new WGraph(graph);


		for(Edge edge: residualGraph.getEdges()){
			edge.weight =0; //set all the flow in the residual graph to 0
		}
		ArrayList<Integer> nodesResidualGraph;
		//while (DFS is able to find a path from s to t){
//		while((nodesResidualGraph = pathDFS(graph.getSource(), graph.getDestination(), residualGraph)).size()>0){
		while((nodesResidualGraph = pathDFS(graph.getSource(), graph.getDestination(), residualGraph, graph)).size()>1){ //bigger than 2 because we add the source at the end

			//this section gets the edges in the path
			ArrayList<Edge> edgesResidualGraph = new ArrayList<>(nodesResidualGraph.size());
			for (int i=0; i<nodesResidualGraph.size(); i++) {
				for (Edge edge : residualGraph.getEdges()) {
					if (edge.nodes[0] == nodesResidualGraph.get(i) && edge.nodes[1] == nodesResidualGraph.get(i+1)) {
						edgesResidualGraph.add(edge);
					}
				}
			}

//			Get the bottleneck from that path
			int bottleneck = getBottleneck(edgesResidualGraph, residualGraph, graph);

			//update the residual graph
			augment(edgesResidualGraph, bottleneck, graph, residualGraph);
			maxFlow = calculateMaxFlow(residualGraph);
			graph = residualGraph;
		


		} //end of while loop, as there are no more augmented paths

		answer += maxFlow + "\n" + graph.toString();	
		return answer;
	}



	private static int getBottleneck(ArrayList<Edge> edgesResidualGraph, WGraph residualGraph, WGraph graph) {
		Edge originalEdge = graph.getEdge(edgesResidualGraph.get(0).nodes[0], edgesResidualGraph.get(0).nodes[1]);
		int bottleneck = originalEdge.weight - edgesResidualGraph.get(0).weight;
		for(int i=1; i<edgesResidualGraph.size();i++){
			originalEdge = graph.getEdge(edgesResidualGraph.get(i).nodes[0], edgesResidualGraph.get(i).nodes[1]);
			if(originalEdge.weight - edgesResidualGraph.get(i).weight < bottleneck)
				bottleneck = originalEdge.weight - edgesResidualGraph.get(i).weight;
		}
		return bottleneck;
	}

	private static int calculateMaxFlow(WGraph residualGraph) {
		int maxFlow=0;
		int sink = residualGraph.getDestination();
		for (Edge edge : residualGraph.getEdges()) {
			if (edge.nodes[1] == sink) {
				maxFlow += edge.weight;
			}
		}
		return maxFlow;
	}


	public static WGraph augment(ArrayList<Edge> edgesInPath, int bottleneck, WGraph originalGraph, WGraph residualGraph){

		for(Edge edge: edgesInPath){
			Edge originalEdge = originalGraph.getEdge(edge.nodes[0], edge.nodes[1]);
			if(originalEdge.weight-edge.weight>0){ //if edge is a forward edge
				edge.weight+=bottleneck; //add the bottleneck to the flow of that edge
			}
			else{ //the edge is a backward edge
				edge.weight-=bottleneck;//subtract the bottleneck from the flow of that edge
			}
		}
		return residualGraph; //return the new residual graph
	}




	 public static void main(String[] args){
//		String file = args[0];
//		File f = new File(file);
//		WGraph g = new WGraph(file);
//	    System.out.println(fordfulkerson(g));


//Example from pdf=========================================================================
//		 WGraph g = new WGraph();
//		 g.setSource(0);
//		 g.setDestination(5);
//		 Edge[] edges = new Edge[] {
//				 new Edge(0, 1, 16),
//				 new Edge(0, 2, 13),
//				 new Edge(1, 3, 12),
//				 new Edge(2, 1, 4),
//				 new Edge(2, 4, 14),
//				 new Edge(3, 2, 9),
//				 new Edge(3, 5, 20),
//				 new Edge(4, 3, 7),
//				 new Edge(4, 5, 4)
//		 };
//========================================================================================

//		 Test 0
//		 WGraph g = new WGraph();
//		 g.setSource(0);
//		 g.setDestination(5);
//		 Edge[] edges = new Edge[] {
//				 new Edge(0, 1, 10),
//				 new Edge(0, 2, 5),
//				 new Edge(2, 4, 5),
//				 new Edge(1, 3, 10),
//				 new Edge(1, 6, 5),
//				 new Edge(3, 0, 10),
//				 new Edge(3, 5, 5)
//		 };
//
//		 Arrays.stream(edges).forEach(e->g.addEdge(e));
//		 FordFulkerson.pathDFS(0, 5, g);


//========================================================================================
//		 Test 1
//
//		 WGraph g = new WGraph();
//		 g.setSource(0);
//		 g.setDestination(5);
//		 Edge[] edges = new Edge[] {
//				 new Edge(0, 1, 10),
//				 new Edge(0, 2, 5),
//				 new Edge(2, 4, 5),
//				 new Edge(1, 3, 10),
//				 new Edge(1, 6, 5),
//				 new Edge(3, 0, 10),
//				 new Edge(3, 5, 5)
//		 };
//
//		 Arrays.stream(edges).forEach(e->g.addEdge(e));
//		 FordFulkerson.pathDFS(0, 5, g);


		 //Test 1 output:
		 //
		 //[0,1,3,5]
//========================================================================================
//		 Test 2

//		 WGraph g = new WGraph();
//		 g.setSource(0);
//		 g.setDestination(9);
//		 Edge[] edges = new Edge[] {
//				 new Edge(0, 1, 10),
//				 new Edge(0, 2, 5),
//				 new Edge(2, 3, 5),
//				 new Edge(1, 3, 10),
//				 new Edge(3, 4, 5),
//				 new Edge(4, 5, 10),
//				 new Edge(4, 6, 5),
//				 new Edge(6, 7, 5),
//				 new Edge(6, 8, 10),
//				 new Edge(8, 9, 10),
//		 };
//		 Arrays.stream(edges).forEach(e->g.addEdge(e));
//		 String result = FordFulkerson.fordfulkerson(g);
//		 System.out.println(result);
//		 weightsFromString(result);

//		 Test 2 output:
//
//		 The following are weights of edges.
//			[5,0,0,5,5,0,5,0,5,5]
//		 OR
//			[0,5,5,0,5,0,5,0,5,5]

		 //========================================================================================
		 //my example
		 WGraph g = new WGraph();
		 g.setSource(0);
		 g.setDestination(5);
		 Edge[] edges = new Edge[] {
				 new Edge(0, 1, 16),
				 new Edge(0, 2, 13),
				 new Edge(1, 3, 12),
				 new Edge(2, 1, 4),
				 new Edge(2, 4, 14),
				 new Edge(3, 2, 9),
				 new Edge(3, 5, 20),
				 new Edge(4, 3, 7),
				 new Edge(4, 5, 4)

		 };
		 Arrays.stream(edges).forEach(e->g.addEdge(e));
		 String result = FordFulkerson.fordfulkerson(g);
		 System.out.println(result);

	 }
}

