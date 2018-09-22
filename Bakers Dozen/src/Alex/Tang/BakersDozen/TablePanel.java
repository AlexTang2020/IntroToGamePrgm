package Alex.Tang.BakersDozen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * Author: Alexander Tang
 * Date Created: 8-30-2018
 * Last Updated: 9-20-2018
 */

public class TablePanel extends JPanel{
	
	//Serialization for Creation
	private static final long serialVersionUID = 1L;
	
	/*****Constants*****/
	private static final int CARDWIDTH = Deck.getCardWidth();
	private static final int CARDHEIGHT = Deck.getCardHeight();
	
	private static final int SPACING = 4;//Space between cards
	private static final int MARGIN = 10;//Margin around table
	private static final int WIDTH = 13*CARDWIDTH + 12*SPACING + 2*MARGIN;//Width of a table
	private static final int HEIGHT = 9*CARDHEIGHT + 3*MARGIN;//Height of a table
	
	private static final int FOUNDATIONX = WIDTH/2 - (4*CARDWIDTH + 3*SPACING)/2;
	private static final int FOUNDATIONY = MARGIN;
	private static final int BORDERX = MARGIN;
	private static final int BORDERY = CARDHEIGHT+MARGIN+MARGIN;
	private static final int OVERLAP = (int)(.65*CARDHEIGHT);
	
	private Deck deck;
	private Deck savedDeck = new Deck();
	//private Card card;
	private CardStack[] foundation = new CardStack[4];
	private CardStack[] column = new CardStack[13];
	
	//moving card
	private Card movingCard;
	private int mouseX = 0;
	private int mouseY = 0;
	private int fromCol = 0;
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		return size;
	}
	
	public TablePanel() {
		int x = FOUNDATIONX;
		int y = FOUNDATIONY;
		for(int i = 0; i < 4; i++) {
			foundation[i] = new CardStack(x,y,0);
			x+=CARDWIDTH+SPACING;
		}//end for
		x = BORDERX;
		y = BORDERY;
		
		for(int i = 0; i < 13; i++) {
			column[i] = new CardStack(x,y,OVERLAP);
			x+=CARDWIDTH+SPACING;
		}//end for
		
		newGame();
		
		//mouse listener
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				clicked(x,y);
			}//end mousePressed()
			
			public void mouseReleased(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				released(x,y);
			}
		});//end listener
		
		addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				dragged(x,y);
			}//end mouseDragged()
		});//end listener
	}//end Constructor
	
	public void newGame() {
		deck = new Deck();
		savedDeck.copyFrom(deck);//save the deck
		deal();
	}//end newGame()
	
	public void replay() {
		deck.copyFrom(savedDeck);
		deal();
	}//end replay
	
	private void deal() {
		for(int i = 0; i < foundation.length; i++) {
			foundation[i].clear();
		}
		for(int i = 0; i < column.length; i++) {
			column[i].clear();
		}
		
		//card = deck.deal();
		//card.setXY(10, 10);
		for(int row = 0; row < 4; row++) {
			for(int col = 0; col < 13; col++) {
				Card card = deck.deal();
				if(card.getValue() == 12) {
					column[col].addToBeginning(card);
				}
				else {
					column[col].add(card);
				}
			}//end for
		}//end for
		repaint();
	}//end deal()
	
	public void paintComponent(Graphics g) {
		//card.draw(g);
		
		//Draw Background
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//Foundation cards
		for(int i = 0; i < 4; i++) {
			if(foundation[i].size() > 0) {
				foundation[i].draw(g);
			}
			else {
				int x = foundation[i].getX();
				int y = foundation[i].getY();
				Card.drawOutline(g, x, y);
			}
		}
		//draw board
		for(int i = 0; i < 13; i++) {
			column[i].draw(g);
		}
		if(movingCard != null) {
			movingCard.draw(g);
		}
	}
	
	private void clicked(int x, int y) {
		movingCard = null;
		for(int col = 0; col < 13 && movingCard == null; col++) {
			if(column[col].size() > 0) {
				Card card = column[col].getLast();
				if(card.contains(x, y)) {
					movingCard = card;
					mouseX = x;
					mouseY = y;
					column[col].removeLast();
					fromCol = col;
					int val = card.getValue();
					System.out.println(val);
				}
			}
		}//for
	}//end clicked()
	
	//mouse drag
	private void dragged(int x, int y) {
		if(movingCard != null) {
			int changeX = x - mouseX;
			int changeY = y - mouseY;
			movingCard.addToXY(changeX, changeY);
			mouseX = x;
			mouseY = y;
			repaint();
		}
	}//end dragged()
	
	private void released(int x, int y) {
		if(movingCard != null) {
			boolean validMove = false;
			
			//play on a foundation
			for(int i = 0; i < 4 && !validMove; i++) {
				int foundationX = foundation[i].getX();
				int foundationY = foundation[i].getY();
				if(movingCard.isNear(foundationX, foundationY)) {
					if(foundation[i].size() == 0) {
						if(movingCard.getValue() == 0) {
							validMove = true;
							foundation[i].add(movingCard);
							movingCard = null;
						}//end if
					}//end if
					else {
						Card topCard = foundation[i].getLast();
						if(movingCard.getSuit() == topCard.getSuit()) {
							if(movingCard.getValue() == topCard.getValue()+1) {
								validMove = true;
								foundation[i].add(movingCard);
								movingCard = null;
								isGameOver();
							}//end if
						}//end if
					}//end else
				}//end if
			}//end for
		
			//check other stacks
			for(int i = 0; i < 13 && !validMove; i++) {
				if(column[i].size() > 0) {
					Card card = column[i].getLast();
					if(movingCard.isNear(card) && movingCard.getValue() == card.getValue()-1) {
						validMove = true;
						column[i].add(movingCard);
						movingCard = null;
					}
				}//end if
			}//end for
			
			//check the abyss
			if(!validMove) {
				column[fromCol].add(movingCard);
				movingCard = null;
			}
			repaint();
		}//end if
	}//end released()
	
	private void isGameOver() {
		boolean gameOver = true;
		for(int i = 0; i < 4 && gameOver; i++) {
			if(foundation[i].size() < 1) {
				gameOver = false;
			}//end if
		}//end for
		if(gameOver) {
			String message = "YOU WIN! Do you want to play again?";
			int option = JOptionPane.showConfirmDialog(this, message, "Play Again?", JOptionPane.YES_NO_OPTION);
			
			//check user response
			if(option == JOptionPane.YES_OPTION) {
				newGame();
			}
			else {
				System.exit(0);
			}
		}
	}//end isGameOver
}//end class
