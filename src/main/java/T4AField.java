import javax.swing.*;
import java.awt.*;
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

    public T4AField(){
        setBackground(new Color(178, 255,100));
        setLayout(new BorderLayout());

        T4ABlackboard blackBoard = T4ABlackboard.getInstance();
        blackBoard.addPropertyChangeListener(this);

        userScore = new JLabel("Score: " + blackBoard.getUserScore());
        userScore.setFont(new Font("Arial", Font.BOLD, 22));
        opponentScore= new JLabel("Score: "+ blackBoard.getOpponentScore());
        opponentScore.setFont(new Font("Arial", Font.BOLD, 22));


        add(userScore, BorderLayout.NORTH);
        add(opponentScore,BorderLayout.SOUTH);

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
}
