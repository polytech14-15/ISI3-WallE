package model.algo;

import java.util.List;
import java.util.Map;
import model.graph.*;
import model.robot.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class IAlgoTest {
    
    public IAlgoTest(){
        
    }

    public IAlgo algo;

    Graph gPlat;
    Node n1, n2, n3;
    Edge e1, e2, e3;
    
    Graph gEscarpe;
    Node n4, n5, n6;
    Edge e4, e5, e6;

    Robot r;

    public abstract IAlgo createInstance();

    @Before
    public void setUp() {
        algo = createInstance();

        gPlat = new Graph();
        n1 = new Node(0, 0, TypeNode.NORMAL);
        n2 = new Node(0, 100, TypeNode.NORMAL);
        n3 = new Node(100, 100, TypeNode.NORMAL);
        e1 = new Edge(n1, n2, TypeEdge.PLAT);
        e2 = new Edge(n2, n3, TypeEdge.PLAT);
        e3 = new Edge(n1, n3, TypeEdge.PLAT);
        this.gPlat.addNode(n1);this.gPlat.addNode(n2);this.gPlat.addNode(n3);
        this.gPlat.addEdge(e1);this.gPlat.addEdge(e2);this.gPlat.addEdge(e3);
        
        gEscarpe = new Graph();
        n4 = new Node(0, 0, TypeNode.NORMAL);
        n5 = new Node(0, 100, TypeNode.NORMAL);
        n6 = new Node(100, 100, TypeNode.NORMAL);
        e4 = new Edge(n4, n5, TypeEdge.PLAT);
        e5 = new Edge(n5, n6, TypeEdge.PLAT);
        e6 = new Edge(n4, n6, TypeEdge.ESCARPE);
        this.gEscarpe.addNode(n4);this.gEscarpe.addNode(n5);this.gEscarpe.addNode(n6);
        this.gEscarpe.addEdge(e4);this.gEscarpe.addEdge(e5);this.gEscarpe.addEdge(e6);

        r = new TrackedRobot(n1);
    }

    @Test
    public void testShortestPath() {
        System.out.println("test Shortest Path");
        Map<Integer, List<Node>> res = algo.shortestPath(n3, gPlat, r);
        
        Assert.assertTrue(res.containsKey(141)); // distance de n1 à n3
        Assert.assertEquals(n1, res.get(141).get(0));
        Assert.assertEquals(n3, res.get(141).get(1));
        
        // test du canMove
        r.setCurrentNode(n4);
        res = algo.shortestPath(n6, gEscarpe, r);
        
        Assert.assertTrue(res.containsKey(200)); // distance de n4 à n5 + distance de n5 à n6
        Assert.assertEquals(n4, res.get(200).get(0));
        Assert.assertEquals(n5, res.get(200).get(1));
        Assert.assertEquals(n6, res.get(200).get(2));
    }

//    @Test
//    public void testExpand() {
//        System.out.println("test expand");
//    }
}
