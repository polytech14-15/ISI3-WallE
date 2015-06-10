package model.graph;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphTest {
    
    Graph graph;
    Node n1, n2, n3;
    Edge e1;
    
    public GraphTest() {
        graph = new Graph();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        //Creation d'un graphe simple, 3 sommets, seulement deux relies
        graph = new Graph();
        n1 = new Node(0, 0, TypeNode.NORMAL);
        n2 = new Node(0, 100, TypeNode.NORMAL);
        n3 = new Node(100, 0, TypeNode.NORMAL);
        e1 = new Edge(n1, n2, TypeEdge.PLAT);
        
        this.graph.addNode(n1); this.graph.addNode(n2); this.graph.addNode(n3);
        this.graph.addEdge(e1);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addNode method, of class Graph.
     */
    @Test
    public void testAddNode() {
        System.out.println("addNode");
        Node a = new Node(100, 100, TypeNode.NORMAL);
        assertFalse(this.graph.getNodes().contains(a));
        this.graph.addNode(a);
        assertTrue(this.graph.getNodes().contains(a));
    }

    /**
     * Test of addEdge method, of class Graph.
     */
    @Test
    public void testAddEdge() {
        System.out.println("addEdge");
        Edge e = new Edge(n2, n3, TypeEdge.PLAT);
        assertFalse(this.graph.getEdges().contains(e));
        this.graph.addEdge(e);
        assertTrue(this.graph.getEdges().contains(e));
    }

    /**
     * Test of getNeighbors method, of class Graph.
     */
    @Test
    public void testGetNeighbors() {
        System.out.println("getNeighbors");
        List expResult = new ArrayList();
        List result = this.graph.getNeighbors(n3);
        assertEquals(expResult, result);
        
        expResult.add(n2);
        result = this.graph.getNeighbors(n1);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDistance method, of class Graph.
     */
    @Test
    public void testGetDistance_Node_Node() {
        System.out.println("getDistance");
        int expResult = 100;
        int result = this.graph.getDistance(n1, n2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getEdge method, of class Graph.
     */
    @Test
    public void testGetEdge() {
        System.out.println("getEdge");
        Edge expResult = e1;
        Edge result = this.graph.getEdge(n1, n2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getEdgesFromNode method, of class Graph.
     */
    @Test
    public void testGetEdgesFromNode() {
        System.out.println("getEdgesFromNode");
        List expResult = new ArrayList();
        List result = this.graph.getEdgesFromNode(n3);
        assertEquals(expResult, result);
        
        expResult.add(e1);
        result = this.graph.getEdgesFromNode(n1);
        assertEquals(expResult, result);
    }

    /**
     * Test of reset method, of class Graph.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        this.graph.reset();
        assertTrue(this.graph.getEdges().isEmpty());
        assertTrue(this.graph.getNodes().isEmpty());
    }
}