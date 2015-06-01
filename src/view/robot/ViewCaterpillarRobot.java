package view.robot;

import java.awt.Color;
import java.awt.Graphics;


public class ViewCaterpillarRobot extends AbstractRobot{

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.drawOval(super.getRobot().getCurrentNode().getX().intValue(), super.getRobot().getCurrentNode().getY().intValue(), 10, 10);
        g.setColor(Color.blue);
        g.drawOval(super.getRobot().getCurrentNode().getX().intValue(), super.getRobot().getCurrentNode().getY().intValue(), 8, 8);
        g.setColor(Color.yellow);
        g.drawOval(super.getRobot().getCurrentNode().getX().intValue(), super.getRobot().getCurrentNode().getY().intValue(), 6, 6);
        g.setColor(Color.pink);
        g.drawOval(super.getRobot().getCurrentNode().getX().intValue(), super.getRobot().getCurrentNode().getY().intValue(), 4, 4);
    }
    
}
