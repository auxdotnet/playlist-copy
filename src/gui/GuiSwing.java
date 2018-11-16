package gui;

import data.PlaylistCopyController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class GuiSwing extends JFrame implements GuiInterface, ActionListener {
    private JButton copyButton, dirChooserButton, playlistOpenerButton;
    private JTextArea copyText, dirChooserText, playlistOpenerText;
    private JLabel infoLabel;

    private PlaylistCopyController controller;

    public GuiSwing() {
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

        // init other components
        controller = PlaylistCopyController.getInstance(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == copyButton) {
            controller.startCopying();
        } else if (e.getSource() == dirChooserButton) {
            controller.chooseTargetDir();
        } else if (e.getSource() == playlistOpenerButton) {
            controller.choosePlaylistFile();
        }
    }

    @Override
    public void setInfoLabel(String text) {
        infoLabel.setText(text);
    }

    @Override
    public void setCopyText(String text) {
        copyText.setText(text);
    }

    @Override
    public void setTargetDirText(String text) {
        dirChooserText.setText(text);
    }

    @Override
    public void setPlaylistOpenerText(String text) {
        playlistOpenerText.setText(text);
    }

    @Override
    public Component getComponent() {
        return this;
    }

    @Override
    public File chooseTargetDir() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        chooser.setDialogTitle("Choose a destination folder");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        File dirTarget = null;

        // disable the "All files" option.
        chooser.setAcceptAllFileFilterUsed(false);
        //
        if (chooser.showOpenDialog(getComponent()) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): "
                    + chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : "
                    + chooser.getSelectedFile());
            dirTarget = chooser.getSelectedFile();
            setTargetDirText(chooser.getSelectedFile().getAbsolutePath());
        }

        return dirTarget;
    }

    @Override
    public File choosePlaylistFile(String currPath) {
        JFileChooser playlistChooser = new JFileChooser();
        File currPlaylistFile = null;

        if (currPath == null) {
            playlistChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        } else {
            playlistChooser.setCurrentDirectory(new File(currPath));
        }

        int result = playlistChooser.showOpenDialog(getComponent());
        if (result == JFileChooser.APPROVE_OPTION) {
            currPlaylistFile = playlistChooser.getSelectedFile();
        }

        return currPlaylistFile;
    }
}
