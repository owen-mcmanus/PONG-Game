import java.awt.*;

public class T4APaddle implements T4AComponent
{

    private final boolean isUser;
    private final int x;

    public T4APaddle(boolean isUser) {
        T4ABlackboard bb = T4ABlackboard.getInstance();
        this.isUser = isUser;
        this.x = isUser ? bb.getUserPaddleX() : bb.getOpponentPaddleX(); // setting x based on user or not
    }

    @Override
    public void draw(Graphics g) {
        T4ABlackboard bb = T4ABlackboard.getInstance();
        int y = isUser ? bb.getUserPaddleY() : bb.getOpponentPaddleY();
        g.fillRect(x, y, bb.PADDLE_WIDTH, bb.PADDLE_HEIGHT);
    }
}
