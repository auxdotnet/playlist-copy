import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.util.*;


public class CopyDirChooser extends JPanel
   implements ActionListener {
   JButton go;
   JTextArea text;
   
   JFileChooser chooser;
   String choosertitle;
   File dir;
   
  public CopyDirChooser() {
    go = new JButton("Choose Copy Directory");
    text = new JTextArea("No Directory Selected!");
    go.addActionListener(this);
    setLayout(new GridLayout(1,0));
    add(go);
    add(text);
   }

  public void actionPerformed(ActionEvent e) {
    int result;
        
    chooser = new JFileChooser(); 
    chooser.setCurrentDirectory(new File("."));
    chooser.setDialogTitle(choosertitle);
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    //
    // disable the "All files" option.
    //
    chooser.setAcceptAllFileFilterUsed(false);
    //    
    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
      System.out.println("getCurrentDirectory(): " 
         +  chooser.getCurrentDirectory());
      System.out.println("getSelectedFile() : " 
         +  chooser.getSelectedFile());
      dir = chooser.getSelectedFile();
      text.setText(chooser.getSelectedFile().getAbsolutePath());
      }
    else {
      System.out.println("No Selection ");
      }
     }
   
  public String getDir(){
	  return dir.getAbsolutePath()+"\\";
  }
}