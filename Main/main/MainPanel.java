package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JTextPane;

public class MainPanel extends JPanel{
	private int count;
	private JButton push;
	private JLabel label;
	
	public MainPanel() {
		setLayout(null);
		count = 0;
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(513, 354));
		
		
		push = new JButton ("Push Me");
		push.setBounds(134, 25, 115, 67);
		push.setFont(new Font("Times New Roman", Font.BOLD, 14));
		push.addActionListener(new ButtonListener());
		
		label = new JLabel("Pushed: "+count);
		label.setBounds(25, 106, 125, 35);
		super.add(label);
		add(push);
		
		
		
		JButton btnNewButton = new JButton("Clear");
		btnNewButton.setBounds(303, 21, 115, 76);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnNewButton);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(70, 165, 137, 20);
		add(textPane);
		
		JLabel lblNewLabel = new JLabel("Enter a Movie");
		lblNewLabel.setBounds(70, 152, 80, 14);
		add(lblNewLabel);
	}
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			count++;
			label.setText("Pressed: "+count);
		}
	}
}