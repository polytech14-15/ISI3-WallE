package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import model.algo.AlgoBreadthFirst;
import model.algo.AlgoDepthFirst;
import model.algo.IAlgo;
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

/**
 * Cette classe est le controlleur du projet. C'est elle qui va gérer les actions de l'utilisateur et controller l'évolution du programme. 
 * On y retrouve la fenêtre principale d'éxecution, le manager et la liste des actions effectuées. 
 * Un boolean onSimulation permet connaitre l'état de la simulation.
 * @author De Sousa Jérémy
 */
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

    /**
     * Fonction qui gère les actions de l'utilisateur, notamment les cliques sur les boutons de la fenêtre principale.
     * @param e : ActionEvent
     */
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

    /**
     * Fonction qui gère les cliques de l'utilisateur sur la map de simulation. Selectionne le noeud si l'utilisateur clique sur un noeud
     *  ou créer un nouveau noeud sinon. Si un noeud est déjà selectionné, le clique sur un autre noeud créer une arrête sinon la fonction créer un nouveau noeud.
     * Si la simulation est lancée, le clique sur les noeuds entraîne la création du robot désiré sur ce noeud.
     * @param e : Mouse Event
     */
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
     * @return le noeud selectionné ou null
     */
    public Node clickIsInANode(int x_point, int y_point) {
        for (Node n : this.manager.getGraph().getNodes()) {
            if (this.manager.getGraph().getDistance(n, x_point, y_point) <= MapPanel.RADIUS) {
                return n;
            }
        }
        return null;
    }

    /**
     * Appel la fenêtre de dialogue pour enregistrer le graph dessiné au format XML.
     */
    private void saveGraph() {
        XmlUtilities.writeXmlFile(this.manager.getGraph());
    }

    /**
     * Ouvre la fenêtre de dialogue pour importer un graph au format XML.Retourne une erreur si le fichier n'est pas valide.
     */
    private void importGraph() {
        Graph graphXml = XmlUtilities.readXmlFile();
        if (graphXml != null) {
            this.resetSimu();
            this.manager.setGraph(graphXml);
            getMainFrame().getMap().setGraph(this.manager.getGraph());
            getMainFrame().getMap().repaint();
        }
    }

    /**
     * Annule la dernière action réalisée par l'utilisateur lors de la création du graph (ajout d'un noeud ou d'une arrête). 
     */
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

    /**
     * Lance la simulation : instancie l'algorithme choisi et lance le thread du manager pour déplacer les robots.
     */
    private void startSimulation() {
        IAlgo algo;
        switch(this.mainFrame.getComboAlgo().getSelectedItem().toString()){
            case "BREADTHFIRST" :
                algo = new AlgoBreadthFirst();
                break;
            case "DEPTHFIRST":
                algo = new AlgoDepthFirst();
                break;
            default :
                algo = new AlgoBreadthFirst();
                break;
        }
        this.onSimulation = true;
        mainFrame.activateSimulation();
        this.manager.setAlgo(algo);
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
     * déjà ou a pour extrémités le même noeuds, on ne l'ajoute pas.
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
     * Ajoute un robot ou un feu aléatoirement sur un noeud.
     */
    private void addRandomRobot() {
        Robot r;
        Random rand = new Random();
        int randomNum = rand.nextInt(manager.getGraph().getNodes().size());
        Node n = manager.getGraph().getNodes().get(randomNum);
        addRobot(n);
    }

    /**
     * Ajoute un robot ou un incendie sur le noeud.
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

    /**
     * Met en pause la simulation.
     */
    private void stopSimulation() {
        this.onSimulation = false;
        mainFrame.desactivateSimulation();
        mainFrame.paintAll();
    }

    /**
     * Réinitialise la simulation.
     */
    private void resetSimu() {
        stopSimulation();
        this.manager.reset();
        Node.previousId = 1;
        this.getActions().clear();
        this.mainFrame.getMap().getRobots().clear();
        mainFrame.repaint();
    }

}
