import java.awt.*;

public class T4Paddle implements T4Component
{

    private final boolean isUser;
    private final int x;

    public T4Paddle(boolean isUser) {
        T4ABlackboard bb = T4ABlackboard.getInstance();
        this.isUser = isUser;
        this.x = isUser ? bb.getUserPaddleX() : bb.getOpponentPaddleX(); // setting x based on user or not
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics g) {
        T4ABlackboard bb = T4ABlackboard.getInstance();
        int y = isUser ? bb.getUserPaddleY() : bb.getOpponentPaddleY();
        g.fillRect(x, y, bb.PADDLE_WIDTH, bb.PADDLE_HEIGHT);
    }
}
