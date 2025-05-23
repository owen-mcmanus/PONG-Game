import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Receives game data from MQTT server
 *
 * @version 1
 * @author  owen-mcmanus
 */
public class T4ASubscriber implements MqttCallback {
    T4AMQTTConnection connection;
    public T4ASubscriber(T4AMQTTConnection connection){
        this.connection = connection;

        try {
            connection.client.setCallback(this);
            connection.client.subscribe(connection.TOPIC_BALL + "/#");
            connection.client.subscribe(connection.TOPIC_PADDLE + "/#");
            connection.client.subscribe(connection.TOPIC_COLLISION + "/#");
            connection.client.subscribe(connection.TOPIC_SCORE + "/#");
            connection.client.subscribe(connection.TOPIC_CHAT + "/#");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("Connection lost: " + throwable.getMessage());
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) {
        if (topic.endsWith("/" + connection.CLIENT_ID)) {
            return;
        }

        T4ABlackboard blackboard = T4ABlackboard.getInstance();
        if((!blackboard.getInControl() && topic.startsWith(connection.TOPIC_BALL)) || topic.startsWith(connection.TOPIC_COLLISION)){
            String[] parts = new String(mqttMessage.getPayload()).split(",");
            int ballX = Integer.parseInt(parts[0]);
            int ballY = Integer.parseInt(parts[1]);
            int ballDX = Integer.parseInt(parts[2]);
            int ballDY = Integer.parseInt(parts[3]);
            blackboard.setBallPosition(blackboard.FIELD_WIDTH - ballX, blackboard.FIELD_HEIGHT-ballY);
            blackboard.setBallDX(-ballDX);
            blackboard.setBallDY(-ballDY);
        }

        if(topic.startsWith(connection.TOPIC_COLLISION)){
            blackboard.takeControl();
        }

        if(topic.startsWith(connection.TOPIC_PADDLE)){
            blackboard.setOpponentPaddlePosition(blackboard.FIELD_HEIGHT - blackboard.PADDLE_HEIGHT -
                    Integer.parseInt(new String(mqttMessage.getPayload())));
        }

        if(topic.startsWith(connection.TOPIC_SCORE)){
            blackboard.setUserScore(Integer.parseInt(new String(mqttMessage.getPayload())));
        }

        if(topic.startsWith(connection.TOPIC_CHAT)){
            String chatMessage = connection.CLIENT_ID + ": " + new String(mqttMessage.getPayload());
            blackboard.receiveChat(chatMessage);
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }
}
