import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class mainMenu extends Application {
    public void start(Stage s){
        openMainMenu(s);
    }

    public void openMainMenu(Stage s){
        s.setTitle("Chess (Main Menu)");
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

        rulesButton.setOnAction(event -> {
            StackPane rulesPane = new StackPane();
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

        quitButton.setOnAction(event -> s.close());



        StackPane buttonHolder = new StackPane();
        buttonHolder.getChildren().addAll(gameButton, rulesButton, quitButton, title);
        buttonHolder.setStyle("-fx-background-color: rgba(255,186,26,0.64)");

        Scene sc = new Scene(buttonHolder, 750, 650);
        s.setScene(sc);
        s.show();
    }
}