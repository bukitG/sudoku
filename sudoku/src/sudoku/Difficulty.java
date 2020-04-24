package sudoku;

public enum Difficulty {
	EASY(1),
	MEDIUM(2),
	HARD(3);
	
	private int difficulty;
	
	Difficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	
	public int getDifficulty() {
		return difficulty;
	}
}
