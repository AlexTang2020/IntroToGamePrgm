package Alex.Tang.BakersDozen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Author: Alexander Tang
 * Date Created: 9-4-2018
 * Last Updated: 9-20-2018
 */

public class Card {
	
	/*****Variables*****/
	
	private String rank = "";
	private int suit = -1;
	private int value = 0;
	private Image img = null;
	private static int width = 0;
	private static int height = 0;
	private int x = 0;
	private int y = 0;
	
	public Card(String rank, int suit, int value, Image img) {
		this.setRank(rank);
		this.setSuit(suit);
		this.setValue(value);
		this.setImg(img);
		width = img.getWidth(null);
		height = img.getHeight(null);
		
	}

	/*****Getters and Setters*****/
	
	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public int getSuit() {
		return suit;
	}

	public void setSuit(int suit) {
		this.suit = suit;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}//end setXY()
	
	public void addToXY(int changeX, int changeY) {
		//add to x y values
		x+=changeX;
		y+=changeY;
	}//end addToXY()
	
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
		Card.drawOutline(g, x, y);
	}//end draw()
	
	public static void drawOutline(Graphics g, int x, int y) {
		g.setColor(Color.BLACK);
		g.drawRoundRect(x, y, width, height, 8, 8);
	}//end drawOutline()
	
	public boolean contains(int pointX, int pointY) {
		boolean contains = false;
		if(pointX >= x && pointX <= x+width && pointY >= y && pointY <= y+height) {
			contains = true;
		}
		return contains;
	}//end contains()
	
	public boolean isNear(int pointX, int pointY) {
		//Is my card near another card
		boolean isNear = false;
		int offsetX = width/2;
		int offsetY = height;
		if(pointX > x-offsetX && pointX < x+offsetX && pointY > y-offsetY && pointY < y+offsetY) {
			isNear = true;
		}
		return isNear;
	}//end isNear()
	
	public boolean isNear(Card card) {
		//Check if another card is near my card
		int pointX = card.getX();
		int pointY = card.getY();
		boolean isNear = isNear(pointX, pointY);
		return isNear; 
	}//end isNear()
	
}
