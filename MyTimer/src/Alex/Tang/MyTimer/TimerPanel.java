package Alex.Tang.MyTimer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Author: Alexander Tang
 * Date Created: 10-2-2018
 * Last Updated: 10-4-2018
 */

public class TimerPanel extends JPanel implements Runnable{

	private static final long serialVersionUID = 1L;

	/*****VARIABLES*****/
	private int width = 150;
	private int height = 24;
	private String timeString = "00:00:00";
	private long time = 10;	//Set timer default
	private Thread timerThread;
	
	//Constructor
	public TimerPanel(long time, Font font) {
		setTimer(time);
		setFont(font);
		height = font.getSize();
		FontMetrics fm = getFontMetrics(font);
		width = fm.stringWidth(timeString);
		
	}//end TimerPanel()
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawString(timeString, 0, height);
	}// paintComponent()
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(width, height);
		return size;
	}//end getPreferredSize()
	
	//Set timer
	public void setTimer(long time) {
		this.time = time;
		long h = time/3600;
		long m = (time/60)%60;
		long s = time%60;
		
		timeString = String.format("%02d:%02d:%02d", h,m,s);
		repaint();
	}//end setTimer()
	
	//Start a new thread
	public void start() {
		stop();
		timerThread = new Thread(this);
		timerThread.start();
	}//end start()
	
	//Time's Up
	protected void timesUp() {
		String message = "Time's Up";
		JOptionPane.showMessageDialog(this, message);
	}//end timesUp()
	
	//Run timer
	public void run() {
		while(time > 0) {
			time--;
			setTimer(time);
			//System.out.println(time);
			try {
				Thread.sleep(1000);
			}
			catch(InterruptedException e) {
				return;
			}
		}
		timesUp();
	}//end run()
	
	//Create a stop timer
	public void stop() {
		if(timerThread != null) {
			timerThread.interrupt();
			timerThread = null;
		}//end if
	}//end stop()
	
	//Get timer input
	public long getTime() {
		return time;
	}//end getTime()
}//end class
