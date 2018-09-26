import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;


public class CopyButton extends JPanel implements ActionListener {
    private CopyDirChooser copyDirChooser;
    private PlaylistFileOpener playlistFileOpener;

    private JButton button;
    private JTextArea text;
    private JLabel infoLabel;

    CopyButton(CopyDirChooser fc, PlaylistFileOpener fo, JLabel infoLabel) {
        copyDirChooser = fc;
        playlistFileOpener = fo;
        this.infoLabel = infoLabel;

        button = new JButton("Start Copy!");
        text = new JTextArea("");
        button.addActionListener(this);
        setLayout(new GridLayout(1, 0));
        add(button);
        add(text);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Thread(() -> {
            String path = copyDirChooser.getDir();
            ArrayList<File> list = playlistFileOpener.getList();
            int amount = 0;

            System.out.println("Start Copying!");
            text.setText("Start Copying!");
            infoLabel.setText("Copying started ..");
            infoLabel.revalidate();
            infoLabel.repaint();

            for (File file : list) {
                try {
                    System.out.println(amount + "/" + list.size() + " - Currently copying: " + file.getName());
                    infoLabel.setText(amount + "/" + list.size() + " - Currently copying: " + file.getName());
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
            text.setText(amount + " Tracks copied!");


            System.out.println("Finished Copying!");
        }).start();

    }

}
