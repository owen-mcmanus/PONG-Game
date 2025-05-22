import java.awt.*;

public class T4AABall implements T4AComponent {

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics g) {
        T4ABlackboard bb = T4ABlackboard.getInstance();
        g.fillOval(bb.getBallX(),bb.getBallY(), bb.BALL_DIAMETER, bb.BALL_DIAMETER);
    }
}
