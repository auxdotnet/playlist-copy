package gui;

import data.PlaylistCopyController;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class AbstractGui extends JFrame implements ActionListener {
    JButton copyButton, dirChooserButton, playlistOpenerButton;
    JTextArea copyText, dirChooserText, playlistOpenerText;
    JLabel infoLabel;

    PlaylistCopyController controller;

    public JButton getCopyButton() {
        return copyButton;
    }

    public void setCopyButton(JButton copyButton) {
        this.copyButton = copyButton;
    }

    public JButton getDirChooserButton() {
        return dirChooserButton;
    }

    public void setDirChooserButton(JButton dirChooserButton) {
        this.dirChooserButton = dirChooserButton;
    }

    public JButton getPlaylistOpenerButton() {
        return playlistOpenerButton;
    }

    public void setPlaylistOpenerButton(JButton playlistOpenerButton) {
        this.playlistOpenerButton = playlistOpenerButton;
    }

    public JTextArea getCopyText() {
        return copyText;
    }

    public void setCopyText(JTextArea copyText) {
        this.copyText = copyText;
    }

    public JTextArea getDirChooserText() {
        return dirChooserText;
    }

    public void setDirChooserText(JTextArea dirChooserText) {
        this.dirChooserText = dirChooserText;
    }

    public JTextArea getPlaylistOpenerText() {
        return playlistOpenerText;
    }

    public void setPlaylistOpenerText(JTextArea playlistOpenerText) {
        this.playlistOpenerText = playlistOpenerText;
    }

    public JLabel getInfoLabel() {
        return infoLabel;
    }

    public void setInfoLabel(JLabel infoLabel) {
        this.infoLabel = infoLabel;
    }
}
