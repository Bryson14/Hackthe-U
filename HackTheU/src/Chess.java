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

    /**
     * Draws the colored squares that make up the chess board.
     * The squares contain the logic that communicates with the backend
     */
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
//                            possibleMoveDots(); // do this later if we got time
                        } else {
                            // playErrorSound(); //TODO Write this
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

    /**
     * This function is not currently doing anything. This is just an idea in process
     *
     */
    private void movePlayers() {
        double w = squaresGrid.getWidth();
        double h = squaresGrid.getHeight();
        //TODO not sure how get a specific image on an arbitrary position on a pane

    }

    /**
     * After a valid move, the board is updated to reflect the change in the state of the backend
     */
    private void updateBoard() {
    updateImages();
    updateGraveyard();
    }

    /**
     * Redraws the images in the grid pane to show moving pieces
     */
    private void updateImages() {
        for (int row = 0; row < 8; row++) {
            if (row == 7) {
                int a = 0;
            }
            for (int column = 0; column < 8; column++) {
                gamePiece piece = cb.getGrid()[column][row];
                if (piece != null) { // TODO if image is already in spot in the grid pane, you have to remove it to avoid stack images

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

    /**
     * When the user clicks a valid first move, dots appear on potential open squares that the highlighted piece could move to
     *
     */
    //TODO make is so the squares can flash until another click is made
    //TODO Not sure if we should put the dots on the squares or above the squares on their own grid pane
    private void possibleMoveDots() {

        ObservableList<Node> children = this.squaresGrid.getChildren();

        for (Node child : children) {
            if (this.moves.contains(new Coordinates(squaresGrid.getColumnIndex(child), squaresGrid.getRowIndex(child)))) {
               //child make flash
            }
        }
    }

    /**
     *draws the killed pieces on the sides
     */
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

    /**
     * Finds the pictures for pieces and saves it to a hash table for fast access
     * @param key normal or nothing for regular pieces, 'avengers' for you know what
     */
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
        updateImages();
    }


    /**
     * Displays text on the board. Used if we decide to say whose turn it is after every move
     * Also can display checkmate and stuff
     * @param text
     */
    void updateText(String text) {
        Text message = new Text(text);
        message.setFont(Font.font(35));
        bp.setTop(message);
    }

    /**
     * initializes all class variables and panes
     */
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

        // player pane is not being used right now
        playerPane = new Pane();
        playerPane.setMouseTransparent(true);

        //image pane is a grid pane that holds the image views
        imagePane = new GridPane();
        imagePane.setMouseTransparent(true);

        //stack pane that holds the main components of the chess board
        center = new StackPane(squaresGrid, playerPane, imagePane);
        this.bp.setCenter(center);

        //preloaded table for fast image movement
        players = new Hashtable<>();

        this.cb = new ChessBoard();

        //aggie blue
        tileColorA = "#f8f8f8";
        //some white color
        tileColorB = "#0f2439";

        //pixel size for each grid cell
        cellSize = 65;

        //available moves for a selected piece
        moves = new ArrayList<>();

        drawSquares();
        changeSyle("normal");
        updateImages();
        updateText("hello");
    }
}
