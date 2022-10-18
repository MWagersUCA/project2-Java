//******************************
//*Matt Wagers
//*CSCI 3381
//*Java Project 2
//******************************
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
import java.util.Timer;

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
	private ImageIcon image;
	private Timer timer;
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
	private JTextField textField;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JLabel recommendationLabel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;

	public MainPanel() {
		allData = new Shows ("allData", "./Project1p/netflixAllWeeksGlobalProcessed.txt");
		allWeeks = allData.getEachWeek();
		purgedShows = new ArrayList<String>(); //collection of shows to be purged and not recommended
		setLayout(null);
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(767, 530));
	
		
		lblMoviesInYour = new JLabel("Movies in your week");
		lblMoviesInYour.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblMoviesInYour.setForeground(Color.WHITE);
		lblMoviesInYour.setBounds(36, 248, 205, 20);
		super.add(lblMoviesInYour);

		weeksComboBox = new JComboBox();
		weeksComboBox.setBounds(36, 193, 205, 22);
		String [] wdata = new String[allWeeks.size()];
		for(int i = 0; i < allWeeks.size(); i++) {
			wdata[i] = allWeeks.get(i);
		}
		weeksComboBox.setModel(new DefaultComboBoxModel(wdata));
		add(weeksComboBox);


		movies = new JComboBox();
		movies.setModel(new DefaultComboBoxModel());
		movies.setBounds(36, 269, 205, 22);
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
		getShowsButton.setBounds(36, 214, 106, 23);
		add(getShowsButton);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(36, 400, 205, 51);
		add(scrollPane_1);

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		scrollPane_1.setViewportView(textArea);
		textArea.setWrapStyleWord(true);

		availWeekLabel = new JLabel("Select Week");
		availWeekLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		availWeekLabel.setForeground(Color.WHITE);
		availWeekLabel.setBounds(36, 178, 205, 14);
		add(availWeekLabel);

		label = new JLabel("");
		label.setIcon(new ImageIcon("./main/netflix_letters_animation.gif"));
		label.setBounds(-120, 0, 341, 112);
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
		purgeRadioButton.setBounds(131, 298, 109, 23);
		add(purgeRadioButton);

		unpurgeRadioButton = new JRadioButton("Unpurge");
		buttonGroup.add(unpurgeRadioButton);
		unpurgeRadioButton.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		unpurgeRadioButton.setForeground(Color.WHITE);
		unpurgeRadioButton.setBackground(Color.BLACK);
		unpurgeRadioButton.setBounds(131, 324, 109, 23);
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
		confirmButton.setBounds(36, 293, 89, 23);
		add(confirmButton);

		JCheckBox purgedCheckBox = new JCheckBox("Include Purged Shows");
		purgedCheckBox.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		purgedCheckBox.setForeground(Color.WHITE);
		purgedCheckBox.setBackground(Color.BLACK);
		purgedCheckBox.setBounds(405, 441, 166, 23);
		add(purgedCheckBox);

		JLabel purgedLabel = new JLabel("Shows Purged:");
		purgedLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		purgedLabel.setForeground(Color.WHITE);
		purgedLabel.setBounds(36, 385, 137, 14);
		add(purgedLabel);

		JTextArea recommendTextArea = new JTextArea();
		recommendTextArea.setBackground(Color.BLACK);
		recommendTextArea.setForeground(Color.WHITE);
		recommendTextArea.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		recommendTextArea.setBounds(405, 378, 310, 22);
		add(recommendTextArea);

		//recommends a movie that is not on the purged list
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
		randomMovieButton.setBounds(405, 411, 205, 23);
		add(randomMovieButton);
		
		//radio buttons for editing show data
		JRadioButton titleRadioButton = new JRadioButton("Title");
		titleRadioButton.setSelected(true);
		titleRadioButton.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		titleRadioButton.setBackground(Color.BLACK);
		titleRadioButton.setForeground(Color.WHITE);
		buttonGroup_1.add(titleRadioButton);
		titleRadioButton.setBounds(328, 187, 109, 23);
		add(titleRadioButton);
		
		JRadioButton rankRadioButton = new JRadioButton("Rank");
		rankRadioButton.setBackground(Color.BLACK);
		rankRadioButton.setForeground(Color.WHITE);
		rankRadioButton.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		buttonGroup_1.add(rankRadioButton);
		rankRadioButton.setBounds(328, 213, 109, 23);
		add(rankRadioButton);
		
		JRadioButton hoursRadioButton = new JRadioButton("Hours Viewed");
		hoursRadioButton.setForeground(Color.WHITE);
		hoursRadioButton.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		hoursRadioButton.setBackground(Color.BLACK);
		buttonGroup_1.add(hoursRadioButton);
		hoursRadioButton.setBounds(328, 239, 142, 22);
		add(hoursRadioButton);
		
		//user input for editing show data
		textField = new JTextField();
		textField.setBounds(493, 214, 192, 23);
		add(textField);
		textField.setColumns(10);
		
		//allows editing of data in shows
		//allData.find() locates the index of a selected show and week and calls setter with 
		//data from textField as the parameter
		JButton editButton = new JButton("Edit Show");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(titleRadioButton.isSelected() && weeksComboBox.getSelectedItem() != null && movies.getSelectedItem() != null) {
					allData.find(movies.getSelectedItem().toString(), weeksComboBox.getSelectedItem().toString()).setShow_titles(textField.getText());
				}
				if(rankRadioButton.isSelected() && weeksComboBox.getSelectedItem() != null && movies.getSelectedItem() != null) {
					allData.find(movies.getSelectedItem().toString(), weeksComboBox.getSelectedItem().toString()).setWeekly_rank(textField.getText());
				}
				if(hoursRadioButton.isSelected() && weeksComboBox.getSelectedItem() != null && movies.getSelectedItem() != null) {
					allData.find(movies.getSelectedItem().toString(), weeksComboBox.getSelectedItem().toString()).setWeekly_hours_viewed(textField.getText());
				}
				
			}
		});
		editButton.setBounds(339, 273, 98, 23);
		add(editButton);
		
		
		
		recommendationLabel = new JLabel("Recommendation:");
		recommendationLabel.setForeground(Color.WHITE);
		recommendationLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		recommendationLabel.setBackground(Color.WHITE);
		recommendationLabel.setBounds(405, 347, 192, 20);
		add(recommendationLabel);
		
		lblNewLabel = new JLabel("Purge a Show!");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblNewLabel.setBounds(60, 140, 142, 27);
		add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Edit a Show! Pick Week, Movie and...");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblNewLabel_1.setBounds(328, 142, 365, 23);
		add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Enter New String");
		lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(494, 197, 137, 14);
		add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("One of these!");
		lblNewLabel_3.setFont(new Font("Trebuchet MS", Font.BOLD, 11));
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setBounds(328, 176, 89, 14);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(MainPanel.class.getResource("/main/NetflixLogoMini.png")));
		lblNewLabel_4.setBounds(711, 482, 46, 37);
		add(lblNewLabel_4);
		
	}
	
	
	//on close, write to file
	//can be changed to write to read file
	public void doClose() {
		allData.doWrite("./Project1p/textwrite.txt");
	}
}
