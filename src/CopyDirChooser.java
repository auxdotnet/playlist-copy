import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class CopyDirChooser extends JPanel
        implements ActionListener {
    private JTextArea text;

    private String choosertitle;
    private File dir;

    CopyDirChooser() {
        JButton go = new JButton("Choose Copy Directory");
        text = new JTextArea("No Directory Selected!");
        go.addActionListener(this);
        setLayout(new GridLayout(1, 0));
        add(go);
        add(text);
    }

    public void actionPerformed(ActionEvent e) {
        int result;

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        chooser.setDialogTitle(choosertitle);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // disable the "All files" option.
        chooser.setAcceptAllFileFilterUsed(false);
        //
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): "
                    + chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : "
                    + chooser.getSelectedFile());
            dir = chooser.getSelectedFile();
            text.setText(chooser.getSelectedFile().getAbsolutePath());
        } else {
            System.out.println("No Selection ");
        }
    }

    String getDir() {
        return dir.getAbsolutePath() + "\\";
    }
}