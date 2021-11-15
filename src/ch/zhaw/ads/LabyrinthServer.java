package ch.zhaw.ads;


import java.util.*;
import java.awt.*;


public class LabyrinthServer implements CommandExecutor {
	ServerGraphics g = new ServerGraphics();

	public Graph<DijkstraNode, Edge> createGraph(String s) {

		Graph graph = new AdjListGraph(DijkstraNode.class, Edge.class);
		String[] graphString = s.split("\n");
		for (int i = 0; i < graphString.length; i++) {
			String graphStringArray[] = graphString[i].split(" ");
			String src = graphStringArray[0];
			String dest = graphStringArray[1];
			Double dist = 1.0;
			try {
				graph.addEdge(src, dest, dist);
			} catch (Throwable throwable) {
				throwable.printStackTrace();
			}
			try {
				graph.addEdge(dest, src, dist);
			} catch (Throwable throwable) {
				throwable.printStackTrace();
			}
		}
		return graph;
	}

	public void drawLabyrinth(Graph<DijkstraNode, Edge> graph) {

		for (DijkstraNode<Edge> node: graph.getNodes()){
			for (Edge edge: node.getEdges()){
				g.drawPath(node.getName(), edge.getDest().toString(), false);
			}
		}
	}

	private boolean search(DijkstraNode<Edge> current, DijkstraNode<Edge> ziel) {

		current.setMark(true);
		if (current == ziel){return true;}
		else {
			for (Edge<DijkstraNode> edge: current.getEdges()){
				if (!edge.getDest().getMark()){
					if (search(edge.getDest(), ziel)){return true;}
				}
			}
		}
		return false;
	}

	// search and draw result
	public void drawRoute(Graph<DijkstraNode, Edge> graph, String startNode, String zielNode) {

		search(graph.findNode(startNode), graph.findNode(zielNode));
		for (DijkstraNode<Edge> node : graph.getNodes()) {
			for (Edge<DijkstraNode> edge : node.getEdges()) {
				if (edge.getDest().getMark()) {
					g.drawPath(node.getName(), edge.getDest().toString(), true);
				}
			}
		}
	}

	public String execute(String s) throws Exception {
		Graph<DijkstraNode, Edge> graph;
		graph = createGraph(s);
		drawLabyrinth(graph);
		drawRoute(graph, "0-6", "3-0");
		return g.getTrace();
	}

}