package model.graph;

/**
 * This is used in the algo functions.
 * A research node is the copy of a node, containging its parent and distance from the start.
 * We can use it ONLY when going through a graph and searching for the shortest path.
 * 
 * @author Quentin Degrange
 */
public class ResearchNode {

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
    
    /**
     * this method permits to search for a ResearchNode in a list, knowing its corresponding Node
     * @param n The Node to search
     * @param list a list of ResearchNode
     * @return the corresponding research node
     */
    public static ResearchNode search(Node n, ResearchNode[] list){
        for (ResearchNode rn : list){
            if (rn.getAssociated().equals(n)){
                return rn;
            }
        }
        return null;
    }
    
    
}
