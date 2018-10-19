package Alex.Tang.myCommonMethods;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
/*****IMPORTS*****/
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

/**
 * Author: Alexander Tang
 * Date Created: 10-9-2018
 * Last Updated: 10-16-2018
 */

public class FileIO {
	public static Clip playClip(Object requestor, String fileName) {
		Clip clip = null;
		try {
			URL url = requestor.getClass().getResource(fileName);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		}
		catch(IOException e) {
			String message = "File " +fileName+ " could not be opened";
			JOptionPane.showMessageDialog(null, message);
		}
		catch(UnsupportedAudioFileException e) {
			String message = "File " +fileName+ " is not a supported file type";
			JOptionPane.showMessageDialog(null, message);
		}
		catch(LineUnavailableException e) {
			String message = "File " +fileName+ " could not be opened at this time";
			JOptionPane.showMessageDialog(null, message);
		}
		return clip;
	}//end playClip
	
	public static BufferedImage readImageFile(Object requestor, String fileName) {
		BufferedImage image = null;
		try {
			InputStream input = requestor.getClass().getResourceAsStream(fileName);
			image = ImageIO.read(input);
		}
		catch(IOException e) {
			String message = "The image file " +fileName+ " could not be opened";
			JOptionPane.showMessageDialog(null, message);
		}
		return image;
	}//end readImageFile()
}//end class
