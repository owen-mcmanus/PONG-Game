import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/*
* Data storage for the PONG game
* @author owen-mcmanus
* @version 1
 */
public class T4ABlackboard extends PropertyChangeSupport {
    private int ballDX = 1;
    private int ballDY = 1;
    private int userPaddleY, opponentPaddleY = 0;
    private List<String> chats = new ArrayList<String>();
    private boolean inControl = false;
    private int userScore, opponentScore = 0;

    public final int BALL_DIAMETER = 10;
    public final int PADDLE_WIDTH = 10;
    public final int PADDLE_HEIGHT = 40;
    public final int PADDLE_X_OFFSET = 10;
    public final int FIELD_WIDTH = 1000;
    public final int FIELD_HEIGHT = 500;

    private int ballX = FIELD_WIDTH / 2;
    private int ballY = FIELD_HEIGHT / 2;


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


    public int getBallX(){return ballX;}
    public int getBallY(){return ballY;}
    public int getBallDX(){return ballDX;}
    public int getBallDY(){return ballDY;}
    public int getUserPaddleY(){return userPaddleY;}
    public int getOpponentPaddleY(){return opponentPaddleY;}
    public List<String> getChats(){return chats;}
    public boolean getInControl(){return inControl;}
    public int getUserScore(){return userScore;}
    public int getOpponentScore(){return opponentScore;}

    public void takeControl(){inControl = true;}
    public void releaseControl(){inControl = false;}


    public void setBallPosition(int x, int y){
        ballX = x;
        ballY = y;
    }

    public void setBallX(int x){
        ballX = x;
    }

    public void setBallY(int y){
        ballY = y;
    }

    public void setBallDX(int dx){
        ballDX = dx;
    }

    public void setBallDY(int dy){
        ballDY = dy;
    }
    public void setBallVelocity(int dx, int dy){
        ballDX = dx;
        ballDY = dy;
    }

    public void setUserPaddlePosition(int y){
        userPaddleY = y;
    }
    public void setOpponentPaddlePosition(int y){
        opponentPaddleY = y;
    }

    public void addChat(String sender, String message){
        String msgFormat = sender + ": " + message;
        chats.add(msgFormat);
        firePropertyChange("chatSent", null, msgFormat);
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
