package anchorlayout;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AnchorLayout extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane pane = new BorderPane();
		
		pane.setCenter(addAnchorPane());
		
		Scene scene = new Scene(pane, 400, 200);
		
		stage.setScene(scene);
		stage.setTitle("Anchor layout sample");
		stage.show();
	}
	
	private AnchorPane addAnchorPane() {
		AnchorPane anchor = new AnchorPane();
		
		Button pressMe = new Button("Press me");
	    Button buttonSave = new Button("Save");
	    Button buttonCancel = new Button("Cancel");
	    
	    HBox hb = new HBox();
	    hb.setPadding(new Insets(0, 10, 10, 10));
	    hb.setSpacing(10);
	    hb.getChildren().addAll(buttonSave, buttonCancel);
	
	    anchor.getChildren().addAll(pressMe, hb);
	    AnchorPane.setTopAnchor(pressMe, 10.);
	    AnchorPane.setLeftAnchor(pressMe, 5.);
	    AnchorPane.setBottomAnchor(hb, 5.);
	    AnchorPane.setRightAnchor(hb, 5.);
	
		return anchor;
	}

	public static void main(String[] args) {
		launch();
	}

}
