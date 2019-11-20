import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
            Chess chess = new Chess();
            final ComboBox menu = new ComboBox();
            stage.setScene(new Scene(new VBox(menu, chess)));
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
        stage.setScene(sc);
        stage.show();
    }
}
