package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;
import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import model.graph.TypeEdge;
import model.graph.TypeNode;
import model.manager.Manager;
import view.MainFrame;
import view.MapPanel;

public class Controller extends MouseAdapter implements ActionListener {
    
    private MainFrame mainFrame;
    private Manager manager;
    private List<Object> actions;

    public Controller() {
        manager = new Manager();
        mainFrame = new MainFrame(this);
        actions = new ArrayList<>();
        manager.addObserver(mainFrame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
         * Quand on ajoute un feu
         *      recupere liste des noeud du graphe du manager
         *      choisir aleatoirement parmis les noeuds normaux
         *      noeud.initFire();
         *      changer type du noeud
         */
        
        /*
         * Quand on exporte xml
         *      Mettre pause thread manager + mettre pause thread robot (si on peut) 
         *      + exporter 
         *      + reprendre thread robots + reprendre thread manager
         */
        
        /*
         * Quand on importe xml
         *      Arreter thread s'il y en a en cours + charger xml + creer nouveau manager(graph,robots,algo)
         *      + maj manager dans mainFrame + maj graph dans mapPanel + ( maj observer ?)
         */
        
        /*
         * Quand on lance la simu
         *      Voir avec jerem la gestion de creation de robot
         *      Ne pas oublier de setter un algo dans le manager!!!!!!
         *      Lancer Thread manager
         */
       
        
        String c = e.getActionCommand();
        // actions des boutons du haut
        switch (c) {
            case "Export":
                saveGraph();
                break;

            case "Back":
                backAction();
                break;

            case "Play":
                startSimulation();
                break;

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x_point = e.getX();
        int y_point = e.getY();
        Node n = clickIsInANode(x_point, y_point);

        if (n != null) {
            if (getMainFrame().getMap().getSelectedNode() == null) {
                getMainFrame().getMap().setSelectedNode(n);
            } else {
                Edge edge = new Edge(getMainFrame().getMap().getSelectedNode(), n, TypeEdge.valueOf(getMainFrame().getTypeEdge().getSelectedItem().toString()));
                getActions().add(edge);
                this.manager.getGraph().addEdge(edge);
                getMainFrame().getMap().setSelectedNode(null);
            }
        } else {
            Node n2 = new Node(new Integer(x_point).doubleValue(), new Integer(y_point).doubleValue(), TypeNode.valueOf(getMainFrame().getTypeNode().getSelectedItem().toString()));
            getActions().add(n2);
            this.manager.getGraph().addNode(n2);
            getMainFrame().getMap().setSelectedNode(null);
        }
        getMainFrame().getMap().repaint();
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

    private void saveGraph() {
        manager.getGraph().printXML();
    }

    private void backAction() {
        Node n;
        Edge e;
        Object c;
        if (getActions() != null && !getActions().isEmpty()) {
            c = getActions().get(getActions().size() - 1);
            if (c instanceof Edge) {
                e = (Edge) c;
                if (manager.getGraph().getEdges().contains(e)) {
                    manager.getGraph().getEdges().remove(e);
                    getActions().remove(getActions().size() - 1);
                }
            } else if (c instanceof Node) {
                n = (Node) c;
                if (manager.getGraph().getNodes().contains(n)) {
                    manager.getGraph().getNodes().remove(n);
                    getActions().remove(getActions().size() - 1);
                    Node.previousId--;
                }
            }
        }
        getMainFrame().getMap().repaint();
    }

    private void startSimulation() {   
        //TODO
    }

    /**
     * @return the mainFrame
     */
    public MainFrame getMainFrame() {
        return mainFrame;
    }

    /**
     * @param mainFrame the mainFrame to set
     */
    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    /**
     * @return the manager
     */
    public Manager getManager() {
        return manager;
    }

    /**
     * @param manager the manager to set
     */
    public void setManager(Manager manager) {
        this.manager = manager;
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
