import javax.swing.*;

import sun.misc.JavaLangAccess;
import sun.nio.ch.Interruptible;
import sun.reflect.ConstantPool;
import sun.reflect.annotation.AnnotationType;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Executable;
import java.security.AccessControlContext;
import java.util.Map;


public class Main{
 
  public static void main(String s[]) {
    JFrame frame = new JFrame("");
    JPanel all = new JPanel(new GridLayout(0,1));
    all.setPreferredSize(new Dimension(650,300));
    
    CopyDirChooser copyDir = new CopyDirChooser();
    PlaylistOpener file = new PlaylistOpener();
    CopyButton button = new CopyButton(copyDir, file);
    
    frame.addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          System.exit(0);
          }
        }
      );
    
    all.add(new JLabel("1. Playlist Datei auswählen"));
    all.add(file);
    all.add(new JLabel("2. Ordner auswählen, wo es hinkopiert werden soll"));
    all.add(copyDir);
    all.add(new JLabel("3. Kopieren Drücken"));
    all.add(button);
    
    frame.getContentPane().add(all);
    frame.pack();
    
    frame.setTitle("PlaylistCopy");
    
    frame.setVisible(true);
    }
}