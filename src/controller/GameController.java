/**
 * 
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.GameView;
import view.TileView;

/**
 * @author Frank J. Mitropoulos
 *
 */
public class GameController {
	// These aren't used, but could be depending on your gme and what you want to do
	private int score;
	private int gameStatus;
	private int rows;
	private int cols;
	private int moveNumber = 0;
	
	private GameView gameView;
	
	

	/**
	 * Create the GameView and pass in the appropriate listeners
	 */
	public GameController() {
		super();		
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {

				/** Game's name changed here */
				gameView = new GameView("Color Buster Game",6,6,null,new NewGameListener(), new TileTouchedListener());
				gameView.setVisible(true);	
				
			}
		});
		
	}
	
	// Listener used to process touches on TileView
	
	class TileTouchedListener implements ActionListener {
		@Override 
		public void actionPerformed(ActionEvent event) {
			TileView tv = (TileView) event.getSource();
			System.out.println("Tile touched..." + tv.toString());

			gameView.processTouchedTile(tv);         /** Added processTouchTile here so the function is called with every tile that's touched */
			// If no move is available display a message
			if (!gameView.isMoveAvailable()) {

				/** Calling the newGame method so a new game starts when there are no more moves available */
				JOptionPane.showMessageDialog(gameView, "No more moves... \nStarting New Game");
				System.out.println("Starting new game...");
				gameView.newGame();
			}
		}
		
	}
	
	// Listener used to process click on New Game Button
	
	class NewGameListener implements ActionListener {
		@Override 
		public void actionPerformed(ActionEvent event) {
			System.out.println("Starting new game...");

			/** Calling the new game method so a new game begins when the New Game button is clicked */
			gameView.newGame();

		}
		
	}
}
