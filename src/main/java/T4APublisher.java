import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Objects;

/**
 * Calls Sender threads that publish game data to MQTT server
 *
 * @version 1
 * @author  owen-mcmanus
 */
public class T4APublisher implements PropertyChangeListener {
    T4AMQTTConnection connection;
    public T4APublisher(T4AMQTTConnection connection){
        this.connection = connection;
    }

    public void broadcast() {
        T4ABlackboard blackboard = T4ABlackboard.getInstance();
        if(blackboard.getInControl()){
            String message = blackboard.getBallX() + "," + blackboard.getBallY() + "," + blackboard.getBallDX() + "," + blackboard.getBallDY();
            Thread t = new Thread(new T4ASender(connection, connection.TOPIC_BALL, message, 0));
            t.start();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        T4ABlackboard blackboard = T4ABlackboard.getInstance();

        if(Objects.equals(propertyChangeEvent.getPropertyName(), "paddle")){
            String message = String.valueOf(blackboard.getUserPaddleY());
            Thread t = new Thread(new T4ASender(connection, connection.TOPIC_PADDLE, message, 0));
            t.start();
        }

        if(Objects.equals(propertyChangeEvent.getPropertyName(), "collision")){
            String message = blackboard.getBallX() + "," + blackboard.getBallY() + "," + blackboard.getBallDX() + "," + blackboard.getBallDY();
            Thread t = new Thread(new T4ASender(connection, connection.TOPIC_COLLISION, message, 2));
            t.start();
        }

        if(Objects.equals(propertyChangeEvent.getPropertyName(), "opponentScore")){
            String message = Integer.toString(blackboard.getOpponentScore());
            Thread t = new Thread(new T4ASender(connection, connection.TOPIC_SCORE, message, 2));
            t.start();
        }

        if(Objects.equals(propertyChangeEvent.getPropertyName(), "chatSent")){
            List<String> chats = blackboard.getChats();
            if (chats.isEmpty()) return;

            String message = chats.get(chats.size() -1);
            Thread t = new Thread(new T4ASender(connection, connection.TOPIC_CHAT, message, 2));
            t.start();
        }

    }


}
