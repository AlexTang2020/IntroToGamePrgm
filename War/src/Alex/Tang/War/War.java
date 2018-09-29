package Alex.Tang.War;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import Alex.Tang.MyComponents.TitleLabel;

/**
 * Author: Alexander Tang
 * Date Created: 9-8-2018
 * Last Updated: 9-28-2018
 */

public class War extends JFrame{

	//Serialization of Creation
	private final static long serialVersionUID = 1L;
		
	//Create Table Panel
	private TablePanel tablePanel = new TablePanel();
			
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
			}//end actionPerformed
		});//end listener
		buttonPanel.add(startBtn);
		
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
				int howTo3 = JOptionPane.showOptionDialog(null, "You first draw up to 5 cards to your hand and\n"
															  + "then select one card you will wage war with.", 
						howTitle, JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, next, null);
				if(howTo3 == JOptionPane.YES_OPTION) {
					int howTo4 = JOptionPane.showOptionDialog(null, "The higher value card is the victor and both players cards will go to the winner.\n"
																  + "These cards are placed at the bottom of the deck, adding to that player's power", 
							howTitle, JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, next, null);
					if(howTo4 == JOptionPane.YES_OPTION) {
						int howTo5 = JOptionPane.showOptionDialog(null, "However, if they are of equal value, the cards are discarded for the rest of the game.\n"
																	  + "Card Values Lowest To Highest:         (2 3 4 5 6 7 8 9 10 Jack Queen King Ace)",  
								howTitle, JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, next, null);
						if(howTo5 == JOptionPane.YES_OPTION) {
							int howTo6 = JOptionPane.showOptionDialog(null, "The war doesn't end till one side is out of cards. Have Fun!", 
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

}//end class
