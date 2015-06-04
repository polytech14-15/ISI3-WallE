package view.robot;

import java.awt.Color;
import java.awt.Graphics;
import model.robot.Robot;

public class ViewFeetRobot extends AbstractRobot {

    public ViewFeetRobot(Robot r) {
        super(r);
    }

    @Override
    public void draw(Graphics g) {
       super.drawTriangle(g, Color.green);
    }

}
