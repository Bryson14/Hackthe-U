import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class chessBoardGUI extends Application {
    @Override
    public void start(Stage stage) {
        //Instantiating the BorderPane class
        BorderPane bPane = new BorderPane();

        //Make stack pane
        StackPane layout = new StackPane();

        // Create Buttons
        int rowNum = 7;
        int colNum = 7;
        int startY = -300;
        Boolean isWhite = false;
        for (int col = 0; col < colNum; col++) {
            int startX = -300;
            startY += 52;
            for (int row = 0; row < rowNum; row++) {
                Button button = new Button();
                if (isWhite) {
                    button.setStyle("-fx-background-color: black");
                } else
                    button.setStyle("-fx-background-color: black");
                button.setMaxSize(50, 50);
                button.setTranslateX(startX);
                button.setTranslateY(startY);
                layout.getChildren().add(button);
                startX += 52;
            }
            }
        }

        //Creating a scene object
        Scene scene = new Scene(layout, 750, 650);
        //Setting title to the Stage
        stage.setTitle("Chess Board");
        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }

    GridPane gridPane = new GridPane();


    public static void main(String[] args)
    {
        Application.launch(args);
    }

}