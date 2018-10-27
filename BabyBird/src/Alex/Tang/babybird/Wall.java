package Alex.Tang.babybird;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import Alex.Tang.myCommonMethods.FileIO;

/*
 * Author: Alexander Tang
 * Date Created: 10-23-18
 * Date Updated: 10-25-18
 */

public class Wall {
	/*****VARIABLES*****/
	private static final String WALL_IMAGE_FILE = "wall.jpg";
	private static final int CHANGE_X = -10;
	private static final int TOP_MIN = 100;
	private static final int TOP_MAX = 300;
	private static final int GAP_MIN = 100;
	private static final int GAP_MAX = 240;
	private static final int POINTS_OFFSET = 80;
	
	private static BufferedImage wallImage;
	private static int width = 72;
	private static int height = 600;
	
	private int x = flightPanel.WIDTH;
	private int bottomY;
	private int topHeight;
	private int bottomHeight;
	
	private BufferedImage topImage;
	private BufferedImage bottomImage;
	private Random rand = new Random();
	private int points = 1;
	private String pointsString;
	private int pointsX;
	
	public Wall(FontMetrics fm) {
		if(wallImage == null) {
			wallImage = FileIO.readImageFile(this, WALL_IMAGE_FILE);
			width = wallImage.getWidth();
			height = wallImage.getHeight();
		}//end if
		
		int range = GAP_MAX-GAP_MIN;
		int pick = rand.nextInt(range);
		int gap = pick + GAP_MIN;
		
		//Calculate ratio of picked gap and possible range
		float ratio = (float) (pick)/range;

		//Ratio to number from 1 to 10
		int intValue = (int)(ratio*10);
		
		//Points range from 10 to 1, smallest gap awards most points
		points = 10 - intValue;
		
		pointsString = ""+points;
		int pointsWidth = fm.stringWidth(pointsString);
		pointsX = (width/2) - (pointsWidth/2);
		
		range = TOP_MAX-TOP_MIN;
		pick = rand.nextInt(range);
		topHeight = pick + TOP_MIN;
		
		bottomY = topHeight + gap;
		bottomHeight = flightPanel.HEIGHT - bottomY;
		topImage = wallImage.getSubimage(0, height - topHeight, width, topHeight);
		
		bottomImage = wallImage.getSubimage(0, 0, width, bottomHeight);
	
	}//end constructor
	
	public void draw(Graphics g) {
		if(wallImage == null) {
			g.setColor(Color.cyan);
			g.fillRect(x, 0, width, topHeight);
			g.fillRect(x, bottomY, width, bottomHeight);
		}
		else {
			g.drawImage(topImage, x, 0, null);
			g.drawImage(bottomImage, x, bottomY, null);
			g.setColor(Color.black);
			g.drawString(pointsString, x+pointsX, bottomY+POINTS_OFFSET);
		}//end if
	}//end draw()
	
	public void move() {
		x+=CHANGE_X;
	}//end move()
	
	public boolean isPassedWindow() {
		int rightEdgeX = x+width;
		return rightEdgeX < 0;
	}//end isPassedWindow()
	
	//Get Top Boundary
	public Rectangle getTopBounds() {
		Rectangle bounds = new Rectangle(x, 0, width, topHeight);
		return bounds;
	}//end getTopBounds()
	
	//Get Bottom Boundary
	public Rectangle getBottomBounds() {
		Rectangle bounds = new Rectangle(x, bottomY, width, bottomHeight);
		return bounds;
	}//end getTopBounds()
	
	//Get Points
	public int getPoints() {
		return points;
	}//end getPoints()
	
	//Get X
	public int getX() {
		return x;
	}//end getX()
	
	//Get Y
	public int getY() {
		return 0;
	}//end getY()
	
	//Get Width
	public int getWidth() {
		return width;
	}//end getWidth()
	
	//Get changeX
	public int getChangeX() {
		return CHANGE_X;
	}//end getChangeX
}//end class
