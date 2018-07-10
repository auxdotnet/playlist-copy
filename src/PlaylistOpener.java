import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class PlaylistOpener extends JPanel implements ActionListener{
	private JFileChooser chooser;
	private JButton button;
	private JTextArea text;
	private File file;
	private int amount;
	private ArrayList<File> list;
	private String path;
	
	public PlaylistOpener(){
		button = new JButton("Select Playlist File");
		text = new JTextArea("No File Selected");
		button.addActionListener(this);
		setLayout(new GridLayout(1,0));
		add(button);
		add(text);
		chooser = new JFileChooser();
	}
	
	public ArrayList<File> getList(){		
		return list;
	}
	
	private void readPaths(){
		list = new ArrayList<File>();

		if (file != null){
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	            String text = null;
	            br.readLine();
	            while ((text = br.readLine()) != null) {
	                if(!text.startsWith("#")){
	                	File f = new File(text);
	                	if(f.exists()){
		                	list.add(f);
	                	}
	                	else{
	                		f = new File(path,text);
	                		if(f.exists()){
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
        text.setText(file.getAbsolutePath()+"\n"+amount+" Tracks identified");

	}
	private void printPaths(){
		for(File f:list){
			System.out.println(f.getAbsolutePath());
		}
		System.out.println("Size: "+amount);
		System.out.println("Path: "+path);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
            path = chooser.getCurrentDirectory().getAbsolutePath();
        } 
        readPaths();
        printPaths();
       	
	}
}
