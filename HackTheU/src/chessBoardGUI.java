import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;


public class chessBoardGUI extends Application {

    public void start(Stage stage) {

        StackPane layout = new StackPane();
        Scene scene = new Scene(layout, 750, 650);

        ArrayList<String> icons = new ArrayList<>();
        icons.add("rookBlack.png");
        icons.add("KnightBlack.png");
        icons.add("BishopBlack.png");
        icons.add("queenBlack.png");
        icons.add("KingBlack.png");
        icons.add("BishopBlack.png");
        icons.add("KnightBlack.png");
        icons.add("rookBlack.png");
        for (int i=0; i<8; i++){
            icons.add("pawnBlack.png");
        }
        for (int j=0; j<32; j++){
            icons.add(" ");
        }
        for (int i=0; i<8; i++){
            icons.add("pawnWhite.png");
        }
        icons.add("rookWhite.png");
        icons.add("KnightWhite.png");
        icons.add("BishopWhite.png");
        icons.add("queenWhite.png");
        icons.add("KingWhite.png");
        icons.add("BishopWhite.png");
        icons.add("KnightWhite.png");
        icons.add("rookWhite.png");

        int rowNum = 8;
        int colNum = 8;
        int starty = -250;
        int listValue = 0;
        boolean value = false;

        for (int row = 0; row < rowNum; row++) {
            int startx = -180;
            starty += 52;
            value ^= true;
            for (int col = 0; col < colNum; col++) {
                value ^= true;
                Button button = new Button();
                Image image = new Image(getClass().getResourceAsStream(icons.get(listValue)));
                listValue ++;
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(40);
                imageView.setFitHeight(40);
                button.setGraphic(imageView);
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
                startx += 52;

            }
        }

        stage.setTitle("Chess Board");
        stage.setScene(scene);

        stage.show();
    }


    public static void main(String[] args)
    {
        Application.launch(args);
    }

}