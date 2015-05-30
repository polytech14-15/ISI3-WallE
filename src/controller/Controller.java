package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import model.manager.Manager;
import view.MainFrame;
import view.MapPanel;

public class Controller extends MouseAdapter implements ActionListener {
    
    private MainFrame mainFrame;
    private Manager manager;
    
    public Controller(){
        manager = new Manager(new Graph());
        mainFrame = new MainFrame(this);        
        manager.addObserver(mainFrame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        /*
         * Quand on lance la simu
         *  Creer les robots necessaire
         *  Ne surtout pas oublier de setter un algo au manager!!!!!!!
         */
        
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x_point = e.getX();
        int y_point = e.getY();
        Node n = clickIsInANode(x_point, y_point);
        
        if(n!=null){
            if(mainFrame.getMap().getSelectedNode() == null){
                mainFrame.getMap().setSelectedNode(n);
            }else{    
                Edge edge = new Edge(mainFrame.getMap().getSelectedNode(), n, "");
                this.manager.getGraph().addEdge(edge);
                mainFrame.getMap().setSelectedNode(null);
            }
        }else{
            Node n2 = new Node(new Integer(x_point).doubleValue(), new Integer(y_point).doubleValue(), mainFrame.getTypeRobot().getSelectedItem().toString());
            this.manager.getGraph().addNode(n2);
            mainFrame.getMap().setSelectedNode(null);
        }
        mainFrame.getMap().repaint();
    }
    
    /**
     * 
     * @param x_point
     * @param y_point
     * @return 
     */
    public Node clickIsInANode(int x_point, int y_point){
        for(Node n : this.manager.getGraph().getNodes()){
            if(this.manager.getGraph().getDistance(n, x_point, y_point) <= MapPanel.RADIUS){
                return n;
            }
        }
        return null;
    }
    
    public Manager getManager(){
        return this.manager;
    }
    
}
