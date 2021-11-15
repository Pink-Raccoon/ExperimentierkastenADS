package ch.zhaw.ads;

import java.util.*;

public class RouteServer implements CommandExecutor {
	/*
	build the graph given a text file with the topology
	*/
	private enum Marker {
		red, black, green
	}
	public Graph<DijkstraNode, Edge> createGraph(String topo) {
		Graph<DijkstraNode, Edge> graph = new AdjListGraph(DijkstraNode.class, Edge.class);
		String[] entry = topo.split("\n");
		for (String tripel : entry) {
			String[] data = tripel.split(" ");
			try {
				if (graph.findNode(data[0]) == null) {
					graph.addNode(data[0]);
				}
				if (graph.findNode(data[1]) == null) {
					graph.addNode(data[1]);
				}
				graph.addEdge(data[0], data[1], Double.parseDouble(data[2]));
				graph.addEdge(data[1], data[0], Double.parseDouble(data[2]));
			} catch (Throwable t) {
				System.out.println(t.toString());
			}
		}
		return graph;
	}


	
	
	/*
	apply the dijkstra algorithm
	*/
	public void dijkstraRoute(Graph<DijkstraNode, Edge> graph, String from, String to) {

		Queue<DijkstraNode> pq = new PriorityQueue();

		DijkstraNode startNode = graph.findNode(from);
		DijkstraNode endNode = graph.findNode(to);
		startNode.dist = 0;
		pq.offer(startNode);

		while (!pq.isEmpty()) {
			DijkstraNode current = pq.poll();
			current.setMark(true);
			if (current == endNode) return;
			for (Object e : current.edges) {
				Edge<Double> edge = (Edge) e;
				DijkstraNode n = (DijkstraNode) ((Edge<?>) e).getDest();
				if (!n.mark) {
					double dist = edge.weight + current.dist;
					if ((n.prev == null) || (dist < n.dist)) {
						n.dist = dist;
						n.prev = current;
						pq.offer(n);
					}
				}
			}
		}
	}
	
	/*
	find the route in the graph after applied dijkstra
	the route should be returned with the start town first
	*/
	public List<DijkstraNode<Edge>> getRoute(Graph<DijkstraNode, Edge> graph, String to) {
		List<DijkstraNode<Edge>> route = new LinkedList<>();
		DijkstraNode<Edge> town = graph.findNode(to);
		do {
		    route.add(0,town);
			town = town.getPrev();
		} while (town != null);
		return route;    
	}

	public String execute(String topo) throws Exception {
		Graph<DijkstraNode, Edge> graph = createGraph(topo);
		dijkstraRoute(graph, "Winterthur", "Lugano");
		List<DijkstraNode<Edge>> route = getRoute(graph, "Lugano");
		// generate result string
		StringBuffer buf = new StringBuffer();	
		for (DijkstraNode<Edge> rt : route) buf.append(rt+"\n");
		return buf.toString();
	}
	
	public static void main(String[] args)throws Exception {
	    String swiss = 
	    "Winterthur Zürich 25\n"+
        "Zürich Bern 126\n"+
        "Zürich Genf 277\n"+
        "Zürich Luzern 54\n"+
        "Zürich Chur 121\n"+
        "Zürich Berikon 16\n"+
        "Bern Genf 155\n"+
        "Genf Lugano 363\n"+
        "Lugano Luzern 206\n"+
        "Lugano Chur 152\n"+
        "Chur Luzern 146\n"+
        "Luzern Bern 97\n"+
        "Bern Berikon 102\n"+
        "Luzern Berikon 41\n";
        RouteServer server = new RouteServer();
        System.out.println(server.execute(swiss));
	}
}
