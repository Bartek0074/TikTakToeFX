import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Field {

    private int buttonIndex;
    private int playerId;
    private Button button;

    public Field(int buttonIndex) {
        this.buttonIndex = buttonIndex;
        button = new Button();
    }
    static public void updateButton(int playerId, Field field, int sizeOfBoard){
        field.setPlayerId(playerId);
        //adding graphic to button
        if (playerId == 1){
            ImageView circle = new ImageView(new Image("circle.png"));
            circle.setFitHeight(600/sizeOfBoard - 30);
            circle.setFitWidth(600/sizeOfBoard - 30);
            field.getButton().setGraphic(circle);
            field.getButton().setText("");
        }
        else {
            ImageView cross = new ImageView(new Image("cross.png"));
            cross.setFitHeight(600/sizeOfBoard - 30);
            cross.setFitWidth(600/sizeOfBoard - 30);
            field.getButton().setGraphic(cross);
            field.getButton().setText("");
        }
    }


    public int getPlayerId() { return playerId; }

    public void setPlayerId(int playerId) { this.playerId = playerId; }


    public Button getButton() { return button; }


}
