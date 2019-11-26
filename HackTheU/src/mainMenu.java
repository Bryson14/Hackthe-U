import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

public class mainMenu extends Application {
    public void start(Stage stage){
        openMainMenu(stage);
    }

    public static void openMainMenu(Stage stage){
        // Set up Title and other title
        stage.setTitle("Chess (Main Menu)");
        Text title = new Text("Chess");
        title.setFont(new Font("Algerian",120));

        // Initialize Buttons
        Button gameButton = new Button("Player vs Computer");
        gameButton.setTextFill(Color.WHITE);
        gameButton.setFont(Font.font("Comic Sans", FontWeight.BOLD, 20));
        gameButton.setStyle("-fx-background-color: rgba(54,17,0,0.92)");
        gameButton.setPrefSize(250, 50);

        Button gameButton2 = new Button("Player vs Player");
        gameButton2.setTextFill(Color.WHITE);
        gameButton2.setFont(Font.font("Comic Sans", FontWeight.BOLD, 20));
        gameButton2.setStyle("-fx-background-color: rgba(54,17,0,0.92)");
        gameButton2.setPrefSize(250, 50);

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
        gameButton.setOnAction(event -> {
            BorderPane stack = new BorderPane();
            Chess chess = new Chess();

            String[] settings = {"Avengers", "Normal"};

            ChoiceBox<? extends String> menu = new ChoiceBox<>(FXCollections.observableArrayList(settings));

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
            stack.setTop(menu);
            stack.setCenter(chess);
            stack.setStyle("-fx-background-color: rgba(255,186,26,0.64)");
            stack.setPadding(new Insets(30, 0, 30, 60));
            stage.setScene(new Scene(stack, 750, 650));
            stage.show();
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
        hbox.getChildren().addAll(gameButton2, gameButton);
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
