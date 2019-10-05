import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static javafx.scene.text.FontWeight.BOLD;
import static javafx.scene.text.FontWeight.EXTRA_BOLD;

public class mainMenu extends Application {

    public void start(Stage s)
    {
        // set title for the stage 
        s.setTitle("Chess");

        Text title = new Text("Chess");
        title.setTranslateX(0);
        title.setTranslateY(-110);

        Font font = new Font("Algerian",70);

        title.setFont(font);

        // create a button
        Button gameButton = new Button("Play Chess");
        Button rulesButton = new Button("Rules");
        Button quitButton = new Button("Quit");

        gameButton.setTranslateX(0);
        gameButton.setTranslateY(-35);
        rulesButton.setTranslateX(0);
        rulesButton.setTranslateY(10);
        quitButton.setTranslateX(0);
        quitButton.setTranslateY(55);

        EventHandler<ActionEvent> startGame = e -> {

        };

        EventHandler<ActionEvent> displayRules = e -> {

        };


        EventHandler<ActionEvent> quitGame = e -> {

        };

        gameButton.setOnAction(startGame);
        rulesButton.setOnAction(displayRules);
        quitButton.setOnAction(quitGame);

        // create a stack pane 
        StackPane r = new StackPane();

        // add button 
        r.getChildren().addAll(gameButton, rulesButton, quitButton, title);
        r.setStyle("-fx-background-color: rgba(255,186,26,0.64)");

        // create a scene 
        Scene sc = new Scene(r, 600, 600);

        // set the scene 
        s.setScene(sc);

        s.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}