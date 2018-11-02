package Alex.Tang.ColorRun;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import Alex.Tang.MyComponents.ScorePanel;
import Alex.Tang.MyComponents.TitleLabel;

/*
 * Author: Alexander Tang
 * Date Created: 11-1-18
 * Date Updated: 11-1-18
 */

public class ColorRun extends JFrame{

	private static final long serialVersionUID = 1L;
	
	/*****VARIABLES*****/
	private ScorePanel scorePanel = new ScorePanel(0, Color.CYAN);
	private TablePanel tablePanel = new TablePanel();
	
	public ColorRun() {
		initGUI();
		
		setTitle("Color Run");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}//end constructor
	
	public static void main(String[] args) {
		try {
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
		}//end try
		catch(Exception e) {}//Catch
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ColorRun();
			}//end run()
		});//end Runnable
	}

	private void initGUI() {
		//Title
		TitleLabel titleLabel = new TitleLabel("Color Run");
		add(titleLabel, BorderLayout.PAGE_START);

		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel, BorderLayout.CENTER);
		
		//Score Panel
		mainPanel.add(scorePanel);
		
		//Table Panel
		mainPanel.add(tablePanel);
	}//end initGUI()
	
	//Score
	public void addToScore(int points) {
		scorePanel.addToScore(points);
	}//end addToScore()
}//end class
