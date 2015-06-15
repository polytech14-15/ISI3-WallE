package view.robot;

import java.awt.Color;
import java.awt.Graphics;
import model.robot.Robot;

/**
 * Classe représantant la vue d'un ViewOffRoad.
 * @author De Sousa Jérémy
 */
public class ViewOffRoadRobot extends AbstractRobot {

    public ViewOffRoadRobot(Robot r) {
        super(r);
    }

    @Override
    public void draw(Graphics g) {
        super.drawRectangle(g, Color.WHITE);
    }

}
