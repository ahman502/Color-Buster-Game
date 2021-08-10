/**
 * 
 */
package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.ImageIcon;

/**
 * @author Frank J. Mitropoulos
 *
 */
public class Tile { 

	
	/**
	 * Some simple jpg files for solid color tiles.
	 * 
	 */
	public static String [] colors = {
			"Resources/images/Green.jpg",
			"Resources/images/Blue.jpg",
			"Resources/images/Cyan.jpg",
			"Resources/images/Red.jpg",
			"Resources/images/Yellow.jpg"
	};
	
	
	private int col;
	private int row;
	private Image img;
	private int color;
	private int status;
	/**
	 * @param x
	 * @param y
	 */
	public Tile(int row, int col) {
		super();
		this.row = row;
		this.col = col;
		color = randInt(0,4);
		status = 0;
		
		try {
			img = ImageIO.read(new FileImageInputStream(new File(colors[color])));
		} catch (IOException ex) {
			
		}
	}
	/**
	 * @return the x
	 */
	public int getRow() { 
		return row;
	}
	/**
	 * @param x the x to set
	 */
	public void setRow(int x) {
		this.row = x;
	}
	/**
	 * @return the y
	 */
	public int getCol() {
		return col;
	}
	/**
	 * @param y the y to set
	 */
	public void setCol(int y) {
		this.col = y;
	}
	/**
	 * @return the img
	 */
	public Image getImg() {
		return img;
	}
	/**
	 * @param img the img to set
	 */
	public void setImg(Image img) {
		this.img = img;
	}
	/**
	 * @return the color
	 */
	public int getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(int color) {
		this.color = color;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int stat) {
		status = stat;
	}
	
	public String toString() {
		String c = "Y";
		if (color == 0) 
			c = "G";
		else if (color == 1)
			c = "B";
		else if (color == 2)
			c = "C";
		else if (color == 3)
			c = "R";
		else
			c = "Y";
		
			
		return "[" + row + "," + col + "," + c + "]";
	}
	
	
	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}


}
