package effects;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ButtonEffects extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane pane = new BorderPane();
		Background blackBackground = new Background(new BackgroundFill(Color.BLACK, null, null));
		pane.setBackground(blackBackground);
		
		Button accept = new Button("Accept");
		accept.setFont(new Font("Tahoma", 20));
		
		accept.setEffect(new Reflection());
		
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);
		KeyValue kv = new KeyValue(accept.opacityProperty(), 0.2);
		KeyFrame kf = new KeyFrame(Duration.millis(800), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
		
		HBox box = new HBox(accept);
		box.setAlignment(Pos.CENTER);
		
		pane.setTop(box);
		
		Scene scene = new Scene(pane, 200, 100);
		
		stage.setTitle("Effects");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}
