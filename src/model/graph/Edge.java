package model.graph;

public class Edge {

    private Node a, b;
    private String type;
    private boolean flooded;
    private double value;

    public Edge(Node a, Node b, String type) {
        this.a = a;
        this.b = b;
        this.type = type;
        this.flooded = false;
        this.value = Math.sqrt(Math.pow((a.getX() - a.getY()), 2) + Math.pow((b.getX() - b.getY()), 2));
    }

    public Node getA() {
        return a;
    }

    public Node getB() {
        return b;
    }

    public String getType() {
        return type;
    }

    public boolean isFlooded() {
        return flooded;
    }

    public double getValue() {
        return value;
    }

    public void setA(Node a) {
        this.a = a;
        this.value = Math.sqrt(Math.pow((a.getX() - a.getY()), 2) + Math.pow((b.getX() - b.getY()), 2));
    }

    public void setB(Node b) {
        this.b = b;
        this.value = Math.sqrt(Math.pow((a.getX() - a.getY()), 2) + Math.pow((b.getX() - b.getY()), 2));
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFlooded(boolean flooded) {
        this.flooded = flooded;
    }

    @Override
    public String toString() {
        String info = "<edge nd1=\"" + this.a.getId() + "\" nd2=\"" + this.b.getId() + "\" type=\"" + this.type + "\" />";
        return info;
    }

}
