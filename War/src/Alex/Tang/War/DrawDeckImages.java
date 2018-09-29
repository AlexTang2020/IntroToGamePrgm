package Alex.Tang.War;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * Author: Alexander Tang
 * Date Created: 9-7-2018
 * Last Updated: 9-28-2018
 */

public class DrawDeckImages {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Get Deck Values
		String[] suits = Deck.getSuitSymbols();
		String[] ranks = Deck.getRanks();
		int cardWidth = Deck.getCardWidth();
		int cardHeight = Deck.getCardHeight();
		
		//Screen Size
		int imageWidth = 13 * cardWidth;
		int imageHeight = 4 * cardHeight;
		
		BufferedImage img = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.setColor(new Color(0,0,0,0));
		g.fillRect(0, 0, imageWidth, imageHeight);
		
		//Font Style
		Font font = new Font(Font.DIALOG, Font.BOLD, 24);
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics(font);
		
		//Draw Cards
		for(int row = 0, y = 0; row < 4; row++ , y+=cardHeight){
			for(int col = 0, x = 0; col < 13; col++, x+=cardWidth) {
				g.setColor(Color.WHITE);
				g.fillRoundRect(x, y, cardWidth-1, cardHeight-1, 8, 8);
				
				//Check first 2 rows to be red
				if(row < 2) {
					g.setColor(Color.RED);
				}
				else {
					g.setColor(Color.BLACK);
				}//end if
				
				//Draw Rank
				String rank = ranks[col];
				String suit = suits[row];
				int rankWidth = fm.stringWidth(rank);
				int fromLeft = x+cardWidth/2-rankWidth/2;
				int fromTop = y+20;
				g.drawString(rank, fromLeft, fromTop);
				g.drawString(suit, fromLeft, fromTop+20);
			}//end for col
		}//end for row
		
		String fileName = "cards.png";
		File file = new File(fileName);
		try {
			ImageIO.write(img, "png", file);
		}
		catch(IOException e){
			String message = "Could not save " + fileName;
			JOptionPane.showMessageDialog(null, message);
		}
		
	}//end main

}//end class
