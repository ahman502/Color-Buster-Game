/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.HashSet;

import model.Tile;

/**
 * @author Frank J. Mitropoulos
 *
 */
public class Board {
	

	private Tile[][] tiles;
	private int cols;
	private int rows;
	
	public Board(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		tiles = new Tile[rows][cols];
		initializeBoard();
	
	}
	
	public void initializeBoard() {
		do {
			for (int row=0;row<rows;row++)
				for (int col=0;col<cols;col++) 
					tiles[row][col] = new Tile(row, col);
			if (!isMoveAvailable()) 
				System.out.println("**********No moves -- reinitializing board");
		} while(!isMoveAvailable());
	}
	
	public boolean hasTileAt(int row, int col) {
		if (tiles[row][col] != null)
			return true;
		else
			return false;
	}
	public Tile tileAt(int row, int col) {
		return tiles[row][col];
	}
	
	public void removeTileAt(int row, int col) {
		tiles[row][col] = null;
	}
	
	public void removeMatchingTiles(HashSet<Tile> matches) {
		for (Tile t: matches) {
			removeTileAt(t.getRow(), t.getCol());
		}
	}
	
	private void collapseColumn(int colToColapse) {
		ArrayList<Tile> newCol = new ArrayList<Tile>();
		
		for (int row=0; row<rows; row++) {
			if (hasTileAt(row, colToColapse)) {
				newCol.add(tileAt(row, colToColapse));
			}
		}
		
       // Add new tiles to the top at random
		int tilesToAdd = rows - newCol.size();
		System.out.println("Adding " + tilesToAdd + " new Tiles");
		
		for (int i=1;i<=tilesToAdd;i++) {
			newCol.add(new Tile(newCol.size() + i, colToColapse));
		}
		
			if (newCol.size() > 0) {
				for (int row=0; row<newCol.size(); row++) {
					tiles[row][colToColapse] = newCol.get(row);
					tiles[row][colToColapse].setRow(row);
					tiles[row][colToColapse].setCol(colToColapse);
				}
			
			}
		}
		
		
	
	
	public void collapseColumns() {
		for (int col=0; col<cols; col++) {
			collapseColumn(col);
		}
	}
	
	public boolean isNorthMatch (int row, int col,  int color) {
		if (row >= 0 && row <= rows-2 && tiles[row+1][col] != null && tiles[row+1][col].getColor() == color)
			return true;
		else
			return false;
	}
	
	public boolean isSouthMatch (int row, int col, int color) {
		if (row >=1  && row <= rows-1 && tiles[row-1][col] != null && tiles[row-1][col].getColor() == color)
			return true;
		else
			return false;
	}
	
	public boolean isEastMatch(int row, int col, int color) {
		if (col >=0 && col <= cols-2 && tiles[row][col+1] != null && tiles[row][col+1].getColor() == color)
			return true;
		else
			return false;
	}
	
	public boolean isWestMatch(int row, int col, int color) {
		if (col >=1 && col <= cols-1 &&  tiles[row][col-1] != null && tiles[row][col-1].getColor() == color)
			return true;
		else
			return false;
	}
	
	
	
	
	
	public void locateNeighbors(int row, int col, int color, HashSet<Tile> matches) {
		
		Tile b = tiles[row][col];
		if (matches.contains(b)) {
			return;
		} else {
			matches.add(b);
		}
		
		// Check NORTH
		if (isNorthMatch(row,col,color)) {
			locateNeighbors(row+1,col,color, matches);
		}
		
		//Check SOUTH

		if (isSouthMatch(row,col,color)) {
			locateNeighbors(row-1,col,color, matches);
		}
		
		
		// Check EAST
		if (isEastMatch(row,col,color)) {
			locateNeighbors(row,col+1,color, matches);
		}
		
		// Check WEST
		
		if (isWestMatch(row,col,color)) {
			locateNeighbors(row,col-1,color, matches);
		}
	}
	
	public String toString() {
		String s = "";
		for (int row=rows-1;row>=0;row--) {
			for (int col=0;col<cols;col++)  {
				if (tiles[row][col] != null)
					s += tiles[row][col].toString();
				else
					s += "[-----]";
			}
			s += "\n";
		}
		return s;
	}
	
	public boolean isMoveAvailable() {
		for (int col=0; col<cols;col++) {
			for (int row=0; row< rows; row++) {
				int color = tiles[row][col].getColor();
				HashSet<Tile> matches = new HashSet<Tile>();
				locateNeighbors(row, col, color, matches);
				if (matches.size() >= 3)
					return true;
			}
		}
		return false;
	}
	
}
