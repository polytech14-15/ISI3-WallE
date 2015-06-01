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

public class MapGraph extends JPanel {
    //Rayon du noeud
    public final static int RADIUS = 10;
    
    private Graph graph;
    

    MapGraph() {
        super();
        graph = new Graph();
    }
    
    MapGraph(Graph graph) {
        super();
        this.graph = graph;
    }
    

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Dimension dim = getSize();
        g.setColor(Color.white);
        g.fillRect(0, 0, dim.width, dim.height);
        showGraph(g);
  } 
    
    public void showGraph(Graphics g) {
        if(!getGraph().getNodes().isEmpty()){
            for (Iterator it = getGraph().getNodes().iterator(); it.hasNext();) {            
                Node n = (Node) it.next();
                System.out.println("Node : "+n);
                if(n!=null){
                    drawNode(n, g);
                }
            }
        }        
        if(!getGraph().getEdges().isEmpty()){
            for (Iterator it = getGraph().getEdges().iterator(); it.hasNext();) {
                Edge e = (Edge) it.next();
                if(e!=null){
                    drawEdge(e, g);
                }
            }
        }
        
        System.out.println(getGraph().getNodes().toString());
    }

    private void drawNode(Node n, Graphics g) {
        Color c = Color.BLACK;
        g.setColor(c);
        drawCircle(g, n.getX().intValue(), n.getY().intValue(), RADIUS);
        c = Color.WHITE;
        g.setColor(c);
        g.drawString(n.getId().toString(), n.getX().intValue()-RADIUS/2, n.getY().intValue()+RADIUS/2);
    }
    

    
    public void drawCircle(Graphics g, int x, int y, int radius) {
        int diameter = radius * 2;
        //shift x and y by the radius of the circle in order to correctly center it
        g.fillOval(x - radius, y - radius, diameter, diameter); 

      }

    private void drawEdge(Edge e, Graphics g) {
        Color c = Color.BLACK;
        g.setColor(c);
        g.drawLine(e.getA().getX().intValue(), e.getA().getY().intValue(), e.getB().getX().intValue(), e.getB().getY().intValue());
    }

    /**
     * @return the graph
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * @param graph the graph to set
     */
    public void setGraph(Graph graph) {
        this.graph = graph;
    }

}
