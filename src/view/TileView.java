/**
 * 
 */
package view;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

import model.Tile;

/**
 * @author Frank J. Mitropoulos
 * 
 * Simple View class that is a JButton and is a view of a Tile
 *
 */
public class TileView extends JButton {
	
	private Tile tile;
	private int row;
	private int col;
	private int posX;
	private int posY;
	
	private ActionListener listener;

	// Several overloaded constructors -- not all are used
	
	public TileView(ActionListener lis) {
		super();
		tile = new Tile(row,col);
		setText(tile.toString());
		listener = lis; 
	}
	
	public TileView(Tile tile) {
		super();
		this.tile = tile;
	
		listener = null; 
		Border emptyBorder = BorderFactory.createEmptyBorder();
		setBorder(emptyBorder);
		setIcon(new ImageIcon(tile.getImg()));
	}
	
	public TileView(int row, int col) {
		super();
		this.tile = null;
		this.row = row;
		this.col = col;
		
	
		listener = null; 
		Border emptyBorder = BorderFactory.createEmptyBorder();
		setBorder(emptyBorder);
	}
	
	public void removeTile() {
		System.out.println("Removing " + this.toString());
		tile = null;
	}
	public int getRow() {

		return tile.getRow();
	}
	public int getCol() {

		return tile.getCol();
	}
	
	public int getColor() {

		return tile.getColor();
	}
	
	public boolean hasTile() {

		return tile!= null;
	}
	
	// Used to add a listener after object contruction
	
	public void addListener(ActionListener lis) {
		listener = lis;
		addActionListener(listener);
	}
	
	public void setPosition(int r, int c) {
		posX = r; posY = c;
		
	}
	
	public void setXY(int row, int col) {
		this.row = row;
		this.col = col;
		tile.setRow(row);
		tile.setCol(col);
	
	}
	
	// set default size for the button as 50x50
	
	public Dimension getPreferredSize() {
		return new Dimension (50,50);
	}
	public String toString() {
		if (tile != null)
			return tile.toString();
		else 
			return "[-----]";
	}
   
}
