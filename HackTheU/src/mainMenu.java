import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class mainMenu extends Application {

    public void start(Stage s)
    {
        // set title for the stage 
        s.setTitle("Chess");

        // create a button 
        Button rulesButton = new Button("Rules");
        Button gameButton = new Button("Play Chess");
        Button quitButton = new Button("Quit");

        rulesButton.setTranslateX(0);
        rulesButton.setTranslateY(10);
        gameButton.setTranslateX(0);
        gameButton.setTranslateY(-35);
        quitButton.setTranslateX(0);
        quitButton.setTranslateY(55);

        EventHandler<ActionEvent> displayRules = e -> {

        };

        EventHandler<ActionEvent> startGame = e -> {

        };
        EventHandler<ActionEvent> quitGame = e -> {

        };

        rulesButton.setOnAction(displayRules);
        gameButton.setOnAction(startGame);
        quitButton.setOnAction(quitGame);

        // create a stack pane 
        StackPane r = new StackPane();

        // add button 
        r.getChildren().addAll(rulesButton, gameButton, quitButton);

        // create a scene 
        Scene sc = new Scene(r, 400, 400);

        // set the scene 
        s.setScene(sc);

        s.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}