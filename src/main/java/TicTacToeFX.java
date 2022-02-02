import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TicTacToeFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){

        //Only 3x3 version at the moment
        int sizeOfBoard = 3;

        //Building fields object from Field class
        Field[] fields = new Field[(int) Math.pow(sizeOfBoard,sizeOfBoard)];
        for (int i = 0 ; i < sizeOfBoard*sizeOfBoard ; i++){
            fields[i] = new Field(i);
        }

        //Building board
        Board board = new Board(sizeOfBoard, fields);

        //Building root and adding board to root
        StackPane application  = new StackPane();
        application.getChildren().add(board.getBoard());

        //Building scene
        Scene scene = new Scene(application);

        PlayerId playerId = new PlayerId();

        //Adding action to every fields button
        for (int i = 0; i < sizeOfBoard*sizeOfBoard; i++){
            int buttonIndex = i;
            fields[i].getButton().setOnAction(event -> {
                //Checking if field is not captured
                if (fields[buttonIndex].getPlayerId() == 0) {
                    //switching players
                    PlayerId.switchPlayer(playerId);
                    //actualisation: changing graphics, adding playerId to field object
                    Field.updateButton(playerId.getPlayerId(), fields[buttonIndex], sizeOfBoard);
                }
            //checking if game is over (win, defeat, draw)
            if (TikTakToeUtil.isGameWon(fields, playerId, sizeOfBoard, application)){
                //post game info, possibility to play again
                TikTakToeUtil.postGameInfo(playerId.getPlayerId(), application, fields, sizeOfBoard, primaryStage, board.getBoard());
            }
        });
        }
        //building primaryStage
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setHeight(639);
        primaryStage.setWidth(615);
        primaryStage.setResizable(false);
    }
}
