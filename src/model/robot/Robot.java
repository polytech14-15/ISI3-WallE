package model.robot;

import model.graph.*;

public abstract class Robot {
    
    public static final int SPEED = 12;

    protected String name;
    protected Node currentNode;
    protected int capacity;
    protected String state;           

    public String getName() {
        return name;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getState() {
        return state;
    }

    public static int getSPEED() {
        return SPEED;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    /**
     * Initialise le thread qui sert a deplacer le robot
     * @param graph - La route que doit emprunter le robot
     */
    public void move(Graph graph){
        this.state = RobotState.ONWAY.toString();
        new Thread(new RobotThread(this, graph)).start();
    }

    /**
     * Decremente la valeur du feu selon la capacite du robot
     */
    public void extinguishFire(){
        int valueFire = this.currentNode.getValueFire();
        valueFire -= this.capacity;
        // Si le feu est eteint
        if (valueFire <= 0){
            this.currentNode.setType(TypeNode.NORMAL.toString());
            this.setState(RobotState.AVAILABLE.toString());
        }
        this.currentNode.setValueFire(valueFire);
    }
    
    /**
     * Verifie si le robot peut se deplacer sur le edge
     * @param e - Edge
     * @return true s'il peut, false sinon
     */
    public abstract boolean canMove(Edge e);
}
