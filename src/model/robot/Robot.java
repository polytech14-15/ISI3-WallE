package model.robot;

import java.util.ArrayList;
import model.graph.*;

public abstract class Robot {

    protected String name;
    protected Node currentNode;
    protected int capacity;
    protected String state;
    public static final int SPEED = 12;

    public void move(ArrayList<Node> n){
        for(Node node : n){
            currentNode = node;
        }
    }

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
     * 
     * @param e
     * @return 
     */
    public abstract boolean canMove(Edge e);
}
