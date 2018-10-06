package Alex.Tang.MyTimer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import Alex.Tang.MyComponents.TitleLabel;

/**
 * Author: Alexander Tang
 * Date Created: 10-2-2018
 * Last Updated: 10-4-2018
 */

public class MyTimer extends JFrame {

	//Verification for send/receive
	private static final long serialVersionUID = 1L;

	/*****VARIABLES*****/
	private Font font = new Font(Font.DIALOG, Font.BOLD, 36);
	private TimerPanel timerPanel = new TimerPanel(0, font);
	
	//Constructor
	public MyTimer() {
		initGUI();
		
		//JFrame Settings
		setTitle("My Timer");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}//end MyTimer()
	
	public static void main(String[] args) {
		// Set up cross platform look and feel
		try{
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
		}//end try
		catch(Exception e) {}	//end catch()
		
		//Create a new instance of MyTimer
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MyTimer();
			}//end run()
		});//end Runnable()
	}

	//Create GUI
	public void initGUI() {
		//Create new TitleLabel
		TitleLabel titleLabel = new TitleLabel("My Timer");
		add(titleLabel, BorderLayout.PAGE_START);
		
		//Create new JPanel
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.orange);
		add(centerPanel,BorderLayout.CENTER);
		timerPanel.setBackground(Color.orange);
		centerPanel.add(timerPanel);
		
		//New Button Panel
		JPanel btnPanel = new JPanel();
		btnPanel.setBackground(Color.BLACK);
		add(btnPanel, BorderLayout.PAGE_END);
		
		//Start Button
		JButton startBtn = new JButton("START");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});//end listener
		btnPanel.add(startBtn);
		
		//Stop Button
		JButton stopBtn = new JButton("STOP");
		stopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stop();
			}
		});//end listener
		btnPanel.add(stopBtn);
		
		//Hour Button
		JButton hourBtn = new JButton("ADD HOUR");
		hourBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addHour();
			}
		});//end listener
		btnPanel.add(hourBtn);
		
		//Minute Button
		JButton minBtn = new JButton("ADD MINUTE");
		minBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMin();
			}
		});//end listener
		btnPanel.add(minBtn);
				
		//Clear Button
		JButton clearBtn = new JButton("CLEAR");
		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});//end listener
		btnPanel.add(clearBtn);
	}//end initGUI()
	
	//Start timer
	private void start() {
		timerPanel.start();
	}//end start()
	
	//Stop timer
	private void stop() {
		timerPanel.stop();
	}//end stop()
	
	//Add hours
	private void addHour() {
		long time = timerPanel.getTime();
		time+=3600;
		timerPanel.setTimer(time);
	}//end addHour()
	
	//Add minutes
	private void addMin() {
		long time = timerPanel.getTime();
		time+=60;
		timerPanel.setTimer(time);
	}//end addMin()
	
	//Clear timer
	private void clear() {
		timerPanel.stop();
		timerPanel.setTimer(0);
	}//end clear()
}//end class
