import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;


public class gamePlayScene {

    private Scene scene;
    private ArrayList<Coordinates> moves = new ArrayList<>(); //possible coordinates for selected piece
    private chessBoard cb;
    private Coordinates lastCoor;

    gamePlayScene() {
        cb = new chessBoard();
        final int HEIGHT = 8;
        final int WIDTH = 8;
        GridPane gp;

        gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setPadding(new Insets(10,10,10,10));
        gp.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.FULL)));
        // TODO figure out how to place border around the chess board only

        boolean whiteOrBlack = true;

        for (int row = 0; row < WIDTH; row++) {
            whiteOrBlack ^= true;
            for (int column = 0; column < HEIGHT; column++) {
                whiteOrBlack ^= true;

                Rectangle tempR= new Rectangle(80,80);
                tempR.setId("" + column + row);
//                tempR.widthProperty().bind(gp.widthProperty().divide(WIDTH));
//                tempR.heightProperty().bind(gp.heightProperty().divide(HEIGHT));
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

                        //TODO highlight the squares that are in the moves list


                    } else { // second click

                        //TODO if second space is in the moves list, then cb.move(lastCoor, coor). updateBoard()

                        System.out.println("a second click");

                    }
                });
                gp.add(tempR, column, row);
            }
        }



        BorderPane bp = new BorderPane();
        bp.setCenter(gp);
        bp.setTop(new Text("You are the best at chess!"));
        this.scene = new Scene(bp);

    }

    public Scene getScene() {
        return scene;
    }

    public void resetBoard() {

    }

    private void updateBoard() {
        gamePiece[][] grid = cb.getGrid();

    }

    private void highlightLegalMoves() {
        //TODO write this
    }
}
