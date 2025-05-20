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
        game.setVisible(true);

        T4AField field = new T4AField();

    }
}