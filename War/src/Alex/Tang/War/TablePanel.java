package Alex.Tang.War;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * Author: Alexander Tang
 * Date Created: 9-7-2018
 * Last Updated: 9-28-2018
 */

public class TablePanel extends JPanel{
	
	//Serialization for Creation
	private static final long serialVersionUID = 1L;
	
	/*****Constants*****/
	private static final int CARDWIDTH = Deck.getCardWidth();
	private static final int CARDHEIGHT = Deck.getCardHeight();
	
	private static final int SPACING = 6;//Space between cards
	private static final int MARGIN = 10;//Margin around table
	private static final int WIDTH = 13*CARDWIDTH + 12*SPACING + 2*MARGIN;//Width of a table
	private static final int HEIGHT = 9*CARDHEIGHT + 3*MARGIN;//Height of a table
	
	//Placement of Player's deck
	private static final int P1DECKX =13*CARDWIDTH;
	private static final int P1DECKY = 8*CARDHEIGHT;
	
	//Placement of AI's deck
	private static final int AIDECKX = CARDWIDTH;
	private static final int AIDECKY = MARGIN;
	
	//Placement of Discard pile
	private static final int DISCARDX = WIDTH/2 - 3*MARGIN/2;
	private static final int DISCARDY = HEIGHT/2 - 4*MARGIN;

	//Placement of AI and Player hands for card stacks
	private static final int AIHANDX = WIDTH/2 - (5*CARDWIDTH + 4*SPACING)/2;
	private static final int AIHANDY = CARDHEIGHT;
	private static final int PHANDX = WIDTH/2 - (5*CARDWIDTH + 4*SPACING)/2;
	private static final int PHANDY = 7*CARDHEIGHT;
	
	//Placement of AI and Player Placed card
	private static final int AICHOICEY = HEIGHT/4;
	private static final int PCHOICEY = 3*HEIGHT/5;
	
	private Deck deck;
	private CardStack[] aiHand = new CardStack[5];
	private CardStack[] playerHand = new CardStack[5];
	
	//Card stacks for AI and Player decks and Discard pile
	private CardStack discard = new CardStack(DISCARDX, DISCARDY,0);
	private CardStack aiStack = new CardStack(AIDECKX, AIDECKY, 0);	
	private CardStack playerStack = new CardStack(P1DECKX, P1DECKY, 0);
	
	//Card stacks for card choices
	private CardStack playerChoice = new CardStack(DISCARDX,PCHOICEY,0);
	private CardStack aiChoice = new CardStack(DISCARDX,AICHOICEY,0);
	
	//Card you are selecting
	private Card movingCard;
	private int mouseX = 0;
	private int mouseY = 0;
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		return size;
	}// end getPreferredSize()
	
	public TablePanel() {
		int x = AIHANDX;
		int y = AIHANDY;
		int px = PHANDX;
		int py = PHANDY;
		
		//Set up card stacks for AI and Player hands
		for(int i = 0; i < 5; i++) {
			aiHand[i] = new CardStack(x,y,0);
			playerHand[i] = new CardStack(px,py,0);
			x+=CARDWIDTH+SPACING;
			px+=CARDWIDTH+SPACING;
		}//end for
		
		
		
		//mouse listener
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				clicked(x,y);
				selected(x,y);
			}//end mousePressed()
					
			public void mouseReleased(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				released(x,y);
			}//end mouseReleased()
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
		deal();
	}//end newGame()
	
	//Will deal both players half of the deck
	private void deal() {
		//Clears the board and hands
		playerStack.clear();
		aiStack.clear();
		discard.clear();
		playerChoice.clear();
		aiChoice.clear();
		for(int i = 0; i < playerHand.length; i ++) {
			playerHand[i].clear();
			aiHand[i].clear();
		}//end for
		
		//Deal cards to player and ai
		for(int i = 0; i < 52; i++) {
			Card card = deck.deal();
			if(i%2 == 0) {
				playerStack.add(card);
			}
			else {
				aiStack.add(card);
			}//end if
		}//end for
		
		//Deal ai's hand
		for(int i = 0; i < aiHand.length; i++) {
			if(aiStack.size() != 0) {
				Card card = aiStack.remove();
				aiHand[i].add(card);
			}//end if
		}//end for
		repaint();
	}//end deal()
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		//Draw Background
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//Player,AI, and Discard decks
		Card.drawOutline(g2d, P1DECKX, P1DECKY);
		Card.drawOutline(g2d, AIDECKX, AIDECKY);
		Card.drawOutline(g2d, DISCARDX, DISCARDY);
		
		//AI hand
		for(int i = 0; i < 5; i++) {
			if(aiHand[i].size() > 0) {
				aiHand[i].drawBack(g2d);
			}
			else {
				int x = aiHand[i].getX();
				int y = aiHand[i].getY();
				Card.drawOutline(g2d, x, y);
			}//end if
		}//end for
		
		//Player hand
		for(int i = 0; i < 5; i++) {
			if(playerHand[i].size() > 0) {
				playerHand[i].draw(g2d);
			}
			else {
				int x = playerHand[i].getX();
				int y = playerHand[i].getY();
				Card.drawOutline(g2d, x, y);
			}//end if
		}//end for
		
		//Player and AI decks, choices, and discard pile
		for(int i = 0; i < aiStack.size(); i++) {
			aiStack.drawBack(g2d);
		}//end for
		for(int i = 0; i < playerStack.size(); i++) {
			playerStack.drawBack(g2d);
		}//end for
		
		//Player and AI played card
		if(playerChoice.size() > 0) {
			playerChoice.draw(g2d);
		}
		else {
			Card.drawOutline(g2d, DISCARDX, PCHOICEY);
		}//end if

		if(aiChoice.size() > 0) {
			aiChoice.draw(g2d);
		}
		else {
			Card.drawOutline(g2d, DISCARDX, AICHOICEY);
		}//end if

		for(int i = 0; i < discard.size(); i++) {
			discard.draw(g2d);
		}//end for
		
		//Draws card you are moving
		if(movingCard != null) {
			movingCard.draw(g2d);
		}//end if
	}//end paint()
	
	private void clicked(int x, int y) {
		movingCard = null;
		if(playerStack.size() > 0) {
			Card card = playerStack.getLast();
			if(card.contains(x, y)) {
				movingCard = card;
				mouseX = x;
				mouseY = y;
				playerStack.removeLast();
			}//end if
		}//end if
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
		}//end if
	}//end dragged()
	
	private void released(int x, int y) {
		if(movingCard != null) {
			boolean validMove = false;

			//place on a hand
			for(int i = 0; i < 5 && !validMove; i++) {
				int pHandX = playerHand[i].getX();
				int pHandY = playerHand[i].getY();
				if(movingCard.isNear(pHandX, pHandY)) {
					if(playerHand[i].size() == 0) {
							validMove = true;
							playerHand[i].add(movingCard);
							movingCard = null;
					}//end if
				}//end if
			}//end for
			
			//check the abyss
			if(!validMove) {
				playerStack.add(movingCard);
				movingCard = null;
			}//end if
			repaint();
		}//end if
	}//end released()
	
	private void selected(int x, int y) {
		//Listen for mouse click on playerHand, move card to playerChoice if empty
		
		for(int i = 0; i < playerHand.length; i++) {
			if(playerHand[i].size() == 1) {
				Card card = playerHand[i].getLast();;
				if(card.contains(x, y)) {
					String message = "Are you ready for battle?";
					int option = JOptionPane.showConfirmDialog(this, message, "Mindful War", JOptionPane.YES_NO_OPTION);
					
					//check user response
					if(option == JOptionPane.YES_OPTION) {
						mouseX = x;
						mouseY = y;
						playerHand[i].removeLast();
						playerChoice.add(card);
						Card aiCard = null;
						int aiPick = 0;
						
						//AI Chooses highest value card in hand
						for(int j = 1; j < aiHand.length; j++) {
							if(aiHand[j].size() != 0) {
								if(aiHand[aiPick].getLast().getValue() < aiHand[j].getLast().getValue()) {
									aiPick = j;
								}//end if
							}//end if
						}//end for
						aiCard = aiHand[aiPick].remove();
						aiChoice.add(aiCard);
						
						JOptionPane.showMessageDialog(this, "Please wait for Opponent");
						repaint();
						
						//Pause for AI
						try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}//end try
						battleCalc(card, aiCard, aiPick);
						
						repaint();
						isGameOver();
					}
				}
			}
		}
	}
	
	//Determines winner of battle
	private void battleCalc(Card card, Card aiCard, int aiPick) {
		if(card.getValue() > aiCard.getValue()) {
			JOptionPane.showMessageDialog(this, "You Won the Battle!");
			playerStack.addToBeginning(card);
			playerStack.addToBeginning(aiCard);
		}
		else if(card.getValue() < aiCard.getValue()) {
			JOptionPane.showMessageDialog(this, "You Lost the Battle!");
			aiStack.addToBeginning(card);
			aiStack.addToBeginning(aiCard);
		}
		else {
			JOptionPane.showMessageDialog(this, "It's a Tie! Both cards are lost!");
			discard.add(card);
			discard.add(aiCard);
		}//end if
		playerChoice.remove();
		aiChoice.remove();
		if(aiStack.size() != 0) {
			Card aiDraw = aiStack.remove();
			aiHand[aiPick].add(aiDraw);
		}//end if
	}
	
	private void isGameOver() {
		//Check player and ai hand and deck
		boolean gameOver = true;
		for(int i = 0; i < playerHand.length; i++) {
			if(playerHand[i].size() != 0) {
				gameOver = false;
				break;
			}//end if
		}//end for
		if(playerStack.size() == 0 && gameOver) {
			String message = "YOU LOST! Do you want to play again?";
			int option = JOptionPane.showConfirmDialog(this, message, "Play Again?", JOptionPane.YES_NO_OPTION);
			
			//check user response
			if(option == JOptionPane.YES_OPTION) {
				newGame();
			}
			else {
				System.exit(0);
			}//end if
		}
		else {
			if(aiStack.size() == 0) {
				if(aiHand[0].size()==0 && aiHand[1].size()==0 && aiHand[2].size()==0 && aiHand[3].size()==0 && aiHand[4].size()==0) {
					String message = "YOU WON! Do you want to play again?";
					int option = JOptionPane.showConfirmDialog(this, message, "Play Again?", JOptionPane.YES_NO_OPTION);
					
					//check user response
					if(option == JOptionPane.YES_OPTION) {
						newGame();
					}
					else {
						System.exit(0);
					}//end if
				}//end if
			}//end if
		}//end if
	}//end isGameOver
	
}//end class
