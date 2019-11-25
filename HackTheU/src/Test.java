import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.media.*;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.io.IOException;


public class Test extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create instance of Chess game
        Chess chess = new Chess();

//        ChessServer chess = new ChessServer();

        // Drop down menu
        String settings[] = {"Avengers", "Normal"};

        ChoiceBox menu = new ChoiceBox(FXCollections.observableArrayList(settings));

        // Add sounds if buttons are clicked on
        Label label = new Label("");

        menu.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            // if the item of the list is changed
            public void changed(ObservableValue ov, Number value, Number new_value)
            {
                label.setText(settings[new_value.intValue()]);
                String sep = System.getProperty("file.separator") + System.getProperty("file.separator");

                if (label.getText() == "Avengers") {
                    String srcDir = System.getProperty("user.dir") + sep + "HackTheU" + sep + "src" + sep;
                    File file = new File(srcDir + "sounds" + sep + "assemble.mp3");
                    Media sound = new Media(file.toURI().toString());
                    MediaPlayer player = new MediaPlayer(sound);
                    chess.changeStyle("avengers");
                    player.play();
                }
                else if (label.getText() == "Normal") {
                    String srcDir1 = System.getProperty("user.dir") + sep + "HackTheU" + sep + "src" + sep;
                    File file1 = new File(srcDir1 + "sounds" + sep +"short-definite-fart.wav");
                    Media sound1 = new Media((file1).toURI().toString());
                    MediaPlayer player1 = new MediaPlayer(sound1);
                    chess.changeStyle("normal");
                    player1.play();
                }
            }

        });

        primaryStage.setScene(new Scene(new VBox(menu, chess)));
        primaryStage.show();

//        try {
//            chess.connect();
//            chess.receiveMove();
//        } catch (IOException ex) {
//            System.out.println("problem with client");
//        }
    }
}