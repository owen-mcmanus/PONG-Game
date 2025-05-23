/**
 * This class is used to draw the ball
 *
 * @author Michael Man
 * @version 1
 *
 */

import java.awt.*;

public class T4ABall implements T4AComponent {
    @Override
    public void draw(Graphics g) {
        T4ABlackboard bb = T4ABlackboard.getInstance();
        g.fillOval(bb.getBallX(),bb.getBallY(), bb.BALL_DIAMETER, bb.BALL_DIAMETER);
    }
}
