package borders;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BordersCSS extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) {
        stage.setTitle("Borders");
                
        BorderPane pane = new BorderPane();
        
        Button button = new Button("Press me");
        button.getStyleClass().add("border");
        
        HBox box = new HBox();
        box.getStyleClass().add("border");
        box.getStyleClass().add("background");
        box.setPrefSize(100, 100);

        pane.setCenter(button);
        pane.setTop(box);
        
        Scene scene = new Scene(pane, 200, 300);
		scene.getStylesheets().add("borders/borders.css");
        
        stage.setScene(scene);
        stage.show();
    }
}