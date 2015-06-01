package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JPanel;
import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import view.robot.AbstractRobot;

public class MapPanel extends JPanel {

    //Rayon du noeud

    public final static int RADIUS = 10;

    private Graph graph;
    private Node selectedNode;
    private List<AbstractRobot> robots;

    public MapPanel(Graph graph) {
        super();
        this.graph = graph;
        robots = new ArrayList<>();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Dimension dim = getSize();
        g.setColor(Color.white);
        g.fillRect(0, 0, dim.width, dim.height);
        showGraph(g);
        showRobots(g);
    }

    public void showGraph(Graphics g) {
        if(!this.graph.getNodes().isEmpty()){
            for (Iterator it = this.graph.getNodes().iterator(); it.hasNext();) {            
                Node n = (Node) it.next();
//                System.out.println("Node : "+n);
                if(n!=null){
                    drawNode(n, g);
                }
            }
        }

        drawSelectedNode(g);
        
        if(!this.graph.getEdges().isEmpty()){
            for (Edge e : this.graph.getEdges()) {
                if (e != null) {
                    drawEdge(e, g);
                }
            }
        }
        
//        System.out.println(this.graph.getNodes().toString());
    }

    private void drawNode(Node n, Graphics g) {
        Color c = Color.BLACK;
        g.setColor(c);
        drawCircle(g, n.getX(), n.getY(), RADIUS);
        c = Color.WHITE;
        g.setColor(c);
        g.drawString(String.valueOf(n.getId()), n.getX() - RADIUS / 2, n.getY() + RADIUS / 2);
    }

    private void drawSelectedNode(Graphics g) {
        if (selectedNode != null) {
            Color c = Color.RED;
            g.setColor(c);
            drawCircle(g, selectedNode.getX(), selectedNode.getY(), RADIUS);
            c = Color.WHITE;
            g.setColor(c);
            g.drawString(String.valueOf(selectedNode.getId()), selectedNode.getX() - RADIUS / 2, selectedNode.getY() + RADIUS / 2);
        }
    }

    public void drawCircle(Graphics g, int x, int y, int radius) {
        int diameter = radius * 2;
        //shift x and y by the radius of the circle in order to correctly center it
        g.fillOval(x - radius, y - radius, diameter, diameter);

    }

    private void drawEdge(Edge e, Graphics g) {
        Color c = Color.BLACK;
        g.setColor(c);
        g.drawLine(e.getA().getX(), e.getA().getY(), e.getB().getX(), e.getB().getY());
    }

    /**
     * @return the selectedNode
     */
    public Node getSelectedNode() {
        return selectedNode;
    }

    /**
     * @param selectedNode the selectedNode to set
     */
    public void setSelectedNode(Node selectedNode) {
        this.selectedNode = selectedNode;
    }


    public void addRobot(AbstractRobot robot) {
        if (getRobots() != null) {
            getRobots().add(robot);
        }
    }

    private void showRobots(Graphics g) {
        for (AbstractRobot robot : getRobots()) {
            if (this.graph.getNodes().contains(robot.getRobot().getCurrentNode())) {
                robot.draw(g);
            }
        }
    }

    /**
     * @return the robots
     */
    public List<AbstractRobot> getRobots() {
        return robots;
    }

    /**
     * @param robots the robots to set
     */
    public void setRobots(List<AbstractRobot> robots) {
        this.robots = robots;
    }

}
