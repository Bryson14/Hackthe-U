import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import pieces.Coordinates;

import java.util.ArrayList;
import java.util.HashMap;


public class chessBoardGUI {

    public static void start2(Stage stage) {
        chessBoard cb = new chessBoard();

        HashMap<Coordinates, String> map = new HashMap<>();

        StackPane layout = new StackPane();
        layout.setStyle("-fx-background-color: rgba(255,186,26,0.64)");

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
        for (int i=0; i<8; i++){
            icons.add("pictures/BlackPawn.png");
        }
        for (int j=0; j<32; j++){
            icons.add(" ");
        }
        for (int i=0; i<8; i++){
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
        for (int i=0; i < icons.size(); i++) {
            for (int k = 0; k < 8; k++) {
                for (int j = 0; j < 8; j++) {
                    map.put(new Coordinates(j, k), icons.get(count));
                    count++;
                }
            }
        }

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
                Button button = new Button();
                Image image = new Image(chessBoardGUI.class.getResourceAsStream(icons.get(listValue)));
                listValue ++;
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(40);
                imageView.setFitHeight(40);
                button.setGraphic(imageView);
                button.setTranslateX(startX);
                button.setTranslateY(startY);
                if (value){
                    button.setStyle("-fx-background-color: blue");
                }
                else{
                    button.setStyle("-fx-background-color: grey");
                }
                button.setMaxSize(50, 50);
                button.setId("" + col + row);

                final String[] imgHolder = {""};
                final Coordinates[] savedcoor = {};
                button.setOnAction(event -> {
                    //first click
                    Coordinates coor = new Coordinates((int)(button.getId().toCharArray())[0], (int)(button.getId().toCharArray())[1]);
                    if (movesList.isEmpty()) {

                        //user clicked on the right team whose turn it is
                        if (cb.isOccupiedWithCorrectTeam(coor)) {
                            movesList.retainAll(cb.getAvailableMoves(coor));
                        }
                        else { //user clicked on an empty spot or on the team whose turn it is not
                            //do nothing
                        }

//                        String number = icons.get(Integer.parseInt(button.getId()) - 1);
//                        movesList.add(number);
                        savedcoor[0] = coor;
                        imgHolder[0] = map.get(coor);
                        ImageView imageView3 = new ImageView(new Image(chessBoardGUI.class.getResourceAsStream(" ")));
                        button.setGraphic(imageView3);
                    }
                    // second click
                    else{
                        if (movesList.contains(coor)) {
                            cb.movePiece(savedcoor[0], coor);
                            //this is then the destination spot


                        } else {
                            //do nothing
                        }
                        movesList.clear(); // setting up for the first click again

                        ImageView imageView2 = new ImageView(new Image(chessBoardGUI.class.getResourceAsStream(map.get(coor))));
                        imageView2.setFitWidth(40);
                        imageView2.setFitHeight(40);
                        button.setGraphic(imageView2);
                        movesList.remove(0);
                    }
                });

                layout.getChildren().add(button);
                startX += 52;

            }


        }

        Scene scene = new Scene(layout, 750, 650);
        stage.setTitle("Chess Board");
        stage.setScene(scene);

        stage.show();
    }


    public static void main(String[] args)
    {
        Application.launch(args);
    }

}