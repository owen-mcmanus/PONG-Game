import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/*
 * Message chats between players.
 * Sending and Receiving chats
 *
 *
 * @author Uriel
 * @version 1
 */
public class T4AChat extends JPanel implements PropertyChangeListener {

    private JTextArea chat;

    public T4AChat(){
        setLayout(new BorderLayout());

        chat = new JTextArea();
        chat.setLineWrap(true);
        chat.setWrapStyleWord(true);
        chat.setFont(new Font("Arial", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(chat);
        add(scrollPane, BorderLayout.CENTER);

        T4ABlackboard blackBoard = T4ABlackboard.getInstance();
        blackBoard.addPropertyChangeListener(this);

        for (String messages: blackBoard.getChats()){
            chat.append(messages + "\n");
        }
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
            if ("chatSent".equals(evt.getPropertyName())){
                String newMessage = (String) evt.getNewValue();
                chat.append(newMessage + "\n");
                chat.setCaretPosition(chat.getDocument().getLength());
            }
    }
}
