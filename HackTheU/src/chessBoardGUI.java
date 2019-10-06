import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;


public class chessBoardGUI {

    public static void start2(Stage stage) {
        chessBoard cb = new chessBoard();
        cb.getGrid();

        StackPane layout = new StackPane();
        layout.setStyle("-fx-background-color: rgba(255,186,26,0.64)");
        Scene scene = new Scene(layout, 750, 650);

        ArrayList<String> qwerty = new ArrayList<>();

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
        icons.add(" ");

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
                Image image = new Image(chessBoardGUI.class.getResourceAsStream(icons.get(listValue)));
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
                button.setId("" + listValue);



                button.setOnAction(event -> {
                    if (qwerty.isEmpty()) {
                        String number = icons.get(Integer.parseInt(button.getId()) - 1);
                        qwerty.add(number);
                    }
                    else{
                        ImageView imageView2 = new ImageView(new Image(chessBoardGUI.class.getResourceAsStream(qwerty.get(0))));
                        imageView2.setFitWidth(40);
                        imageView2.setFitHeight(40);
                        button.setGraphic(imageView2);
                        qwerty.remove(0);
                    }
                });

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