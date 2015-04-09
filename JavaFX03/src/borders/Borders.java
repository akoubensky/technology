package borders;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Borders extends Application {

	@Override
	public void start(Stage stage) throws Exception {
        stage.setTitle("Borders");
        
        BorderPane pane = new BorderPane();
        
        Button button = new Button("Press me");
        Border border = new Border(new BorderStroke(Color.RED, BorderStrokeStyle.DASHED, new CornerRadii(10, 0, 15, 5, false), BorderStroke.MEDIUM));
        button.setBorder(border);
        
        HBox box = new HBox();
        box.setPrefSize(50, 40);
        box.setBorder(border);
        Background background = new Background(new BackgroundFill(Color.AQUA, new CornerRadii(15), new Insets(5, 5, 5, 5)));
        box.setBackground(background);
        
        pane.setCenter(button);
        pane.setTop(box);
        
        Scene scene = new Scene(pane, 200, 200);
        
        stage.setScene(scene);
        stage.show();
	}

    public static void main(String[] args) {
        launch(args);
    }

}
