package model.manager;

import java.util.List;
import java.util.Map;
import model.algo.IAlgo;
import model.graph.Graph;
import model.graph.Node;
import model.robot.FeetRobot;
import model.robot.Robot;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ManagerTest {
    
    public ManagerTest() {
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
     * Test of addRobot method, of class Manager.
     */
    @Test
    public void testAddRobot() {
        System.out.println("addRobot");
        Robot r = new FeetRobot();
        Manager instance = new Manager();
        assertFalse(instance.getRobots().contains(r));
        instance.addRobot(r);
        assertTrue(instance.getRobots().contains(r));
    }
}