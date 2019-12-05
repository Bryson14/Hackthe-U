import javafx.animation.FadeTransition;
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
import javafx.util.Duration;
import pieces.Coordinates;
import pieces.gamePiece;
import java.io.File;
import java.net.ServerSocket;
import java.util.*;
import java.io.*;
import java.net.Socket;

public class Chess extends Pane {
    private ArrayList<Coordinates> moves;
    private ChessBoard cb;
    private Coordinates lastCoordinate;
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
    private GridPane imagePane;
    private int cellSize;
    private boolean avengeSounds;
    private String host;
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    StockFish client = new StockFish();

    Chess() {
//        this("localhost");
        sep = System.getProperty("file.separator") + System.getProperty("file.separator");
        srcDir = System.getProperty("user.dir");
        base = new StackPane();
        newGame();
        getChildren().add(base);
    }

    Chess(String hostAddress) {
        this.host = hostAddress;
        sep = System.getProperty("file.separator") + System.getProperty("file.separator");
        srcDir = System.getProperty("user.dir");
        base = new StackPane();
        newGame();
        getChildren().add(base);
    }

    public void connect() throws IOException {
        if(host.equals("00")){ // Server
            try {
                ServerSocket server = new ServerSocket(5678);
                System.out.println("waiting on acceptation");
                socket = server.accept();
                System.out.println("Server connection established");
            } catch (Exception ex) {
                System.err.println("Server couldn't connect.");
            }
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }
        else { // Client
            try {
                this.socket = new Socket(this.host, 5678);
                System.out.println("Client connection established");
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            } catch (Exception ex) {
                System.err.println("Server couldn't connect.");
            }
        }
    }




    private void sendMove(Coordinates coordinate) throws IOException {
        writer.write(lastCoordinate.toString() + "\r\n");
        writer.write(coordinate.toString() + "\r\n");
        writer.flush();
    }
    public void receiveMove() throws IOException{
        String from = reader.readLine().trim();
        System.out.println("server received from move" + from);
        String to = reader.readLine().trim();
        System.out.println("server received to move" + to);
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
                    Coordinates coordinate = new Coordinates(Character.getNumericValue(rec.getId().charAt(0)),
                            Character.getNumericValue(rec.getId().charAt(1)));

                    if (moves.isEmpty()) { // first click
                        if (cb.isOccupiedWithCorrectTeam(coordinate)){
                            playSound(coordinate);
                            moves = cb.getAvailableMoves(coordinate);
                            lastCoordinate = coordinate;
                            possibleMoveDots(); // do this later if we got time
                        }
                    }
                    else { // second click

                        dotPane.getChildren().clear(); // removes dots from the board

                        if (moves.contains(coordinate)) {
                            if (cb.checkMate(coordinate)) {
                                reset();

                                Pane winnerMessage = new StackPane();
                                base.getChildren().add(winnerMessage);
                                Text mes = new Text("You Win!!");
                                mes.setFont(Font.font("Comic Sans", FontWeight.BOLD, 50));
                                Rectangle r = new Rectangle(500, 350, Color.DODGERBLUE);
                                r.setArcHeight(30);
                                r.setArcWidth(30);
                                winnerMessage.getChildren().addAll(r, mes);

                                FadeTransition ft = new FadeTransition(Duration.seconds(7),winnerMessage);
                                ft.setFromValue(1.0);
                                ft.setToValue(0.0);
                                ft.setCycleCount(1);
                                ft.play();

                            }
                            else {
                                if(host == null) {
                                    String fen = cb.GridToFEN() + " b--042";
                                    System.out.println(client.getBestMove(fen, 100));
                                    cb.movePiece(lastCoordinate, coordinate);
                                    updateBoard(lastCoordinate, coordinate);
                                    displayTurn();
                                }
                                else {
                                    try {
                                        cb.movePiece(lastCoordinate, coordinate);
                                        updateBoard(lastCoordinate, coordinate);
                                        displayTurn();
                                        System.out.println("updated and moved");
                                        sendMove(coordinate);
                                        receiveMove();
                                    } catch (IOException ex) {
                                        System.out.println("There was a problem sending the move (Client)" + ex.toString());
                                    }
                                }
                            }
                        }
                        else{
                            playSound(null);
                        }
                        lastCoordinate = coordinate;
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
    private void updateBoard(Coordinates lastCoordinate, Coordinates newCoordinate) {
    updateGraveyard();
    updateImages(lastCoordinate, newCoordinate);
    }

    private void updateImages(Coordinates lastCoordinate, Coordinates newCoordinate) {
        removeImage(lastCoordinate.x, lastCoordinate.y); //removing image
        removeImage(newCoordinate.x, newCoordinate.y); //removing old box
        Rectangle rec = new Rectangle(cellSize,cellSize);
        rec.setFill(Color.TRANSPARENT);
        imagePane.add(rec, lastCoordinate.x, lastCoordinate.y); //adding the box

        ImageView image = new ImageView(players.get(cb.getGrid()[newCoordinate.x][newCoordinate.y].getName()));
        image.setFitHeight(cellSize );
        image.setFitWidth(cellSize );
        imagePane.add(image, newCoordinate.x, newCoordinate.y);
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
                } else {
                    Rectangle rec = new Rectangle(cellSize,cellSize);
                    rec.setFill(Color.TRANSPARENT);
                    imagePane.add(rec, column, row);
                }
                imagePane.setAlignment(Pos.CENTER);
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
            if (col == GridPane.getColumnIndex(child) && row ==  GridPane.getRowIndex(child)) {
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

        for (Node child : children) {
            if (this.moves.contains(new Coordinates(GridPane.getColumnIndex(child), GridPane.getRowIndex(child)))) {
                Circle dot = new Circle();
                dot.setRadius(8);
                if(ChessBoard.isEnemy(new Coordinates(GridPane.getColumnIndex(child), GridPane.getRowIndex(child)))){
                    dot.setFill(Color.GREEN);
                }
                else dot.setFill(Color.RED);
                GridPane.setHalignment(dot, HPos.CENTER); // To align horizontally in the cell
                GridPane.setValignment(dot, VPos.CENTER);
                GridPane.setColumnIndex(dot, GridPane.getColumnIndex(child));
                GridPane.setRowIndex(dot, GridPane.getRowIndex(child));
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
        avengeSounds = false;
        String[] pieces = {"WhiteBishop", "BlackBishop", "WhiteQueen", "BlackQueen", "WhiteKing", "BlackKing",
                "WhiteRook", "BlackRook", "BlackKnight", "WhiteKnight", "BlackPawn", "WhitePawn"};
        String imgDir = srcDir + sep + "HackTheU" + sep + "src" + sep + "pictures" + sep;
//        String soundDir = srcDir + sep + "HackTheU" + sep + "src" + sep + "sounds" + sep;


        if (key.toLowerCase().equals("avengers")) {
            avengeSounds = true;
            imgDir += "AvengersChess" + sep;
//            soundDir += "AvengersChess" + sep;
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

    void reset() {
        cb.reset();
        updateGraveyard();
        imagePane.getChildren().clear();
        drawImages();
    }

    void playSound(Coordinates coordinate){
        if (avengeSounds) {
            String pieceName;
            if (coordinate == null) {
                pieceName = "Error";
            } else {
                pieceName = this.cb.getGrid()[coordinate.x][coordinate.y].getName();
            }
            try {
                File file = new File(srcDir + sep + "HackTheU" + sep + "src" + sep + "sounds" + sep + pieceName + ".mp3");
                Media sound = new Media(file.toURI().toString());
                MediaPlayer player = new MediaPlayer(sound);
                player.play();
            } catch (MediaException e) {
                System.out.println("Couldn't load image " + pieceName + "\n" + e.getMessage());
            }
        }
    }

    /**
     * initializes all class variables and panes
     */
    public void newGame() {
        bp = new BorderPane();
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
        StackPane center = new StackPane(squaresGrid, imagePane, dotPane);
        this.bp.setCenter(center);

        //preloaded table for fast image movement
        players = new Hashtable<>();


        this.cb = new ChessBoard();

        tileColorA = "#f8f8f8"; //aggie blue
//        tileColorA = "7d3a02"; //dark brown

        tileColorB = "#0f2439"; //some white color
//        tileColorB = "#fae3be"; //light light brown

        //pixel size for each grid cell
        cellSize = 65;

        //available moves for a selected piece
        moves = new ArrayList<>();

        drawSquares();
        updateGraveyard();
        changeStyle("normal");
        updateText(" ");
    }
}
