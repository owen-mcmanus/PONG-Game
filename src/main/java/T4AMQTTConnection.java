import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * Establishes a connection to an MQTT server for the publisher and subscriber
 *
 * @version 1
 * @author  owen-mcmanus
 */
public class T4AMQTTConnection {
    private final String BROKER = "tcp://broker.hivemq.com:1883";

    public final String TOPIC_BALL = "CSC307-T4A-PONG-BALL";
    public final String TOPIC_PADDLE = "CSC307-T4A-PONG-PADDLE";
    public final String TOPIC_COLLISION = "CSC307-T4A-PONG-COLLISION";
    public final String TOPIC_SCORE = "CSC307-T4A-PONG-SCORE";
    public final String TOPIC_CHAT = "CSC307-T4A-PONG-CHAT";

    public final String CLIENT_ID;
    public MqttClient client;

    public T4AMQTTConnection(String clientID){
        this.CLIENT_ID = clientID;
        try {
            client = new MqttClient(BROKER, clientID);
            client.connect();

            System.out.println("Connected to BROKER: " + BROKER);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}
