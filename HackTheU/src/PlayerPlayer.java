import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.io.*;

public class PlayerPlayer extends MainMenu {
    static String ipAddress;

    public static void playerPlayer(Stage stage){
        HBox hbox = new HBox();
        hbox.setStyle("-fx-background-color: rgba(255,186,26,0.64)");
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(40);
        Button server = new Button("Server");
        server.setTextFill(Color.WHITE);
        server.setFont(Font.font("Comic Sans", FontWeight.BOLD, 30));
        server.setStyle("-fx-background-color: rgba(54,17,0,0.92)");
        server.setPrefSize(150, 200);

        Button client = new Button("Client");
        client.setTextFill(Color.WHITE);
        client.setFont(Font.font("Comic Sans", FontWeight.BOLD, 30));
        client.setStyle("-fx-background-color: rgba(54,17,0,0.92)");
        client.setPrefSize(150, 200);

        VBox serverBox = new VBox();
        TextField ipLabel = new TextField();
        ipLabel.setText("IP Address");
        ipLabel.setOnAction(event -> ipAddress = ipLabel.getText());

        serverBox.getChildren().addAll(server, ipLabel);

        serverBox.setTranslateY(225);
        serverBox.setSpacing(25);
        hbox.getChildren().addAll(serverBox, client);
        stage.setScene(new Scene(hbox, 750, 650));
        stage.show();

        server.setOnAction(event -> {
            Chess chess = new Chess("00");
            setUpBoard("00", stage);

        try {
            chess.connect();
            chess.receiveMove();
        } catch (IOException ex) {
            System.out.println("problem with client");
        }
        });



        client.setOnAction(event -> {
            Chess chess = new Chess(ipAddress);
            setUpBoard(ipAddress, stage);

            try {
                chess.connect();
            } catch (IOException ex) {
                System.out.println("problem with client");
            }
        });
    }

    public static void setUpBoard(String ipAddress, Stage stage){
        Chess chess = new Chess(ipAddress);
        StackPane stack = new StackPane();
        Rectangle background = new Rectangle(545, 543);
        background.setTranslateY(-8);
        background.setArcHeight(15.0d);
        background.setArcWidth(15.0d);
        Image img = new Image("/pictures/wood-background.jpg");
        background.setFill(new ImagePattern(img));

        String[] settings = {"Avengers", "Normal"};

        ChoiceBox<? extends String> menu = new ChoiceBox<>(FXCollections.observableArrayList(settings));
        menu.setPrefSize(75, 30);
        // Add sounds if buttons are clicked on
        Label label = new Label("");

        // if the item of the list is changed
        menu.getSelectionModel().selectedIndexProperty().addListener((ov, value, new_value) -> {
            label.setText(settings[new_value.intValue()]);
            String sep = System.getProperty("file.separator") + System.getProperty("file.separator");
            String srcDir = System.getProperty("user.dir") + sep + "HackTheU" + sep + "src" + sep;
            File file = null;

            if (label.getText().equals("Avengers")) {
                chess.changeStyle("avengers");
                file = new File(srcDir + "sounds" + sep + "assemble.mp3");
            }
            else if (label.getText().equals("Normal")) {
                chess.changeStyle("normal");
                file = new File(srcDir + "sounds" + sep +"short-definite-fart.wav");
            }
            assert file != null;
            Media sound = new Media((file).toURI().toString());
            MediaPlayer player = new MediaPlayer(sound);
            player.play();
        });
        stack.getChildren().addAll(background, chess, menu);
        menu.setTranslateX(-325);
        menu.setTranslateY(-300);
        chess.setTranslateX(62);
        chess.setTranslateY(0);
        stack.setStyle("-fx-background-color: rgba(255,186,26,0.64)");
        stage.setScene(new Scene(stack, 750, 650));
        stage.show();
    }
}
