package view.robot;

import java.awt.Color;
import java.awt.Graphics;
import model.robot.Robot;

/**
 * Classe représantant la vue d'un ViewTracked.
 * @author De Sousa Jérémy
 */
public class ViewTrackedRobot extends AbstractRobot {

    public ViewTrackedRobot(Robot r) {
        super(r);
    }

    @Override
    public void draw(Graphics g) {
        super.drawCross(g, Color.yellow);
    }

}
