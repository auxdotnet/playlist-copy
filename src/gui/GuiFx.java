package gui;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.awt.event.ActionEvent;

public class GuiFx extends AbstractGui {
    private Application fxApp;
    private Button copyButton, dirChooserButton, playlistOpenerButton;
    private TextArea copyText, dirChooserText, playlistOpenerText;
    private Label infoLabel;

    public GuiFx() {

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
    public void actionPerformed(ActionEvent e) {

    }
}
