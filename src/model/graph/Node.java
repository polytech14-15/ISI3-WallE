package graph;

public class Node {

    private Integer id;
    private Double x;
    private Double y;
    private String type;
    private boolean visited;
    private static Integer previousId = 1;

    public Node() {
    }

    public Node(Double x, Double y, String type) {
        this.id = previousId;
        this.x = x;
        this.y = y;
        this.type = type;
        this.visited = false;
        Node.previousId++;
    }

    public void visit() {
        visited = true;
    }

    public void unvisit() {
        visited = false;
    }

    public Integer getId() {
        return id;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public String getType() {
        return type;
    }

    public Integer getPreviousId() {
        return previousId;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        String info = "<node id=\"" + this.id + "\" x=\"" + this.x + "\" y=\"" + this.y + "\" type=\"" + this.type + "\" />";
        return info;
    }

}
