import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RulesPage extends mainMenu{
    public static void rulesPage(Stage stage) {
        Pane rulesPane = new Pane();
        rulesPane.setStyle("-fx-background-color: rgba(255,186,26,0.64)");

        Text title2 = new Text("Rules");
        title2.setFont(new Font("Algerian",70));

        Button respawn = new Button("Main Menu");
        respawn.setOnAction(event1 -> {
            mainMenu.openMainMenu(new Stage());
            stage.close();
        });
        javafx.scene.text.Font ruleFont = new Font("Comic Sans", 12);
        Text pawn = new Text("Pawn = Pawn chess pieces can only directly forward one square, with two exceptions.\n" +
                "Pawns can move directly forward two squares on their first move only. Pawns can move diagonally \n" +
                "forward when capturing an opponent's chess piece. Once a pawn chess piece reaches the other side \n" +
                " of the chess board, the player may \"trade\" the pawn in for any other chess piece if they \n" +
                "choose, except another king.\n");
        pawn.setFont(ruleFont);

        Text rook = new Text("Rook = The rook piece can move forward, backward, left or right at any time. The \n" +
                "rook piece can move anywhere from 1 to 7 squares in any direction, so long as it is not \n" +
                "obstructed by any other piece.\n");

        Text bishop = new Text("Bishop = The bishop can move in any direction diagonally, so long as it is not \n" +
                "obstructed by another piece. The bishop piece cannot move past any piece that is obstructing \n" +
                "its path. The bishop can take any other piece on the board that is within its bounds of movement.");

        Text knight = new Text("The Knight = piece can move forward, backward, left or right two squares and \n" +
                "must then move one square in either perpendicular direction. The Knight piece can only move \n" +
                "to one of up to eight positions on the board. The Knight piece can move to any position not \n" +
                "already inhabited by another piece of the same color. The Knight piece can skip over any \n" +
                "other pieces to reach its destination position.\n");

        Text queen = new Text("The queen = can move in any direction on a straight or diagonal path. The queen \n" +
                "cannot \"jump\" over any piece on the board, so its movements are restricted to any \n" +
                "direction of unoccupied squares. The queen can be used to capture any of your opponent's \n" +
                "pieces on the board.\n");

        Text king = new Text("The king = piece can move one single square in any direction. The king cannot \n" +
                "move onto a square that is currently occupied by a piece from its own team. The king piece \n" +
                "cannot move to any square that puts them into a \"check\" position. The king piece can \n" +
                "participate in a move known as \"castling\", where the piece can move up to three squares \n" +
                "while exchanging places with a rook chess piece.\n");

        VBox vBox = new VBox(title2, king, queen, knight, bishop, rook, pawn, respawn);
        BorderPane bp = new BorderPane();
        bp.setCenter(vBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: rgba(255,186,26,0.64)");
        Scene sc = new Scene(bp, 750, 650);
        stage.setScene(sc);
        stage.show();
    }
}
