import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

// Sorry Bryson, I was tweaking this to see if this drag thing would work.

public class anotherTest extends Application {
    static class Delta { double x, y; }

    @Override
    public void start(Stage primaryStage) {
//        Pane pane = new Pane();
//        Scene scene = new Scene(pane, 500, 500);
//    javafx.scene.image.ImageView piece = new ImageView("pictures/BlackPawn.png");
//        piece.setFitHeight(60);
//        piece.setFitWidth(60);
//        piece.setTranslateX(200);
//        piece.setTranslateY(200);
//        pane.getChildren().add(piece);
//
//    Delta dragDelta = new Delta();
//        piece.setOnMousePressed(mouseEvent -> {
//        dragDelta.x = piece.getLayoutX() - mouseEvent.getSceneX();
//        dragDelta.y = piece.getLayoutY() - mouseEvent.getSceneY();
//        piece.setCursor(Cursor.MOVE);
//    });
//        piece.setOnMouseReleased(mouseEvent -> piece.setCursor(Cursor.HAND));
//        piece.setOnMouseDragged(mouseEvent -> {
//        piece.setLayoutX(mouseEvent.getSceneX() + dragDelta.x);
//        piece.setLayoutY(mouseEvent.getSceneY() + dragDelta.y);
//    });
//        piece.setOnMouseEntered(mouseEvent -> piece.setCursor(Cursor.HAND));

        Chess chess = new Chess();
        chess.updateText("you suck");
        primaryStage.setScene(new Scene(chess));

        primaryStage.show();
}
}
