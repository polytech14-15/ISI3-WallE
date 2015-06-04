package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.Stroke;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import model.graph.TypeEdge;
import view.robot.AbstractRobot;

public class MapPanel extends JPanel {

    //Rayon du noeud
    public final static int RADIUS = 10;

    protected Graph graph;
    protected Node selectedNode;
    private List<AbstractRobot> robots;
    private ImageIcon image;

    public MapPanel() {
        super();
        this.graph = new Graph();
        robots = new ArrayList<>();
    }

    public MapPanel(Graph graph) {
        super();
        this.graph = graph;
        robots = new ArrayList<>();
        loadImage();
        this.setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
        this.setSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
    }

    private void loadImage() {
        this.image = new ImageIcon("src/resources/mapsixieme.jpg");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        Dimension dim = getSize();
//        g.setColor(Color.white);
//        g.fillRect(0, 0, dim.width, dim.height);
        if (image != null) {
            image.paintIcon(this, g, 0, 0);
        }
        showGraph(g);
        showRobots(g);
    }

    public void showGraph(Graphics g) {
        if (!this.graph.getNodes().isEmpty()) {
            for (Iterator it = this.graph.getNodes().iterator(); it.hasNext();) {
                Node n = (Node) it.next();
//                System.out.println("Node : "+n);
                if (n != null) {
                    drawNode(n, g);
                }
            }
        }

        drawSelectedNode(g);

        if (!this.graph.getEdges().isEmpty()) {
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

        //Le noeud est en feu
        if (n.getValueFire() > 0) {
            c = Color.RED;
            g.setColor(c);
            drawCircle(g, n.getX(), n.getY(), n.getValueFire());
        }
    }

    private void drawSelectedNode(Graphics g) {
        if (selectedNode != null) {
            Color c = Color.BLUE;
            g.setColor(c);
            drawCircle(g, selectedNode.getX(), selectedNode.getY(), RADIUS);
            c = Color.WHITE;
            g.setColor(c);
            g.drawString(String.valueOf(selectedNode.getId()), selectedNode.getX() - RADIUS / 2, selectedNode.getY() + RADIUS / 2);
        }
    }

    public void drawDashedLine(Graphics g, int x1, int y1, int x2, int y2) {

        //creates a copy of the Graphics instance
        Graphics2D g2d = (Graphics2D) g.create();

        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setStroke(dashed);
        g2d.drawLine(x1, y1, x2, y2);

        //gets rid of the copy
        g2d.dispose();
    }

    public void drawCircle(Graphics g, int x, int y, int radius) {
        int diameter = radius * 2;
        //shift x and y by the radius of the circle in order to correctly center it
        g.fillOval(x - radius, y - radius, diameter, diameter);

    }

    private void drawEdge(Edge e, Graphics g) {
        Color c;
        //On dessine l'arrête bleu si elle est inondée
        if (e.isFlooded()) {
            c = Color.blue;
        } else {
            c = Color.BLACK;
        }
        g.setColor(c);

        if (e.getType() == TypeEdge.PLAT) {
            g.drawLine(e.getA().getX(), e.getA().getY(), e.getB().getX(), e.getB().getY());
        } else {
            drawDashedLine(g, e.getA().getX(), e.getA().getY(), e.getB().getX(), e.getB().getY());
        }

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

    protected void showRobots(Graphics g) {
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
