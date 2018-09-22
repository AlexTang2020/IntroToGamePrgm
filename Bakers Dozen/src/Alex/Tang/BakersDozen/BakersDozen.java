package Alex.Tang.BakersDozen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import Alex.Tang.MyComponents.TitleLabel;

/**
 * Author: Alexander Tang
 * Date Created: 8-30-2018
 * Last Updated: 9-20-2018
 */


public class BakersDozen extends JFrame{

	//Serialization of Creation
	private final static long serialVersionUID = 1L;
	
	//Create Table Panel
	private TablePanel tablePanel = new TablePanel();
	
	//Constructor
	public BakersDozen(){
		
		initGUI();
		
		setTitle("Baker's Dozen");//Window Title
		setResizable(false);//Do not resize window
		pack();//pack the window
		setLocationRelativeTo(null);//Center window
		setVisible(true);//Make window visible
		setDefaultCloseOperation(EXIT_ON_CLOSE);//Exit on close
		
	}//end Constructor
	
	//Initialize GUI - Creates GUI
	private void initGUI() {
		TitleLabel gameTitle = new TitleLabel("Baker's Dozen");
		add(gameTitle, BorderLayout.PAGE_START);
		add(tablePanel, BorderLayout.CENTER);
		
		//button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.black);
		add(buttonPanel, BorderLayout.PAGE_END);
		
		//start button
		JButton newBtn = new JButton("New Game");
		newBtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					tablePanel.newGame();
				}
			});
		buttonPanel.add(newBtn);
		
		JButton replayBtn = new JButton("Replay");
		replayBtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					tablePanel.replay();
				}
			});
		buttonPanel.add(replayBtn);
	}//end initGUI()
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);		
		}//end try
		catch(Exception e) {}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new BakersDozen();
			}//end run()
		});//end Runnable()
	}//end main

}//end class
