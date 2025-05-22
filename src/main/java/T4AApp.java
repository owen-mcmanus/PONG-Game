import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class T4AApp extends JFrame {

    public T4AApp(String clientId){
        setTitle("Pong Game");
        setSize( 1000,800);
        setLayout(new BorderLayout());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        T4AField field = new T4AField();
        T4AChat chatArea = new T4AChat();
        T4APubSub pubSub = new T4APubSub(clientId);

        add(field, BorderLayout.CENTER);
        add(chatArea, BorderLayout.SOUTH);
        setVisible(true);

        T4ABlackboard.getInstance().addPropertyChangeListener(pubSub);

        Timer timer = new Timer(1000 / T4ABlackboard.FPS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                field.updateGame();
                field.repaint();
                pubSub.broadcast();
            }
        });
        timer.start();
    }


}