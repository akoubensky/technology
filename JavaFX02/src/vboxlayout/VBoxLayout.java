package vboxlayout;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VBoxLayout extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane pane = new BorderPane();
		
		pane.setLeft(addVBox());
		
		Scene scene = new Scene(pane, 300, 200);
		
		stage.setScene(scene);
		stage.setTitle("HBox layout sample");
		stage.show();
	}

	private VBox addVBox() {
	    VBox vbox = new VBox();
	    vbox.setPadding(new Insets(10));
	    vbox.setSpacing(8);
	    
	    vbox.getChildren().add(new Hyperlink("Sales"));
	    vbox.getChildren().add(new Hyperlink("Marketing"));
	    vbox.getChildren().add(new Hyperlink("Distribution"));
	    vbox.getChildren().add(new Hyperlink("Costs"));
	    
	    vbox.getChildren().forEach(hp -> VBox.setMargin(hp, new Insets(0, 0, 0, 8)));
	    
	    Text title = new Text("Data");
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
	    vbox.getChildren().add(0, title);
	
	    return vbox;
	}
	
	public static void main(String[] args) {
		launch();
	}

}
