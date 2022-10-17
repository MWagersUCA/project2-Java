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
import Project1p.Shows;
import javax.swing.JTextField;

public class MainPanel extends JPanel{

	private JLabel label;
	private Shows allData;
	private JTextField movieTitle;
	
	public MainPanel() {
		allData = new Shows ("allData", "./Project1p/netflixAllWeeksGlobalProcessed.txt");
		setLayout(null);
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(610, 412));
		
		label = new JLabel("Movies");
		label.setForeground(Color.WHITE);
		label.setBounds(64, 221, 106, 20);
		super.add(label);
		
		movieTitle = new JTextField();
		movieTitle.setBounds(64, 164, 155, 22);
		add(movieTitle);
		movieTitle.setColumns(10);
		
		JLabel weekLabel = new JLabel("Enter a Week (YYYY-MM-DD)");
		weekLabel.setForeground(Color.WHITE);
		weekLabel.setBounds(64, 151, 183, 14);
		add(weekLabel);
		
		JComboBox weeksComboBox = new JComboBox();
		weeksComboBox.setBounds(64, 56, 205, 22);
		add(weeksComboBox);
		
		JComboBox movies = new JComboBox();
		
		
		movies.setModel(new DefaultComboBoxModel());
		movies.setBounds(64, 242, 205, 22);
		add(movies);
		
		JButton getShowsButton = new JButton("Get Shows");
		getShowsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<ShowWeek> moviesInWeek = allData.getOneWeek(movieTitle.getText());
				if (moviesInWeek != null && moviesInWeek.size() > 0) {
					String [] data = new String[moviesInWeek.size()];
					int index = 0;
					for(ShowWeek sw : moviesInWeek) {
						data[index] = sw.getShow_titles();
						index++;
					}
					movies.setModel(new DefaultComboBoxModel(data));
				}
			}
		});
		getShowsButton.setBounds(64, 187, 106, 23);
		add(getShowsButton);
		
		
		
		
	}
}
