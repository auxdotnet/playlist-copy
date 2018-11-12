package data;

import gui.AbstractGui;

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
    private static AbstractGui currGui;

    // Singleton getInstance
    public static PlaylistCopyController getInstance(AbstractGui gui) {
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
            currGui.getCopyText().setText("Start Copying!");
            currGui.getInfoLabel().setText("Copying started ..");
            currGui.getInfoLabel().revalidate();
            currGui.getInfoLabel().repaint();

            for (File file : fileList) {
                try {
                    System.out.println(amount + "/" + fileList.size() + " - Currently copying: " + file.getName());
                    currGui.getInfoLabel().setText(amount + "/" + fileList.size() + " - Currently copying: " + file.getName());
                    Files.copy(file.toPath(),
                            (new File(path + "\\" + file.getName())).toPath(),
                            StandardCopyOption.REPLACE_EXISTING);
                    amount++;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            System.out.println(amount + " Tracks copied!");
            currGui.getInfoLabel().setText("Copy complete!");
            currGui.getCopyText().setText(amount + " Tracks copied!");


            System.out.println("Finished Copying!");
        }).start();
    }

    public void chooseTargetDir() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        chooser.setDialogTitle("Choose a destination folder");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // disable the "All files" option.
        chooser.setAcceptAllFileFilterUsed(false);
        //
        if (chooser.showOpenDialog(currGui) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): "
                    + chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : "
                    + chooser.getSelectedFile());
            dirTarget = chooser.getSelectedFile();
            currGui.getDirChooserText().setText(chooser.getSelectedFile().getAbsolutePath());
        } else {
            System.out.println("No Selection ");
        }
    }

    public void choosePlaylistFile() {
        JFileChooser playlistChooser = new JFileChooser();

        if (path == null) {
            playlistChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        }
        else {
            playlistChooser.setCurrentDirectory(new File(path));
        }

        int result = playlistChooser.showOpenDialog(currGui);
        if (result == JFileChooser.APPROVE_OPTION) {
            currPlaylistFile = playlistChooser.getSelectedFile();
            path = playlistChooser.getCurrentDirectory().getAbsolutePath();
        }

        if (path != null) {
            String drive = path.substring(0,2);
            readPaths(drive);
            printPaths(fileList);
        }
        else {
            System.out.println("Path is Null!");
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
                JOptionPane.showMessageDialog(currGui, "Failed to read file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (currPlaylistFile != null) {
            currGui.getPlaylistOpenerText().setText(currPlaylistFile.getAbsolutePath() + "\n" + fileList.size() + " Tracks identified");
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
