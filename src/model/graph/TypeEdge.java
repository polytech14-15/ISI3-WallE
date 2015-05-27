package model.graph;

public enum TypeEdge {
    PLAT("PLAT"),
    ESCARPE("ESCARPE");
    
    private final String text;
    private TypeEdge(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
