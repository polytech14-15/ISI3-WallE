/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import model.graph.Edge;
import model.graph.Node;
import model.graph.TypeEdge;
import model.graph.TypeNode;
import model.robot.FeetRobot;
import model.robot.OffRoadRobot;
import model.robot.TrackedRobot;
import view.robot.AbstractRobot;
import view.robot.ViewFeetRobot;
import view.robot.ViewOffRoadRobot;
import view.robot.ViewTrackedRobot;

/**
 *
 * @author Jérémy
 */
public class InstructionsPanel extends MapPanel {

    public InstructionsPanel() {
        super();
        int previousId = Node.previousId;
        Node.previousId = 1;
        this.setPreferredSize(new Dimension(350, 350));

        Node normalNode = new Node(20, 20, TypeNode.NORMAL);
        this.graph.addNode(normalNode);
        Node incendieNode = new Node(20, 60, TypeNode.INCENDIE);
        this.graph.addNode(incendieNode);

        Node n1 = new Node(20, 100, TypeNode.NORMAL);
        this.graph.addNode(n1);
        Node n2 = new Node(80, 100, TypeNode.NORMAL);
        this.graph.addNode(n2);
        Edge platEdge = new Edge(n1, n2, TypeEdge.PLAT);
        this.graph.addEdge(platEdge);

        Node n3 = new Node(20, 140, TypeNode.NORMAL);
        this.graph.addNode(n3);
        Node n4 = new Node(80, 140, TypeNode.NORMAL);
        this.graph.addNode(n4);

        Edge escarpeEdge = new Edge(n3, n4, TypeEdge.ESCARPE);
        this.graph.addEdge(escarpeEdge);

        Node n5 = new Node(20, 180, TypeNode.NORMAL);
        this.graph.addNode(n5);

        Node n6 = new Node(80, 180, TypeNode.NORMAL);
        this.graph.addNode(n6);

        Edge inondeEdge = new Edge(n5, n6, TypeEdge.PLAT);
        inondeEdge.setFlooded(true);
        this.graph.addEdge(inondeEdge);

        Node n7 = new Node(20, 220, TypeNode.NORMAL);
        this.graph.addNode(n7);
        AbstractRobot r1 = new ViewFeetRobot(new FeetRobot(n7));
        Node n8 = new Node(20, 260, TypeNode.NORMAL);
        this.graph.addNode(n8);
        AbstractRobot r2 = new ViewOffRoadRobot(new OffRoadRobot(n8));
        Node n9 = new Node(20, 300, TypeNode.NORMAL);
        this.graph.addNode(n9);
        AbstractRobot r3 = new ViewTrackedRobot(new TrackedRobot(n9));
        super.addRobot(r1);
        super.addRobot(r2);
        super.addRobot(r3);

        super.selectedNode = new Node(20, 340, TypeNode.NORMAL);
        
        Node.previousId = previousId;

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawText(g);
    }

    private void drawText(Graphics g) {
        int i = 0;
        g.setColor(Color.black);
        for (Node n : graph.getNodes()) {
            String texte = "";
            switch (i) {
                case 0:
                    texte = "Normal Node";
                    break;
                case 1:
                    texte = "Fire Node";
                    break;
                case 2:
                    texte = "Plat Edge";
                    break;
                case 4:
                    texte = "Escarpe Edge";
                    break;
                case 6:
                    texte = "Inonde Edge";
                    break;
                case 8:
                    texte = "Feet Robot";
                    break;
                case 9:
                    texte = "Off-Road Robot";
                    break;
                case 10:
                    texte = "TrackedRobot";
                    break;
            }
            g.drawString(texte, n.getX() + 80, n.getY() + 5);
            i++;
        }
        g.drawString("Selected node", selectedNode.getX() + 80, selectedNode.getY() + 5);
    }

}
