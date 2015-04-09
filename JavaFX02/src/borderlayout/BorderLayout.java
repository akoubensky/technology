package borderlayout;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BorderLayout extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane pane = new BorderPane();
		
		HBox topBox = new HBox();
		topBox.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
		topBox.setPrefSize(350,  30);
		topBox.setAlignment(Pos.CENTER);
		topBox.getChildren().add(new Label("Top"));
		
		HBox leftBox = new HBox();
		leftBox.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, null, null)));
		leftBox.setPrefSize(100,  50);
		leftBox.setAlignment(Pos.CENTER);
		leftBox.getChildren().add(new Label("Left"));
		
		HBox centerBox = new HBox();
		centerBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
		centerBox.setPrefSize(150,  50);
		centerBox.setAlignment(Pos.CENTER);
		centerBox.getChildren().add(new Label("Center"));
		
		HBox rightBox = new HBox();
		rightBox.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, null, null)));
		rightBox.setPrefSize(100,  50);
		rightBox.setAlignment(Pos.CENTER);
		rightBox.getChildren().add(new Label("Right"));
		
		HBox bottomBox = new HBox();
		bottomBox.setBackground(new Background(new BackgroundFill(Color.LIGHTSALMON, null, null)));
		bottomBox.setPrefSize(350,  30);
		bottomBox.setAlignment(Pos.CENTER);
		bottomBox.getChildren().add(new Label("Bottom"));
		
		pane.setTop(topBox);
		pane.setBottom(bottomBox);
		pane.setLeft(leftBox);
		pane.setCenter(centerBox);
		pane.setRight(rightBox);
		
		Scene scene = new Scene(pane);
		
		stage.setScene(scene);
		stage.setTitle("Border layout sample");
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}
