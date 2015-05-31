package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Iterator;
import javax.swing.JPanel;
import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;

public class MapPanel extends JPanel {
    //Rayon du noeud
    public final static int RADIUS = 10;
    
    private Graph graph;
    private Node selectedNode;
    

    public MapPanel(Graph graph) {
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
            for (Iterator it = this.graph.getEdges().iterator(); it.hasNext();) {
                Edge e = (Edge) it.next();
                if(e!=null){
                    drawEdge(e, g);
                }
            }
        }
        
//        System.out.println(this.graph.getNodes().toString());
    }

    private void drawNode(Node n, Graphics g) {
        Color c = Color.BLACK;
        g.setColor(c);
        drawCircle(g, n.getX().intValue(), n.getY().intValue(), RADIUS);
        c = Color.WHITE;
        g.setColor(c);
        g.drawString(n.getId().toString(), n.getX().intValue(), n.getX().intValue());
    }
    
    private void drawSelectedNode(Graphics g) {
        if(selectedNode != null){
            Color c = Color.RED;
            g.setColor(c);
            drawCircle(g, selectedNode.getX().intValue(), selectedNode.getY().intValue(), RADIUS);
            c = Color.WHITE;
            g.setColor(c);
            g.drawString(selectedNode.getId().toString(), selectedNode.getX().intValue(), selectedNode.getX().intValue());
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
        g.drawLine(e.getA().getX().intValue(), e.getA().getY().intValue(), e.getB().getX().intValue(), e.getB().getY().intValue());
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

}
