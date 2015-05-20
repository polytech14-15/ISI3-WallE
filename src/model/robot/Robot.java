package model.robot;

import java.util.ArrayList;
import model.graph.*;

public abstract class Robot {

    protected String name;
    protected Node currentNode;
    protected int capacity;
    protected State state;
    public static final int SPEED = 12;

    public void move(ArrayList<Node> n){
        for(Node node : n){
            currentNode = node;
        }
    }

    public abstract boolean canMove(Edge e);
}
