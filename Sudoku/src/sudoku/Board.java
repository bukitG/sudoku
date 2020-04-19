package sudoku;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Board extends JPanel {
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
