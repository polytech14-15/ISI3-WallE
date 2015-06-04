package view.robot;

import java.awt.Color;
import java.awt.Graphics;
import model.robot.Robot;

public class ViewOffRoadRobot extends AbstractRobot {

    public ViewOffRoadRobot(Robot r) {
        super(r);
    }

    @Override
    public void draw(Graphics g) {
        super.drawRectangle(g, Color.WHITE);
    }

}
