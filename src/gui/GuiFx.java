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

import java.awt.Component;

public class GuiFx extends AbstractGui implements EventHandler<ActionEvent> {
    private Application fxApp;
    private Button copyButton, dirChooserButton, playlistOpenerButton;
    private TextArea copyText, dirChooserText, playlistOpenerText;
    private Label infoLabel;

    public GuiFx() {
        fxApp = new Application() {
            @Override
            public void start(Stage primaryStage) throws Exception {
                primaryStage.setTitle("Playlist-Copy");
                copyButton = new Button("Copy!");
                copyButton.setOnAction(GuiFx.this);

                StackPane layout = new StackPane();
                layout.getChildren().add(copyButton);

                Scene scene = new Scene(layout, 300, 200);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        };

    }

    @Override
    public void setInfoLabel(String text) {

    }

    @Override
    public void setCopyText(String text) {

    }

    @Override
    public void setTargetDirText(String text) {

    }

    @Override
    public void setPlaylistOpenerText(String text) {

    }

    @Override
    public Component getComponent() {
        return null;
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("test");
    }
}
