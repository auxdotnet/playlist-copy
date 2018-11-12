package gui;

import data.PlaylistCopyController;

import java.awt.*;

public abstract class AbstractGui {

    PlaylistCopyController controller;

    public abstract void setInfoLabel(String text);
    public abstract void setCopyText(String text);
    public abstract void setTargetDirText(String text);
    public abstract void setPlaylistOpenerText(String text);

    public abstract Component getComponent();
}
