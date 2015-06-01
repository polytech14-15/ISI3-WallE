package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import model.graph.Edge;
import model.graph.Node;
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
//        manager.addObserver(mainFrame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
                Edge edge = new Edge(getMainFrame().getMap().getSelectedNode(), n, getMainFrame().getTypeEdge().getSelectedItem().toString());
                actions.add(edge);
                getMainFrame().getMap().setSelectedNode(null);
            }
        } else {
            Node n2 = new Node(new Integer(x_point).doubleValue(), new Integer(y_point).doubleValue(), getMainFrame().getTypeNode().getSelectedItem().toString());
            actions.add(n);
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
    public Node clickIsInANode(int x_point, int y_point) {
        for (Node n : getMainFrame().getMap().getGraph().getNodes()) {
            if (n.getDistance(x_point, y_point) <= MapPanel.RADIUS) {
                return n;
            }
        }
        return null;
    }

    private void saveGraph() {
        getMainFrame().getMap().getGraph().printXML();
    }

    private void backAction() {
        Node n;
        Edge e;
        Object c;
        if (getMainFrame().getMap().getActions() != null && !mainFrame.getMap().getActions().isEmpty()) {
            c = getMainFrame().getMap().getActions().get(getMainFrame().getMap().getActions().size() - 1);
            if (c instanceof Edge) {
                e = (Edge) c;
                if (getMainFrame().getMap().getGraph().getEdges().contains(e)) {
                    getMainFrame().getMap().getGraph().getEdges().remove(e);
                    getMainFrame().getMap().getActions().remove(getMainFrame().getMap().getActions().size() - 1);
                }
            } else if (c instanceof Node) {
                n = (Node) c;
                if (getMainFrame().getMap().getGraph().getNodes().contains(n)) {
                    getMainFrame().getMap().getGraph().getNodes().remove(n);
                    getMainFrame().getMap().getActions().remove(getMainFrame().getMap().getActions().size() - 1);
                    Node.previousId--;
                }
            }
        }
        System.out.println("liste : " + getMainFrame().getMap().getActions().toString());
        getMainFrame().getMap().repaint();
    }

    private void startSimulation() {   
        //TODO
        manager.setGraph(mainFrame.getMap().getGraph());
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


}
