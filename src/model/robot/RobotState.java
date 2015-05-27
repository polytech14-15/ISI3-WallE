package model.robot;

public enum RobotState {
    AVAILABLE("AVAILABLE"),
    ONWAY("ONWAY"),
    BUSY("BUSY");
    
    private final String text;
    private RobotState(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
