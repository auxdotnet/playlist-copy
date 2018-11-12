package gui;

import data.PlaylistCopyController;

import java.awt.*;
import java.awt.event.ActionListener;

public abstract class AbstractGui implements ActionListener {

    PlaylistCopyController controller;

    public abstract void setInfoLabel(String text);
    public abstract void setCopyText(String text);
    public abstract void setTargetDirText(String text);
    public abstract void setPlaylistOpenerText(String text);

    public abstract Component getComponent();
}
