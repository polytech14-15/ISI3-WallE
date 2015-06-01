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
        g.setColor(Color.red);
        g.fillOval(super.getRobot().getCurrentNode().getX(), super.getRobot().getCurrentNode().getY(), 10, 10);
        g.setColor(Color.blue);
        g.fillOval(super.getRobot().getCurrentNode().getX(), super.getRobot().getCurrentNode().getY(), 8, 8);
        g.setColor(Color.yellow);
        g.fillOval(super.getRobot().getCurrentNode().getX(), super.getRobot().getCurrentNode().getY(), 6, 6);
        g.setColor(Color.pink);
        g.fillOval(super.getRobot().getCurrentNode().getX(), super.getRobot().getCurrentNode().getY(), 4, 4);
    }

}
