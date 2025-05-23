import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionListener;

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
    private JTextField chatInput;
    private JButton sendButton;

    public T4AChat(){
        setLayout(new BorderLayout());

        chat = new JTextArea();
        chat.setLineWrap(true);
        chat.setWrapStyleWord(true);
        chat.setEditable(false);
        chat.setFont(new Font("Arial", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(chat);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        chatInput = new JTextField();
        sendButton = new JButton("Send");

        inputPanel.add(chatInput, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        T4ABlackboard blackBoard = T4ABlackboard.getInstance();
        blackBoard.addPropertyChangeListener(this);

        for (String messages: blackBoard.getChats()){
            chat.append(messages + "\n");
        }

        ActionListener sendAction = e -> {
            String message = chatInput.getText().trim();
            if (!message.isEmpty()) {
                blackBoard.sendChatMessage(message);
                chatInput.setText("");
            }
        };
        sendButton.addActionListener(sendAction);
        chatInput.addActionListener(sendAction);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
            if ("chatSent".equals(evt.getPropertyName()) || "chatHist".equals(evt.getPropertyName())){
                String newMessage = (String) evt.getNewValue();
                SwingUtilities.invokeLater(() -> {
                    chat.append(newMessage + "\n");
                    chat.setCaretPosition(chat.getDocument().getLength()); });
            }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(T4ABlackboard.getInstance().FIELD_WIDTH, 150); // or whatever height fits best
    }
}
