/**
 * 
 */
package view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.JPanel;

import model.Board;
import model.Tile;
import view.TileView;

/**
 * @author Frank J. Mitropoulos
 * 
 * View class over the board model.
 *
 *
 */
public class BoardView extends JPanel {
	
	
	private static final long serialVersionUID = 1L;
	private Board b;
	private int rows;
	private int cols;
	private int tileSize;
	private ActionListener listener;
	private int spacer;

	
	private TileView [][] tileGrid;
	
	// Create  BoardView with rows and cols and lis is the actionListener for all the TileViews
	public BoardView(int rows, int cols, ActionListener lis) {
	    spacer = 4;
		listener = lis;
		Dimension s = getPreferredSize();
		System.out.println("BoardView: " + s);
		this.rows = rows;
		this.cols = cols;	
			
		int totalSpace = (cols+1) * spacer;
		System.out.println("totalSpace : " + totalSpace);
		
		tileSize = (s.width-totalSpace) / cols;
		System.out.println("TileSize : " + tileSize);
		
		// I'm not using a layout manager here since I'm using XY to layout the TileViews
		// Quick and simple
		
		setLayout(null);
		tileGrid = new TileView[rows][cols];
		b = new Board(rows,cols);
		for (int row=0; row<rows; row++) {
			for (int col=0; col<cols; col++) {
				TileView tv = new TileView(b.tileAt(row, col));
				add(tv);
				tileGrid[row][col] = tv;
			
				tv.setBounds((tileSize * col + spacer), (tileSize * ((rows-1)-row) + spacer),
							             tileSize, tileSize);
				tv.setPosition(tileSize * col + spacer, (tileSize * ((rows-1)-row) + spacer));
				
				tv.addListener(listener);

				
			}
		}
		
	}
	
// Call this method whenever you want to update the boardView on the display from the current status of the board
	
	public void updateBoardViewFromBoard() {
		System.out.println("In updateBoardViewFromBoard...");
		TileView tv;
		removeAll();
		tileGrid = new TileView[rows][cols];
		for (int row=0; row<rows; row++) {
			for (int col=0; col<cols; col++) {
				if (b.hasTileAt(row,col)) {
					 tv = new TileView(b.tileAt(row, col));
				}
				else  {
					tv = new TileView(row,col);
					
				}
				add(tv);
				tileGrid[row][col] = tv;
			
			
				tv.setBounds((tileSize * col + spacer), (tileSize * ((rows-1)-row) + spacer),
							             tileSize, tileSize);
				tv.setPosition(tileSize * col + spacer, (tileSize * ((rows-1)-row) + spacer));
				
				tv.addListener(listener);

				
			}
		}
		
		
	}
	

	// returns the score which would be however you decide to score matched tiles.
	// You don't have to handle the score this way -- do it however you decide works best for you
	public int processTouchedTile(TileView tv) {

		/** Calling located neighbors, then remove and collapse tiles method if there are 3 or more matching tiles in a cluster */
		HashSet<Tile> matches = new HashSet<Tile>();
		b.locateNeighbors(tv.getRow(), tv.getCol(), tv.getColor(), matches);
		if (matches.size() >= 3) {
			b.removeMatchingTiles(matches);
			b.collapseColumns();
			// Don' forget to call updateBoardViewFromBoard() at the end of this method
			updateBoardViewFromBoard();
			System.out.println(b);
			return (matches.size() * 10);       /** Each matching tile in a cluster of 3 or more tiles is worth 10 points */
		}

		else {
			return (-10);   /** Each tile touched that is either alone or in a cluster of 2 tiles will subtract 10 points from the total score */
		}
	}
	
	
	public String toString() {
		return b.toString();
	}
	

	public boolean isMoveAvailable() {

		return b.isMoveAvailable();
	}
	
	// set the default size of the boardView to a reasonable size
	public Dimension getPreferredSize() {

		return new Dimension(400,400);
	}
}
