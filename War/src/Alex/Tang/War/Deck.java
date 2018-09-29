package Alex.Tang.War;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * Author: Alexander Tang
 * Date Created: 9-7-2018
 * Last Updated: 9-27-2018
 */
	

public class Deck {
	/*****SETTING CONSTANTS*****/
	private static final String[] RANKS = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
	
	private static final String[] SUITSYMBOLS = {"\u2665","\u2666","\u2660","\u2663" };
	
	private static final String CARD_FRONT = "cards.png";
	
	private static final String CARD_BACK = "cardBack.png";
	
	private static final int CARDWIDTH = 30;
	
	private static final int CARDHEIGHT = 50;
	
	/*****GET METHODS*****/
	
	public static String[] getRanks() {
		return RANKS;
	}
	
	public static String[] getSuitSymbols() {
		return SUITSYMBOLS;
	}
	
	public static int getCardWidth() {
		return CARDWIDTH;
	}
	
	public static int getCardHeight() {
		return CARDHEIGHT;
	}
	
	public Deck() {
		
		Random rand = new Random();//Creates random
		
		try {
			InputStream input1 = getClass().getResourceAsStream(CARD_FRONT);
			InputStream input2 = getClass().getResourceAsStream(CARD_BACK);
			BufferedImage cardsImg = ImageIO.read(input1);
			BufferedImage cardBackImg = ImageIO.read(input2);
			for(int suit = 0; suit < SUITSYMBOLS.length; suit++) {
				for(int rank = 0; rank < RANKS.length; rank++) {
					int pos = 0;
					if(cards.size() > 0) {
						pos = rand.nextInt(cards.size()+1);
					}//end if
					int x = rank*CARDWIDTH;
					int y = suit*CARDHEIGHT;
					Image cardImg = cardsImg.getSubimage(x,y,CARDWIDTH,CARDHEIGHT);
					Card card = new Card(RANKS[rank],suit,VALUES[rank],cardImg,cardBackImg);
					cards.add(pos,card);
				}//end for
			}//end for
		}//end try
		catch(IOException e) {
			String message = "Could not open image file"+CARD_FRONT+" and/or "+CARD_BACK;
			JOptionPane.showMessageDialog(null, message);
		}//end catch()
	}//end Constructor
	
	public Card deal() {
		Card card = cards.remove(0);
		return card;
	}//end deal()
	
	public int size() {
		return cards.size();
	}//end size()
	
	/*****Variables*****/
	private static final int[] VALUES = {0,1,2,3,4,5,6,7,8,9,10,11,12};
	private ArrayList<Card> cards = new ArrayList<Card>();
	
}//end class