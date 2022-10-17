package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import Project1p.ShowWeek;
import Project1p.AllWeeks;

public class MainPanel extends JPanel{

	private JLabel label;
	private AllWeeks allData;
	
	public MainPanel() {
		allData = new AllWeeks ("allData", "./Project1p/netflixAllWeeksGlobalProcessed.txt");
		setLayout(null);
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(610, 412));
		
		label = new JLabel("Movies");
		label.setForeground(Color.WHITE);
		label.setBounds(64, 221, 106, 20);
		super.add(label);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(64, 166, 156, 20);
		add(textPane);
		
		JLabel weekLabel = new JLabel("Enter a Week (YYYY-MM-DD)");
		weekLabel.setForeground(Color.WHITE);
		weekLabel.setBounds(64, 151, 183, 14);
		add(weekLabel);
		
		JComboBox movies = new JComboBox();
		ArrayList<ShowWeek> moviesInWeek = allData.getShowsinWeek("2021-07-04");
		movies.setModel(new DefaultComboBoxModel(new String[] {"item 1", "item 2", "item 3"}));
		movies.setBounds(64, 242, 205, 22);
		add(movies);
		
		JButton btnNewButton = new JButton("Get Shows");
		btnNewButton.setBounds(64, 187, 89, 23);
		add(btnNewButton);
	}
}
