import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Thread that sends data to MQTT server
 *
 * @version 1
 * @author  owen-mcmanus
 */
class T4ASender implements Runnable {
    private final T4AMQTTConnection connection;
    private final String topic;
    private final String message;
    private final int qos;

    public T4ASender(T4AMQTTConnection connection, String topic, String message, int qos){
        this.connection = connection;
        this.topic = topic;
        this.message = message;
        this.qos = qos;
    }

    @Override
    public void run(){
        try{
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            mqttMessage.setQos(qos);

            if(connection.client.isConnected()) connection.client.publish(topic + "/" + connection.CLIENT_ID, mqttMessage);
            else System.out.println("did not connect");
        }catch (MqttException e){
            System.out.println("error");
            e.printStackTrace();
        }
    }
}