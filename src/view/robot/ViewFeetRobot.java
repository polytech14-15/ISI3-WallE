package view.robot;

import java.awt.Color;
import java.awt.Graphics;
import model.robot.Robot;

/**
 * Classe représantant la vue d'un FeetRobot.
 * @author De Sousa Jérémy
 */
public class ViewFeetRobot extends AbstractRobot {

    public ViewFeetRobot(Robot r) {
        super(r);
    }

    @Override
    public void draw(Graphics g) {
       super.drawTriangle(g, Color.green);
    }

}
