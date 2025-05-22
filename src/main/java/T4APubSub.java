import org.eclipse.paho.client.mqttv3.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

/**
 * MQTT
 *
 * @version 1
 * @author  owen-mcmanus
 */
public class T4APubSub implements MqttCallback, PropertyChangeListener {
    private final static String BROKER = "tcp://broker.hivemq.com:1883";
    private final static String TOPIC_BALL = "CSC307-T4A-PONG-BALL";
    private final static String TOPIC_PADDLE = "CSC307-T4A-PONG-PADDLE";
    private final static String TOPIC_COLLISION = "CSC307-T4A-PONG-COLLISION";
    private final static String TOPIC_SCORE = "CSC307-T4A-PONG-SCORE";
    private String clientID;
    private MqttClient client;

    public T4APubSub(String id) {
        try {
            clientID = id;
            client = new MqttClient(BROKER, clientID);
            client.setCallback(this);
            client.connect();

            client.subscribe(TOPIC_BALL + "/#");
            client.subscribe(TOPIC_PADDLE + "/#");
            client.subscribe(TOPIC_COLLISION + "/#");
            client.subscribe(TOPIC_SCORE + "/#");
            System.out.println("Connected to BROKER: " + BROKER);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    public void broadcast() {
        T4ABlackboard repository = T4ABlackboard.getInstance();
        if(repository.getInControl()){
            Thread t = new Thread(new PublishBallPosition());
            t.start();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if(Objects.equals(propertyChangeEvent.getPropertyName(), "paddle")){
            Thread t = new Thread(new PublishPaddlePosition());
            t.start();
        }
        if(Objects.equals(propertyChangeEvent.getPropertyName(), "collision")){
            Thread t = new Thread(new PublishCollision());
            t.start();
        }
        if(Objects.equals(propertyChangeEvent.getPropertyName(), "userScore")){
            Thread t = new Thread(new PublishScore());
            t.start();
        }
    }

    class PublishBallPosition implements Runnable {
        @Override
        public void run(){
            try{
                T4ABlackboard repository = T4ABlackboard.getInstance();
                String content = repository.getBallX() + "," + repository.getBallY() + "," + repository.getBallDX() + "," + repository.getBallDY();

                MqttMessage message = new MqttMessage(content.getBytes());
                message.setQos(0);

                if(client.isConnected()) {client.publish(TOPIC_BALL + "/" + clientID, message);}
                else System.out.println("did not connect");
            }catch (MqttException  e){
                System.out.println("error");
                e.printStackTrace();
            }
        }
    }

    class PublishPaddlePosition implements Runnable {
        @Override
        public void run() {
            try {
                T4ABlackboard repository = T4ABlackboard.getInstance();
                String content = String.valueOf(repository.getUserPaddleY());

                MqttMessage message = new MqttMessage(content.getBytes());
                message.setQos(0);

                if (client.isConnected()) {
                    client.publish(TOPIC_PADDLE + "/" + clientID, message);
                } else System.out.println("did not connect");

            } catch (MqttException e) {
                System.out.println("error");
                e.printStackTrace();
            }
        }
    }

    class PublishCollision implements Runnable {
        @Override
        public void run() {
            try {
                T4ABlackboard repository = T4ABlackboard.getInstance();
                String content = repository.getBallX() + "," + repository.getBallY() + "," + repository.getBallDX() + "," + repository.getBallDY();

                MqttMessage message = new MqttMessage(content.getBytes());
                message.setQos(2);

                if (client.isConnected()) {
                    client.publish(TOPIC_COLLISION + "/" + clientID, message);
                } else System.out.println("did not connect");

            } catch (MqttException e) {
                System.out.println("error");
                e.printStackTrace();
            }
        }
    }

    class PublishScore implements Runnable {
        @Override
        public void run() {
            try {
                T4ABlackboard repository = T4ABlackboard.getInstance();
                String content = Integer.toString(repository.getUserScore());

                MqttMessage message = new MqttMessage(content.getBytes());
                message.setQos(2);

                if (client.isConnected()) {
                    client.publish(TOPIC_SCORE + "/" + clientID, message);
                } else System.out.println("did not connect");

            } catch (MqttException e) {
                System.out.println("error");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("Connection lost: " + throwable.getMessage());
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) {
        if (topic.endsWith("/" + clientID)) {
            return;
        }
        T4ABlackboard blackboard = T4ABlackboard.getInstance();
        if(!blackboard.getInControl() && topic.startsWith(TOPIC_BALL)){
            String[] parts = new String(mqttMessage.getPayload()).split(",");
            int ballX = Integer.parseInt(parts[0]);
            int ballY = Integer.parseInt(parts[1]);
            int ballDX = Integer.parseInt(parts[2]);
            int ballDY = Integer.parseInt(parts[3]);
            blackboard.setBallPosition(blackboard.FIELD_WIDTH - ballX, blackboard.FIELD_HEIGHT-ballY);
            blackboard.setBallVelocity(-ballDX, -ballDY);
        }

        if(topic.startsWith(TOPIC_COLLISION)){
            String[] parts = new String(mqttMessage.getPayload()).split(",");
            int ballX = Integer.parseInt(parts[0]);
            int ballY = Integer.parseInt(parts[1]);
            int ballDX = Integer.parseInt(parts[2]);
            int ballDY = Integer.parseInt(parts[3]);
            blackboard.setBallPosition(blackboard.FIELD_WIDTH - ballX, blackboard.FIELD_HEIGHT-ballY);
            blackboard.setBallVelocity(-ballDX, -ballDY);
            blackboard.takeControl();
        }

        if(topic.startsWith(TOPIC_PADDLE)){
            blackboard.setOpponentPaddlePosition(blackboard.FIELD_HEIGHT - Integer.parseInt(new String(mqttMessage.getPayload())));
        }

        if(topic.startsWith(TOPIC_SCORE)){
            blackboard.setOpponentScore(Integer.parseInt(new String(mqttMessage.getPayload())));
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }

}