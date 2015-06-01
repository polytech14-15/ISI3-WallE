package view;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import model.graph.Graph;
import view.robot.AbstractRobot;

public class MapSimulation extends MapGraph {

    private List<AbstractRobot> robots;

    //private Feu
    public MapSimulation(Graph graph) {
        super(graph);
        robots = new ArrayList<AbstractRobot>();
    }

    public void addRobot(AbstractRobot robot) {
        if (getRobots() != null) {
            getRobots().add(robot);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        showRobots(g);
    }

    private void showRobots(Graphics g) {
        for(AbstractRobot robot : getRobots()){
            if(super.getGraph().getNodes().contains(robot.getRobot().getCurrentNode())){
                robot.draw(g);
            }            
        }
    }

    /**
     * @return the robots
     */
    public List<AbstractRobot> getRobots() {
        return robots;
    }

    /**
     * @param robots the robots to set
     */
    public void setRobots(List<AbstractRobot> robots) {
        this.robots = robots;
    }

}
