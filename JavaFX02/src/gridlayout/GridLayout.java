package gridlayout;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GridLayout extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Scene scene = new Scene(addGridPane());
		
		stage.setScene(scene);
		stage.setTitle("HBox layout sample");
		stage.show();
	}

	public GridPane addGridPane() {
	    GridPane grid = new GridPane();
	    grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(0, 10, 0, 10));

	    // Category in column 2, row 1
	    Text category = new Text("Sales:");
	    category.setFont(Font.font("Arial", FontWeight.BOLD, 20));
	    grid.add(category, 1, 0); 

	    // Title in column 3, row 1
	    Text chartTitle = new Text("Current Year");
	    chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
	    grid.add(chartTitle, 2, 0);

	    // Subtitle in columns 2-3, row 2
	    Text chartSubtitle = new Text("Goods and Services");
	    grid.add(chartSubtitle, 1, 1, 2, 1);

	    // House icon in column 1, rows 1-2
	    ImageView imageHouse = new ImageView(
	      new Image(getClass().getResourceAsStream("house.png")));
	    grid.add(imageHouse, 0, 0, 1, 2); 

	    // Left label in column 1 (bottom), row 3
	    Text goodsPercent = new Text("Goods\n80%");
	    GridPane.setValignment(goodsPercent, VPos.BOTTOM);
	    grid.add(goodsPercent, 0, 2); 

	    // Chart in columns 2-3, row 3
	    ImageView imageChart = new ImageView(
	     new Image(getClass().getResourceAsStream("piechart.png")));
	    grid.add(imageChart, 1, 2, 2, 1); 

	    // Right label in column 4 (top), row 3
	    Text servicesPercent = new Text("Services\n20%");
	    GridPane.setValignment(servicesPercent, VPos.TOP);
	    grid.add(servicesPercent, 3, 2);

	    return grid;
	}	
	
	public static void main(String[] args) {
		launch();
	}

}
