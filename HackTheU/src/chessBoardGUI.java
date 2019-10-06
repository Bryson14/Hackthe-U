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
        int rowNum = 8;
        int colNum = 8;
        int starty = -250;
        boolean value = false;
        for (int row = 0; row < rowNum; row++) {
            int startx = -180;
            starty = starty + 52;
            value ^= true;
            for (int col = 0; col < colNum; col++) {
                value ^= true;
                Button button = new Button();
                button.setTranslateX(startx);
                button.setTranslateY(starty);
                if (value){
                    button.setStyle("-fx-background-color: black");
                }
                else{
                    button.setStyle("-fx-background-color: grey");
                }
                button.setMaxSize(50, 50);
                layout.getChildren().add(button);
                startx = startx + 52;

            }
        }

        Scene scene = new Scene(layout, 750, 650);
        stage.setTitle("Chess Board");
        stage.setScene(scene);

        stage.show();
    }

    GridPane gridPane = new GridPane();


    public static void main(String[] args)
    {
        Application.launch(args);
    }

}