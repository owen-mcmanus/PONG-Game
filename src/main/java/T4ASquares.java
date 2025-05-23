import java.awt.*;

/**
 * Square decoration that appears when a certain number of points are reached
 *
 * @version 1
 * @author  owen-mcmanus
 */
public class T4ASquares extends T4AComposite{
    @Override
    public void draw(Graphics g){
        T4ABlackboard blackboard = T4ABlackboard.getInstance();
        int y = blackboard.FIELD_HEIGHT / 2;

        g.setColor(Color.BLUE);
        g.fillRect(50, y, 10, 10);
        g.fillRect(100, y, 10, 10);
        g.fillRect(150, y, 10, 10);

        super.draw(g);
    }
}
