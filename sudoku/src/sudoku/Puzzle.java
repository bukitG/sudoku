package sudoku;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains the necessary information to play a sudoku game. This object 
 * requires the original puzzle, solved puzzle, and difficulty to be created.
 * 
 * @author Zac and Alex
 *
 */
public class Puzzle implements Serializable{
	/**
	 * Serial version 8801662294765010210L.
	 */
	private static final long serialVersionUID = 8801662294765010210L;
	private final List<ArrayList<Integer>> originalPuzzle;
	private final List<ArrayList<Integer>> solvedPuzzle;
	private List<ArrayList<Integer>> playingPuzzle;
	private Difficulty difficulty;
	
	/**
	 * Constructs a puzzle.
	 * 
	 * @param puzzle the original puzzle
	 * @param solvedPuzzle the solved puzzle
	 * @param difficulty the difficulty of the puzzle
	 */
	public Puzzle(ArrayList<ArrayList<Integer>> puzzle, ArrayList<ArrayList<Integer>> solvedPuzzle, Difficulty difficulty) {
		originalPuzzle = new ArrayList<ArrayList<Integer>>();
		playingPuzzle = new ArrayList<ArrayList<Integer>>();
		this.solvedPuzzle = new ArrayList<ArrayList<Integer>>();
		for (int i=0; i<9; i++) {
        	originalPuzzle.add(new ArrayList<Integer>());
        	playingPuzzle.add(new ArrayList<Integer>());
        	this.solvedPuzzle.add(new ArrayList<Integer>());
        	for (int j=0; j<9; j++) {
        		originalPuzzle.get(i).add(puzzle.get(i).get(j));
        		playingPuzzle.get(i).add(puzzle.get(i).get(j));
        		this.solvedPuzzle.get(i).add(solvedPuzzle.get(i).get(j));
        	}
        }
		this.difficulty = difficulty;
	}
	
	/**
	 * Constructs a puzzle object. This constructor allows the user to retrieve a partially completed puzzle by taking in the
	 * playing puzzle as an argument.
	 * 
	 * @param puzzle the original puzzle
	 * @param playingPuzzle the partially finished puzzle
	 * @param solvedPuzzle the solved puzzle
	 * @param difficulty the difficulty of the puzzle
	 */
	public Puzzle(ArrayList<ArrayList<Integer>> puzzle, ArrayList<ArrayList<Integer>> playingPuzzle, ArrayList<ArrayList<Integer>> solvedPuzzle, Difficulty difficulty) {
		this(puzzle, solvedPuzzle, difficulty);
		this.playingPuzzle = new ArrayList<ArrayList<Integer>>();
		for (int i=0; i<9; i++) {
        	this.playingPuzzle.add(new ArrayList<Integer>());
        	for (int j=0; j<9; j++) {
        		this.playingPuzzle.get(i).add(playingPuzzle.get(i).get(j));
        	}
        }
		this.difficulty = difficulty;
	}
	
	/**
	 * Determines if the puzzle has been solved.
	 * 
	 * @return true if the puzzle has been solved.
	 */
	public boolean isSolved() {
		ArrayList<ArrayList<Integer>> cols = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> box = new ArrayList<ArrayList<Integer>>();
		for (int i=0; i<9; i++) {
			cols.add(new ArrayList<Integer>());
			box.add(new ArrayList<Integer>());
			for (int j=0; j<9; j++) {
				cols.get(i).add(playingPuzzle.get(j).get(i));
			}
		}
		
		int numCol = 0;
		for (int row=0; row<9; row=row+3) {
			for (int col=0; col<9; col=col+3) {
				for (int i=row; i<row+3; i++) {
					for (int j=col; j<col+3; j++) {
						box.get(numCol).add(playingPuzzle.get(i).get(j));
					}
				}
				numCol++;
			}
		}
		
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				if (!playingPuzzle.get(j).contains(i+1) || !cols.get(j).contains(i+1) || !box.get(j).contains(i+1)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public Difficulty getDifficulty() {
		return difficulty;
	}
	
	/**
	 * Returns the value of the playing puzzle at a given location.
	 * 
	 * @param row
	 * @param col
	 * @return the value held at a given row and column location
	 */
	public Integer get(int row, int col) {
		return playingPuzzle.get(row).get(col);
	}
	
	/**
	 * Returns the value of the original puzzle at a position.
	 * 
	 * @param row
	 * @param col
	 * @return original puzzle value at specified position
	 */
	public Integer getOriginal(int row, int col) {
		return originalPuzzle.get(row).get(col);
	}
	
	/**
	 * Set the value of the playing puzzle at a given location.
	 * 
	 * @param row
	 * @param col
	 * @param number the number to be set
	 */
	public void set(int row, int col, int number) {
		playingPuzzle.get(row).set(col, number);
	}
	
	/**
	 * Checks whether or not the the guesses are right or wrong.
	 * 
	 * @param row
	 * @param colunm
	 * @return boolean
	 */
	public boolean check(int row, int col) {
		if(playingPuzzle.get(row).get(col) == solvedPuzzle.get(row).get(col)) 
			return true;
		else
			return false;
	}
	
	/**
	 * Checks whether or not the number is part of the original or not.
	 * 
	 * @param row
	 * @param colunm
	 * @return boolean
	 */
	public boolean checkOriginal(int row, int col) {
		if(playingPuzzle.get(row).get(col) == originalPuzzle.get(row).get(col)) 
			return true;
		else
			return false;
	}
}
