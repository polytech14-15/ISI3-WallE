package view.robot;

import java.awt.Color;
import java.awt.Graphics;
import model.robot.Robot;

public class ViewTrackedRobot extends AbstractRobot {

    public ViewTrackedRobot(Robot r) {
        super(r);
    }

    @Override
    public void draw(Graphics g) {
        super.drawCross(g, Color.yellow);
    }

}
