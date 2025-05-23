import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The T4App sets up main app  window for Pong game.
 * Sets up frame, field, and chat
 *
 * @author Uriel Hernandez
 * @version 1
 *
 */
public class T4AApp extends JFrame {

    public T4AApp(String clientId){
        setTitle("Pong Game");
        setLayout(new BorderLayout());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        T4AField field = new T4AField();
        T4AChat chatArea = new T4AChat();
        T4AMQTTConnection connection = new T4AMQTTConnection(clientId);
        T4APublisher pub = new T4APublisher(connection);
        T4ASubscriber sub = new T4ASubscriber(connection);

        add(field, BorderLayout.CENTER);
        add(chatArea, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        T4ABlackboard.getInstance().addPropertyChangeListener(pub);
        Timer timer = new Timer(1000 / T4ABlackboard.FPS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                field.updateGame();
                field.repaint();
                pub.broadcast();
            }
        });
        timer.start();
    }


}