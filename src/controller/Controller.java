package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import model.algo.AlgoBreadthFirst;
import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import model.graph.TypeEdge;
import model.graph.TypeNode;
import model.manager.Manager;
import model.robot.FeetRobot;
import model.robot.OffRoadRobot;
import model.robot.Robot;
import model.robot.TrackedRobot;
import util.XmlUtilities;
import view.InstructionsFrame;
import view.MainFrame;
import view.MapPanel;
import view.robot.ViewFeetRobot;
import view.robot.ViewOffRoadRobot;
import view.robot.ViewTrackedRobot;

public class Controller extends MouseAdapter implements ActionListener {

    public static boolean onSimulation = false;
    private MainFrame mainFrame;
    private Manager manager;
    private List<Object> actions;

    public Controller() {
        manager = new Manager();
        mainFrame = new MainFrame(this);
        actions = new ArrayList<>();
        manager.addObserver(mainFrame);
        this.onSimulation = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      
        String c = e.getActionCommand();
        // actions des boutons du haut
        switch (c) {
            case "Export":
                saveGraph();
                break;
            case "Import":
                importGraph();
                break;
            case "Back":
                backAction();
                break;
            case "Play":
                startSimulation();
                break;
            case "Stop":
                //TODO
                stopSimulation();
                break;
            case "Add":
                addRandomRobot();
                break;
            case "Reset":
                resetSimu();
                break;
            case "Instructions":
                new InstructionsFrame();
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x_point = e.getX();
        int y_point = e.getY();
        Node n = clickIsInANode(x_point, y_point);
        if (!onSimulation) {
            if (n != null) {
                if (getMainFrame().getMap().getSelectedNode() == null) {
                    getMainFrame().getMap().setSelectedNode(n);
                } else {
                    addEdge(getMainFrame().getMap().getSelectedNode(), n, TypeEdge.valueOf(getMainFrame().getTypeEdge().getSelectedItem().toString()));
                    getMainFrame().getMap().setSelectedNode(null);
                }
            } else {
                Node n2 = new Node(new Integer(x_point), new Integer(y_point), TypeNode.valueOf(getMainFrame().getTypeNode().getSelectedItem().toString()));
                getActions().add(n2);
                this.manager.getGraph().addNode(n2);
                getMainFrame().getMap().setSelectedNode(null);
            }
            getMainFrame().getMap().repaint();
        } else {
            if (n != null) {
                addRobot(n);
            }
        }
    }

    /**
     * Retourne le noeud correspondant au coordonnées du click de la souris.
     * Retourne null s'il n'y a pas de noeud correspondant à ses coordonnées.
     *
     * @param x_point
     * @param y_point
     * @return
     */
    public Node clickIsInANode(int x_point, int y_point) {
        for (Node n : this.manager.getGraph().getNodes()) {
            if (this.manager.getGraph().getDistance(n, x_point, y_point) <= MapPanel.RADIUS) {
                return n;
            }
        }
        return null;
    }

    private void saveGraph() {
        XmlUtilities.writeXmlFile(this.manager.getGraph());
    }

    private void importGraph() {
        Graph graphXml = XmlUtilities.readXmlFile();
        if (graphXml != null) {
            this.resetSimu();
            this.manager.setGraph(graphXml);
            getMainFrame().getMap().setGraph(this.manager.getGraph());
            getMainFrame().getMap().repaint();
        }
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
        this.onSimulation = true;
        mainFrame.getPanel1().setVisible(false);
        mainFrame.getPanel2().setVisible(true);
        mainFrame.paintAll();
        this.manager.setAlgo(new AlgoBreadthFirst());
        new Thread(this.manager).start();
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

    /**
     * Fonction qui ajoute une arrête au graph du manager. Si l'arrête existe
     * déjà ou a pour extrémités le même noeuds, on ne l'ajoute pas
     */
    private void addEdge(Node a, Node b, TypeEdge type) {
        //Test si le noeud d'entré est le même que le noeud fils
        if (!a.equals(b)) {
            int i = 0;
            boolean find = false;
            //test si l'arrête du graph existe déjà
            while (!find && i < manager.getGraph().getEdges().size()) {
                if (manager.getGraph().getEdges().get(i).getA().equals(a) && manager.getGraph().getEdges().get(i).getB().equals(b)) {
                    find = true;
                }
                i++;
            }
            if (!find) {
                Edge edge = new Edge(a, b, type);
                getActions().add(edge);
                this.manager.getGraph().addEdge(edge);
            }
        }
    }

    /**
     * Ajoute un robot ou un feu aléatoirement sur un noeud
     */
    private void addRandomRobot() {
        Robot r;
        Random rand = new Random();
        int randomNum = rand.nextInt(manager.getGraph().getNodes().size());
        Node n = manager.getGraph().getNodes().get(randomNum);
        addRobot(n);
    }

    /**
     * Ajoute un robot ou un incendie sur le noeud
     *
     * @param n
     */
    private void addRobot(Node n) {
        Robot r;
        switch (mainFrame.getGroupTypeRobot().getSelection().getActionCommand()) {
            case "FeetRobot":
                r = new FeetRobot();
                r.setCurrentNode(n);
                r.setName(mainFrame.getTextName().getText());
                mainFrame.getMap().addRobot(new ViewFeetRobot(r));
                manager.addRobot(r);
                break;
            case "OffRoadRobot":
                r = new OffRoadRobot();
                r.setCurrentNode(n);
                r.setName(mainFrame.getTextName().getText());
                mainFrame.getMap().addRobot(new ViewOffRoadRobot(r));
                manager.addRobot(r);
                break;
            case "TrackedRobot":
                r = new TrackedRobot();
                r.setCurrentNode(n);
                r.setName(mainFrame.getTextName().getText());
                mainFrame.getMap().addRobot(new ViewTrackedRobot(r));
                manager.addRobot(r);
                break;
            case "Fire":
                int intensityI = -1;
                String intensity = mainFrame.getTextIntensity().getText();
                try {
                    intensityI = Integer.parseInt(intensity);
                } catch (NumberFormatException e) {
                    intensityI = -1;
                }
                System.out.println(intensity);
                if ((intensityI < 1) || (intensityI > 20)) {
                    n.initFire();
                } else {
                    n.setType(TypeNode.INCENDIE);
                    n.setValueFire(intensityI);
                }
                break;
        }
        mainFrame.paintAll();
    }

    private void stopSimulation() {
        this.onSimulation = false;
        mainFrame.getPanel1().setVisible(true);
        mainFrame.getPanel2().setVisible(false);
        mainFrame.paintAll();
    }

    private void resetSimu() {
        stopSimulation();
        this.manager.reset();
        Node.previousId = 1;
        this.getActions().clear();
        this.mainFrame.getMap().getRobots().clear();
        mainFrame.repaint();
    }

}
