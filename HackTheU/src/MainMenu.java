import javafx.application.Application;
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
import java.io.File;

public class MainMenu extends Application {
    public void start(Stage stage){
        openMainMenu(stage);
    }

    public static void openMainMenu(Stage stage){
        // Set up Title and other title
        stage.setTitle("Chess (Main Menu)");
        Text title = new Text("Chess");
        title.setFont(new Font("Algerian",120));

        // Initialize Buttons
        Button gameButtonPC = new Button("Player vs Computer");
        gameButtonPC.setTextFill(Color.WHITE);
        gameButtonPC.setFont(Font.font("Comic Sans", FontWeight.BOLD, 20));
        gameButtonPC.setStyle("-fx-background-color: rgba(54,17,0,0.92)");
        gameButtonPC.setPrefSize(250, 50);

        Button gameButtonPP = new Button("Player vs Player");
        gameButtonPP.setTextFill(Color.WHITE);
        gameButtonPP.setFont(Font.font("Comic Sans", FontWeight.BOLD, 20));
        gameButtonPP.setStyle("-fx-background-color: rgba(54,17,0,0.92)");
        gameButtonPP.setPrefSize(250, 50);

        Button rulesButton = new Button("Rules");
        rulesButton.setTextFill(Color.WHITE);
        rulesButton.setFont(Font.font("Comic Sans", FontWeight.BOLD, 20));
        rulesButton.setStyle("-fx-background-color: rgba(54,17,0,0.92)");
        rulesButton.setPrefSize(150, 50);

        Button quitButton = new Button("Quit");
        quitButton.setTextFill(Color.WHITE);
        quitButton.setFont(Font.font("Comic Sans", FontWeight.BOLD, 20));
        quitButton.setStyle("-fx-background-color: rgba(54,17,0,0.92)");
        quitButton.setPrefSize(150, 50);

        // Play Game button logic
        gameButtonPC.setOnAction(event -> {
            StackPane stack = new StackPane();
            Chess chess = new Chess();

            Rectangle background = new Rectangle(545, 543);
            background.setTranslateY(-8);
            background.setArcHeight(15.0d);
            background.setArcWidth(15.0d);
            Image img = new Image("/pictures/dank_4k_wood.jpg");
            background.setFill(new ImagePattern(img));

            String[] settings = {"Normal", "Avengers", "Main Menu", "New Game"};

            ChoiceBox<? extends String> menu = new ChoiceBox<>(FXCollections.observableArrayList(settings));
            menu.setPrefSize(75, 30);
            menu.setTooltip(new Tooltip("Change game style"));

            Label label = new Label("");

            
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
                else if (label.getText().equals("Main Menu")){
                    MainMenu.openMainMenu(stage);
                }
                else if (label.getText().equals("New Game")){
                    chess.getChildren().clear();//this needs work
                    chess.getChildren().addAll();
                    chess.newGame();
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
        });

        gameButtonPP.setOnAction(event -> {
            PlayerPlayer.playerPlayer(new Stage());
            stage.close();
                });

        // Rules Button logic
        rulesButton.setOnAction(event -> {
            RulesPage.rulesPage(new Stage());
            stage.close();
        });

        // Quit Button logic
        quitButton.setOnAction(event -> stage.close());

        // Set Up Stage and Scene
        VBox buttonHolder = new VBox();
        HBox hbox = new HBox();
        hbox.getChildren().addAll(gameButtonPP, gameButtonPC);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(30);
        buttonHolder.getChildren().addAll(title, hbox, rulesButton, quitButton);
        buttonHolder.setStyle("-fx-background-color: rgba(255,186,26,0.64)");
        buttonHolder.setAlignment(Pos.CENTER);
        buttonHolder.setSpacing(30);
        Scene sc = new Scene(buttonHolder, 750, 650);
        stage.setResizable(false);
        stage.setScene(sc);
        stage.show();
    }
}
