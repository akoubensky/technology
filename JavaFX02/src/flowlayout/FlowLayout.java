package flowlayout;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class FlowLayout extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane pane = new BorderPane();
		
		pane.setRight(addFlowPane());
		
		Scene scene = new Scene(pane, 300, 353);
		
		stage.setScene(scene);
		stage.setTitle("Flow layout sample");
		stage.show();
	}
	
	private FlowPane addFlowPane() {
	    FlowPane flow = new FlowPane(Orientation.HORIZONTAL);
	    flow.setPadding(new Insets(5, 5, 5, 0));
	    flow.setVgap(5);
	    flow.setHgap(5);
	    flow.setPrefWidth(162);
	    flow.setStyle("-fx-background-color: DAE6F3;");
	
	    for (int i=0; i<8; i++) {
	        ImageView picture = new ImageView(
	            new Image(
	            	getClass().getResourceAsStream(
	            	    "chart_"+(i+1)+".png")));
	        flow.getChildren().add(picture);
	    }
	
	    return flow;
	}

	public static void main(String[] args) {
		launch();
	}

}
