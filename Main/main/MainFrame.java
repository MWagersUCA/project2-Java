//******************************
//*Matt Wagers
//*CSCI 3381
//*Java Project 2
//******************************
package main;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class MainFrame {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Netflix Database");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainPanel panel = new MainPanel();
		Image icon = Toolkit.getDefaultToolkit().getImage("./main/NetflixLogoMini.png");
		frame.setIconImage(icon);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() { //write to file when closed
			public void windowClosing(WindowEvent we) {
				panel.doClose();
			}
		});

	}

}
