import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Main {

    public static void main(String s[]) {
        JFrame frame = new JFrame("");
        JPanel all = new JPanel(new GridLayout(0, 1));
        all.setPreferredSize(new Dimension(650, 300));

        PlaylistOpener file = new PlaylistOpener();
        CopyDirChooser copyDir = new CopyDirChooser();
        CopyButton button = new CopyButton(copyDir, file);

        frame.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );

        all.add(new JLabel("1. Select Playlist file"));
        all.add(file);
        all.add(new JLabel("2. Select destination directory"));
        all.add(copyDir);
        all.add(new JLabel("3. Press Copy"));
        all.add(button);

        frame.getContentPane().add(all);
        frame.pack();

        frame.setTitle("PlaylistCopy");

        frame.setVisible(true);
    }
}