package view.robot;

import java.awt.Color;
import java.awt.Graphics;
import model.robot.Robot;


public class ViewTrackedRobot extends AbstractRobot{

    public ViewTrackedRobot(Robot r) {
        super(r);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(super.getRobot().getCurrentNode().getX(), super.getRobot().getCurrentNode().getY(), 20, 20);
        g.setColor(Color.blue);
        g.fillOval(super.getRobot().getCurrentNode().getX(), super.getRobot().getCurrentNode().getY(), 15, 15);
        g.setColor(Color.yellow);
        g.fillOval(super.getRobot().getCurrentNode().getX(), super.getRobot().getCurrentNode().getY(), 6, 6);
        g.setColor(Color.pink);
        g.fillOval(super.getRobot().getCurrentNode().getX(), super.getRobot().getCurrentNode().getY(), 4, 4);
    }
    
}
