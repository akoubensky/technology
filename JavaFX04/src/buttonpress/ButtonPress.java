package buttonpress;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ButtonPress extends Application {
	static final String[] colors = 
		{ "aqua", "red", "yellow", "blue", "brown", "lightgray" };

	@Override
	public void start(Stage stage) throws Exception {
		Group g = new Group();
		
		HBox box = new HBox();
		box.setPrefSize(100, 50);
		box.setStyle("-fx-background-color: aqua;");
		
		Button button = new Button("Switch");
		
		g.getChildren().addAll(box, button);
		button.setLayoutY(60);
		button.setOnAction(new EventHandler<ActionEvent>() {
			int colorIndex = 0;

			@Override
			public void handle(ActionEvent event) {
				colorIndex = (colorIndex + 1) % colors.length;
				box.setStyle("-fx-background-color: " + colors[colorIndex]);
			}
			
		});
		
		Scene scene = new Scene(g, 200, 100);
		
		stage.setTitle("ActionEvent");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
