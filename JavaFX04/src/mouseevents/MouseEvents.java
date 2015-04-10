package mouseevents;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MouseEvents extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Circle circle = new Circle(40, Color.YELLOWGREEN);
		
		circle.setLayoutX(100);
		circle.setLayoutY(50);
		
		circle.setOnMouseEntered(e -> System.out.println("Mouse entered"));
		circle.setOnMouseExited(e -> System.out.println("Mouse exited"));
		circle.setOnMousePressed(e -> System.out.println("Mouse pressed " + e.getButton().name()));
		
		Pane pane = new Pane(circle);
		
		Scene scene = new Scene(pane, 200, 100);
		
		stage.setTitle("Mouse Events");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
