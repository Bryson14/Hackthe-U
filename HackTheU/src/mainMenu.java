import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class mainMenu extends Application {
    public void start(Stage s){
        openMainMenu(s);
    }

    public void openMainMenu(Stage s){
        // Set up Title and other title
        s.setTitle("Chess (Main Menu)");
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
            StackPane root2 = new StackPane();
            Scene secondScene = new Scene(root2, 750,650);
            Stage secondStage = new Stage();
            secondStage.setScene(secondScene);
            secondStage.setTitle("Chess");
//            chessBoardGUI.start2(secondStage);
            secondStage.show();
            s.close();
        });

        // Rules Button logic
        rulesButton.setOnAction(event -> {
            Pane rulesPane = new Pane();
            rulesPane.setStyle("-fx-background-color: rgba(255,186,26,0.64)");

            Text title2 = new Text("Rules");
            title2.setTranslateX(0);
            title2.setTranslateY(-270);
            title2.setFont(new Font("Algerian",70));

            Button respawn = new Button();
            respawn.setOnAction(event1 -> {
                Stage third = new Stage();
                third.setScene(new Scene(new StackPane(), 750,650));

                openMainMenu(third);
                third.show();
                s.close();
            });
            rulesPane.getChildren().addAll(title2, respawn);
            Scene rulesScene = new Scene(rulesPane, 750,650);
            Stage rulesStage = new Stage();
            rulesStage.setScene(rulesScene);
            rulesStage.setTitle("Chess (Rules)");
            rulesStage.show();
            s.close();
        });

        // Quit Button logic
        quitButton.setOnAction(event -> s.close());


        // Set Up Stage and Scene
        Pane buttonHolder = new Pane();
        buttonHolder.getChildren().addAll(gameButton, rulesButton, quitButton, title);
        buttonHolder.setStyle("-fx-background-color: rgba(255,186,26,0.64)");
        Scene sc = new Scene(buttonHolder, 750, 650);
        s.setScene(sc);
        s.show();
    }
}