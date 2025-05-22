import java.awt.*;

public class T4Ball implements T4Component{
    private final T4Physics physics;

    public T4Ball() {
        this.physics = new T4Physics();
    }

    @Override
    public void update() {
        physics.updateBall();
    }

    @Override
    public void draw(Graphics g) {
        T4ABlackboard bb = T4ABlackboard.getInstance();
        g.fillOval(bb.getBallX(),bb.getBallY(), 10, 10);
    }
}
