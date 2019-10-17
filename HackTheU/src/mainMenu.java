import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class mainMenu extends Application {

    public void start(Stage s) {
        openMainMenu(s);
    }

    private void openMainMenu(Stage s){

        s.setTitle("Chess (Main Menu)");

        Text title = new Text("Chess");
        title.setTranslateX(0);
        title.setTranslateY(-110);
        title.setFont(new Font("Algerian",70));

        Button gameButton = new Button("Play Chess");
        Button rulesButton = new Button("Rules");
        Button quitButton = new Button("Quit");

        gameButton.setTranslateX(0);
        gameButton.setTranslateY(-35);
        rulesButton.setTranslateX(0);
        rulesButton.setTranslateY(10);
        quitButton.setTranslateX(0);
        quitButton.setTranslateY(55);


        EventHandler<ActionEvent> startGame = e -> {
            StackPane root2 = new StackPane();
            Scene secondScene = new Scene(root2, 750,650);
            Stage secondStage = new Stage();
            secondStage.setScene(secondScene);
            secondStage.setTitle("Chess");
            chessBoardGUI.start2(secondStage);
            secondStage.show();
            s.close();
        };



        EventHandler<ActionEvent> displayRules = e -> {
            StackPane rulesPane = new StackPane();
            rulesPane.setStyle("-fx-background-color: rgba(255,186,26,0.64)");

            Text title2 = new Text("Rules");
            title2.setTranslateX(0);
            title2.setTranslateY(-270);
            title2.setFont(new Font("Algerian",70));

            Button respawn = new Button();
            EventHandler<ActionEvent> returntomenu = f -> {
                Stage third = new Stage();
                third.setScene(new Scene(new StackPane(), 750,650));

                openMainMenu(third);
                third.show();
                s.close();

            };
            respawn.setOnAction(returntomenu);

            Font ruleFont = new Font("Comic Sans", 16);

            Text pawn = new Text("Pawn = Pawn chess pieces can only directly forward one square, with two exceptions. Pawns can move directly forward two squares on \n" +
                    "their first move only. Pawns can move diagonally forward when capturing an opponent's chess piece. Once a pawn chess piece reaches \n" +
                    "the other side of the chess board, the player may \"trade\" the pawn in for any other chess piece if they choose, except another king.");
            pawn.setTranslateX(0);
            pawn.setTranslateY(-150);

            Text rook = new Text("Rook = The rook piece can move forward, backward, left or right at any time. The rook piece can move anywhere from 1 to 7 squares \n" +
                    "in any direction, so long as it is not obstructed by any other piece.\n");
            rook.setTranslateX(0);
            rook.setTranslateY(-85);

            Text bishop = new Text("Bishop = The bishop can move in any direction diagonally, so long as it is not obstructed by another piece. The bishop piece \n" +
                    "cannot move past any piece that is obstructing its path. The bishop can take any other piece on the board that is within its\n " +
                    "bounds of movement.\n");
            bishop.setTranslateX(-30);
            bishop.setTranslateY(-20);

            Text knight = new Text("The Knight = piece can move forward, backward, left or right two squares and \n" +
                    "must then move one square in either perpendicular direction. The Knight piece can only move \n" +
                    "to one of up to eight positions on the board. The Knight piece can move to any position not \n" +
                    "already inhabited by another piece of the same color. The Knight piece can skip over any \n" +
                    "other pieces to reach its destination position.\n");
            knight.setTranslateX(-100);
            knight.setTranslateY(60);

            Text queen = new Text("The queen = can move in any direction on a straight or diagonal path. The queen \n" +
                    "cannot \"jump\" over any piece on the board, so its movements are restricted to any \n" +
                    "direction of unoccupied squares. The queen can be used to capture any of your opponent's \n" +
                    "pieces on the board.");
            queen.setTranslateX(-100);
            queen.setTranslateY(135);

            Text king = new Text("The king = piece can move one single square in any direction. The king cannot \n" +
                    "move onto a square that is currently occupied by a piece from its own team. The king piece \n" +
                    "cannot move to any square that puts them into a \"check\" position. The king piece can \n" +
                    "participate in a move known as \"castling\", where the piece can move up to three squares \n" +
                    "while exchanging places with a rook chess piece.");
            king.setTranslateX(-110);
            king.setTranslateY(225);

            rulesPane.getChildren().addAll(title2, pawn, rook, bishop, knight, queen, king, respawn);
            Scene rulesScene = new Scene(rulesPane, 750,650);
            Stage rulesStage = new Stage();
            rulesStage.setScene(rulesScene);
            rulesStage.setTitle("Chess (Rules)");
            rulesStage.show();
            s.close();

        };


        EventHandler<ActionEvent> quitGame = e -> s.close();

        gameButton.setOnAction(startGame);
        rulesButton.setOnAction(displayRules);
        quitButton.setOnAction(quitGame);

        StackPane buttonHolder = new StackPane();

        buttonHolder.getChildren().addAll(gameButton, rulesButton, quitButton, title);
        buttonHolder.setStyle("-fx-background-color: rgba(255,186,26,0.64)");

        // create a scene 
        Scene sc = new Scene(buttonHolder, 750, 650);

        // set the scene 
        s.setScene(sc);

        s.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}