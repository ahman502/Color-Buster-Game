/**
 * 
 */
package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;


/**
 * @author Frank J. Mitropoulos
 * 
 *  A very simple score panel
 *  call updateScore and pass in the score to update the display
 */
public class ScoreView extends JPanel {

	
	private static final long serialVersionUID = 1L;
	
	private JLabel scoreLabel;
	public int totalScore;                /** Added a new variable */

	public ScoreView() {
		scoreLabel = new JLabel("Score: 0");
		add(scoreLabel);

	}
	
	public void updateScore(int score) {
		totalScore += score;             /** The old score from touching a tile is added to the new score generated from clicking more tiles.  */
		scoreLabel.setText("Score: " + totalScore);       /** Then the total score is shown in the score View area */
	}

	public void clearScore() {                 /** Added a new function which is called when a new game is generated so the score can go back to being a 0 */
		totalScore = 0;              /** Total score is set to 0 */
		scoreLabel.setText("Score: " + totalScore);          /** The new game's total score is 0 now */
	}

}
