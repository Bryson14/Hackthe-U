import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;

import java.io.File;


public class RulesPage extends mainMenu{
    public static void rulesPage(Stage stage) {
        // Setup Pane and Title
        Text title2 = new Text("Rules");
        title2.setFont(new Font("Algerian",70));
        Pane rulesPane = new Pane();
        rulesPane.setStyle("-fx-background-color: rgba(255,186,26,0.64)");

        //Main Menu button
        Button respawn = new Button("Main Menu");
        respawn.setTextFill(Color.WHITE);
        respawn.setFont(Font.font("Comic Sans", FontWeight.BOLD, 12));
        respawn.setStyle("-fx-background-color: rgba(54,17,0,0.92)");

        respawn.setOnAction(event1 -> {
            mainMenu.openMainMenu(new Stage());
            stage.close();
        });

        // Test parameters and Text
        int wrapText = 700;
        javafx.scene.text.Font ruleFont = new Font("Comic Sans", 12);
        Text pawn1= new Text("THE PAWN");
        pawn1.setFont(Font.font("Comic Sans", FontWeight.BOLD, 14));
        Text pawn = new Text("Pawn chess pieces can only directly forward one square, with two exceptions. Pawns can" +
                " move directly forward two squares on their first move only. Pawns can move diagonally forward when" +
                " capturing an opponent's chess piece. Once a pawn chess piece reaches the other side of the chess b" +
                "oard, the player may \"trade\" the pawn in for any other chess piece if they choose, except another" +
                " king.\n");
        pawn.setFont(ruleFont);
        pawn.setWrappingWidth(wrapText);
        pawn.setTextAlignment(TextAlignment.CENTER);

        Text rook1= new Text("THE ROOK");
        rook1.setFont(Font.font("Comic Sans", FontWeight.BOLD, 14));
        Text rook = new Text("The rook piece can move forward, backward, left or right at any time. The rook piece c" +
                "an move anywhere from 1 to 7 squares in any direction, so long as it is not obstructed by any other" +
                " piece.\n");
        rook.setFont(ruleFont);
        rook.setWrappingWidth(wrapText);
        rook.setTextAlignment(TextAlignment.CENTER);

        Text bishop1= new Text("THE BISHOP");
        bishop1.setFont(Font.font("Comic Sans", FontWeight.BOLD, 14));
        Text bishop = new Text("The bishop can move in any direction diagonally, so long as it is not obstructed by " +
                "another piece. The bishop piece cannot move past any piece that is obstructing its path. The bishop" +
                " can take any other piece on the board that is within its bounds of movement.\n");
        bishop.setFont(ruleFont);
        bishop.setWrappingWidth(wrapText);
        bishop.setTextAlignment(TextAlignment.CENTER);

        Text knight1= new Text("THE KNIGHT");
        knight1.setFont(Font.font("Comic Sans", FontWeight.BOLD, 14));
        Text knight = new Text("This piece can move forward, backward, left or right two squares and must then move " +
                "one square in either perpendicular direction. The Knight piece can only move to one of up to eight " +
                "positions on the board. The Knight piece can move to any position not already inhabited by another " +
                "piece of the same color. The Knight piece can skip over any other pieces to reach its destination p" +
                "osition.\n");
        knight.setFont(ruleFont);
        knight.setWrappingWidth(wrapText);
        knight.setTextAlignment(TextAlignment.CENTER);

        Text queen1= new Text("THE QUEEN");
        queen1.setFont(Font.font("Comic Sans", FontWeight.BOLD, 14));
        Text queen = new Text("The queen can move in any direction on a straight or diagonal path. The queen cannot " +
                "\"jump\" over any piece on the board, so its movements are restricted to any direction of unoccupie" +
                "d squares. The queen can be used to capture any of your opponent's pieces on the board.\n");
        queen.setFont(ruleFont);
        queen.setWrappingWidth(wrapText);
        queen.setTextAlignment(TextAlignment.CENTER);

        Text king1= new Text("THE KING");
        king1.setFont(Font.font("Comic Sans", FontWeight.BOLD, 14));
        Text king = new Text("This piece can move one single square in any direction. The king cannot move onto a sq" +
                "uare that is currently occupied by a piece from its own team. The king piece cannot move to any squ" +
                "are that puts them into a \"check\" position. The king piece can participate in a move known as \"c" +
                "astling\", where the piece can move up to three squares while exchanging places with a rook chess p" +
                "iece.\n");
        king.setFont(ruleFont);
        king.setWrappingWidth(wrapText);
        king.setTextAlignment(TextAlignment.CENTER);

        // Scene, VBox, and Border Set Up
        VBox vBox = new VBox(title2, pawn1, pawn, rook1, rook, bishop1, bishop, knight1, knight, queen1, queen, king1, king, respawn);
        BorderPane bp = new BorderPane();
        bp.setCenter(vBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: rgba(255,186,26,0.64)");
        Scene sc = new Scene(bp, 750, 650);
        stage.setScene(sc);
        stage.show();
    }
}
