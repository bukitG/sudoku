package sudoku;

import java.awt.BorderLayout;

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
import javax.swing.ButtonGroup;

public class Game extends JFrame {
	private JPanel contentPane;
	private final JButton btnSolve = new JButton("Solved");
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Puzzle puzzle;
	private JComponent[][] grid;

	/**
	 * Launch the application.
	 */
	public void run(Difficulty diff, Puzzle puzzle) {
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
		setBounds(100, 100, 681, 576);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel lblTitle = title();
		contentPane.add(lblTitle, BorderLayout.NORTH);

		JPanel mainPanel = mainPanel();
		contentPane.add(mainPanel, BorderLayout.CENTER);

		JPanel bottomSelection = bottomPanel(diff);
		contentPane.add(bottomSelection, BorderLayout.SOUTH);

		JPanel sideControl = sidePanel();
		contentPane.add(sideControl, BorderLayout.WEST);
	}

	/**
	 * Creates the sidepanel.
	 * 
	 * @return JPanel sidePanel
	 */
	private JPanel sidePanel() {
		JPanel sideControl = new JPanel();
		JPanel topControl = new JPanel();
		sideControl.setLayout(new BorderLayout(0, 0));

		topControl.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnCheck = new JButton("Check");
		btnCheck.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		topControl.add(btnCheck);
		btnSolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						if(grid[i][j] instanceof JTextField) {
							puzzle.set(i, j, Integer.parseInt(((JTextField)grid[i][j]).getText()));
						}
					}
				}
				if(puzzle.isSolved())
					JOptionPane.showMessageDialog(null, "Congratulations");
				else
					JOptionPane.showMessageDialog(null, "Nope");
			}
		});
		btnSolve.setFont(new Font("Tahoma", Font.PLAIN, 22));
		topControl.add(btnSolve);
		sideControl.add(topControl, BorderLayout.NORTH);

		JPanel bottomControl = new JPanel();
		sideControl.add(bottomControl, BorderLayout.SOUTH);

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
		JPanel bottomSelection = new JPanel();
		bottomSelection.setLayout(new BorderLayout(0, 0));
		JPanel bottomSide = new JPanel();
		bottomSelection.add(bottomSide, BorderLayout.EAST);
		bottomSide.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 5));

		JLabel lblLevel = new JLabel(diff.toString());
		lblLevel.setFont(new Font("Tahoma", Font.PLAIN, 22));

		bottomSelection.add(lblLevel, BorderLayout.WEST);

		JRadioButton rdbtnSave = new JRadioButton("Save");
		buttonGroup.add(rdbtnSave);
		rdbtnSave.setFont(new Font("Tahoma", Font.PLAIN, 22));
		bottomSide.add(rdbtnSave);

		JRadioButton rdbtnReset = new JRadioButton("Reset");
		buttonGroup.add(rdbtnReset);
		rdbtnReset.setFont(new Font("Tahoma", Font.PLAIN, 22));
		bottomSide.add(rdbtnReset);

		JButton btnChange = new JButton("Go");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PuzzleCreator.toFile(puzzle, fileName);
			}
		});
		btnChange.setFont(new Font("Tahoma", Font.PLAIN, 22));
		bottomSide.add(btnChange);
		return bottomSelection;
	}

	/**
	 * Creates the title jlabel.
	 * 
	 * @return JLabel title
	 */
	private JLabel title() {
		JLabel lblTitle = new JLabel("Sudoku");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		return lblTitle;
	}

	/**
	 * Creates the text field grid for user input.
	 * 
	 * @return JPanel mainpanel
	 */
	private JPanel mainPanel() {
		JPanel mainPanel = new Board();
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
				if (puzzle.get(i, j) == 0) {
					JTextField current = new JTextField();
					current.setColumns(1);
					current.setHorizontalAlignment(SwingConstants.CENTER);
					grid[i][j] = current;
				} else if (puzzle.get(i, j) != 0) {
					JLabel fin = new JLabel(puzzle.get(i, j).toString());
					fin.setHorizontalAlignment(SwingConstants.CENTER);
					grid[i][j] = fin;
				}
				grid[i][j].setBounds(x, y, 48, 46);
				grid[i][j].setFont(new Font("Tahoma", Font.PLAIN, 22));
				mainPanel.add(grid[i][j]);

			}
			y += 46;
		}

		return mainPanel;
	}
}
