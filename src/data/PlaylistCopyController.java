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
    private ArrayList<File> fileList;
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
            String path = dirTarget.getAbsolutePath() + "\\";
            int amount = 0;

            System.out.println("Start Copying!");
            currGui.setCopyText("Start Copying!");
            currGui.setInfoLabel("Copying started ..");
            //currGui.getInfoLabel().revalidate();
            //currGui.getInfoLabel().repaint();

            for (File file : fileList) {
                try {
                    System.out.println(amount + "/" + fileList.size() + " - Currently copying: " + file.getName());
                    currGui.setInfoLabel(amount + "/" + fileList.size() + " - Currently copying: " + file.getName());
                    Files.copy(file.toPath(),
                            (new File(path + "\\" + file.getName())).toPath(),
                            StandardCopyOption.REPLACE_EXISTING);
                    amount++;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            System.out.println(amount + " Tracks copied!");
            currGui.setInfoLabel("Copy complete!");
            currGui.setCopyText(amount + " Tracks copied!");


            System.out.println("Finished Copying!");
        }).start();
    }

    public void chooseTargetDir() {
        dirTarget = currGui.chooseTargetDir();
        if (dirTarget == null) {
            System.err.println("Target Dir not found.");
        }
    }

    public void choosePlaylistFile() {
        currPlaylistFile = currGui.choosePlaylistFile(path);

        if (currPlaylistFile != null) {
            path = currPlaylistFile.getAbsolutePath();
            String drive = path.substring(0, 2);
            readPaths(drive);
            printPaths(fileList);
        } else {
            System.err.println("Path is Null!");
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
                        File f = new File(text);
                        if (f.exists()) {
                            fileList.add(f);
                        } else {
                            f = new File(drive, text);
                            if (f.exists()) {
                                fileList.add(f);
                            }
                        }
                        //check file
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

    private void printPaths(ArrayList<File> list) {
        int amount = list.size();
        for (File f : list) {
            System.out.println(f.getAbsolutePath());
        }
        System.out.println("Size: " + amount);
    }
}
