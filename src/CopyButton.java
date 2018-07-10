import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class CopyButton extends JPanel implements ActionListener {
	private CopyDirChooser mFC;
	private PlaylistOpener mFO;
	
	private JButton button;
	private JTextArea text;
	
	public CopyButton(CopyDirChooser fc, PlaylistOpener fo) {
		mFC = fc;
		mFO = fo;
		button = new JButton("Start Copy!");
		text = new JTextArea("");
		button.addActionListener(this);
		setLayout(new GridLayout(1,0));
		add(button);
		add(text);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String path = mFC.getDir();
		ArrayList<File> list = mFO.getList();
		int amount = 0;
		
		System.out.println("Start Copying!");
		text.setText("Starte Kopieren!");
		for(File file : list) {
		    try {
				System.out.println("now copying: " + file.getName());
				text.setText("Aktuell am Kopieren: "+ file.getName());
				Files.copy(file.toPath(),
				    (new File(path+"\\" + file.getName())).toPath(),
				    StandardCopyOption.REPLACE_EXISTING);
				amount++;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		System.out.println(amount+" Tracks copied!");
		text.setText(amount+ "Lieder kopiert - Kopieren fertig!");

		
		System.out.println("Finished Copying!");
	}

}
