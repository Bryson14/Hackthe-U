import javafx.application.Application;
import javafx.scene.Cursor;
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
    static class Delta { double x, y; }

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

        int rowNum2 = 7;
        int colNum2 = 8;
        int startY2 = -250;
        int listValue2 = 0;

        for (int row = 0; row < rowNum2; row++) {
            int startX2 = -180;
            startY2 += 52;
            for (int col = 0; col < colNum2; col++) {
                if(icons.get(listValue2) != " "){
                    ImageView piece = new ImageView(icons.get(listValue2));
                    piece.setTranslateX(startX2);
                    piece.setTranslateY(startY2);
                    piece.setFitHeight(50);
                    piece.setFitWidth(50);
                    piece.setId("" + col + row);
                    layout.getChildren().add(piece);
                }
                listValue2++;
                startX2 += 52;


//                Delta dragDelta = new Delta();
//                piece.setOnMousePressed(mouseEvent -> {
//                    dragDelta.x = piece.getLayoutX() - mouseEvent.getSceneX();
//                    dragDelta.y = piece.getLayoutY() - mouseEvent.getSceneY();
//                    piece.setCursor(Cursor.MOVE);
//                });
//                piece.setOnMouseReleased(mouseEvent -> piece.setCursor(Cursor.HAND));
//                piece.setOnMouseDragged(mouseEvent -> {
//                    piece.setLayoutX(mouseEvent.getSceneX() + dragDelta.x);
//                    piece.setLayoutY(mouseEvent.getSceneY() + dragDelta.y);
//                });
//                piece.setOnMouseEntered(mouseEvent -> piece.setCursor(Cursor.HAND));
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