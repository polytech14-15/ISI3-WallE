package view.robot;

import java.awt.Graphics;
import model.robot.Robot;


public abstract class AbstractRobot {
    private Robot robot;
    
    public abstract void draw(Graphics g);

    /**
     * @return the robot
     */
    public Robot getRobot() {
        return robot;
    }

    /**
     * @param robot the robot to set
     */
    public void setRobot(Robot robot) {
        this.robot = robot;
    }
    
}
