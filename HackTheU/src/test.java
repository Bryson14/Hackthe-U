import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class test extends Application {

    @Override
    public void start(Stage primaryStage) {

        Scene gameScene = new gamePlayScene().getScene();

        Button bt = new Button("Go to next Game");
        bt.setTranslateY(50);
        bt.setTranslateX(50);
        bt.setOnAction(e -> {
            primaryStage.setScene(gameScene);
            primaryStage.setTitle("Why not a Game of Chess?");
        });
        primaryStage.setScene(new Scene(new Pane(bt)));
        primaryStage.setHeight(700);
        primaryStage.setWidth(1000);
        primaryStage.show();
    }
}
