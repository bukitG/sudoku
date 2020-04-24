package sudoku;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Creates the background of the game to look like sudoku board.
 * 
 * @author alexp
 *
 */
public class Board extends JPanel {
	/**
	 * Serial Version -530356459707743384L.
	 */
	private static final long serialVersionUID = -530356459707743384L;
	private ImageIcon playBoard = new ImageIcon(Board.class.getResource("/Resources/sudoku-blankgrid.png"));
	/**
	 * Create the panel.
	 */
	public Board() {
		setLayout(null);
	}
	
	@Override
	  protected void paintComponent(Graphics g) {
	    super.paintComponent(g);      
	    ImageIcon background = playBoard;
		background.paintIcon(this, g, 0, 0);
	}
}
