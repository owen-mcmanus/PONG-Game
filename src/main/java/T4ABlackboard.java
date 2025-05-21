import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/*
* Data storage for the PONG game
* @author owen-mcmanus
* @version 1
 */
public class T4ABlackboard extends PropertyChangeSupport {
    private float ballX, ballY, ballDX, ballDY = 0;
    private float userPaddleY, opponentPaddleY = 0;
    private List<String> chats = new ArrayList<String>();
    private int fieldWidth, fieldHeight = 0;
    private boolean inControl = false;
    private int userScore, opponentScore = 0;

    private static T4ABlackboard instance;

    public static T4ABlackboard getInstance(){
        if(instance == null){
            instance = new T4ABlackboard();
        }
        return instance;
    }

    private T4ABlackboard(){
        super(new Object());
    }


    public float getBallX(){return ballX;}
    public float getBallY(){return ballY;}
    public float getBallDX(){return ballDX;}
    public float getBallDY(){return ballDY;}
    public float getUserPaddleY(){return userPaddleY;}
    public float getOpponentPaddleY(){return opponentPaddleY;}
    public List<String> getChats(){return chats;}
    public float getFieldWidth(){return fieldWidth;}
    public float getFieldHeight(){return fieldHeight;}
    public boolean getInControl(){return inControl;}
    public int getUserScore(){return userScore;}
    public int getOpponentScore(){return opponentScore;}

    public void takeControl(){inControl = true;}
    public void releaseControl(){inControl = false;}


    public void setBallPosition(float x, float y){
        ballX = x;
        ballY = y;
    }

    public void setBallVelocity(float dx, float dy){
        ballDX = dx;
        ballDY = dy;
    }

    public void setUserPaddlePosition(float y){
        userPaddleY = y;
    }
    public void setOpponentPaddlePosition(float y){
        opponentPaddleY = y;
    }

    public void addChat(String sender, String message){
        String msgFormat = sender + ": " + message;
        chats.add(msgFormat);
        firePropertyChange("chatSent", null, msgFormat);
    }
    public void setFieldDimensions(int width, int height){
        fieldWidth = width;
        fieldHeight = height;
    }

    public void incrementOpponentScore() {
        int oldScore = this.opponentScore;
        opponentScore++;
        firePropertyChange("opponentScore", oldScore, opponentScore);
    }
    public void incrementUserScore() {
        int oldScore = this.userScore;
        userScore++;
        firePropertyChange("userScore", oldScore, userScore);
    }
}
