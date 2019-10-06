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
        int rowNum = 0;
        int colNum = 0;
        for (int row = 0; row < rowNum; row++) {
            for (int col = 0; col < colNum; col++) {
                Button button1 = new Button();
                button1.setStyle("-fx-background-color: rgba(255, 186, 26, 0.64)");
                layout.getChildren().add(button1);
               }
        }
        /*Button button1 = new Button();
        button1.setStyle("-fx-background-color: black");
        button1.setMaxSize(50, 50);
        button1.setTranslateX(-275);
        button1.setTranslateY(-300);*/

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