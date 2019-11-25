import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
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
        title.setTranslateX(300);
        title.setTranslateY(250);
        title.setFont(new Font("Algerian",70));

        // Initialize Buttons
        Button gameButton = new Button("Play Chess");
        Button rulesButton = new Button("Rules");
        Button quitButton = new Button("Quit");

        // Set Button locations
        gameButton.setTranslateX(350);
        gameButton.setTranslateY(315);
        rulesButton.setTranslateX(350);
        rulesButton.setTranslateY(360);
        quitButton.setTranslateX(350);
        quitButton.setTranslateY(405);

        // Play Game button logic
        gameButton.setOnAction(event -> {
            BorderPane stack = new BorderPane();
            Chess chess = new Chess();

            String[] settings = {"Avengers", "Normal"};

            ChoiceBox menu = new ChoiceBox(FXCollections.observableArrayList(settings));

            // Add sounds if buttons are clicked on
            Label label = new Label("");

            // if the item of the list is changed
            menu.getSelectionModel().selectedIndexProperty().addListener((ov, value, new_value) -> {
                label.setText(settings[new_value.intValue()]);
                String sep = System.getProperty("file.separator") + System.getProperty("file.separator");

                if (label.getText().equals("Avengers")) {
                    String srcDir = System.getProperty("user.dir") + sep + "HackTheU" + sep + "src" + sep;
                    File file = new File(srcDir + "sounds" + sep + "assemble.mp3");
                    Media sound = new Media(file.toURI().toString());
                    MediaPlayer player = new MediaPlayer(sound);
                    chess.changeStyle("avengers");
                    player.play();
                }
                else if (label.getText().equals("Normal")) {
                    String srcDir1 = System.getProperty("user.dir") + sep + "HackTheU" + sep + "src" + sep;
                    File file1 = new File(srcDir1 + "sounds" + sep +"short-definite-fart.wav");
                    Media sound1 = new Media((file1).toURI().toString());
                    MediaPlayer player1 = new MediaPlayer(sound1);
                    chess.changeStyle("normal");
                    player1.play();
                }
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
        Pane buttonHolder = new Pane();
        buttonHolder.getChildren().addAll(gameButton, rulesButton, quitButton, title);
        buttonHolder.setStyle("-fx-background-color: rgba(255,186,26,0.64)");
        Scene sc = new Scene(buttonHolder, 750, 650);
        stage.setResizable(false);
        stage.setScene(sc);
        stage.show();
    }
}
