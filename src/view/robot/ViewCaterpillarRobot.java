package view.robot;

import java.awt.Color;
import java.awt.Graphics;


public class ViewCaterpillarRobot extends AbstractRobot{

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.drawOval(super.getRobot().getCurrentNode().getX(), super.getRobot().getCurrentNode().getY(), 10, 10);
        g.setColor(Color.blue);
        g.drawOval(super.getRobot().getCurrentNode().getX(), super.getRobot().getCurrentNode().getY(), 8, 8);
        g.setColor(Color.yellow);
        g.drawOval(super.getRobot().getCurrentNode().getX(), super.getRobot().getCurrentNode().getY(), 6, 6);
        g.setColor(Color.pink);
        g.drawOval(super.getRobot().getCurrentNode().getX(), super.getRobot().getCurrentNode().getY(), 4, 4);
    }
    
}
