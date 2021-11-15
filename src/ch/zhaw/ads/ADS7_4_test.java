/**
 * @(#)TreeTest.java
 *
 *
 * @author K Rege
 * @version 1.00 2018/3/17
 * @version 1.01 2021/8/1
 */

package ch.zhaw.ads;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.io.*;

public class ADS7_4_test {
	RouteServer routeServer;
	Graph<DijkstraNode, Edge> graph;
	String fileToTest = "src/ch/zhaw/ads/RouteServer.java";
	
	@Test
    public void checkFileUpload() throws Exception {
        File f = new File(fileToTest);
        assertTrue("File uploaded "+fileToTest,f.exists());
    }
    
    private void init() throws Exception {
      	 String swiss = 
	    "Winterthur Z�rich 25\n"+
        "Z�rich Bern 126\n"+
        "Z�rich Genf 277\n"+
        "Z�rich Luzern 54\n"+
        "Z�rich Chur 121\n"+
        "Z�rich Berikon 16\n"+
        "Bern Genf 155\n"+
        "Genf Lugano 363\n"+
        "Lugano Luzern 206\n"+
        "Lugano Chur 152\n"+
        "Chur Luzern 146\n"+
        "Luzern Bern 97\n"+
        "Bern Berikon 102\n"+
        "Luzern Berikon 41\n";
         routeServer = new RouteServer();  
         graph =  routeServer.createGraph(swiss);
    }
    
    private void testDest(List<DijkstraNode<Edge>> route, String startName, String destName, double dist) {
        for (DijkstraNode<Edge> town : route) {
            if (destName.equals(town.getName())) {
                assertEquals(startName + " to "+ destName,dist,town.getDist(),1E-10);
                return;
            }
        }
        fail(startName + " not connected to "+destName);
    }
    
    
    @Test
    public void testWinterthurLugano() throws Exception {
        init();
        routeServer.dijkstraRoute(graph, "Winterthur", "Lugano");
        List<DijkstraNode<Edge>> route = routeServer.getRoute(graph, "Lugano");
        testDest(route,"Winterthur","Winterthur",0);
        testDest(route,"Winterthur","Z�rich",25);
        testDest(route,"Winterthur","Luzern",79);
        testDest(route,"Winterthur","Lugano",285);
    }
    
    @Test
    public void testLuganoWinterthur() throws Exception {
        init();
        routeServer.dijkstraRoute(graph, "Lugano", "Winterthur");
        List<DijkstraNode<Edge>> route = routeServer.getRoute(graph, "Winterthur");
        testDest(route,"Lugano","Winterthur",285);
        testDest(route,"Lugano","Z�rich",260);
        testDest(route,"Lugano","Luzern",206);
        testDest(route,"Lugano", "Lugano",0);
    }

}
