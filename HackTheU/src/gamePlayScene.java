import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class gamePlayScene {

    private GridPane gp = new GridPane();
    private BorderPane bp = new BorderPane();
    private Scene scene = new Scene(bp);

    gamePlayScene() {

        this.bp.setCenter(new Text(100,100, "You suck"));

    }

    public Scene getScene() {
        return scene;
    }
}
