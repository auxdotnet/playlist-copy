package gui;

import java.awt.*;
import java.io.File;

public interface GuiInterface {

    void setInfoLabel(String text);

    void setCopyText(String text);

    void setTargetDirText(String text);

    void setPlaylistOpenerText(String text);

    Component getComponent();

    File chooseTargetDir();

    File choosePlaylistFile(String currPath);
}
