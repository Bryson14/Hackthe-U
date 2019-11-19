import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.media.*;
import javafx.scene.media.MediaPlayer;
import java.io.File;

// Sorry Bryson, I was tweaking this to see if this drag thing would work.

public class anotherTest extends Application {
    static class Delta { double x, y; }

    @Override
    public void start(Stage primaryStage) {
        String sep = System.getProperty("file.separator") + System.getProperty("file.separator");
        String srcDir = System.getProperty("user.dir") + sep + "HackTheU" + sep + "src" + sep;
        File file = new File(srcDir + "sounds" + sep +"assemble.mp3");
        Chess chess = new Chess();
        chess.updateText("AVENGERS ASSEMBLE");
        Button bt = new Button("AVENGERS");
//        String assemble = ("file");
        Media sound = new Media(file.toURI().toString());
        MediaPlayer player = new MediaPlayer(sound);
        bt.setOnAction(e->{
            chess.changeStyle("avengers");
            player.play();
//            player.setVolume(0.5);
        });
        String sep1 = System.getProperty("file.separator") + System.getProperty("file.separator");
        String srcDir1 = System.getProperty("user.dir") + sep1 + "HackTheU" + sep1 + "src" + sep1;
        File file1 = new File(srcDir1 + "sounds" + sep1 +"short-definite-fart.wav");
        Button bt2 = new Button("NORMAL");
//        String fart = "/src/sounds\\short-definite-fart.wav";
        Media sound1 = new Media((file1).toURI().toString());
        MediaPlayer player1 = new MediaPlayer(sound1);
        bt2.setOnAction(e->{
            chess.changeStyle("normal");
            player1.play();
        });
        primaryStage.setScene(new Scene(new VBox(chess, new HBox(bt, bt2))));

        primaryStage.show();
}
}
