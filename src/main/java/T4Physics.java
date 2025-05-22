public class T4Physics {
    private final T4ABlackboard bb;

    public T4Physics() {
        this.bb = T4ABlackboard.getInstance();
    }

    public void updateBall() {
        bb.setBallX(bb.getBallX() + bb.getBallDX());
        bb.setBallY(bb.getBallY() + bb.getBallDY());

        if (bb.getBallY() <= 0 || bb.getBallY() >= bb.FIELD_HEIGHT) {
            bb.setBallDY(-bb.getBallDY());
        }

        if (bb.getBallX() <= 0 || bb.getBallX() >= bb.FIELD_WIDTH) {
            bb.setBallDX(-bb.getBallDX());
        }
    }
}
