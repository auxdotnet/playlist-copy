package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FxApplication extends Application implements EventHandler<ActionEvent> {
    private Button copyButton, dirChooserButton, playlistOpenerButton;
    private TextArea copyText, dirChooserText, playlistOpenerText;
    private Label infoLabel;

    public FxApplication() {
        //launch();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Playlist-Copy");
        copyButton = new Button("Copy!");
        copyButton.setOnAction(this);

        StackPane layout = new StackPane();
        layout.getChildren().add(copyButton);

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("action!");
    }
}
