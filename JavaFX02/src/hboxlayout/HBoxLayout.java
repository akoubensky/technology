package hboxlayout;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HBoxLayout extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane pane = new BorderPane();
		
		pane.setTop(addHBox());
		
		Scene scene = new Scene(pane, 300, 150);
		
		stage.setScene(scene);
		stage.setTitle("HBox layout sample");
		stage.show();
	}

	private HBox addHBox() {
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;");
	
	    Button buttonCurrent = new Button("Current");
	    buttonCurrent.setPrefSize(100, 20);
	
	    Button buttonProjected = new Button("Projected");
	    buttonProjected.setPrefSize(100, 20);
	    
	    hbox.getChildren().addAll(buttonCurrent, buttonProjected);
	
	    return hbox;
	}
	
	public static void main(String[] args) {
		launch();
	}

}
