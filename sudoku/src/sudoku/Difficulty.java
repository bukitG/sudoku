package sudoku;

public enum Difficulty {
	EASY(3),
	MEDIUM(2),
	HARD(1);
	
	private int difficulty;
	
	Difficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	
	public int getDifficulty() {
		return difficulty;
	}
}
