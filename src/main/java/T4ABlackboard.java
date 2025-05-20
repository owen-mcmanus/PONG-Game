import java.util.ArrayList;
import java.util.List;

public class T4ABlackboard {
    private float ballX, ballY = 0;
    private float userPaddleY, opponentPaddleY = 0;
    private List<String> chats = new ArrayList<String>();
    private int fieldWidth, fieldHeight = 0;

    private static T4ABlackboard instance;

    private static T4ABlackboard getInstance(){
        if(instance == null){
            instance = new T4ABlackboard();
        }
        return instance;
    }

    public float getBallX(){return ballX;}
    public float getBallY(){return ballY;}
    public float getUserPaddleY(){return userPaddleY;}
    public float getOpponentPaddleY(){return opponentPaddleY;}
    public List<String> getChats(){return chats;}
    public float getFieldWidth(){return fieldWidth;}
    public float getFieldHeight(){return fieldHeight;}

    public void setBallPosition(float x, float y){
        ballX = x;
        ballY = y;
    }

    public void setUserPaddlePosition(float y){
        userPaddleY = y;
    }
    public void setOpponentPaddlePosition(float y){
        opponentPaddleY = y;
    }

    public void addChat(String message){
        chats.add(message);
    }
    public void setFieldDimensions(int width, int height){
        fieldWidth = width;
        fieldHeight = height;
    }
}
