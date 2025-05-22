public class T4Physics {
    private final T4ABlackboard bb;
    private boolean moveUp = false;
    private boolean moveDown = false;


    public T4Physics() {
        this.bb = T4ABlackboard.getInstance();
    }

    public void updateBall() {
        bb.setBallX(bb.getBallX() + bb.getBallDX());
        bb.setBallY(bb.getBallY() + bb.getBallDY());

        if (bb.getBallY() <= 0 || bb.getBallY() >= bb.FIELD_HEIGHT) {
            bb.setBallDY(-bb.getBallDY());
        }

        // if there is a scores
        if (bb.getBallX() <= 0) {
            bb.incrementOpponentScore();
            bb.setBallStarting();
        } else if (bb.getBallX() >= bb.FIELD_WIDTH){
            bb.incrementUserScore();
            bb.setBallStarting();
        }
    }

    public void updatePaddle(){
        int y = bb.getUserPaddleY();

        if (moveUp) {
            y = Math.max(y - bb.PADDLE_SPEED, 0);
        } else if (moveDown) {
            y = Math.min(y + bb.PADDLE_SPEED, 500 - bb.PADDLE_HEIGHT);
        }

        bb.setUserPaddlePosition(y);
    }

    private void handlePaddleCollision() {
        int ballX = bb.getBallX();
        int ballY = bb.getBallY();

        // user paddle
        int paddleX = bb.getUserPaddleX();
        int paddleY = bb.getUserPaddleY();

        if (ballX <= paddleX + bb.PADDLE_WIDTH &&
                ballX >= paddleX &&
                ballY + bb.BALL_DIAMETER >= paddleY &&
                ballY <= paddleY + bb.PADDLE_HEIGHT) {
            bb.setBallDX(Math.abs(bb.getBallDX())); // bounce to the right
        }

        // opponent paddle
        paddleX = bb.getOpponentPaddleX();
        paddleY = bb.getOpponentPaddleY();

        if (ballX + bb.BALL_DIAMETER >= paddleX &&
                ballX <= paddleX + bb.PADDLE_WIDTH &&
                ballY + bb.BALL_DIAMETER >= paddleY &&
                ballY <= paddleY + bb.PADDLE_HEIGHT) {
            bb.setBallDX(-Math.abs(bb.getBallDX())); // bounce to the left
        }
    }

    public void setMoveUp(boolean move) { this.moveUp = move; }
    public void setMoveDown(boolean move) { this.moveDown = move; }

    public void update() {
        updateBall();
        updatePaddle();
        handlePaddleCollision();
    }

}
