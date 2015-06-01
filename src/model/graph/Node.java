package model.graph;

public class Node {

    private Integer id;
    private int x;
    private int y;
    private TypeNode type;
    private Integer valueFire;
    public static Integer previousId = 1;
    private static final Integer INIT_VALUE_FIRE = 15;

    public Node(int x, int y, TypeNode type) {
        this.id = Node.previousId;
        this.x = x;
        this.y = y;
        this.type = type;
        this.valueFire = this.type.equals(TypeNode.INCENDIE) ? INIT_VALUE_FIRE : 0;
        Node.previousId++;
    }

    public Integer getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public TypeNode getType() {
        return type;
    }

    public Integer getPreviousId() {
        return previousId;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
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
    
    @Override
    public String toString() {
        String info = "<node id=\"" + this.id + "\" x=\"" + this.x + "\" y=\"" + this.y + "\" type=\"" + this.type + "\" />";
        return info;
    }

    public boolean equals(Node n) {
        return this.id == n.getId();
    }

}
