package model.graph;

public class Node {

    private Integer id;
    private Double x;
    private Double y;
    private TypeNode type;
    private boolean visited;
    private Integer valueFire;
    private static Integer previousId = 1;
    private static final Integer INIT_VALUE_FIRE = 15;

    public Node() {
    }

    public Node(Double x, Double y, TypeNode type) {
        this.id = Node.previousId;
        this.x = x;
        this.y = y;
        this.type = type;
        this.visited = false;
        this.valueFire = this.type.equals(TypeNode.INCENDIE) ? INIT_VALUE_FIRE : 0;
        Node.previousId++;
    }

    /**
     * Allume le feu
     */
    public void initFire() {
        this.valueFire = INIT_VALUE_FIRE;
    }

    /**
     * Eteind le feu
     */
    public void extinctFire() {
        this.valueFire = 0;
    }

    /**
     * Visite le noeud
     */
    public void visit() {
        visited = true;
    }

    /**
     * Annule la visite du noeud
     */
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

    public TypeNode getType() {
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

    public void setType(TypeNode type) {
        this.type = type;
    }
    
    public Integer getValueFire() {
        return this.valueFire;
    }

    public void setValueFire(Integer valueFire) {
        this.valueFire = valueFire;
    }

    @Override
    public String toString() {
        String info = "<node id=\"" + this.id + "\" x=\"" + this.x + "\" y=\"" + this.y + "\" type=\"" + this.type + "\" />";
        return info;
    }

    public boolean equals(Node n) {
        return this.id == n.getId();
    }

}
