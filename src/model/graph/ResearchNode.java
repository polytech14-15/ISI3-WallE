package model.graph;

public class ResearchNode {
    // This is used in the algo functions.
    // A research node is the copy of a node, containging its parent and distance from the start.
    // We can use it ONLY when going through a graph and searching for the shortest path.
    
    private ResearchNode parent;
    private Node associated;
    private int value;
    
    public ResearchNode(Node associated, ResearchNode parent, int value){
        this.associated = associated;
        this.parent = parent;
        this.value = value;
    }
    
    public void setValue(int value){
        this.value = value;
    }
    
    public void setParent(ResearchNode parent){
        this.parent = parent;
    }

    public ResearchNode getParent() {
        return parent;
    }

    public int getValue() {
        return value;
    }
    
    public Node getAssociated() {
        return associated;
    }
    
    public static ResearchNode search(Node n, ResearchNode[] list){
        // this method permits to search for a ResearchNode in a list knowing its corresponding Node
        for (ResearchNode rn : list){
            if (rn.getAssociated().equals(n)){
                return rn;
            }
        }
        return null;
    }
    
    
}
