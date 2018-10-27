package Alex.Tang.babybird;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

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
 * Date Created: 10-16-18
 * Date Updated: 10-25-18
 */

public class BabyBird extends JFrame {

	private static final long serialVersionID = 1L;
	
	private static final int LIVES = 4;
	
	/*****VARIABLES*****/
	private ScorePanel scorePanel = new ScorePanel(0, Color.CYAN);
	private flightPanel flightPanel = new flightPanel(this);
	private BirdNestPanel birdNestPanel;
	
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
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.BLACK);
		add(bottomPanel, BorderLayout.PAGE_END); 
		
		//Bird Nest Panel
		Bird bird = flightPanel.getBird();
		BufferedImage birdImage = bird.getImage();
		birdNestPanel = new BirdNestPanel(birdImage, LIVES-1);
		bottomPanel.add(birdNestPanel);
		
	}//end initGUI()
	
	//Refresh Lives
	public void nextBird() {
		int birdsRemaining = birdNestPanel.removeBirds();
		if(birdsRemaining == 0) {
			String message = "PLAY AGAIN?";
			int option = JOptionPane.showConfirmDialog(this, message, null, JOptionPane.YES_NO_OPTION);
			if(JOptionPane.YES_OPTION == option) {
				birdNestPanel.setBirdCount(LIVES-1);
				scorePanel.reset();
				flightPanel.restart();
			}
			else {
				System.exit(0);
			}//end if
		}//end if
	}//end nextBird()
	
	//Score
	public void addToScore(int points) {
		scorePanel.addToScore(points);
	}//end addToScore()
	
}//end class
