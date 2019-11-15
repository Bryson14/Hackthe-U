import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
        respawn.setOnAction(event1 -> {
            mainMenu.openMainMenu(new Stage());
            stage.close();
        });

        // Test parameters and Text
        int wrapText = 600;
        javafx.scene.text.Font ruleFont = new Font("Comic Sans", 12);
        Text pawn = new Text("THE PAWN\nPawn chess pieces can only directly forward one square, with two exceptions. " +
                "Pawns can move directly forward two squares on their first move only. Pawns can move diagonally " +
                "forward when capturing an opponent's chess piece. Once a pawn chess piece reaches the other side " +
                " of the chess board, the player may \"trade\" the pawn in for any other chess piece if they " +
                "choose, except another king.\n");
        pawn.setFont(ruleFont);
        pawn.setWrappingWidth(wrapText);

        Text rook = new Text("THE ROOK\nThe rook piece can move forward, backward, left or right at any time. The " +
                "rook piece can move anywhere from 1 to 7 squares in any direction, so long as it is not " +
                "obstructed by any other piece.\n");
        rook.setFont(ruleFont);
        rook.setWrappingWidth(wrapText);

        Text bishop = new Text("THE BISHOP\nThe bishop can move in any direction diagonally, so long as it is not " +
                "obstructed by another piece. The bishop piece cannot move past any piece that is obstructing " +
                "its path. The bishop can take any other piece on the board that is within its bounds of movement.\n");
        bishop.setFont(ruleFont);
        bishop.setWrappingWidth(wrapText);

        Text knight = new Text("THE KNIGHT\nThis piece can move forward, backward, left or right two squares and " +
                "must then move one square in either perpendicular direction. The Knight piece can only move " +
                "to one of up to eight positions on the board. The Knight piece can move to any position not " +
                "already inhabited by another piece of the same color. The Knight piece can skip over any " +
                "other pieces to reach its destination position.\n");
        knight.setFont(ruleFont);
        knight.setWrappingWidth(wrapText);

        Text queen = new Text("THE QUEEN\nThe queen can move in any direction on a straight or diagonal path. The queen " +
                "cannot \"jump\" over any piece on the board, so its movements are restricted to any " +
                "direction of unoccupied squares. The queen can be used to capture any of your opponent's " +
                "pieces on the board.\n");
        queen.setFont(ruleFont);
        queen.setWrappingWidth(wrapText);

        Text king = new Text("THE KING\nThis piece can move one single square in any direction. The king cannot " +
                "move onto a square that is currently occupied by a piece from its own team. The king piece " +
                "cannot move to any square that puts them into a \"check\" position. The king piece can " +
                "participate in a move known as \"castling\", where the piece can move up to three squares " +
                "while exchanging places with a rook chess piece.\n");
        king.setFont(ruleFont);
        king.setWrappingWidth(wrapText);

        // Scene, VBox, and Border Set Up
        VBox vBox = new VBox(title2, pawn, rook, bishop, knight, queen, king, respawn);
        BorderPane bp = new BorderPane();
        bp.setCenter(vBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: rgba(255,186,26,0.64)");
        Scene sc = new Scene(bp, 750, 650);
        stage.setScene(sc);
        stage.show();
    }
}
