package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;

public class Game extends JFrame {
	private JPanel contentPane;
	private Puzzle puzzle;
	private JComponent[][] grid;
	private JPanel mainPanel;

	/**
	 * Launch the application.
	 */
	public void run(Difficulty diff, Puzzle puzzle) {
		JOptionPane.showMessageDialog(null,
				"Quick Instructions:\nCheck Button: Tells you what is wrong or right\n"
				+ "Solved Button: Tells you if it is totally solved or not.\n"
				+ "New Game Button: Sends you Back to the menu and gets rid of current game.\n"
				+ "Save: Saves to file for later or to have friends try.\n"
				+ "Restart: sets it back to original.");
		try {
			Game frame = new Game(diff, puzzle);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public Game(Difficulty diff, Puzzle puzzle) {
		this.puzzle = puzzle;
		setIconImage(Toolkit.getDefaultToolkit().getImage(Game.class.getResource("/Resources/soduku.png")));
		setTitle("Play");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 567);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel lblTitle = title();
		contentPane.add(lblTitle, BorderLayout.NORTH);

		mainPanel = mainPanel();
		contentPane.add(mainPanel, BorderLayout.CENTER);

		JPanel bottomSelection = bottomPanel(diff);
		contentPane.add(bottomSelection, BorderLayout.SOUTH);

		JPanel sideControl = sidePanel();
		contentPane.add(sideControl, BorderLayout.WEST);
	}

	/**
	 * Creates the title jlabel.
	 * 
	 * @return JLabel title
	 */
	private JLabel title() {
		JLabel lblTitle = new JLabel("Sudoku");
		lblTitle.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 36));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		return lblTitle;
	}

	/**
	 * Creates the sidepanel.
	 * 
	 * @return JPanel sidePanel
	 */
	private JPanel sidePanel() {
		JPanel sideControl = new JPanel();
		JPanel topControl = new JPanel();
		JPanel bottomControl = new JPanel();
		sideControl.setLayout(new BorderLayout(0, 0));
		topControl.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		sideControl.add(bottomControl, BorderLayout.SOUTH);
		sideControl.add(topControl, BorderLayout.NORTH);

		JButton btnCheck = new JButton("Check");
		btnCheck.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setGuesses();
				int x;
				int y;
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						if (grid[i][j] instanceof JTextField) {
							mainPanel.remove(grid[i][j]);
							if (!puzzle.checkOriginal(i, j)) {
								if (puzzle.check(i, j)) {
									x = grid[i][j].getX();
									y = grid[i][j].getY();
									JLabel fin = new JLabel(puzzle.get(i, j).toString());
									fin.setHorizontalAlignment(SwingConstants.CENTER);
									fin.setBackground(Color.GREEN);
									fin.setOpaque(true);
									fin.setBorder(BorderFactory.createLineBorder(Color.black));
									grid[i][j] = fin;
									grid[i][j].setBounds(x, y, 48, 46);
									grid[i][j].setFont(new Font("Tahoma", Font.PLAIN, 22));
								} else {
									if (grid[i][j].getBackground() == Color.YELLOW)
										grid[i][j].setBackground(Color.ORANGE);
									else if (grid[i][j].getBackground() == Color.ORANGE)
										grid[i][j].setBackground(Color.RED);
									else
										grid[i][j].setBackground(Color.YELLOW);
									puzzle.set(i, j, 0);
								}
							}
							mainPanel.add(grid[i][j]);
						}
					}
					mainPanel.repaint();
				}
			}
		});
		topControl.add(btnCheck);

		JButton btnSolve = new JButton("Solved");
		btnSolve.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnSolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setGuesses();
				if (puzzle.isSolved())
					JOptionPane.showMessageDialog(null, "Congratulations");
				else
					JOptionPane.showMessageDialog(null,
							"The puzzle is not complete.\n" + "Try to use the check button to see what is correct.");
			}
		});
		topControl.add(btnSolve);

		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu.frame.setVisible(true);
				dispose();
			}
		});
		bottomControl.add(btnNewGame);

		return sideControl;
	}

	/**
	 * Creates the bottom panel.
	 * 
	 * @return JPanel bottomPanel
	 */
	private JPanel bottomPanel(Difficulty diff) {
		ButtonGroup buttonGroup = new ButtonGroup();
		JPanel bottomSelection = new JPanel();
		JPanel bottomSide = new JPanel();
		bottomSelection.setLayout(new BorderLayout(0, 0));
		bottomSide.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 5));
		bottomSelection.add(bottomSide, BorderLayout.EAST);

		JLabel lblLevel = new JLabel(diff.toString());
		lblLevel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		bottomSelection.add(lblLevel, BorderLayout.WEST);

		JRadioButton rdbtnSave = new JRadioButton("Save");
		buttonGroup.add(rdbtnSave);
		rdbtnSave.setFont(new Font("Tahoma", Font.PLAIN, 22));
		bottomSide.add(rdbtnSave);

		JRadioButton rdbtnRestart = new JRadioButton("Restart");
		buttonGroup.add(rdbtnRestart);
		rdbtnRestart.setFont(new Font("Tahoma", Font.PLAIN, 22));
		bottomSide.add(rdbtnRestart);

		JButton btnChange = new JButton("Go");
		btnChange.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnRestart.isSelected()) {
					for (int i = 0; i < 9; i++) {
						for (int j = 0; j < 9; j++) {
							puzzle.set(i, j, puzzle.getOriginal(i, j));
							setUpGrid(mainPanel);
							mainPanel.repaint();
						}
					}
				} else if (rdbtnSave.isSelected()) {
					// save to a file
				} else {

				}

			}
		});
		bottomSide.add(btnChange);

		return bottomSelection;
	}

	/**
	 * Creates the text field grid for user input.
	 * 
	 * @return JPanel mainpanel
	 */
	private JPanel mainPanel() {
		JPanel mainPanel = new Board();
		setUpGrid(mainPanel);
		return mainPanel;
	}

	/**
	 * Creates the grid originally.
	 * 
	 * @param mainPanel
	 */
	private void setUpGrid(JPanel mainPanel) {
		grid = new JComponent[9][9];
		int x;
		int y = 8;
		for (int i = 0; i < 9; i++) {
			x = -41;
			if (i % 3 == 0 && i > 0)
				y += 3;
			for (int j = 0; j < 9; j++) {
				x += 48;
				if (j % 3 == 0 && j > 0)
					x += 3;
				if (puzzle.getOriginal(i, j) == 0) {
					JTextField current = new JTextField();
					current.setColumns(1);
					current.setHorizontalAlignment(SwingConstants.CENTER);
					grid[i][j] = current;
				} else if (puzzle.getOriginal(i, j) != 0) {
					JLabel fin = new JLabel(puzzle.get(i, j).toString());
					fin.setHorizontalAlignment(SwingConstants.CENTER);
					fin.setBackground(Color.LIGHT_GRAY);
					fin.setOpaque(true);
					fin.setBorder(BorderFactory.createLineBorder(Color.black));
					grid[i][j] = fin;
					
				}
				grid[i][j].setBounds(x, y, 48, 46);
				grid[i][j].setFont(new Font("Tahoma", Font.PLAIN, 22));
				mainPanel.add(grid[i][j]);
			}
			y += 46;
		}
	}

	/**
	 * Sets the guesses in the playing puzzle.
	 */
	private void setGuesses() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (grid[i][j] instanceof JTextField) {
					try {
						puzzle.set(i, j, Integer.parseInt(((JTextField) grid[i][j]).getText()));
					} catch (Exception e) {
					}
				}
			}
		}

	}
}
