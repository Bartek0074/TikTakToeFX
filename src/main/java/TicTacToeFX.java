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

        //Na razie tylko wersjaa 3x3
        //mozna zmienic na inne ale wtedy nie ma warunkow zwyciestwa dla innych rozmiarow planszy
        int sizeOfBoard = 3;

        //Stworzenie obiektów fields z klasy field
        Field[] fields = new Field[(int) Math.pow(sizeOfBoard,sizeOfBoard)];
        for (int i = 0 ; i < sizeOfBoard*sizeOfBoard ; i++){
            fields[i] = new Field(i);
        }

        //Stworzenie planszy
        Board board = new Board(sizeOfBoard, fields);

        //Stworzenia roota application i dodanie do niego planszy
        StackPane application  = new StackPane();
        application.getChildren().add(board.getBoard());

        //Stworzenie sceny
        Scene scene = new Scene(application);

        //Stworzenie obiektu playerId z klasy PlayerId (do przełlączania graczy)
        //Pewnie można to było prościej zrobic
        PlayerId playerId = new PlayerId();

        //Przypisanie wszystkim przyciskom z obiektów fields akcji
        for (int i = 0; i < sizeOfBoard*sizeOfBoard; i++){
            int buttonIndex = i;
            fields[i].getButton().setOnAction(event -> {
                //sprawdzenie, czy pole nie jest zajęte przez innego gracza
                if (fields[buttonIndex].getPlayerId() == 0) {
                    //zmiana gracza
                    PlayerId.switchPlayer(playerId);
                    //aktualizacja pzrycisku (zmiana grafiki, przypisanie obiektowi field playerId)
                    Field.updateButton(playerId.getPlayerId(), fields[buttonIndex], sizeOfBoard);
                }
            //sprawdzenie, czy gra została zakończona(zwyciestwo, porazka, remis)
            if (TikTakToeUtil.isGameWon(fields, playerId, sizeOfBoard, application)){
                //info po grze, możliwość ponownego zagrania
                TikTakToeUtil.postGameInfo(playerId.getPlayerId(), application, fields, sizeOfBoard, primaryStage, board.getBoard());
            }
        });
        }

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setHeight(639);
        primaryStage.setWidth(615);
        primaryStage.setResizable(false);

    }
}
