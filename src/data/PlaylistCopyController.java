package data;

import gui.GuiInterface;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class PlaylistCopyController {
    private static PlaylistCopyController instance;

    private File dirTarget, currPlaylistFile;
    private ArrayList<TrackFile> fileList;
    private String path;
    private static GuiInterface currGui;

    // Singleton getInstance
    public static PlaylistCopyController getInstance(GuiInterface gui) {
        currGui = gui;
        if (instance == null) {
            instance = new PlaylistCopyController();
        }
        return instance;
    }

    private PlaylistCopyController() {

    }

    public void startCopying() {
        new Thread(() -> {
            if (dirTarget == null) {
                currGui.setInfoLabel("Error: Target Dir not selected!");
            }
            else if (fileList == null) {
                currGui.setInfoLabel("Error: No Playlist file selected!");
            }
            else if (fileList.size() == 0) {
                currGui.setInfoLabel("Error: File List is empty!");
            }
            else {
                String path = dirTarget.getAbsolutePath() + "\\";
                int amount = 0;

                System.out.println("Start Copying!");
                currGui.setCopyText("Start Copying!");
                currGui.setInfoLabel("Copying started ..");

                for (TrackFile file : fileList) {
                    if (file.isDoCopy()) {
                        try {
                            System.out.println(amount + "/" + fileList.size() + " - Currently copying: " + file.getName());
                            currGui.setInfoLabel(amount + "/" + fileList.size() + " - Currently copying: " + file.getName());
                            Files.copy(file.toPath(),
                                    (new TrackFile(path + "\\" + file.getName())).toPath(),
                                    StandardCopyOption.REPLACE_EXISTING);
                            amount++;
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                    else {
                        System.out.println("Skipped: " + file.getName());
                    }
                }
                System.out.println(amount + " Tracks copied!");
                currGui.setInfoLabel("Copy complete!");
                currGui.setCopyText(amount + " Tracks copied!");


                System.out.println("Finished Copying!");
            }
        }).start();
    }

    public void chooseTargetDir() {
        dirTarget = currGui.chooseTargetDir();
        if (dirTarget == null) {
            System.err.println("Target Dir not found.");
            currGui.setTargetDirText("No Directory Selected");
        }
    }

    public void choosePlaylistFile() {
        currPlaylistFile = currGui.choosePlaylistFile(path);

        if (currPlaylistFile != null) {
            path = currPlaylistFile.getAbsolutePath();
            String drive = path.substring(0, 2);
            readPaths(drive);
            printPaths(fileList);
            currGui.showFileCheckboxList(fileList);
        } else {
            System.err.println("Path is Null!");
            currGui.setPlaylistOpenerText("No File Selected");
        }
    }

    private void readPaths(String drive) {
        fileList = new ArrayList<>();

        if (currPlaylistFile != null) {
            try (BufferedReader br = new BufferedReader(new FileReader(currPlaylistFile))) {
                String text;
                br.readLine();
                while ((text = br.readLine()) != null) {
                    if (!text.startsWith("#")) {
                        TrackFile f = new TrackFile(text);
                        if (f.exists()) {
                            fileList.add(f);
                        } else {
                            f = new TrackFile(drive, text);
                            if (f.exists()) {
                                fileList.add(f);
                            }
                        }
                    }
                }
            } catch (IOException exp) {
                exp.printStackTrace();
                JOptionPane.showMessageDialog(currGui.getComponent(), "Failed to read file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (currPlaylistFile != null) {
            currGui.setPlaylistOpenerText(currPlaylistFile.getAbsolutePath() + "\n" + fileList.size() + " Tracks identified");
        }
    }

    private void printPaths(ArrayList<TrackFile> list) {
        int amount = list.size();
        for (File f : list) {
            System.out.println(f.getAbsolutePath());
        }
        System.out.println("Size: " + amount);
    }
}
