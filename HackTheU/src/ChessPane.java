import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pieces.Coordinates;
import pieces.gamePiece;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

public class ChessPane {

    private Scene scene;
    private ArrayList<Coordinates> moves = new ArrayList<>(); //possible coordinates for selected piece
    private ChessBoard cb;
    private Coordinates lastCoor;
    private GridPane gp;
    private FlowPane whiteGraveYard = new FlowPane();
    private FlowPane blackGraveYard = new FlowPane();
    private Text topMessage = new Text();
    private String srcDir;
    private String sep = System.getProperty("file.separator") + System.getProperty("file.separator");
    private Hashtable<String, ImageView> images;

    ChessPane(Stage primaryStage) {
        this.srcDir = System.getProperty("user.dir") + sep + "HackTheU" + sep + "src" + sep;
        cb = new ChessBoard();
        final int HEIGHT = 8;
        final int WIDTH = 8;

        gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setPadding(new Insets(10,10,10,10));
        gp.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.FULL)));
        // TODO figure out how to place border around the chess board only

        boolean whiteOrBlack = true;

        // Setting the rectangles on the grid pane

        for (int row = 0; row < WIDTH; row++) {
            whiteOrBlack ^= true;
            for (int column = 0; column < HEIGHT; column++) {
                whiteOrBlack ^= true;

                Rectangle tempR= new Rectangle(75,75);
                tempR.setId("" + column + row);

                if (whiteOrBlack) tempR.setFill(Color.WHITE);
                else tempR.setFill(Color.BLUE);

                Color c = Color.web("#f8f8f8");
                Color b = Color.web("#0f2439");
                if (whiteOrBlack) tempR.setFill(c);
                else tempR.setFill(b);

                tempR.setOnMouseClicked(e -> {
                    System.out.println("Position: " + tempR.getId());
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


                    } else { // second click
                        System.out.println("a second click, last coor: " + lastCoor);

                        if (moves.contains(coor)) {
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
                this.gp.add(tempR, column, row);
            }
        }

        BorderPane bp = new BorderPane();
        bp.setCenter(this.gp);
        bp.setTop(topMessage);
        bp.setAlignment(topMessage, Pos.CENTER);
        bp.setAlignment(this.gp, Pos.CENTER);
        bp.setLeft(this.whiteGraveYard);
        bp.setRight(this.blackGraveYard);
        //TODO figure out why board starts out off the screen
        whiteGraveYard.setOrientation(Orientation.VERTICAL);
        blackGraveYard.setOrientation(Orientation.VERTICAL);
        File file = new File(this.srcDir + "pictures" + sep +"dank_4k_wood.jpg");
        ImageView background = new ImageView(new Image(file.toURI().toString()));
        background.fitHeightProperty().bind(primaryStage.heightProperty());
        background.fitWidthProperty().bind(primaryStage.widthProperty());
        this.scene = new Scene(new StackPane(background, bp));

        updateBoard();

    }

    public Scene getScene() {
        return this.scene;
    }

    private void updateBoard() {
        gamePiece[][] grid = this.cb.getGrid();
        System.out.println(System.getProperty("user.dir"));
        String sep = System.getProperty("file.separator");
        String dir = sep + "HackTheU" + sep + "src" + sep + "pictures" + sep;


        ObservableList<Node> children = this.gp.getChildren();

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
        updateGraveYard();
//        if (cb.isCurrentTeam()) topMessage.setText("White Team's Turn");
//        else topMessage.setText("Black Team's Turn");
    }

    private void highlightLegalMoves() {

        ObservableList<Node> children = this.gp.getChildren();
        DropShadow borderShadow = new DropShadow(10, 0f, 0f, Color.RED);
        borderShadow.setHeight(30);

        for (Node child : children) {
            if (this.moves.contains(new Coordinates(gp.getColumnIndex(child),gp.getRowIndex(child)))) {
                child.setEffect(borderShadow);
            } else {
                child.setEffect(null);
            }
        }
    }

    private void updateGraveYard() {
        whiteGraveYard.getChildren().clear();
        blackGraveYard.getChildren().clear();
        for (gamePiece white : cb.teamTrueGraveyard) {
            File file = new File(this.srcDir + "pictures" + sep +  white.getName() + ".png");
            ImageView image = new ImageView(new Image(file.toURI().toString()));
            image.setFitWidth(50);
            image.setFitHeight(50);
            whiteGraveYard.getChildren().add(image);
        }
        for (gamePiece black : cb.teamFalseGraveyard) {
            File file = new File(this.srcDir + "pictures" +  sep +  black.getName() + ".png");
            ImageView image = new ImageView(new Image(file.toURI().toString()));
            image.setFitHeight(50);
            image.setFitWidth(50);
            blackGraveYard.getChildren().add(image);
        }
    }
}