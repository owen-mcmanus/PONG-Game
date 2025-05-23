import java.awt.*;

/**
 * Dots decoration that appears when a certain number of points are reached
 *
 * @version 1
 * @author  owen-mcmanus
 */
public class T4ADots extends T4AComposite{
    @Override
    public void draw(Graphics g){
        T4ABlackboard blackboard = T4ABlackboard.getInstance();
        int y1 = blackboard.FIELD_HEIGHT / 4;
        int y2 = blackboard.FIELD_HEIGHT - blackboard.FIELD_HEIGHT / 4;

        g.setColor(Color.BLUE);
        g.fillOval(50, y1, 10, 10);
        g.fillOval(100, y1, 10, 10);
        g.fillOval(150, y1, 10, 10);
        g.fillOval(50, y2, 10, 10);
        g.fillOval(100, y2, 10, 10);
        g.fillOval(150, y2, 10, 10);

        super.draw(g);
    }
}
