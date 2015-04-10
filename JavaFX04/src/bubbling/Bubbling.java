package bubbling;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Bubbling extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane pane = new BorderPane();
		
		Button saveButton = new Button("Save");
		Button cancelButton = new Button("Cancel");
		
		HBox box = new HBox(10, saveButton, cancelButton);
		box.setPadding(new Insets(10));
		box.setAlignment(Pos.CENTER_RIGHT);
		
		pane.setBottom(box);
		
		Scene scene = new Scene(pane, 200, 200);
		
		EventHandler<ActionEvent> handlerHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.format("Action on %s handled (target = %s)\n",
						event.getSource().getClass().getSimpleName(),
						event.getTarget().getClass().getSimpleName());
			}
		};
		
		EventHandler<ActionEvent> handlerFilter = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.format("Action on %s filtered (target = %s)\n",
						event.getSource().getClass().getSimpleName(),
						event.getTarget().getClass().getSimpleName());
			}
		};
		
		saveButton.addEventHandler(ActionEvent.ACTION, handlerHandler);
		saveButton.addEventHandler(ActionEvent.ACTION, handlerHandler);
		box.addEventHandler(ActionEvent.ACTION, handlerHandler);
		pane.addEventHandler(ActionEvent.ACTION, event -> { handlerHandler.handle(event); /* event.consume(); */ });
		scene.addEventHandler(ActionEvent.ACTION, handlerHandler);
		stage.addEventHandler(ActionEvent.ACTION, handlerHandler);
		
		saveButton.addEventFilter(ActionEvent.ACTION, handlerFilter);
		box.addEventFilter(ActionEvent.ACTION, handlerFilter);
		pane.addEventFilter(ActionEvent.ACTION, event -> { handlerFilter.handle(event); /* event.consume(); */ });
		scene.addEventFilter(ActionEvent.ACTION, handlerFilter);
		stage.addEventFilter(ActionEvent.ACTION, handlerFilter);
		
		cancelButton.setOnAction(e -> stage.close());
		
		stage.setTitle("Bubbling");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
