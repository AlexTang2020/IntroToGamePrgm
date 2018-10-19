package Alex.Tang.War;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import Alex.Tang.MyComponents.TitleLabel;
import Alex.Tang.MyTimer.TimerPanel;
import Alex.Tang.myCommonMethods.FileIO;

/**
 * Author: Alexander Tang
 * Date Created: 9-8-2018
 * Last Updated: 10-18-2018
 */

public class War extends JFrame{

	//Serialization of Creation
	private final static long serialVersionUID = 1L;
		
	//Create Table Panel
	private TablePanel tablePanel = new TablePanel();
	private static Font font = new Font(Font.DIALOG, Font.BOLD, 36);
	private static TimerPanel timerPanel = new TimerPanel(31, font);
	
	//Constructor
	public War(){	
		initGUI();
		
		setTitle("Mindful War");//Window Title
		setResizable(false);//Do not resize window
		pack();//pack the window
		setLocationRelativeTo(null);//Center window
		setVisible(true);//Make window visible
		setDefaultCloseOperation(EXIT_ON_CLOSE);//Exit on close
			
	}//end Constructor
			
	//Initialize GUI - Creates GUI
	private void initGUI() {
		TitleLabel gameTitle = new TitleLabel("Mindful War");
		add(gameTitle, BorderLayout.PAGE_START);
		add(tablePanel, BorderLayout.CENTER);
		
		//Adding Button Panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.black);
		add(buttonPanel, BorderLayout.PAGE_END);
		
		//Add New Game Button
		buttonPanel.setBackground(Color.black);
		JButton startBtn = new JButton("Start New Game");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablePanel.newGame();
				timerPanel.setTimer(31);
				getTimerPanel().setVisible(true);
				getTimerPanel().start();
			}//end actionPerformed
		});//end listener
		buttonPanel.add(startBtn);
		
		//Add Timer Panel
		getTimerPanel().setBackground(Color.BLACK);
		buttonPanel.add(getTimerPanel());
		getTimerPanel().setVisible(false);
		
		//Add How To Play Button
		JButton newBtn = new JButton("How To Play");
		newBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				howToPlay();
			}//end actionPerformed()
		});//end listener
		buttonPanel.add(newBtn);
	}//end initGUI()
	
	public void howToPlay() {
		String howTitle = "How To Play";
		String[] next = {"Next"};
		//Option Dialogs For How To Play
		int howTo1 = JOptionPane.showOptionDialog(null, "This is a variation of the card game War", 
				howTitle, JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, next, null);
		if(howTo1 == JOptionPane.YES_OPTION) {
			int howTo2 = JOptionPane.showOptionDialog(null, "The goal is to make your opponent lose all their cards by waging\n"
														  + "war against them, in which both of you select a card and do battle.", 
					howTitle, JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, next, null);
			if(howTo2 == JOptionPane.YES_OPTION) {
				int howTo3 = JOptionPane.showOptionDialog(null, "First draw up to 5 cards by dragging cards from your deck to your hand.\n"
															  + "Then select one card from your hand within the 30 seconds given.\n"
															  + "After 30 seconds, you can only select what's in your hand.\n"
															  + "If you don't have any cards in your hand, please hit Start New Game ", 
						howTitle, JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, next, null);
				if(howTo3 == JOptionPane.YES_OPTION) {
					int howTo4 = JOptionPane.showOptionDialog(null, "The higher value card is the victor and both players cards will go to the winner.\n"
																  + "These cards are placed at the bottom of the deck, adding to that player's power", 
							howTitle, JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, next, null);
					if(howTo4 == JOptionPane.YES_OPTION) {
						int howTo5 = JOptionPane.showOptionDialog(null, "However, if they are of equal value, the cards are discarded for the rest of the game.\n"
																	  + "Card Values Lowest To Highest:         (2 3 4 5 6 7 8 9 10 Jack Queen King Ace)\n"
																	  + "Remember winning only means getting a higher value, it doesn't matter by how much.\n"
																	  + "Keep track what cards you and your opponent have and plan your next attack.",  
								howTitle, JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, next, null);
						if(howTo5 == JOptionPane.YES_OPTION) {
							int howTo6 = JOptionPane.showOptionDialog(null, "The war will either end if one side runs out of cards\n"
																		  + "or when the turn limit has been reached. Turn Limit: 50 Turns\n"
																		  + "Then the winner will be decided by the number of cards held.", 
									howTitle, JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, next, null);
						}//end if 5
					}//end if 4
				}//end if 3
			}//end if 2
		}//end if 1
	}//end howToPlay()
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);		
		}//end try
		catch(Exception e) {}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new War();
			}//end run()
		});//end Runnable()
	}//end main

	//Retrieve timerPanel for TablePanel.java
	public static TimerPanel getTimerPanel() {
		return timerPanel;
	}//end getTimerPanel()
}//end class
