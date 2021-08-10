/**
 * 
 */
package view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.*;

/**
 * @author Frank J. Mitropoulos
 *
 */
public class ButtonView extends JPanel {

	 
	/**
	 * Simple button view that contains a New Game button and a levelSelector combo box.
	 * Only the NewGame button listener is currently implemented.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton newGameButton;
	private JComboBox levelSelector;
	private JLabel levelLabel;
	
	public ButtonView(ActionListener gameButtonListener) {
		
		levelLabel = new JLabel();
		levelLabel.setText("Match Size: ");
		add(levelLabel);
		
		levelSelector = new JComboBox();

		/** Removed the 4 and 5 number options from the JCombo box */
		levelSelector.addItem("3");

		add(levelSelector);
		
		newGameButton = new JButton();
		newGameButton.setText("New Game");
		newGameButton.addActionListener(gameButtonListener);
		add(newGameButton);

	}
}
