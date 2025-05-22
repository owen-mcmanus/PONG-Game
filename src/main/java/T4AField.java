import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/*
    * Main field and score for PONG game.
    * Listens for score changes and updates
    *
    *
    * @author Uriel
    * @version 1
 */

public class T4AField extends JPanel implements PropertyChangeListener {
    private JLabel userScore;
    private JLabel opponentScore;
    private final T4Component ball;
    private final T4Paddle userPaddle = new T4Paddle(true);
    private final T4Paddle opponentPaddle = new T4Paddle(false);
    private final T4Physics physics = new T4Physics();

    public T4AField(){
        setBackground(new Color(178, 255,100));
        setLayout(new BorderLayout());
        setupControls();

        T4ABlackboard blackBoard = T4ABlackboard.getInstance();
        blackBoard.addPropertyChangeListener(this);

        userScore = new JLabel("Score: " + blackBoard.getUserScore());
        userScore.setFont(new Font("Arial", Font.BOLD, 22));
        opponentScore= new JLabel("Score: "+ blackBoard.getOpponentScore());
        opponentScore.setFont(new Font("Arial", Font.BOLD, 22));


        add(userScore, BorderLayout.NORTH);
        add(opponentScore,BorderLayout.SOUTH);

        this.ball = new T4Ball();


    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch(evt.getPropertyName()){
            case "userScore":
                userScore.setText("Score: " + evt.getNewValue());
                break;
            case "opponentScore":
                opponentScore.setText("Score: " + evt.getNewValue());
                break;
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.RED);
        ball.draw(g);
        userPaddle.draw(g);
        opponentPaddle.draw(g);
    }

    public void updateGame() {
        physics.update();
    }

    private void setupControls() {
        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    physics.setMoveUp(true);
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    physics.setMoveDown(true);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    physics.setMoveUp(false);
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    physics.setMoveDown(false);
                }
            }
        });
    }
}
