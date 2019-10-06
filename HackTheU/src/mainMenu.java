import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class mainMenu extends Application {

    public void start(Stage s) {
        openMainMenu(s);
    }

    private void openMainMenu(Stage s){
        s.setTitle("Chess");

        Text title = new Text("Chess");
        title.setTranslateX(0);
        title.setTranslateY(-110);
        title.setFont(new Font("Algerian",70));

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
            StackPane root2 = new StackPane();
            Scene secondScene = new Scene(root2, 750,650);
            Stage secondStage = new Stage();
            secondStage.setScene(secondScene);
            secondStage.setTitle("Second Form");
//            chessBoardGUI.start2(secondStage);
            secondStage.show();
            s.close();
        };

        EventHandler<ActionEvent> displayRules = e -> {


        };


        EventHandler<ActionEvent> quitGame = e -> s.close();

        gameButton.setOnAction(startGame);
        rulesButton.setOnAction(displayRules);
        quitButton.setOnAction(quitGame);

        StackPane buttonHolder = new StackPane();

        buttonHolder.getChildren().addAll(gameButton, rulesButton, quitButton, title);
        buttonHolder.setStyle("-fx-background-color: rgba(255,186,26,0.64)");

        // create a scene 
        Scene sc = new Scene(buttonHolder, 750, 650);

        // set the scene 
        s.setScene(sc);

        s.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}