import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private final T4AComponent ball;
    private final T4APaddle userPaddle = new T4APaddle(true);
    private final T4APaddle opponentPaddle = new T4APaddle(false);
    private final T4APhysics physics = new T4APhysics();

    public T4AField(){
        setBackground(new Color(178, 255,100));
        setLayout(new BorderLayout());
        setupControls();

        T4ABlackboard blackBoard = T4ABlackboard.getInstance();
        blackBoard.addPropertyChangeListener(this);

        userScore = new JLabel("Your Score: " + blackBoard.getUserScore());
        userScore.setFont(new Font("Arial", Font.BOLD, 22));
        opponentScore= new JLabel("Opponent Score: "+ blackBoard.getOpponentScore());
        opponentScore.setFont(new Font("Arial", Font.BOLD, 22));


        add(userScore, BorderLayout.NORTH);
        add(opponentScore,BorderLayout.SOUTH);

        this.ball = new T4ABall();

        setFocusable(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch(evt.getPropertyName()){
            case "userScore":
                userScore.setText("Your Score: " + evt.getNewValue());
                break;
            case "opponentScore":
                opponentScore.setText("Opponent Score: " + evt.getNewValue());
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
        Toolkit.getDefaultToolkit().sync();
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

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(T4ABlackboard.getInstance().FIELD_WIDTH, T4ABlackboard.getInstance().FIELD_HEIGHT);
    }
}
