package Alex.Tang.War;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Author: Alexander Tang
 * Date Created: 9-6-2018
 * Last Updated: 9-28-2018
 */


public class CardStack {
	ArrayList<Card> cards = new ArrayList<Card>();
	int stackX = 0;
	int stackY = 0;
	int overlap = 0;
	
	public CardStack(int stackX, int stackY, int overlap) {
		this.stackX = stackX;
		this.stackY = stackY;
		this.overlap = overlap;
		
	}//end constructor
	
	public void add(Card card){
		int cardX = stackX;
		int cardY = stackY+overlap*cards.size();
		card.setXY(cardX, cardY);
		cards.add(card);
	}//end add()
	
	public void addToBeginning(Card card) {
		card.setXY(stackX, stackY);
		cards.add(0,card);
		for(int i = 1; i < cards.size(); i++) {
			Card nextCard = cards.get(i);
			nextCard.addToXY(0, overlap);
		}//for
	}//end addToBeginning()
	
	//Remove top card from the stack(meaning hand and/or deck)
	public Card remove() {
		Card card = cards.remove(cards.size()-1);
		return card;
	}//end remove()
	
	public void draw(Graphics2D g) {
		if(cards.size() > 0 && overlap == 0) {
			int lastIndex = cards.size()-1;
			Card card = cards.get(lastIndex);
			card.draw(g);	
		}
		else {
			for(int i = 0; i < cards.size(); i++) {
				Card card = cards.get(i);
				card.draw(g);
			}//end for
		}//end if
	}//draw()
	
	public void drawBack(Graphics2D g) {
		if(cards.size() > 0 && overlap == 0) {
			int lastIndex = cards.size()-1;
			Card card = cards.get(lastIndex);
			card.drawBack(g);	
		}
		else {
			for(int i = 0; i < cards.size(); i++) {
				Card card = cards.get(i);
				card.drawBack(g);
			}//end for
		}//end if
	}// end drawBack()
	
	public Card getLast() {
		int index = cards.size()-1;
		return cards.get(index);
	}// end getLast()
	
	public void removeLast() {
		int index = cards.size()-1;
		cards.remove(index);
	}// end removeLast()
	
	public int size() {
		return cards.size();
	}//end size()
	
	public int getX() {
		return stackX;
	}//end getX()
	
	public int getY() {
		return stackY;
	}//end getY()
	
	public void clear() {
		cards.clear();
	}//end clear()
}//end class
