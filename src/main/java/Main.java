import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main(){
        setTitle("Pong Game");
        setSize( 1000,800);
        setLayout(new BorderLayout());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        Main game = new Main();
        T4AField field = new T4AField();
        T4AChat chatArea = new T4AChat();
        game.add(field, BorderLayout.CENTER);
        game.add(chatArea, BorderLayout.EAST);
        game.setVisible(true);

    }
}