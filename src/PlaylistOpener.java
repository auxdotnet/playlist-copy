import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class PlaylistOpener extends JPanel implements ActionListener {
    private JFileChooser chooser;
    private JTextArea text;
    private File file;
    private int amount;
    private ArrayList<File> list;
    private String path;

    PlaylistOpener() {
        JButton button = new JButton("Select Playlist File");
        text = new JTextArea("No File Selected");
        button.addActionListener(this);
        setLayout(new GridLayout(1, 0));
        add(button);
        add(text);
        chooser = new JFileChooser();
    }

    ArrayList<File> getList() {
        return list;
    }

    private void readPaths(String drive) {
        list = new ArrayList<>();

        if (file != null) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String text = null;
                br.readLine();
                while ((text = br.readLine()) != null) {
                    if (!text.startsWith("#")) {
                        File f = new File(text);
                        if (f.exists()) {
                            list.add(f);
                        } else {
                            f = new File(drive, text);
                            if (f.exists()) {
                                list.add(f);
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

        amount = list.size();
        if (file != null) {
            text.setText(file.getAbsolutePath() + "\n" + amount + " Tracks identified");
        }

    }

    private void printPaths() {
        for (File f : list) {
            System.out.println(f.getAbsolutePath());
        }
        System.out.println("Size: " + amount);
        System.out.println("Path: " + path);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
            path = chooser.getCurrentDirectory().getAbsolutePath();
        }

        String drive = path.substring(0,2);
        readPaths(drive);
        printPaths();

    }
}
