package Alex.Tang.babybird;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Alex.Tang.myCommonMethods.FileIO;

/*
 * Author: Alexander Tang
 * Date Created: 10-16-18
 * Date Updated: 10-25-18
 */


public class Bird {
	
	/*****VARIABLES*****/
	private static final String BIRD_FLAP_UP = "babyBirdFlapUp.gif";
	private static final String BIRD_GLIDE = "babyBirdGlide.gif";
	private static final String BIRD_FLAP_DOWN = "babyBirdFlapDown.gif";
	
	private static final int FLAP_UP = 0;
	private static final int FLAP_GLIDE = 1;
	private static final int FLAP_DOWN = 2;
	private static final float GRAVITY = .5f;
	private static final int FLAP_FORCE = -8;
		
	private BufferedImage[] birds = new BufferedImage[3];
	private int width;
	private int height;
	private int x = 10;
	private int y = 10;
	private int flap = FLAP_GLIDE;
	private boolean flapping = false;
	private float changeY = 0;
	private int panelHeight;
	
	public Bird(int panelHeight) {
		birds[FLAP_UP] = FileIO.readImageFile(this, BIRD_FLAP_UP);
		birds[FLAP_GLIDE] = FileIO.readImageFile(this, BIRD_GLIDE);
		birds[FLAP_DOWN] = FileIO.readImageFile(this, BIRD_FLAP_DOWN);
		
		width = birds[0].getWidth();
		height = birds[0].getHeight();
		
		this.panelHeight = panelHeight;
	}//end constructor
	
	public void draw(Graphics g) {
		g.drawImage(birds[flap], x, y, null);
	}//end draw()

	public void startFlapping() {
		flapping = true;
		changeY = FLAP_FORCE;
	}//end startFlapping()

	public void move() {
		int changeYInt = (int) changeY;						//Cast float to int
		int distanceFromTop = y + height + changeYInt;		//Check from top
		if(distanceFromTop > panelHeight) {
			y = panelHeight - height;
			changeY = 0;
		}
		else {
			y+=changeY;
			changeY+=GRAVITY;
			if(changeY > 0) {
				flapping = false;
			}//end if
		}// end if
		if(flapping) {
			flap+=1;
			flap%=3;
		}
		else {
			flap = FLAP_GLIDE;
		}//end if
	}//end move()
	
	//Get Top Boundary
	public Rectangle getBounds() {
		Rectangle bounds = new Rectangle(x, y, width, height);
		return bounds;
	}//end getBounds()
	
	//Get Bird Image
	public BufferedImage getImage() {
		return birds[FLAP_GLIDE];
	}//end getImage()
	
	
	//Get X
	public int getX() {
		return x;
	}//end getX()
	
	//Get Y
	public int getY() {
		return y;
	}//end getY()
	
	//Get Width
	public int getWidth() {
		return width;
	}//end getWidth()
	
	//Get Height
	public int getHeight() {
		return height;
	}//end getHeight()
	
	//Get changeY
	public int getChangeY() {
		return (int) changeY;
	}//end getChangeY()
	
}//end class
