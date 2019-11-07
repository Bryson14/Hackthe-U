import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

// Bryson, what is this?

public class test extends Application {

    @Override
    public void start(Stage primaryStage) {

        Scene gameScene = new ChessPane(primaryStage).getScene();
//        Scene rulesScene = new RulesPage().getScene();

        Button bt = new Button("Go to next Game");
        bt.setTranslateY(50);
        bt.setTranslateX(200);
        bt.setOnAction(e -> {
            primaryStage.setScene(gameScene);
            primaryStage.setTitle("Why not a Game of Chess?");
        });

        Button bt2 = new Button("Go to Rules");
        bt2.setTranslateY(150);
        bt2.setTranslateX(200);
        bt2.setOnAction(e -> {
//            primaryStage.setScene(rulesScene);
            primaryStage.setTitle("Learn to play sucka!");
        });

        primaryStage.setScene(new Scene(new Pane(bt, bt2)));
        primaryStage.setHeight(750);
        primaryStage.setWidth(1000);
        primaryStage.show();

        //TODO learn how to make a call back so closing a game or rules return to the main page
    }
}
