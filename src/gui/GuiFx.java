package gui;

import data.PlaylistCopyController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;

public class GuiFx extends Application implements GuiInterface, EventHandler<ActionEvent> {
    private Button copyButton, dirChooserButton, playlistOpenerButton;
    private Label copyText, dirChooserText, playlistOpenerText;
    private Label infoLabel;

    private PlaylistCopyController controller;

    public GuiFx() {

    }

    @Override
    public void start(Stage primaryStage) {
        controller = PlaylistCopyController.getInstance(this);

        primaryStage.setTitle("Playlist-Copy");
        BorderPane topPane = new BorderPane();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(8);
        grid.setVgap(5);

        // Add Playlist File Opener Elements
        grid.add(new Label("1. Select Playlist File"), 0, 0);
        HBox playlistBox = new HBox();
        playlistOpenerButton = new Button("Select Playlist File");
        playlistOpenerButton.setOnAction(this);
        playlistOpenerText = new Label("No File Selected");
        playlistBox.getChildren().addAll(playlistOpenerButton, playlistOpenerText);
        grid.add(playlistBox, 0, 1);

        // Add Copy Dir Chooser Elements
        grid.add(new Label("2. Select Target Directory"), 0, 2);
        HBox targetBox = new HBox();
        dirChooserButton = new Button("Select Target Directory");
        dirChooserButton.setOnAction(this);
        dirChooserText = new Label("No Directory Selected");
        targetBox.getChildren().addAll(dirChooserButton, dirChooserText);
        grid.add(targetBox, 0, 3);

        // Add Start Copy Elements
        grid.add(new Label("3. Start Copying"), 0, 4);
        HBox copyBox = new HBox();
        copyButton = new Button("Start Copy");
        copyButton.setOnAction(this);
        copyText = new Label("");
        copyBox.getChildren().addAll(copyButton, copyText);
        grid.add(copyBox, 0, 5);

        // Add grid pane to top pane
        topPane.setCenter(grid);

        // Add Info Label
        infoLabel = new Label("Welcome to Playlist-Copy.");
        infoLabel.setAlignment(Pos.BOTTOM_CENTER);
        topPane.setBottom(infoLabel);

        // Put everything together in a scene
        Scene scene = new Scene(topPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void setInfoLabel(String text) {
        Platform.runLater(() -> infoLabel.setText(text));
    }

    @Override
    public void setCopyText(String text) {
        Platform.runLater(() -> copyText.setText(text));
    }

    @Override
    public void setTargetDirText(String text) {
        Platform.runLater(() -> dirChooserText.setText(text));
    }

    @Override
    public void setPlaylistOpenerText(String text) {
        Platform.runLater(() -> playlistOpenerText.setText(text));
    }

    @Override
    public Component getComponent() {
        return null;
    }

    @Override
    public File chooseTargetDir() {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.setTitle("Choose a destination folder");

        // get selected Directory
        File dirTarget = chooser.showDialog(null);

        if (dirTarget != null) {
            System.out.println("getSelectedFile() : "
                    + dirTarget.getAbsolutePath());
            setTargetDirText(dirTarget.getAbsolutePath());
        } else {
            System.out.println("No directory selected!");
        }

        return dirTarget;
    }

    @Override
    public File choosePlaylistFile(String currPath) {
        FileChooser chooser = new FileChooser();
        if (currPath != null) {
            chooser.setInitialDirectory(new File(currPath));
        } else {
            chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        }
        chooser.setTitle("Choose a destination folder");

        // get selected File
        File file = chooser.showOpenDialog(null);

        if (file != null) {
            System.out.println("getSelectedFile() : "
                    + file.getAbsolutePath());
            setTargetDirText(file.getAbsolutePath());
        } else {
            System.out.println("No file selected!");
        }

        return file;
    }

    @Override
    public void handle(ActionEvent e) {
        if (e.getSource() == copyButton) {
            controller.startCopying();
        } else if (e.getSource() == dirChooserButton) {
            controller.chooseTargetDir();
        } else if (e.getSource() == playlistOpenerButton) {
            controller.choosePlaylistFile();
        }
    }
}
