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
	
	public void set(int row, int col, int number) {
		playingPuzzle.get(row).set(col, number);
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
