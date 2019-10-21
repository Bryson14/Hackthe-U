import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.ArrayList;

public class RulesPage {
    private Scene scene;

    RulesPage() {
        Text pawn = new Text("Pawn = Pawn chess pieces can only directly forward one square, with two exceptions. Pawns can move directly forward two squares on \n" +
                "their first move only. Pawns can move diagonally forward when capturing an opponent's chess piece. Once a pawn chess piece reaches \n" +
                "the other side of the chess board, the player may \"trade\" the pawn in for any other chess piece if they choose, except another king.");

        Text rook = new Text("Rook = The rook piece can move forward, backward, left or right at any time. The rook piece can move anywhere from 1 to 7 squares \n" +
                "in any direction, so long as it is not obstructed by any other piece.\n");

        Text bishop = new Text("Bishop = The bishop can move in any direction diagonally, so long as it is not obstructed by another piece. The bishop piece \n" +
                "cannot move past any piece that is obstructing its path. The bishop can take any other piece on the board that is within its\n " +
                "bounds of movement.\n");

        Text knight = new Text("The Knight = piece can move forward, backward, left or right two squares and \n" +
                "must then move one square in either perpendicular direction. The Knight piece can only move \n" +
                "to one of up to eight positions on the board. The Knight piece can move to any position not \n" +
                "already inhabited by another piece of the same color. The Knight piece can skip over any \n" +
                "other pieces to reach its destination position.\n");

        Text queen = new Text("The queen = can move in any direction on a straight or diagonal path. The queen \n" +
                "cannot \"jump\" over any piece on the board, so its movements are restricted to any \n" +
                "direction of unoccupied squares. The queen can be used to capture any of your opponent's \n" +
                "pieces on the board.");

        Text king = new Text("The king = piece can move one single square in any direction. The king cannot \n" +
                "move onto a square that is currently occupied by a piece from its own team. The king piece \n" +
                "cannot move to any square that puts them into a \"check\" position. The king piece can \n" +
                "participate in a move known as \"castling\", where the piece can move up to three squares \n" +
                "while exchanging places with a rook chess piece.");

        HBox hbox = new HBox();
        Node[] nodes = {king, queen, knight, bishop, rook, pawn};
        for (Node n: nodes) {
            hbox.getChildren().add(n);
        }
        BorderPane bp = new BorderPane();
        scene = new Scene(hbox);
        //TODO don't know why this is so weird
    }

    public Scene getScene() {
        return scene;
    }
}
