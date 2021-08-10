/**
 * 
 */
package view;
import javax.swing.JFrame;
import java.awt.*;

//import view.ScoreView;
//import view.ButtonView;
import view.BoardView;
import java.awt.event.*;

/**
 * @author Frank J. Mitropoulos
 *
 */
public class GameView extends JFrame {
	// Create the HUD Panel
	// Create the Board
	
	
	private static final long serialVersionUID = 1L;
	private ScoreView scoreView;
	private ButtonView buttonView;
	private BoardView boardView;
	
	private int score;
	
	// Setting default to 8x8
	
	int rows = 8, cols = 8;
	int width, height;

	ActionListener newGameListener;
	ActionListener tileTouchedListener;
	
	/**
	 * @param title
	 * @throws HeadlessException
	 */
	
	
	public GameView(String title, int rows, int cols, MouseListener listener, ActionListener newGameListener, ActionListener tileTouched) throws HeadlessException {
		super(title);
		setResizable(false);
		
		width = 400;
		height = 500;

		/** Commented out the score */
		//score = 0;
		this.rows = rows;
		this.cols = cols;
		
		this.newGameListener = newGameListener;
		this.tileTouchedListener = tileTouched;
		
		// Set up some reasonable sizes for the gameView
		
		setBounds(100,50,width, height);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		scoreView = new ScoreView();
    	add(scoreView, BorderLayout.NORTH);
		
    	buttonView = new ButtonView(newGameListener);
    	add(buttonView, BorderLayout.SOUTH);
		
		boardView = new BoardView(rows,cols, tileTouchedListener);
		add(boardView, BorderLayout.CENTER);
		
		setVisible(true);
				
	}
	
	// Delegate to boardView
	public boolean isMoveAvailable() {
		/** Checking if more moves are available by delegating it to boardView */
		if (boardView.isMoveAvailable()){
			return true;
		}
		else {
			return false;
		}
	}
	
	// Call this method to start a new Game
	public void newGame() {
		// Recreate some components and update the GUI.
		
		Container c = getContentPane();
		c.remove(boardView);
		c.invalidate();
		pack();

		boardView = null;
		score = 0;                     /** The score is initialized to 0 here */
		scoreView.clearScore();       /** Method is called when a new game is started */
		
		boardView = new BoardView(rows,cols, tileTouchedListener);
		
		add(boardView, BorderLayout.CENTER);
		pack();
		revalidate();
		setBounds(100,50,width, height);
		
		System.out.println(boardView);  // debug
	}
	
	public void processTouchedTile(TileView tv) {

		System.out.println("GameView == processing tile touch");

		/** When a tile is touched, the processTouchTile is called, and x is returned in the form of the score, which is displayed */
		int x = boardView.processTouchedTile(tv);
		scoreView.updateScore(x);              /** The score view's function updateScore is updated with x, which is what is returned by the processTouchTile function. x is the score for how many tiles were in the cluster that was touched  */
		System.out.println(boardView); // debug
	}

}
