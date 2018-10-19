package Alex.Tang.babybird;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import Alex.Tang.MyComponents.ScorePanel;
import Alex.Tang.MyComponents.TitleLabel;

/*
 * Author: Alexander Tang
 * Date Created: 10-16-18
 * Date Updated: 10-16-18
 */

public class BabyBird extends JFrame {

	private static final long serialVersionID = 1L;
	
	/*****VARIABLES*****/
	private ScorePanel scorePanel = new ScorePanel(0, Color.CYAN);
	private flightPanel flightPanel = new flightPanel(this);
	
	public BabyBird() {
		initGUI();
		
		setTitle("Baby Bird");
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
				new BabyBird();
			}//end run()
		});//end Runnable

	}//end main()

	
	public void initGUI() {
		//Title
		TitleLabel titleLabel = new TitleLabel("Baby Bird");
		add(titleLabel, BorderLayout.PAGE_START);
		
		//Main Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.BLUE);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel, BorderLayout.CENTER);
		
		//Score Panel
		mainPanel.add(scorePanel);
		
		//Flight Panel
		mainPanel.add(flightPanel);
		
		//Bottom Panel
		
	}//end initGUI()
}//end class
