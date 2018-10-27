package Alex.Tang.babybird;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Alex.Tang.myCommonMethods.FileIO;

/*
 * Author: Alexander Tang
 * Date Created: 10-16-18
 * Date Updated: 10-25-18
 */

public class flightPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/*****VARIABLES*****/
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	private static final int SEPARATION = 40;
	private static final Font BIG_FONT = new Font(Font.DIALOG, Font.BOLD, 30);
	private static final String THUD_SOUND = "thud.wav";
	
	private BabyBird babyBird;
	private Bird bird = new Bird(HEIGHT);
	private Timer timer;
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	private int count = 0;
	private FontMetrics fm;

	
	public flightPanel(BabyBird babyBird) {
		this.babyBird = babyBird;
		setFont(BIG_FONT);
		fm = getFontMetrics(BIG_FONT);
		
		setFocusable(true);
		requestFocusInWindow();
		
		//Listeners
		addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent e) {
				char key = e.getKeyChar();
				if(key == ' ') {
					bird.startFlapping();
				}//end if
			}//end keyRelease()
		});//end Listener
		
		//Timer
		timer  = new Timer(40, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timedAction();
			}//end actionPerformed()
		});//end timer
		
		Wall wall = new Wall(fm);
		walls.add(wall);
		
		timer.start();
	}//end constructor
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		return size;
	}//end getPreferredSize()
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//bird
		bird.draw(g);
		
		//wall
		for(int i = 0; i < walls.size(); i++) {
			Wall wall = walls.get(i);
			wall.draw(g);
		}
	}//end paintComponent
	
	private void timedAction() {
		int changeY = bird.getChangeY();
		//move bird
		bird.move();
		int paintX = bird.getX();
		int paintY = bird.getY();
		
		if(changeY > 0) {
			paintY-=changeY;
		}//end if
		int paintWidth = bird.getWidth();
		int paintHeight = bird.getHeight() + Math.abs(changeY);
		repaint(paintX, paintY, paintWidth, paintHeight);

		//move walls
		for(int i = 0; i < walls.size(); i++) {
			Wall wall = walls.get(i);
			wall.move();
			paintX = wall.getX();
			paintY = wall.getY();
			paintWidth = wall.getWidth() - wall.getChangeX();
			paintHeight = HEIGHT;
			repaint(paintX, paintY, paintWidth, paintHeight);
			if(wall.isPassedWindow()) {
				walls.remove(wall);
				int points = wall.getPoints();
				babyBird.addToScore(points);
			}//end if
		}//end for
		
		//check to add wall
		count++;
		if(count > SEPARATION) {
			Wall wall = new Wall(fm);
			walls.add(wall);
			count = 0;
		}//end if
		
		//check collision
		Wall firstWall = walls.get(0);
		Rectangle birdBounds = bird.getBounds();
		Rectangle topWallBounds = firstWall.getTopBounds();
		Rectangle bottomWallBounds = firstWall.getBottomBounds();
		
		if(birdBounds.intersects(topWallBounds) || birdBounds.intersects(bottomWallBounds)) {
			nextLife();
		}//end if
	}//end timedAction()
	
	//Next Life Reload
	private void nextLife() {
		FileIO.playClip(this, THUD_SOUND);
		babyBird.nextBird();
		count = 0;
		walls.clear();
		Wall wall = new Wall(fm);
		walls.add(wall);
		repaint();
	}//end nextLife()
	
	//Get Bird
	public Bird getBird() {
		return bird;
	}//end getBird()
	
	public void restart() {
		count = 0;
		bird = new Bird(HEIGHT);
		walls.clear();
		Wall wall = new Wall(fm);
		walls.add(wall);
		repaint();
	}//end restart()
}//end class
