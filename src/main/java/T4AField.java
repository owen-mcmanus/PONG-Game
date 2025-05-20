import javax.swing.*;
import java.awt.*;


public class T4AField extends JPanel{
    private int User_Score;
    private int Opponent_Score;

    public T4AField(){
        JPanel field = new JPanel();
        field.setBackground(new Color(178, 255,100));
        field.setVisible(true);

        JTextArea UserScore = new JTextArea("SCORE: ");

        JTextArea OpponentScore = new JTextArea("SCORE: ");
    }


}
