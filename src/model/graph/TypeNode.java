package model.graph;

public enum TypeNode {
    NORMAL("NORMAL"),
    INCENDIE("INCENDIE");
    
    private final String text;
    private TypeNode(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
