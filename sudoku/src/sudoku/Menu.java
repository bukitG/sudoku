package sudoku;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
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
		setBounds(100, 100, 350, 284);
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
		rdbtnFromFile.setFont(new Font("Tahoma", Font.PLAIN, 18));
		level.add(rdbtnFromFile);
		panelLevel.add(rdbtnFromFile);
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
				}
					
				else if(rdbtnMedium.isSelected()) {
					diff = Difficulty.MEDIUM;
				}


				else if(rdbtnHard.isSelected()) {
					diff = Difficulty.HARD;
				}

				else if(rdbtnFromFile.isSelected()) {
					diff = Difficulty.EASY;
				}
				else {
					diff = Difficulty.EASY;
				}
				Game game = new Game(diff);
				game.run(diff);
				frame.setVisible(false);
			}
		});
		return btnStart;
	}

}
