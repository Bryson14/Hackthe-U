import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class dropDownMenu extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    final Button button = new Button ("Send");
    final Label notification = new Label ();
    final TextField subject = new TextField("");
    final TextArea text = new TextArea ("");

    String address = " ";

    @Override public void start(Stage stage) {
        stage.setTitle("ComboBoxSample");
        Scene scene = new Scene(new Group(), 450, 250);

        final ComboBox emailComboBox = new ComboBox();
        emailComboBox.getItems().addAll(
                button,
                "isabella.johnson@example.com",
                "ethan.williams@example.com",
                "emma.jones@example.com",
                "michael.brown@example.com"
        );

        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(new Label("Settings: "), 0, 0);
        grid.add(emailComboBox, 1, 0);
        grid.add(new Label("Subject: "), 0, 1);

        Group root = (Group)scene.getRoot();
        root.getChildren().add(grid);
        stage.setScene(scene);
        stage.show();
    }
}