import javafx.scene.layout.Pane;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import pieces.Coordinates;
import pieces.gamePiece;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

public class Chess extends Pane {

    private ArrayList<Coordinates> moves;
    private ChessBoard cb;
    private Coordinates lastCoor;
    private GridPane squaresGrid;
    private String srcDir;
    private String sep;
    private Hashtable<String, Image> players;
    private BorderPane bp;
    private StackPane base;
    private FlowPane whiteGraveYard;
    private FlowPane blackGraveYard;
    private String tileColorA;
    private String tileColorB;
    private StackPane center;
    private Pane playerPane;
    private GridPane imagePane;
    private int cellSize;


    Chess() {
        sep = System.getProperty("file.separator") + System.getProperty("file.separator");
        srcDir = System.getProperty("user.dir");
        base = new StackPane();
        newGame();
        getChildren().add(base);
    }

    private void drawSquares() {
        final int HEIGHT = 8;
        final int WIDTH = 8;

        boolean colorAorB = true;

        for (int row = 0; row < WIDTH; row++) {
            colorAorB ^= true;
            for (int column = 0; column < HEIGHT; column++) {
                colorAorB ^= true;

                Rectangle rec= new Rectangle(cellSize,cellSize);
                rec.setId("" + column + row);

                if (colorAorB) rec.setFill(Color.web(tileColorA));
                else rec.setFill(Color.web(tileColorB));

                Color a = Color.web(tileColorA);
                Color b = Color.web(tileColorB);
                if (colorAorB) rec.setFill(a);
                else rec.setFill(b);

                rec.setOnMouseClicked(e -> {
                    Coordinates coor = new Coordinates(Character.getNumericValue(rec.getId().charAt(0)),
                            Character.getNumericValue(rec.getId().charAt(1)));

                    if (moves.isEmpty()) { // first click
                        if (cb.isOccupiedWithCorrectTeam(coor)){
                            moves = cb.getAvailableMoves(coor);
                            lastCoor = coor;
//                            flashMoves(); // do this later if we got time
                        }
                    } else { // second click
                        if (moves.contains(coor)) {
                            cb.movePiece(lastCoor, coor);
                            updateBoard();
                        }
                        moves.clear();
                        lastCoor = coor;
                        cb.printBoard();
                    }
                });

                this.squaresGrid.add(rec, column, row);
            }
        }
    }

    private void movePlayers() {
        double w = squaresGrid.getWidth();
        double h = squaresGrid.getHeight();
        //TODO not sure how get a specific image on an arbitrary position on a pane

    }

    private void updateBoard() {

    updateGraveyard();
    }

    private void placeImages() {
        for (int row = 0; row < 8; row++) {
            if (row == 7) {
                int a = 0;
            }
            for (int column = 0; column < 8; column++) {
                gamePiece piece = cb.getGrid()[column][row];
                if (piece != null) {
                    ImageView image = new ImageView(players.get(piece.getName()));
                    image.setFitHeight(cellSize );
                    image.setFitWidth(cellSize );
                    imagePane.add(image, column, row);
                } else {
                    Rectangle rec = new Rectangle(cellSize,cellSize);  //TODO adding invisible rectangle might cause problems in the future
                    rec.setFill(Color.TRANSPARENT);
                    imagePane.add(rec, column, row);
                }
            }
        }
    }

    //TODO make is so the squares can flash until another click is made
    private void flashMoves() {

        ObservableList<Node> children = this.squaresGrid.getChildren();

        for (Node child : children) {
            if (this.moves.contains(new Coordinates(squaresGrid.getColumnIndex(child), squaresGrid.getRowIndex(child)))) {
               //child make flash
            }
        }
    }

    private void updateGraveyard() {
        whiteGraveYard.getChildren().clear();
        blackGraveYard.getChildren().clear();
        for (gamePiece white : cb.teamTrueGraveyard) {
            ImageView image = new ImageView(players.get(white.getName()));
            image.setFitHeight(cellSize - 20);
            image.setFitWidth(cellSize - 20);
            whiteGraveYard.getChildren().add(image);
        }
        for (gamePiece black : cb.teamFalseGraveyard) {
            ImageView image = new ImageView(players.get(black.getName()));
            image.setFitHeight(cellSize - 20);
            image.setFitWidth(cellSize - 20);
            blackGraveYard.getChildren().add(image);
        }
    }


    void changeSyle(String key) {
        String[] pieces = {"WhiteBishop", "BlackBishop", "WhiteQueen", "BlackQueen", "WhiteKing", "BlackKing",
                "WhiteRook", "BlackRook", "BlackKnight", "WhiteKnight", "BlackPawn", "WhitePawn"};
        String imgDir = srcDir + sep + "HackTheU" + sep + "src" + sep + "pictures" + sep;

        if (key.equals("avengers")) {
            imgDir += "AvengersChess" + sep;
        }

        players.clear();

        for (String piece: pieces) {
            File file = new File(imgDir + piece + ".png");
            Image image = new Image(file.toURI().toString());
            players.put(piece, image);
        }
    }

    private void newGame() {
        bp = new BorderPane();
        //TODO add background image first
        base.getChildren().add(bp);

        //graveyards
        whiteGraveYard = new FlowPane();
        whiteGraveYard.setOrientation(Orientation.VERTICAL);
        bp.setRight(whiteGraveYard);

        blackGraveYard = new FlowPane();
        blackGraveYard.setOrientation(Orientation.VERTICAL);
        bp.setLeft(blackGraveYard);

        //center panes, square pane, and players pane
        squaresGrid = new GridPane();

        squaresGrid.setAlignment(Pos.CENTER);
        squaresGrid.setPadding(new Insets(10,10,10,10));

        playerPane = new Pane();
        playerPane.setMouseTransparent(true);
        imagePane = new GridPane();
        imagePane.setMouseTransparent(true);

        center = new StackPane(squaresGrid, playerPane, imagePane);
        this.bp.setCenter(center);

        //preloaded table for fast image movement
        players = new Hashtable<>();

        this.cb = new ChessBoard();

        tileColorA = "#f8f8f8";
        tileColorB = "#0f2439";
        cellSize = 65;
        moves = new ArrayList<>();

        drawSquares();
        changeSyle("normal");
        placeImages();
        for (int i = 0; i < 8; i++) {
            RowConstraints rc = new RowConstraints();
            rc.setMinHeight(cellSize);
            ColumnConstraints cc = new ColumnConstraints();
            cc.setMinWidth(cellSize);
        }
        updateBoard();
        updateText("hello");
    }

    void updateText(String text) {
        Text message = new Text(text);
        message.setFont(Font.font(35));
        bp.setTop(message);
    }
}
