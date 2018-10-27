package Alex.Tang.babybird;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/*
 * Author: Alexander Tang
 * Date Created: 10-25-18
 * Date Updated: 10-25-18
 */

import javax.swing.JPanel;

public class BirdNestPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/*****VARIABLES*****/
	private static final int MARGIN = 10;
	private static final int SPACING = 20;
	
	private BufferedImage image;
	private int count = 0;
	private int birdWidth;
	private int birdHeight;
	private int width;
	private int height;
	
	public BirdNestPanel(BufferedImage image, int count) {
		this.image = image;
		this.count = count;
		
		birdWidth = image.getWidth();
		birdHeight = image.getHeight();
		width = (birdWidth * count) + (2 * MARGIN) + ((count-1) * SPACING);
		height = birdHeight + (2 * MARGIN);
	}//end constructor
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		for(int i = 0; i < count; i++) {
			int x = MARGIN + (birdWidth + SPACING) * i;
			g.drawImage(image, x, MARGIN, null);
		}//end for
	}//end paintComponent()
	
	//Panel Dimensions
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(width, height);
		return size;
	}//end getPreferredSize()
	
	//Removing Birds
	public int removeBirds() {
		count--;
		repaint();
		return count;
	}//end removeBirds()
	
	public void setBirdCount(int count) {
		this.count = count;
		repaint();
	}//end setBirdCount()
}//end class
