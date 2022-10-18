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
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;


public class MainPanel extends JPanel{


	private JLabel lblMoviesInYour;
	private JLabel availWeekLabel;
	private JLabel label;
	private JLabel headerLabel;

	private JComboBox weeksComboBox;
	private JComboBox movies;

	private JButton confirmButton;
	private JButton getShowsButton;
	private Shows allData;
	private ArrayList<String> purgedShows;
	private ArrayList<String> allWeeks;
	private JRadioButton purgeRadioButton;
	private JRadioButton unpurgeRadioButton;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JScrollPane scrollPane_1;

	public MainPanel() {
		allData = new Shows ("allData", "./Project1p/netflixAllWeeksGlobalProcessed.txt");
		allWeeks = allData.getEachWeek();
		purgedShows = new ArrayList<String>();
		setLayout(null);
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(767, 530));

		lblMoviesInYour = new JLabel("Movies in your week");
		lblMoviesInYour.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblMoviesInYour.setForeground(Color.WHITE);
		lblMoviesInYour.setBounds(36, 252, 205, 20);
		super.add(lblMoviesInYour);

		weeksComboBox = new JComboBox();
		weeksComboBox.setBounds(36, 166, 205, 22);
		String [] wdata = new String[allWeeks.size()];
		for(int i = 0; i < allWeeks.size(); i++) {
			wdata[i] = allWeeks.get(i);
		}
		weeksComboBox.setModel(new DefaultComboBoxModel(wdata));
		add(weeksComboBox);


		movies = new JComboBox();
		movies.setModel(new DefaultComboBoxModel());
		movies.setBounds(36, 273, 205, 22);
		add(movies);

		getShowsButton = new JButton("Get Shows");
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

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(36, 400, 205, 51);
		add(scrollPane_1);

		JTextArea textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		textArea.setWrapStyleWord(true);

		availWeekLabel = new JLabel("Available Weeks");
		availWeekLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		availWeekLabel.setForeground(Color.WHITE);
		availWeekLabel.setBounds(36, 151, 205, 14);
		add(availWeekLabel);

		label = new JLabel("");
		label.setIcon(new ImageIcon("./main/NetflixName.png"));
		label.setBounds(0, 0, 252, 112);
		add(label);

		headerLabel = new JLabel("Database Control Panel");
		headerLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 36));
		headerLabel.setForeground(Color.RED);
		headerLabel.setBounds(262, 31, 453, 51);
		add(headerLabel);

		purgeRadioButton = new JRadioButton("Purge");
		purgeRadioButton.setSelected(true);
		buttonGroup.add(purgeRadioButton);
		purgeRadioButton.setBackground(Color.BLACK);
		purgeRadioButton.setForeground(Color.WHITE);
		purgeRadioButton.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		purgeRadioButton.setBounds(36, 302, 109, 23);
		add(purgeRadioButton);

		unpurgeRadioButton = new JRadioButton("Unpurge");
		buttonGroup.add(unpurgeRadioButton);
		unpurgeRadioButton.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		unpurgeRadioButton.setForeground(Color.WHITE);
		unpurgeRadioButton.setBackground(Color.BLACK);
		unpurgeRadioButton.setBounds(36, 328, 109, 23);
		add(unpurgeRadioButton);

		confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(purgeRadioButton.isSelected()) {
					if(!purgedShows.contains(movies.getSelectedItem())) {
						purgedShows.add(movies.getSelectedItem().toString());
						textArea.setText(purgedShows.toString());
					}
				}
				else if(unpurgeRadioButton.isSelected()) {
					if(purgedShows.contains(movies.getSelectedItem())) {
						purgedShows.remove(movies.getSelectedItem().toString());
						textArea.setText(purgedShows.toString());
					}
				}
			}
		});
		confirmButton.setBounds(151, 296, 89, 23);
		add(confirmButton);

		JCheckBox purgedCheckBox = new JCheckBox("Include Purged Shows");
		purgedCheckBox.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		purgedCheckBox.setForeground(Color.WHITE);
		purgedCheckBox.setBackground(Color.BLACK);
		purgedCheckBox.setBounds(262, 478, 166, 23);
		add(purgedCheckBox);

		JLabel purgedLabel = new JLabel("Shows Purged:");
		purgedLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		purgedLabel.setForeground(Color.WHITE);
		purgedLabel.setBounds(36, 385, 137, 14);
		add(purgedLabel);

		JTextArea recommendTextArea = new JTextArea();
		recommendTextArea.setBounds(474, 477, 192, 22);
		add(recommendTextArea);

		JButton randomMovieButton = new JButton("Recommend a Random Movie!");
		randomMovieButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ranShow = allData.ranSuggest();
				if(purgedCheckBox.isSelected()) {
					if(purgedShows.contains(ranShow)) {
						ranShow = allData.ranSuggest();
					}
				}
				recommendTextArea.setText(ranShow);
			}		
		});
		randomMovieButton.setBounds(36, 478, 205, 23);
		add(randomMovieButton);



	}
}
