package ch.zhaw.ads;


import java.util.*;
import java.awt.*;


public class LabyrinthServer implements CommandExecutor {
	ServerGraphics g = new ServerGraphics();
  
	public Graph<DijkstraNode, Edge> createGraph(String s) {
	    // TODO implement 8.2
	}
  
	public void drawLabyrinth(Graph<DijkstraNode, Edge> graph) {
	    // TODO implement 8.3
	}
  
	private boolean search(DijkstraNode<Edge> current, DijkstraNode<Edge> ziel) {
	    // TODO implement 8.4
	}
  
	// search and draw result
	public void drawRoute(Graph<DijkstraNode, Edge> graph, String startNode, String zielNode) {
	    // TODO implement 8.4
	}

	public String execute(String s) throws Exception {
		Graph<DijkstraNode, Edge> graph;
		graph = createGraph(s);
		drawLabyrinth(graph);
		drawRoute(graph, "0-6", "3-0");
		return g.getTrace();
	}

}
