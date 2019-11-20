import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.media.*;
import javafx.scene.media.MediaPlayer;
import java.io.File;


public class Test extends Application {
    static class Delta { double x, y; }

    @Override
    public void start(Stage primaryStage) {
        // Create instance of Chess game
        ChessClient chess = new ChessClient();

        // Drop down menu
        Button Avengers = new Button("AVENGERS");
        Button Normal = new Button("NORMAL");

        final ComboBox menu = new ComboBox();
        menu.getItems().addAll(
                Avengers, Normal);
        menu.setPromptText("Settings");

        // Add sounds if buttons are clicked on
        String sep = System.getProperty("file.separator") + System.getProperty("file.separator");
        String srcDir = System.getProperty("user.dir") + sep + "HackTheU" + sep + "src" + sep;
        File file = new File(srcDir + "sounds" + sep + "assemble.mp3");
        Media sound = new Media(file.toURI().toString());
        MediaPlayer player = new MediaPlayer(sound);
        Avengers.setOnAction(e->{
            chess.changeStyle("avengers");
            player.play();
        });
        String srcDir1 = System.getProperty("user.dir") + sep + "HackTheU" + sep + "src" + sep;
        File file1 = new File(srcDir1 + "sounds" + sep +"short-definite-fart.wav");
        Media sound1 = new Media((file1).toURI().toString());
        MediaPlayer player1 = new MediaPlayer(sound1);
        Normal.setOnAction(e->{
            chess.changeStyle("normal");
            player1.play();
        });

        primaryStage.setScene(new Scene(new VBox(menu, chess)));
        primaryStage.show();
}
}
