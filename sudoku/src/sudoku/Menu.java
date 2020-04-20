package sudoku;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
	public static Menu frame;
	private JPanel contentPane;
	private final ButtonGroup level = new ButtonGroup();
	private JRadioButton rdbtnEasy;
	private JRadioButton rdbtnHard;
	private JRadioButton rdbtnMedium;
	private JTextField JTextFromFile;
	private Puzzle puzzle;
	private JLabel lblFromFile;
	private JRadioButton rdbtnFromFile;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/resources/soduku.png")));
		setTitle("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblTitle = new JLabel("Sudoku");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tw Cen MT", Font.ITALIC, 36));
		contentPane.add(lblTitle, BorderLayout.NORTH);
		
		JButton btnNewGame = btnNewGame();
		contentPane.add(btnNewGame, BorderLayout.SOUTH);
		
		JPanel panelLevel = panelLevel();
		contentPane.add(panelLevel, BorderLayout.CENTER);
	}

	/**
	 * Creates the panel to house the JRadioButtons for the level selection.
	 */
	private JPanel panelLevel() {
		JPanel panelLevel = new JPanel();
		panelLevel.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 5));
		
		rdbtnHard = new JRadioButton("Hard");
		rdbtnHard.setFont(new Font("Tahoma", Font.PLAIN, 18));
		level.add(rdbtnHard);
		rdbtnHard.setMargin(new Insets(4, 4, 4, 34));
		panelLevel.add(rdbtnHard);
		
		rdbtnMedium = new JRadioButton("Medium");
		rdbtnMedium.setMargin(new Insets(4, 4, 4, 10));
		rdbtnMedium.setFont(new Font("Tahoma", Font.PLAIN, 18));
		level.add(rdbtnMedium);
		panelLevel.add(rdbtnMedium);
		
		rdbtnEasy = new JRadioButton("Easy");
		rdbtnEasy.setFont(new Font("Tahoma", Font.PLAIN, 18));
		level.add(rdbtnEasy);
		rdbtnEasy.setMargin(new Insets(4, 4, 4, 36));
		panelLevel.add(rdbtnEasy);
		
		rdbtnFromFile = new JRadioButton("From File");
		level.add(rdbtnFromFile);
		rdbtnFromFile.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelLevel.add(rdbtnFromFile);
		
		lblFromFile = new JLabel("From File:");
		lblFromFile.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelLevel.add(lblFromFile);
		
		JTextFromFile = new JTextField();
		JTextFromFile.setColumns(20);
		JTextFromFile.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelLevel.add(JTextFromFile);
		return panelLevel;
	}

	/**
	 * Creates the JButton for new game.
	 */
	private JButton btnNewGame() {
		JButton btnStart = new JButton("PLAY NOW!!!");
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Difficulty diff;
				if(rdbtnEasy.isSelected()) {
					diff = Difficulty.EASY;
					puzzle = PuzzleCreator.generate(diff);
				}
					
				else if(rdbtnMedium.isSelected()) {
					diff = Difficulty.MEDIUM;
					puzzle = PuzzleCreator.generate(diff);
				}


				else if(rdbtnHard.isSelected()) {
					diff = Difficulty.HARD;
					puzzle = PuzzleCreator.generate(diff);
				}

				else if(rdbtnFromFile.isSelected()) {
					puzzle = PuzzleCreator.fromFile(JTextFromFile.getText());
					diff = puzzle.getDifficulty();
				}
				else {
					diff = Difficulty.EASY;
					puzzle = PuzzleCreator.generate(diff);
				}
				Game game = new Game(diff, puzzle);
				game.run(diff, puzzle);
				frame.setVisible(false);
			}
		});
		return btnStart;
	}
	

}
