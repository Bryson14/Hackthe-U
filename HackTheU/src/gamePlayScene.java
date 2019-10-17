import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;


public class gamePlayScene {

    private Scene scene;
    private ArrayList<Coordinates> moves; //possible coordinates for selected piece
    private chessBoard cb;

    gamePlayScene() {
        cb = new chessBoard();
        final int HEIGHT =  8;
        final int WIDTH = 8;
        TilePane tp;

        tp = new TilePane();
        tp.setAlignment(Pos.CENTER);
        tp.setPadding(new Insets(10,10,10,10));

        boolean whiteOrBlack = true;

        for (int row = 0; row < WIDTH; row++) {
            whiteOrBlack ^= true;
            for (int column = 0; column < HEIGHT; column++) {
                whiteOrBlack ^= true;

                Rectangle tempR= new Rectangle();
                tempR.setId("" + column + row);
                tempR.widthProperty().bind(tp.widthProperty().divide(WIDTH));
                tempR.heightProperty().bind(tp.heightProperty().divide(HEIGHT));
                if (whiteOrBlack) tempR.setFill(Color.WHITE);
                else tempR.setFill(Color.BLACK);

                tempR.setOnMouseClicked(e -> {

                    if (moves.isEmpty()) { // first click
                        Coordinates coor = new Coordinates((int)tempR.getId().charAt(0), (int)tempR.getId().charAt(1))
                        if (cb.isOccupiedWithCorrectTeam(coor)){
                            moves = cb.getAvailableMoves(coor);
                        }


                    } else { // second click

                    }
                });
                tp.getChildren().add(tempR);
            }
        }



        BorderPane bp = new BorderPane();
        bp.setCenter(tp);
        this.scene = new Scene(bp);

    }

    public Scene getScene() {
        return scene;
    }

    public void resetBoard() {

    }

    private void updateBoard() {

    }
}
