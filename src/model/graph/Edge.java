package model.graph;

public class Edge {

    private Node a, b;
    private TypeEdge type;
    private boolean flooded;
    private int value;

    public Edge(Node a, Node b, TypeEdge type) {
        this.a = a;
        this.b = b;
        this.type = type;
        this.flooded = false;
        this.updateValue();
    }

    public Node getA() {
        return a;
    }

    public Node getB() {
        return b;
    }

    public TypeEdge getType() {
        return type;
    }

    public boolean isFlooded() {
        return flooded;
    }

    public int getValue() {
        return value;
    }

    public void setA(Node a) {
        this.a = a;
        this.updateValue();
    }

    public void setB(Node b) {
        this.b = b;
        this.updateValue();
    }

    public void setType(TypeEdge type) {
        this.type = type;
    }

    public void setFlooded(boolean flooded) {
        this.flooded = flooded;
    }
    
    /**
     * Met a jour la valeur de l'edge
     */
    private void updateValue(){
        this.value = (int) Math.sqrt(Math.pow((double)(b.getX() - a.getX()), 2) + Math.pow((double)(b.getY() - a.getY()), 2));
    }

    @Override
    public String toString() {
        String info = "<edge nd1=\"" + this.a.getId() + "\" nd2=\"" + this.b.getId() + "\" type=\"" + this.type + "\" />";
        return info;
    }

}
