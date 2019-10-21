import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;

import static javafx.scene.layout.GridPane.getColumnIndex;


public class gamePlayScene {

    private Scene scene;
    private ArrayList<Coordinates> moves = new ArrayList<>(); //possible coordinates for selected piece
    private chessBoard cb;
    private Coordinates lastCoor;
    private GridPane gp;

    gamePlayScene() {
        cb = new chessBoard();
        final int HEIGHT = 8;
        final int WIDTH = 8;

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
                    System.out.println("Position" + tempR.getId());
                    // subtract 48 for correction of ascii '0' = 48
                    Coordinates coor = new Coordinates((int)tempR.getId().charAt(0) - 48, (int)tempR.getId().charAt(1) - 48);

                    if (moves.isEmpty()) { // first click
                        System.out.println("fIrst Click");
                        if (cb.isOccupiedWithCorrectTeam(coor)){
                            moves = cb.getAvailableMoves(coor);
                            lastCoor = coor;
                        }
                        highlightLegalMoves();
                        System.out.println(moves);

                        //TODO highlight the squares that are in the moves list


                    } else { // second click
                        System.out.println("a second click, last coor: " + lastCoor);

                        if (moves.contains(coor)) { //TODO its never entering here
                            cb.movePiece(lastCoor, coor);
                            System.out.println("Moved the piece!");
                            updateBoard();
                        }
                        System.out.println("clearing the list");
                        moves.clear();
                        highlightLegalMoves();
                        lastCoor = coor;
                        cb.printBoard();
                    }
                });
                gp.add(tempR, column, row);
            }
        }

        BorderPane bp = new BorderPane();
        bp.setCenter(gp);
        bp.setTop(new Text("You are the best at chess!"));
        this.scene = new Scene(bp);

        updateBoard();

    }

    public Scene getScene() {
        return scene;
    }

    public void resetBoard() {
        cb.setNewBoard();
        updateBoard();
    }

    private void updateBoard() {
        gamePiece[][] grid = cb.getGrid();
        System.out.println(System.getProperty("user.dir"));
        String dir = "\\HackTheU\\src\\pictures\\";

        ObservableList<Node> children = gp.getChildren();

        for (Node child : children) {
            gamePiece piece = grid[gp.getColumnIndex(child)][gp.getRowIndex(child)];
            System.out.println(piece);
            if (piece != null) {
                File file = new File(System.getProperty("user.dir") + dir + piece.getName() + ".png");
                Image image = new Image(file.toURI().toString());
                ((Rectangle) child).setFill(new ImagePattern(image));
                //TODO find a way to put on a picture on rectangle without using setFILL

            } else {
                //TODO remove a picture if it is there
            }
        }
    }

    private void highlightLegalMoves() {

        ObservableList<Node> children = gp.getChildren();
        gamePiece[][] grid = cb.getGrid();
        DropShadow borderShadow = new DropShadow(10, 0f, 0f, Color.RED);
        borderShadow.setHeight(30);

        for (Node child : children) {
            if (moves.contains(new Coordinates(gp.getColumnIndex(child),gp.getRowIndex(child)))) {
                child.setEffect(borderShadow);
            } else {
                child.setEffect(null);
            }
        }
    }
}
