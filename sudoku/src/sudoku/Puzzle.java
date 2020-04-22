package sudoku;

import java.util.ArrayList;
import java.util.List;

public class Puzzle {
	private final List<ArrayList<Integer>> originalPuzzle;
	private final List<ArrayList<Integer>> solvedPuzzle;
	private List<ArrayList<Integer>> playingPuzzle;
	private Difficulty difficulty;
	
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
	
	public boolean isSolved() {
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				if (playingPuzzle.get(i).get(j) != solvedPuzzle.get(i).get(j)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public Difficulty getDifficulty() {
		return difficulty;
	}
	
	public Integer get(int row, int col) {
		return playingPuzzle.get(row).get(col);
	}
	
	/**
	 * Returns the value at a position.
	 * 
	 * @param row
	 * @param col
	 * @return original puzzle value at specified position
	 */
	public Integer getOriginal(int row, int col) {
		return originalPuzzle.get(row).get(col);
	}
	
	public void set(int row, int col, int number) {
		playingPuzzle.get(row).set(col, number);
	}
	
	/**
	 * Checks whether or not the the guesses are right or wrong.
	 * 
	 * @param row
	 * @param colunm
	 * @return bolean
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
	 * @return bolean
	 */
	public boolean checkOriginal(int row, int col) {
		if(playingPuzzle.get(row).get(col) == originalPuzzle.get(row).get(col)) 
			return true;
		else
			return false;
	}
	
//	public void display() {
//		display(playingPuzzle);
//	}
//	
//	public void displaySolved() {
//		display(solvedPuzzle);
//	}
//	
//	private void display(List<ArrayList<Integer>> puzzle) {
//		for (int i=0; i<9; i++) {
//			for (int j=0; j<9; j++) {
//				System.out.print(puzzle.get(i).get(j) + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}

}
