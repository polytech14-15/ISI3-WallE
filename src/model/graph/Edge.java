package model.graph;

public class Edge {

    private Node a, b;
    private String type;

    public Edge(Node a, Node b, String type) {
        this.a = a;
        this.b = b;
        this.type = type;
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

    public void setA(Node a) {
        this.a = a;
    }

    public void setB(Node b) {
        this.b = b;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        String info = "<edge nd1=\"" + this.a.getId() + "\" nd2=\"" + this.b.getId() + "\" type=\"" + this.type + "\" />";
        return info;
    }

}