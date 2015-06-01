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

public class MapPanel extends MapGraph {

    //Rayon du noeud

    public final static int RADIUS = 10;

    private Node selectedNode;
    private List<Object> actions;

    MapPanel() {
        super();
        actions = new ArrayList<>();
    }

    public void addNode(Node n) {
        getGraph().addNode(n);
        actions.add(n);
    }

    public void addEdge(Edge e) {
        getGraph().addEdge(e);
        actions.add(e);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawSelectedNode(g);
    }

    private void drawSelectedNode(Graphics g) {
        if (selectedNode != null) {
            Color c = Color.RED;
            g.setColor(c);
            drawCircle(g, selectedNode.getX().intValue(), selectedNode.getY().intValue(), RADIUS);
            c = Color.WHITE;
            g.setColor(c);
            g.drawString(selectedNode.getId().toString(), selectedNode.getX().intValue() - RADIUS / 2, selectedNode.getY().intValue() + RADIUS / 2);
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

    /**
     * @return the actions
     */
    public List<Object> getActions() {
        return actions;
    }

    /**
     * @param actions the actions to set
     */
    public void setActions(List<Object> actions) {
        this.actions = actions;
    }

}
