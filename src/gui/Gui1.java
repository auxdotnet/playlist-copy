package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class Gui1 extends JFrame implements ActionListener {
    private JButton copyButton, dirChooserButton, playlistOpenerButton;
    private JTextArea copyText, dirChooserText, playlistOpenerText;
    private File dirTarget, currPlaylistFile;
    private JLabel infoLabel;
    private ArrayList<File> fileList;
    private String path;

    public Gui1() {
        super("Playlist-Copy Application");
        JPanel all = new JPanel(new GridLayout(0, 1));
        all.setPreferredSize(new Dimension(650, 300));

        infoLabel = new JLabel();
        infoLabel.setText("Welcome to the Playlist-Copy Program.");
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );

        // Add Playlist File Opener Elements
        JPanel playlistOpenerPanel = new JPanel();
        playlistOpenerButton = new JButton("Select Playlist File");
        playlistOpenerText = new JTextArea("No File Selected");
        playlistOpenerButton.addActionListener(this);
        playlistOpenerPanel.setLayout(new GridLayout(1, 0));
        playlistOpenerPanel.add(playlistOpenerButton);
        playlistOpenerPanel.add(playlistOpenerText);

        // Add Copy Dir Chooser Elements
        JPanel dirChooserPanel = new JPanel();
        dirChooserButton = new JButton("Choose Copy Directory");
        dirChooserText = new JTextArea("No Directory Selected!");
        dirChooserButton.addActionListener(this);
        dirChooserPanel.setLayout(new GridLayout(1, 0));
        dirChooserPanel.add(dirChooserButton);
        dirChooserPanel.add(dirChooserText);

        // Add Copy Button
        JPanel copyButtonPanel = new JPanel();
        copyButtonPanel.setLayout(new GridLayout(1, 0));
        copyButton = new JButton("Start Copy!");
        copyText = new JTextArea("");
        copyButton.addActionListener(this);
        copyButtonPanel.add(copyButton);
        copyButtonPanel.add(copyText);

        // Add all components
        all.add(new JLabel("1. Select Playlist file"));
        all.add(playlistOpenerPanel);
        all.add(new JLabel("2. Select destination directory"));
        all.add(dirChooserPanel);
        all.add(new JLabel("3. Press Copy"));
        all.add(copyButtonPanel);
        all.add(infoLabel);

        this.getContentPane().add(all);
        this.pack();

        this.setTitle("PlaylistCopy");

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == copyButton) {
            new Thread(() -> {
                String path = dirTarget.getAbsolutePath() + "\\";
                int amount = 0;

                System.out.println("Start Copying!");
                copyText.setText("Start Copying!");
                infoLabel.setText("Copying started ..");
                infoLabel.revalidate();
                infoLabel.repaint();

                for (File file : fileList) {
                    try {
                        System.out.println(amount + "/" + fileList.size() + " - Currently copying: " + file.getName());
                        infoLabel.setText(amount + "/" + fileList.size() + " - Currently copying: " + file.getName());
                        Files.copy(file.toPath(),
                                (new File(path + "\\" + file.getName())).toPath(),
                                StandardCopyOption.REPLACE_EXISTING);
                        amount++;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                System.out.println(amount + " Tracks copied!");
                infoLabel.setText("Copy complete!");
                copyText.setText(amount + " Tracks copied!");


                System.out.println("Finished Copying!");
            }).start();
        }
        else if (e.getSource() == dirChooserButton) {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            chooser.setDialogTitle("Choose a destination folder");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            // disable the "All files" option.
            chooser.setAcceptAllFileFilterUsed(false);
            //
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                System.out.println("getCurrentDirectory(): "
                        + chooser.getCurrentDirectory());
                System.out.println("getSelectedFile() : "
                        + chooser.getSelectedFile());
                dirTarget = chooser.getSelectedFile();
                dirChooserText.setText(chooser.getSelectedFile().getAbsolutePath());
            } else {
                System.out.println("No Selection ");
            }
        }
        else if (e.getSource() == playlistOpenerButton) {
            JFileChooser playlistChooser = new JFileChooser();

            if (path == null) {
                playlistChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            }
            else {
                playlistChooser.setCurrentDirectory(new File(path));
            }

            int result = playlistChooser.showOpenDialog(this);
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
    }

    private void readPaths(String drive) {
        fileList = new ArrayList<>();

        if (currPlaylistFile != null) {
            try (BufferedReader br = new BufferedReader(new FileReader(currPlaylistFile))) {
                String text = null;
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
                JOptionPane.showMessageDialog(this, "Failed to read file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (currPlaylistFile != null) {
            playlistOpenerText.setText(currPlaylistFile.getAbsolutePath() + "\n" + fileList.size() + " Tracks identified");
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
