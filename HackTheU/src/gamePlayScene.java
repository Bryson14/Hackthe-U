import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;


public class gamePlayScene {

    private Scene scene;
    private ArrayList<Coordinates> moves = new ArrayList<>(); //possible coordinates for selected piece
    private chessBoard cb;

    gamePlayScene() {
        cb = new chessBoard();
        final int HEIGHT =  8;
        final int WIDTH = 8;
        GridPane gp;

        gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setPadding(new Insets(10,10,10,10));

        boolean whiteOrBlack = true;

        for (int row = 0; row < WIDTH; row++) {
            whiteOrBlack ^= true;
            for (int column = 0; column < HEIGHT; column++) {
                whiteOrBlack ^= true;

                Rectangle tempR= new Rectangle(50,50);
                tempR.setId("" + column + row);
                tempR.widthProperty().bind(gp.widthProperty().divide(WIDTH));
                tempR.heightProperty().bind(gp.heightProperty().divide(HEIGHT));
                if (whiteOrBlack) tempR.setFill(Color.WHITE);
                else tempR.setFill(Color.BLACK);

                tempR.setOnMouseClicked(e -> {
                    System.out.println(tempR.getId());

                    if (moves.isEmpty()) { // first click
                        // subtract 48 for correction of ascii '0' = 48
                        Coordinates coor = new Coordinates((int)tempR.getId().charAt(0) - 48, (int)tempR.getId().charAt(1) - 48);

                        if (cb.isOccupiedWithCorrectTeam(coor)){
                            moves = cb.getAvailableMoves(coor);
                        }


                    } else { // second click
                        System.out.println("a second click");
                    }
                });
                gp.add(tempR, column, row);
            }
        }



        BorderPane bp = new BorderPane();
        bp.setCenter(gp);
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
