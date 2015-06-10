package model.robot;

import model.graph.Edge;
import model.graph.Node;
import model.graph.TypeEdge;
import model.graph.TypeNode;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TrackedRobotTest {
    
    public TrackedRobotTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of canMove method, of class TrackedRobot.
     */
    @Test
    public void testCanMove() {
        System.out.println("canMove");
        TrackedRobot instance = new TrackedRobot();

        Edge e = new Edge(new Node(0, 0, TypeNode.NORMAL), new Node(100, 100, TypeNode.NORMAL), TypeEdge.PLAT);
        assertTrue(instance.canMove(e));
        
        e.setFlooded(true);
        assertTrue(instance.canMove(e));
        
        e.setType(TypeEdge.ESCARPE);
        assertFalse(instance.canMove(e));
          
    }
}