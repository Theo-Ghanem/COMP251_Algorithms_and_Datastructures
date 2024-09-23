import java.util.*;


public class Q2 {
	
	public static int rings(Hashtable<Integer, ArrayList<Integer>> graph, int[] location) {
		int counterPlanetTravel =0;
		LinkedList<Integer> topologicalOrder = new LinkedList<>();
		int currentPlanet = location[getFirstSource(graph)];
		while(graph.size()>0) {
			int source = getSource(graph,location, currentPlanet);
			if(!(currentPlanet == location[source])) { //if we change planets, we increment the counter
				counterPlanetTravel++;
			}
			currentPlanet = location[source]; //get the current planet. 1=Earth, 2=Asgard
			topologicalOrder.add(source);
			graph.remove(source);
		}

		return counterPlanetTravel;
	}

	public static void main(String[] args) {


//		Hashtable<Integer, ArrayList<Integer>> graph = new Hashtable<>();
//		graph.put(1,new ArrayList<Integer>(Arrays.asList(2,3)));
//		graph.put(2,new ArrayList<Integer>(Arrays.asList(4,5)));
//		graph.put(3,new ArrayList<Integer>(Arrays.asList(4,5)));
//		graph.put(4,new ArrayList<Integer>());
//		graph.put(5,new ArrayList<Integer>());
//
//		int[] location = {1,2,1,2,1};
//		System.out.println(rings(graph,location));
//
//		Hashtable<Integer, ArrayList<Integer>> graph2 = new Hashtable<>();
//		graph2.put(1,new ArrayList<Integer>(Arrays.asList(4,2)));
//		graph2.put(2,new ArrayList<Integer>(Arrays.asList(4)));
//		graph2.put(3,new ArrayList<Integer>(Arrays.asList(2,5,1,4)));
//		graph2.put(4,new ArrayList<Integer>());
//		graph2.put(5,new ArrayList<Integer>(Arrays.asList(4,2,1)));
//
//		int[] location2 = {2, 1, 2, 2, 1};
//		System.out.println(rings(graph2,location2));
//
//		Hashtable<Integer, ArrayList<Integer>> graph3 = new Hashtable<>();
//		graph3.put(1,new ArrayList<Integer>());
//		graph3.put(2,new ArrayList<Integer>(Arrays.asList(1)));
//		graph3.put(3,new ArrayList<Integer>(Arrays.asList(1,2)));
//		graph3.put(4,new ArrayList<Integer>(Arrays.asList(2,3,1)));
//		graph3.put(5,new ArrayList<Integer>(Arrays.asList(3,4,2,1)));
//
//		int[] location3 = {2, 1, 2, 2, 1};
//		System.out.println(rings(graph3,location3));
//
//		Hashtable<Integer, ArrayList<Integer>> graph4 = new Hashtable<>();
//		graph4.put(1,new ArrayList<Integer>(Arrays.asList(2,3,5,4)));
//		graph4.put(2,new ArrayList<Integer>(Arrays.asList()));
//		graph4.put(3,new ArrayList<Integer>(Arrays.asList(4,2,5)));
//		graph4.put(4,new ArrayList<Integer>(Arrays.asList(2)));
//		graph4.put(5,new ArrayList<Integer>(Arrays.asList(2,4)));
//
//		int[] location4 = {2, 2, 2, 1, 2};
//		System.out.println(rings(graph4,location4));

	}


	/**
	 * Helper method to find the source node (the one that is unreachable from other nodes)
	 * @param graph
	 * @return
	 */
	public static int getSource(Hashtable<Integer, ArrayList<Integer>> graph, int[] location, int currentPlanet){
		Set<Integer> setOfKeys = graph.keySet();
		Set<Integer> setOfReachableNodes = new HashSet<>();
		Set<Integer> possibleSources = new HashSet<>();

		for (Integer key : setOfKeys) {							//go through all the nodes
			ArrayList<Integer> arrayOfNodes = graph.get(key);	//get the reachable vertices
			for(Integer node : arrayOfNodes)					//go through the reachable nodes
				setOfReachableNodes.add(node);					//and add them all to a set
		}
		for (Integer key : setOfKeys) {							//go through every node once again
			if(!setOfReachableNodes.contains(key))				//if a node isn't reachable then it means it's the source
				possibleSources.add(key);
		}
		for (Integer possibleSource: possibleSources){ //we want the source that will give us the least number of travels in between planets
			if(currentPlanet==location[possibleSource])
				return possibleSource;
		}
		return possibleSources.iterator().next(); //if we don't have a source that is "better" then just return the first one
	}

	/**
	 * Get the first source
	 * @param graph
	 * @return
	 */
	public static int getFirstSource(Hashtable<Integer, ArrayList<Integer>> graph){
		Set<Integer> setOfKeys = graph.keySet();
		Set<Integer> setOfReachableNodes = new HashSet<>();
		for (Integer key : setOfKeys) {
			ArrayList<Integer> arrayOfNodes = graph.get(key);
			for(Integer node : arrayOfNodes)
				setOfReachableNodes.add(node);
		}
		for (Integer key : setOfKeys) {
			if(!setOfReachableNodes.contains(key))
				return key;
		}
		return -1; //there is no source node
	}

}
