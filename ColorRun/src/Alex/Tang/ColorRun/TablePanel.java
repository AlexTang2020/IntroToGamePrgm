package Alex.Tang.ColorRun;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * Author: Alexander Tang
 * Date Created: 11-1-18
 * Date Updated: 11-1-18
 */

public class TablePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	/*****VARIABLES*****/
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	
	
	public TablePanel() {
		
		setFocusable(true);
		requestFocusInWindow();
		
		//Listeners
		addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();
				if(key == ' ') {
					jump();
				}//end if
			}//end keyRelease()
			public void keyPressed(KeyEvent e) {
				char key = e.getKeyChar();
				switch(key) {
					case 'q':
						
						break;
					case 'w':
						
						break;
					case 'e':
						
						break;
				}//end switch
			}//end keyPressed()
		});//end Listener
	}//end constructor
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		return size;
	}//end getPreferredSize()
	
	public void paintComponent(Graphics g) {
		
	}//end paintComponent
	
	public void timedAction() {
		
	}//end timedAction()
	
	private void jump() {
		
	}//end jump()
	
	public void restart() {
		
	}//end restart()
}//end class
