package tilelayout;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TileLayout extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane pane = new BorderPane();
		
		pane.setLeft(addTilePane());
		
		Scene scene = new Scene(pane, 300, 300);
		
		stage.setScene(scene);
		stage.setTitle("Tile layout sample");
		stage.show();
	}

	private TilePane addTilePane() {
	    TilePane tiles = new TilePane();
	    tiles.setVgap(5);
	    tiles.setHgap(5);
	    tiles.setPrefColumns(2);
	    tiles.setStyle("-fx-background-color: Yellow;");
	    
	    tiles.getChildren().addAll(
	    		new Text("my text"),
	    		new Button("OK"),
	    		new Label("your text"),
	    		new ImageView(new Image(
	    				getClass().getResourceAsStream("chart_1.png"))),
	    		new Button("Press me now"),
	    		new Text("This is a very long text"));
	
	    return tiles;
	}

	public static void main(String[] args) {
		launch();
	}

}
