package stacklayout;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StackLayout extends Application {

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
	    
	    Separator separator = new Separator();
	    separator.setPrefSize(0, 0);
	    separator.setVisible(false);
	    HBox.setHgrow(separator, Priority.ALWAYS);
	    
	    StackPane stack = addStackPane();
	    hbox.getChildren().addAll(separator, stack);
	
	    return hbox;
	}
	
	private StackPane addStackPane() {
	    StackPane stack = new StackPane();
	    Rectangle helpIcon = new Rectangle(30, 25);
	    helpIcon.setFill(Color.web("#4977A3"));
	    helpIcon.setStroke(Color.web("#D0E6FA"));
	
	    Text helpText = new Text("?");
	    helpText.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
	    helpText.setFill(Color.WHITE);
	    helpText.setStroke(Color.web("#7080A0")); 
	
	    stack.getChildren().addAll(helpIcon, helpText);
	
	    return stack;
	}
	
	public static void main(String[] args) {
		launch();
	}

}
