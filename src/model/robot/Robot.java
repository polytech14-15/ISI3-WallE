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

    public abstract boolean canMove(Edge e);
}
