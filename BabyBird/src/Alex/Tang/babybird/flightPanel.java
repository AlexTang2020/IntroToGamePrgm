package Alex.Tang.babybird;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/*
 * Author: Alexander Tang
 * Date Created: 10-16-18
 * Date Updated: 10-16-18
 */

public class flightPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/*****VARIABLES*****/
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;
	
	private BabyBird babyBird;
	private Bird bird = new Bird();
	
	public flightPanel(BabyBird babyBird) {
		this.babyBird = babyBird;
		
	}//end constructor
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		return size;
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		bird.draw(g);
	}//end paintComponent
	
}//end class
