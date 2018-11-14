package gui;

import javafx.application.Application;

import java.awt.Component;

public class GuiFx extends AbstractGui {
    private Application fxApp;

    public GuiFx() {
        fxApp = new FxApplication();
        fxApp.launch(FxApplication.class);
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

}
