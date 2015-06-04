package view.robot;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import model.robot.Robot;
import view.MapPanel;

public abstract class AbstractRobot {

    private Robot robot;

    public AbstractRobot(Robot r) {
        this.robot = r;
    }

    public abstract void draw(Graphics g);

    /**
     * @return the robot
     */
    public Robot getRobot() {
        return robot;
    }

    /**
     * @param robot the robot to set
     */
    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    /**
     * Dessine un traingle à coté du robot
     * @param g
     * @param c 
     */
    public void drawTriangle(Graphics g, Color c) {
        int xpoints[] = {robot.getCurrentNode().getX(), robot.getCurrentNode().getX() - MapPanel.RADIUS / 2, robot.getCurrentNode().getX() + MapPanel.RADIUS / 2};
        int ypoints[] = {robot.getCurrentNode().getY() + MapPanel.RADIUS, robot.getCurrentNode().getY() + 2*MapPanel.RADIUS , robot.getCurrentNode().getY() + 2* MapPanel.RADIUS};
        int npoints = 3;
        g.setColor(c);
        g.fillPolygon(xpoints, ypoints, npoints);
    }

    /**
     * Dessine un rectangle à coté du robot
     * @param g
     * @param c 
     */
    public void drawRectangle(Graphics g, Color c) {
        g.setColor(c);
        g.fillRect(robot.getCurrentNode().getX() - MapPanel.RADIUS*2, robot.getCurrentNode().getY() - MapPanel.RADIUS*2, MapPanel.RADIUS, MapPanel.RADIUS);
    }
    
    /**
     * Dessine une croix à coté du robot
     * @param g
     * @param c 
     */
    public void drawCross(Graphics g, Color c) {        
        g.setColor(c);
        g.setFont(new Font("TimesRoman", Font.PLAIN, MapPanel.RADIUS*2)); 
        g.drawString("+", robot.getCurrentNode().getX()+MapPanel.RADIUS, robot.getCurrentNode().getY()+MapPanel.RADIUS);
    }

}
