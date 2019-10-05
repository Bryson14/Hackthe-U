import javafx.application.Application;
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

        // create a stack pane 
        StackPane r = new StackPane();

        // add button 
        r.getChildren().add(rulesButton);

        // create a scene 
        Scene sc = new Scene(r, 200, 200);

        // set the scene 
        s.setScene(sc);

        s.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}