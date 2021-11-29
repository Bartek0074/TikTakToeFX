import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;

public class TikTakToeUtil {

    public static boolean isGameWon(Field[] field, PlayerId playerId, int sizeOfBoard, StackPane appliction){

        //stworzenie obrazkow linii
        ImageView horizontalLine = new ImageView(new Image("horizontal-line.png"));
        ImageView verticalLine = new ImageView(new Image("vertical-line.png"));
        ImageView diagonalLineUp = new ImageView(new Image("diagonal-line-up.png"));
        ImageView diagonalLineDown = new ImageView(new Image("diagonal-line-down.png"));

        //zebranie do listy wszystkich obiektów field dla poszczególnego playerId
        ArrayList<Field> occupiedFields = new ArrayList<>();
        for (int i = 0; i < (sizeOfBoard*sizeOfBoard) ; i++){
            if (field[i].getPlayerId() == playerId.getPlayerId()){
                occupiedFields.add(field[i]);
            }
        }

        //wypisanie wszystkich przypadków zwyciestwa
        ArrayList<Field> case1 = new ArrayList<>();
        case1.add(field[0]);
        case1.add(field[1]);
        case1.add(field[2]);
        //sprawdzenie, czy warunek zwyciestwa jest prawdziwy
        if (occupiedFields.containsAll(case1))
        {
            //dodanie "lini zwyciestwa" i zwrócenie wartosci true
            horizontalLine.setTranslateY(-200);
            appliction.getChildren().add(horizontalLine);
            return true;
        }

        ArrayList<Field> case2 = new ArrayList<>();
        case2.add(field[3]);
        case2.add(field[4]);
        case2.add(field[5]);
        if (occupiedFields.containsAll(case2))
        {
            appliction.getChildren().add(horizontalLine);
            return true;
        }

        ArrayList<Field> case3 = new ArrayList<>();
        case3.add(field[6]);
        case3.add(field[7]);
        case3.add(field[8]);
        if (occupiedFields.containsAll(case3))
        {
            horizontalLine.setTranslateY(200);
            appliction.getChildren().add(horizontalLine);
            return true;
        }

        ArrayList<Field> case4 = new ArrayList<>();
        case4.add(field[0]);
        case4.add(field[3]);
        case4.add(field[6]);
        if (occupiedFields.containsAll(case4))
        {
            verticalLine.setTranslateX(-200);
            appliction.getChildren().add(verticalLine);
            return true;
        }

        ArrayList<Field> case5 = new ArrayList<>();
        case5.add(field[1]);
        case5.add(field[4]);
        case5.add(field[7]);
        if (occupiedFields.containsAll(case5))
        {
            appliction.getChildren().add(verticalLine);
            return true;
        }

        ArrayList<Field> case6 = new ArrayList<>();
        case6.add(field[2]);
        case6.add(field[5]);
        case6.add(field[8]);
        if (occupiedFields.containsAll(case6))
        {
            verticalLine.setTranslateX(200);
            appliction.getChildren().add(verticalLine);
            return true;
        }

        ArrayList<Field> case7 = new ArrayList<>();
        case7.add(field[0]);
        case7.add(field[4]);
        case7.add(field[8]);
        if (occupiedFields.containsAll(case7))
        {
            appliction.getChildren().add(diagonalLineDown);
            return true;
        }

        ArrayList<Field> case8 = new ArrayList<>();
        case8.add(field[2]);
        case8.add(field[4]);
        case8.add(field[6]);
        if (occupiedFields.containsAll(case8))
        {
            appliction.getChildren().add(diagonalLineUp);
            return true;
        }

        //zmienna licząca liczbę ruchów
        int numberOfMoves = 0;

        //sprawdzenie, czy istnieje jeszcze jakieś wolne pole
        //jeżeli nie zwracana jest wartość true i gra jest remisowana
        for (int i = 0; i < Math.pow(sizeOfBoard,sizeOfBoard); i++){
            try {
                if (field[i].getPlayerId() != 0) {
                    numberOfMoves++;
                }
            }
            catch (NullPointerException e){ }
        }
        if (numberOfMoves == 9){
            playerId.setPlayerId(3);
            System.out.println("remis");
            return true;
        }
        else
            return false;

    }

    static void postGameInfo(int playerId, StackPane application, Field[] fields, int sizeOfBoard, Stage stage, GridPane board){


        //Zadeklarowanie wszystkich potrzebnych labelek, przycisków, ktore zostana wyswietlone
        //po zakonczeniu gry
        Font font1 = Font.font("Arial",FontWeight.BOLD, 60);
        Font font2 = Font.font("Arial",FontWeight.BOLD, 40);

        Label circlesWonInfo = new Label("CIRCLES WON!!!");
        circlesWonInfo.setVisible(true);
        circlesWonInfo.setFont(font1);
        circlesWonInfo.setTextFill(Color.WHITE);
        circlesWonInfo.setTranslateY(-100);
        //szmery bajery
        circlesWonInfo.setStyle("-fx-background-color: rgba(0, 0, 0, 0.03);" +
                                "-fx-effect: dropshadow(gaussian, black, 40, 0, 0, 0);" +
                                "-fx-background-insets: 10;");

        Label crossesWonInfo = new Label("CROSSES WON!!!");
        crossesWonInfo.setVisible(true);
        crossesWonInfo.setFont(font1);
        crossesWonInfo.setTextFill(Color.WHITE);
        crossesWonInfo.setTranslateY(-100);
        crossesWonInfo.setStyle("-fx-background-color: rgba(0, 0, 0, 0.02);" +
                                "-fx-effect: dropshadow(gaussian, black, 40, 0, 0, 0);" +
                                "-fx-background-insets: 10;");

        Label drawInfo = new Label("IT'S A DRAW!!!");
        drawInfo.setVisible(true);
        drawInfo.setFont(font1);
        drawInfo.setTextFill(Color.WHITE);
        drawInfo.setTranslateY(-100);
        drawInfo.setStyle("-fx-background-color: rgba(0, 0, 0, 0.02);" +
                          "-fx-effect: dropshadow(gaussian, black, 40, 0, 0, 0);" +
                          "-fx-background-insets: 10;");

        Label playAgain = new Label(" Do you want to play again?");
        playAgain.setVisible(true);
        playAgain.setFont(font2);
        playAgain.setTextFill(Color.WHITE);
        playAgain.setTranslateY(-40);
        playAgain.setStyle("-fx-background-color: rgba(0, 0, 0, 0.02);" +
                            "-fx-effect: dropshadow(gaussian, black, 40, 0, 0, 0);" +
                            "-fx-background-insets: 10;");

        ImageView blank = new ImageView(new Image("blank.png"));

        Button yes = new Button("YES");
        yes.setPrefWidth(150);
        yes.setFont(font2);
        yes.setTranslateX(-80);
        yes.setTranslateY(50);
        yes.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7);" +
                     "-fx-effect: dropshadow(gaussian, black, 40, 0, 0, 0);" +
                     "-fx-background-insets: 10;");

        Button no = new Button("NO");
        no.setPrefWidth(150);
        no.setFont(font2);
        no.setTranslateX(80);
        no.setTranslateY(50);
        no.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7);" +
                     "-fx-effect: dropshadow(gaussian, black, 40, 0, 0, 0);" +
                     "-fx-background-insets: 10;");

        //ustawienie akcji po wcisnięciu przycisku tak
        //gra jest rozpoczynana od początku
        yes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //wyczyszczenie aplikacji, roota ze wszystkich kontrolek
                application.getChildren().clear();
                //ustawienie nowej planszy
                application.getChildren().add(board);
                //dla wszystkich obiektów field ustawienie playerId na 0,
                //włączenie buttonow i przełączenie grafiki buttonow na przezroczyste
                for (int i = 0; i < Math.pow(sizeOfBoard, sizeOfBoard); i++) {
                    try {
                        fields[i].setPlayerId(0);
                        fields[i].getButton().setDisable(false);
                        fields[i].getButton().setGraphic(blank);
                    }
                    catch (NullPointerException e){}
                }
            }
        });

        //ustawienie akcji po wcisnieciu buttona no
        no.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //wyjscie z programu
                stage.close();
            }
        });


        //dodanie do roota kontrolek po skonczeniu gry
        if (playerId == 1)
            application.getChildren().add(circlesWonInfo);
        else if (playerId == 2)
            application.getChildren().add(crossesWonInfo);
        else
            application.getChildren().add(drawInfo);

        application.getChildren().add(playAgain);
        application.getChildren().add(yes);
        application.getChildren().add(no);

        //wylaczenie wszystkich buttonow po zakonczeniu gry
        for (int i = 0; i < sizeOfBoard*sizeOfBoard; i++){
            fields[i].getButton().setDisable(true);

        }
    }
}
