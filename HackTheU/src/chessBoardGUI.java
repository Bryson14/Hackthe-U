import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import pieces.Coordinates;

import java.util.ArrayList;
import java.util.HashMap;


public class chessBoardGUI extends  mainMenu {

    public static void start2(Stage stage) {
        stage.setTitle("Chess (Main Menu)");
        StackPane layout = new StackPane();
        layout.setStyle("-fx-background-color: rgba(255,186,26,0.64)");

        int rowNum = 8;
        int colNum = 8;
        int startY = -250;
        int listValue = 0;
        boolean value = false;

        for (int row = 0; row < rowNum; row++) {
            int startX = -180;
            startY += 52;
            value ^= true;
            for (int col = 0; col < colNum; col++) {
                value ^= true;
                Rectangle box = new Rectangle();
                listValue++;
                box.setTranslateX(startX);
                box.setTranslateY(startY);
                if (value) {
                    box.setFill(Color.BLUE);
                } else {
                    box.setFill(Color.GREY);
                }
                box.setHeight(50);
                box.setWidth(50);
                box.setId("" + col + row);

                layout.getChildren().add(box);
                startX += 52;
            }
        }


        ChessBoard cb = new ChessBoard();
        HashMap<Coordinates, String> map = new HashMap<>();
        ArrayList<Coordinates> movesList = new ArrayList<>();

        ArrayList<String> icons = new ArrayList<>();
        icons.add("pictures/BlackRook.png");
        icons.add("pictures/BlackKnight.png");
        icons.add("pictures/BlackBishop.png");
        icons.add("pictures/BlackQueen.png");
        icons.add("pictures/BlackKing.png");
        icons.add("pictures/BlackBishop.png");
        icons.add("pictures/BlackKnight.png");
        icons.add("pictures/BlackRook.png");
        for (int i = 0; i < 8; i++) {
            icons.add("pictures/BlackPawn.png");
        }
        for (int j = 0; j < 32; j++) {
            icons.add(" ");
        }
        for (int i = 0; i < 8; i++) {
            icons.add("pictures/WhitePawn.png");
        }
        icons.add("pictures/WhiteRook.png");
        icons.add("pictures/WhiteKnight.png");
        icons.add("pictures/WhiteBishop.png");
        icons.add("queenWhite.png");
        icons.add("pictures/WhiteKing.png");
        icons.add("pictures/WhiteBishop.png");
        icons.add("pictures/WhiteKnight.png");
        icons.add("pictures/WhiteRook.png");
        icons.add(" ");

        int count = 0;
        for (int i = 0; i < icons.size(); i++) {
            for (int k = 0; k < 8; k++) {
                for (int j = 0; j < 8; j++) {
//                    map.put(new Coordinates(j, k), icons.get(count));
                    count++;
                }
            }
        }
                Button main = new Button("Main Menu");
        main.setOnAction(event -> {
            mainMenu.openMainMenu(new Stage());
            stage.close();
        });
                layout.getChildren().add(main);
                main.setTranslateX(0);
                main.setTranslateY(300);
                Scene scene = new Scene(layout, 750, 650);
                stage.setTitle("Chess Board");
                stage.setScene(scene);

                stage.show();
            }
        }