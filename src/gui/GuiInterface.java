package gui;

import java.awt.*;

public interface GuiInterface {

    void setInfoLabel(String text);
    void setCopyText(String text);
    void setTargetDirText(String text);
    void setPlaylistOpenerText(String text);

    Component getComponent();
}
