import javafx.geometry.*;
import javafx.scene.layout.Pane;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.media.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import pieces.Coordinates;
import pieces.gamePiece;
import java.io.*;
import java.net.*;
import java.util.*;

public class ChessServer extends Pane {

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
    private GridPane dotPane;
    private String tileColorA;
    private String tileColorB;
    private StackPane center;
    private GridPane imagePane;
    private int cellSize;
    private Socket socket;
    private BufferedWriter writer;
    private BufferedReader reader;


    ChessServer() {
        sep = System.getProperty("file.separator") + System.getProperty("file.separator");
        srcDir = System.getProperty("user.dir");
        base = new StackPane();
        newGame();
        getChildren().add(base);
    }

    public void connect() throws IOException {
        try {
            ServerSocket server = new ServerSocket(5678);
            System.out.println("waiting on acception");
            socket = server.accept();
            System.out.println("Server connection established");

        } catch (Exception ex) {
            System.err.println("Server couldn't connect.");
        }

        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    private void sendMove(Coordinates coor) throws IOException {
        writer.write(lastCoor.toString() + "\r\n");
        writer.write(coor.toString() + "\r\n");
        writer.flush();
    }

    public void receiveMove() throws IOException{
        String from = reader.readLine().trim();
        System.out.println("server recieved from move" + from);
        String to = reader.readLine().trim();
        System.out.println("server recieved to move" + to);
        cb.movePiece(new Coordinates(from), new Coordinates(to));
        updateBoard(new Coordinates(from), new Coordinates(to));
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
                            playSound("WhiteKing.mp3");
                            moves = cb.getAvailableMoves(coor);
                            lastCoor = coor;
                            possibleMoveDots(); // do this later if we got time
                        }
                    } else { // second click

                        dotPane.getChildren().clear(); // removes dots from the board

                        if (moves.contains(coor)) {
                            try {
                                cb.movePiece(lastCoor, coor);
                                updateBoard(lastCoor, coor);
                                displayTurn();
                                sendMove(coor);
                                receiveMove();
                            } catch (IOException ex) {
                                System.out.println("chess server failed in lambda");
                            }
                        }
                        else{
                            playSound("Error.mp3");
                        }

                        lastCoor = coor;
                        moves.clear();
                    }
                });
                this.squaresGrid.add(rec, column, row);
            }
        }
    }

    /**
     * After a valid move, the board is updated to reflect the change in the state of the backend
     */
    private void updateBoard(Coordinates lastCoor, Coordinates newCoor) {
        updateGraveyard();
        updateImages(lastCoor, newCoor);
    }

    private void updateImages(Coordinates lastCoor, Coordinates newCoor) {
        removeImage(lastCoor.x, lastCoor.y); //removing image
        removeImage(newCoor.x, newCoor.y); //removing old invisibox
        Rectangle rec = new Rectangle(cellSize,cellSize);
        rec.setFill(Color.TRANSPARENT);
        imagePane.add(rec, lastCoor.x, lastCoor.y); //adding the invisiBox

        ImageView image = new ImageView(players.get(cb.getGrid()[newCoor.x][newCoor.y].getName()));
        image.setFitHeight(cellSize );
        image.setFitWidth(cellSize );
        imagePane.add(image, newCoor.x, newCoor.y);
        imagePane.setAlignment(Pos.CENTER);
    }

    /**
     * Only called once to initially draw all the images
     */
    private void drawImages() {
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                gamePiece piece = cb.getGrid()[column][row];
                if (piece != null) {
                    ImageView image = new ImageView(players.get(piece.getName()));
                    image.setFitHeight(cellSize );
                    image.setFitWidth(cellSize );
                    imagePane.add(image, column, row);
                    imagePane.setAlignment(Pos.CENTER);
                } else {
                    Rectangle rec = new Rectangle(cellSize,cellSize);
                    rec.setFill(Color.TRANSPARENT);
                    imagePane.add(rec, column, row);
                    imagePane.setAlignment(Pos.CENTER);
                }
            }
        }
    }

    /**
     * removes any node inside a certain index in imagePane
     * @param col column index
     * @param row row index
     */
    private void removeImage(int col, int row) {
        ObservableList<Node> children = this.imagePane.getChildren();

        for (Node child : children) {
            if (col == squaresGrid.getColumnIndex(child) && row ==  squaresGrid.getRowIndex(child)) {
                imagePane.getChildren().remove(child);
                break;
            }
        }
    }

    /**
     * When the user clicks a valid first move, dots appear on potential open squares that the highlighted piece could move to
     *
     */
    private void possibleMoveDots() {

        ObservableList<Node> children = this.squaresGrid.getChildren();
        gamePiece[][] grid = cb.getGrid();

        for (Node child : children) {
            if (this.moves.contains(new Coordinates(squaresGrid.getColumnIndex(child), squaresGrid.getRowIndex(child)))) {
                Circle dot = new Circle();
                dot.setRadius(8);
                if(ChessBoard.isEnemy(new Coordinates(squaresGrid.getColumnIndex(child), squaresGrid.getRowIndex(child)))){
                    dot.setFill(Color.GREEN);
                }
                else dot.setFill(Color.RED);
                GridPane.setHalignment(dot, HPos.CENTER); // To align horizontally in the cell
                GridPane.setValignment(dot, VPos.CENTER);
                GridPane.setColumnIndex(dot, squaresGrid.getColumnIndex(child));
                GridPane.setRowIndex(dot, squaresGrid.getRowIndex(child));
                //dotPane.setConstraints(dot, squaresGrid.getColumnIndex(child), squaresGrid.getRowIndex(child) );
//                dotPane.add(dot, squaresGrid.getColumnIndex(child), squaresGrid.getRowIndex(child));
                dotPane.getChildren().add(dot);
            }
        }
    }

    private void displayTurn() {
        if (cb.getCurrentTeam()) updateText("White Team's Turn");
        else updateText("Black Team's Turn");
    }

    /**
     *draws the killed pieces on the sides
     */
    private void updateGraveyard() {
        whiteGraveYard.getChildren().clear();
        blackGraveYard.getChildren().clear();
        if (cb.teamTrueGraveyard.size() == 0) {
            whiteGraveYard.getChildren().add(new Rectangle(cellSize-20,cellSize, Color.TRANSPARENT));
        }
        if (cb.teamFalseGraveyard.size() == 0) {
            blackGraveYard.getChildren().add(new Rectangle(cellSize-20,cellSize, Color.TRANSPARENT));
        }
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
    void changeStyle(String key) {
        String[] pieces = {"WhiteBishop", "BlackBishop", "WhiteQueen", "BlackQueen", "WhiteKing", "BlackKing",
                "WhiteRook", "BlackRook", "BlackKnight", "WhiteKnight", "BlackPawn", "WhitePawn"};
        String imgDir = srcDir + sep + "HackTheU" + sep + "src" + sep + "pictures" + sep;

        if (key.toLowerCase().equals("avengers")) {
            imgDir += "AvengersChess" + sep;
        }

        players.clear();

        for (String piece: pieces) {
            File file = new File(imgDir + piece + ".png");
            Image image = new Image(file.toURI().toString());
            players.put(piece, image);
        }

        imagePane.getChildren().clear();
        drawImages();
    }


    /**
     * Displays text on the board. Used if we decide to say whose turn it is after every move
     * Also can display checkmate and stuff
     * @param text text to be displayed
     */
    void updateText(String text) {
        Text message = new Text(text);
        message.setFont(Font.font(35));
        HBox box = new HBox(message);
        box.setAlignment(Pos.CENTER);
        bp.setTop(box);
    }

    void playSound(String piece){
        String sep = System.getProperty("file.separator") + System.getProperty("file.separator");
        String srcDir = System.getProperty("user.dir") + sep + "HackTheU" + sep + "src" + sep;
        File file = new File(srcDir + "sounds" + sep + piece);
        Media sound = new Media(file.toURI().toString());
        MediaPlayer player = new MediaPlayer(sound);
        player.play();
    }

    /**
     * initializes all class variables and panes
     */
    private void newGame() {
        bp = new BorderPane();
        //TODO add background image first

        base.setStyle("/pictures/dank_4k_wood.jpg/");
//        base.setStyle(String.valueOf(Chess.class.getResource("/pictures/dank_4k_wood.jpg/")));
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
        dotPane = new GridPane();
        dotPane.setMouseTransparent(true);
        for (int i = 0; i < 8; i++) {
            dotPane.getColumnConstraints().add(new ColumnConstraints(65));

        }
        for (int i = 0; i < 8; i++) {
            dotPane.getRowConstraints().add(new RowConstraints(65));

        }
        dotPane.setPadding(new Insets(10,10,10,10));
        squaresGrid.setAlignment(Pos.CENTER);
        squaresGrid.setPadding(new Insets(10,10,10,10));

        //image pane is a grid pane that holds the image views
        imagePane = new GridPane();
        imagePane.setMouseTransparent(true);

        //stack pane that holds the main components of the chess board
        center = new StackPane(squaresGrid, imagePane, dotPane);
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
        updateGraveyard();
        changeStyle("normal");
        updateText("SERVER");
    }
}
