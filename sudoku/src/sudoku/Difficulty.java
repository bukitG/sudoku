package sudoku;

/**
 * Determines the difficulty of a sudoku puzzle.
 * 
 * @author Zac
 */
public enum Difficulty {
	EASY(1),
	MEDIUM(2),
	HARD(3);
	
	private int difficulty;
	
	/**
	 * Allows the difficulty enum to havean integer value.
	 * 
	 * @param difficulty the integer value associated with a difficulty
	 */
	Difficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	
	public int getDifficulty() {
		return difficulty;
	}
}
