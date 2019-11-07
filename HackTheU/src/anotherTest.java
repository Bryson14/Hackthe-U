import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Also what's this?

public class anotherTest extends Application {

    @Override
    public void start(Stage primaryStage) {
        Chess chess = new Chess();

        primaryStage.setScene(new Scene(chess));
        primaryStage.show();
    }
}
