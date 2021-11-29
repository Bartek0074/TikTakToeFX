import javafx.scene.layout.GridPane;

public class Board
{

    GridPane board = new GridPane();

    int buttonIndex = 0;

    Board(int sizeOfBoard, Field[] fields){

        int sizeOfBoardPowered = sizeOfBoard * sizeOfBoard;
        int buttonSize = 600 / sizeOfBoard;

        for (int i = 0 ; i < sizeOfBoardPowered ; i++){
            fields[i] = new Field(i);
            fields[i].getButton().setPrefHeight(buttonSize);
            fields[i].getButton().setPrefWidth(buttonSize);
        }

        for (int i = 0 ; i < sizeOfBoard ; i++){
            for (int j = 0 ; j < sizeOfBoard ; j++){
                board.add(fields[buttonIndex].getButton(),j,i);
                buttonIndex++;
            }
        }
    }

    public GridPane getBoard() {
        return board;
    }

}
