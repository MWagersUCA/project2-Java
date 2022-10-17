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
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import Project1p.ShowWeek;
import Project1p.Shows;
import javax.swing.JTextField;

public class MainPanel extends JPanel{

	private JLabel lblMoviesInYour;
	private Shows allData;
	private ArrayList<String> allWeeks;
	
	public MainPanel() {
		allData = new Shows ("allData", "./Project1p/netflixAllWeeksGlobalProcessed.txt");
		allWeeks = allData.getEachWeek();
		setLayout(null);
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(767, 530));
		
		lblMoviesInYour = new JLabel("Movies in your week");
		lblMoviesInYour.setFont(new Font("Arial", Font.PLAIN, 15));
		lblMoviesInYour.setForeground(Color.WHITE);
		lblMoviesInYour.setBounds(36, 252, 205, 20);
		super.add(lblMoviesInYour);
		
		JComboBox weeksComboBox = new JComboBox();
		weeksComboBox.setBounds(36, 166, 205, 22);
		String [] wdata = new String[allWeeks.size()];
		for(int i = 0; i < allWeeks.size(); i++) {
			wdata[i] = allWeeks.get(i);
		}
		weeksComboBox.setModel(new DefaultComboBoxModel(wdata));
		add(weeksComboBox);
		
		
		JComboBox movies = new JComboBox();
		movies.setModel(new DefaultComboBoxModel());
		movies.setBounds(36, 273, 205, 22);
		add(movies);
		
		JButton getShowsButton = new JButton("Get Shows");
		getShowsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<ShowWeek> moviesInWeek = allData.getOneWeek(weeksComboBox.getSelectedItem().toString());  
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
		getShowsButton.setBounds(36, 187, 106, 23);
		add(getShowsButton);
		
		JLabel availWeekLabel = new JLabel("Available Weeks");
		availWeekLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		availWeekLabel.setForeground(Color.WHITE);
		availWeekLabel.setBounds(36, 151, 205, 14);
		add(availWeekLabel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("./main/NetflixName.png"));
		label.setBounds(0, 0, 252, 112);
		add(label);

		
		
	}
}
