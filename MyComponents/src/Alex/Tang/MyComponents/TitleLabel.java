package Alex.Tang.MyComponents;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;

public class TitleLabel extends JLabel{

	/**
	 * Author: Alexander Tang
	 * Date Created: 8-23-2018
	 * Last Updated: 8-28-2018
	 */
	
	//Serialization for Recreation
	private static final long serialVersionUID = 1L;
	
	//Constructor
	public TitleLabel(String title) {
		Font myFont = new Font(Font.SERIF, Font.BOLD, 32);
		setFont(myFont);
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		setOpaque(true);
		setHorizontalAlignment(JLabel.CENTER);
		setText(title);
	}// end constructor
	
}//end class
