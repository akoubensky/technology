package checkboxsample;

import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class CheckboxSample extends Application {

    final String[] names = { "Security", "Project", "Chart" };
    final Image[] images = new Image[names.length];
    final ImageView[] icons = new ImageView[names.length];
    final CheckBox[] cbs = new CheckBox[names.length];

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        Rectangle rect = new Rectangle(90, 30);
        rect.setArcHeight(10);
        rect.setArcWidth(10);
        rect.setFill(Color.rgb(41, 41, 41));

        for (int i = 0; i < names.length; i++) {
            final Image image = images[i] =
                new Image(getClass().getResourceAsStream(names[i] + ".png"));
            final ImageView icon = icons[i] = new ImageView();
            final CheckBox cb = cbs[i] = new CheckBox(names[i]);
            cb.selectedProperty().addListener(
            		(ov, old_val, new_val) -> icon.setImage(new_val ? image : null));
        }

        VBox vbox = new VBox();
        vbox.getChildren().addAll(cbs);
        vbox.setSpacing(5);

        HBox hbox = new HBox();
        hbox.getChildren().addAll(icons);
        hbox.setPadding(new Insets(0, 0, 0, 5));

        StackPane stack = new StackPane();

        stack.getChildren().add(rect);
        stack.getChildren().add(hbox);
        StackPane.setAlignment(rect, Pos.TOP_CENTER);                

        HBox root = new HBox();
        root.getChildren().add(vbox);
        root.getChildren().add(stack);
        root.setSpacing(40);
        root.setPadding(new Insets(20, 10, 10, 20));

        Group group = new Group();
        Scene scene = new Scene(group, 230, 110);
        group.getChildren().add(root);

        stage.setTitle("Checkbox Sample");
        stage.setScene(scene);
        stage.show();
    }
}
