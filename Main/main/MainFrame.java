package main;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
public class MainFrame {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Netflix Database");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainPanel panel = new MainPanel();
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);

	}

}
