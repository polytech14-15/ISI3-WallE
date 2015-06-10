package model.graph;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class NodeTest {
    
    public NodeTest() {
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
     * Test of initFire method, of class Node.
     */
    @Test
    public void testInitFire() {
        System.out.println("initFire");
        Node instance = new Node(0, 0, TypeNode.NORMAL);
        instance.initFire();
        assertEquals(instance.getType(), TypeNode.INCENDIE);
        assertEquals(instance.getValueFire(), Node.INIT_VALUE_FIRE);
    }

    /**
     * Test of extinctFire method, of class Node.
     */
    @Test
    public void testExtinctFire() {
        System.out.println("extinctFire");
        Node instance = new Node(0, 0, TypeNode.INCENDIE);
        assertEquals(instance.getValueFire(), Node.INIT_VALUE_FIRE);
        instance.extinctFire();
        assertTrue(instance.getValueFire() == 0);
    }

    /**
     * Test of equals method, of class Node.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Node n = new Node(0, 0, TypeNode.NORMAL);
        Node instance = new Node(0, 0, TypeNode.INCENDIE);
        boolean expResult = false;
        boolean result = instance.equals(n);
        assertEquals(expResult, result);
    }
}