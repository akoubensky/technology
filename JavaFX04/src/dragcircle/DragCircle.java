package dragcircle;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class DragCircle extends Application {

	static final Paint[] colors = 
		{ Color.YELLOWGREEN, Color.AQUA, Color.RED, Color.DARKBLUE, Color.GREEN, Color.BROWN, Color.GRAY };

    private static final class DragContext {
        public double mouseAnchorX;
        public double mouseAnchorY;
        public double initialTranslateX;
        public double initialTranslateY;
        public boolean inDragMode;
        public int colorIndex;
    }

	@Override
	public void start(Stage stage) throws Exception {
		final DragContext dragContext = new DragContext();
		
		Circle circle = new Circle(40, colors[dragContext.colorIndex]);
		Group group = new Group(circle);
		
		circle.setLayoutX(100);
		circle.setLayoutY(50);
		
		group.addEventFilter(MouseEvent.ANY, e -> e.consume());
		
		group.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
			dragContext.mouseAnchorX = e.getX();
			dragContext.mouseAnchorY = e.getY();
			dragContext.initialTranslateX = circle.getTranslateX();
			dragContext.initialTranslateY = circle.getTranslateY();
		});
		
		group.addEventFilter(MouseEvent.MOUSE_DRAGGED, e -> {
			dragContext.inDragMode = true;
			circle.setTranslateX(dragContext.initialTranslateX + e.getX() - dragContext.mouseAnchorX);
			circle.setTranslateY(dragContext.initialTranslateY + e.getY() - dragContext.mouseAnchorY);
		});
		
		group.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> {
			if (dragContext.inDragMode) {
				dragContext.inDragMode = false;
				circle.setFill(colors[dragContext.colorIndex = (dragContext.colorIndex + 1) % colors.length]);;
			}
		});
		
		Scene scene = new Scene(group, 400, 300);
		
		stage.setTitle("Drag\'n\'drop");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}
