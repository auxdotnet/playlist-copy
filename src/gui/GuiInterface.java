package gui;

import data.TrackFile;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public interface GuiInterface {

    void setInfoLabel(String text);

    void setCopyText(String text);

    void setTargetDirText(String text);

    void setPlaylistOpenerText(String text);

    Component getComponent();

    File chooseTargetDir();

    File choosePlaylistFile(String currPath);

    void showFileCheckboxList(ArrayList<TrackFile> fileList);
}
