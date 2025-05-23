import java.awt.*;

/**
 * Text decoration that appears when a certain number of points are reached
 *
 * @version 1
 * @author  owen-mcmanus
 */
public class T4ACelebrationText extends T4AComposite{
    @Override
    public void draw(Graphics g){
        g.drawString("Good Job!", 50, 50);

        super.draw(g);
    }
}
