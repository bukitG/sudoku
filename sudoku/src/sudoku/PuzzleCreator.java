package sudoku;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;



/**
 * Provides necessary functionality for puzzle objects. This class provides the ability to solve, create, and save puzzles.
 * 
 * @author Zac and Alex
 *
 */
public class PuzzleCreator {
	/**
	 * Writes the puzzle to a file.
	 * 
	 * @param puzzle the puzzle to be written
	 * @param fileName the name of the file
	 */
	public static void toFile(Puzzle puzzle, String fileName) {
		if (!fileName.contains(".")) {
			fileName = fileName + ".pzl";
		}
		try (BufferedWriter fileOut = Files.newBufferedWriter(
				Paths.get(fileName),
				Charset.defaultCharset(),
				StandardOpenOption.CREATE,
				StandardOpenOption.valueOf("TRUNCATE_EXISTING")))
		{
			fileOut.write(puzzle.getDifficulty() + "\n\n");
			for (int i=0; i<9; i++) {
				for (int j=0; j<9; j++) {
					fileOut.write(puzzle.getOriginal(i, j) + " ");
				}
				fileOut.write("\n");
			}
			
			for (int i=0; i<9; i++) {
				for (int j=0; j<9; j++) {
					fileOut.write(puzzle.get(i, j) + " ");
				}
				fileOut.write("\n");
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "The puzzle could not be exported to a file.");
		}
	}
	
	/**
	 * Creates a puzzle object based on the contents of a file.
	 * 
	 * @param fileName the name of the file
	 * @return the puzzle object contained in the file
	 */
	public static Puzzle fromFile(String fileName) {
		if (!fileName.contains(".")) {
			fileName = fileName + ".pzl";
		}
		try (BufferedReader fileIn = Files.newBufferedReader(
				Paths.get(fileName),
				Charset.defaultCharset()))
		{
			Puzzle p;
			ArrayList<ArrayList<Integer>> puzzle = new ArrayList<ArrayList<Integer>>();
			ArrayList<ArrayList<Integer>> playingPuzzle = new ArrayList<ArrayList<Integer>>();
			Difficulty difficulty;
			String d = fileIn.readLine();
			fileIn.readLine();
			if (d.equals("EASY")) {
				difficulty = Difficulty.EASY;
			}
			else if (d.equals("MEDIUM")) {
				difficulty = Difficulty.MEDIUM;
			}
			else {
				difficulty = Difficulty.HARD;
			}
			for (int i=0; i<9; i++) {
				puzzle.add(new ArrayList<Integer>());
				String tempLine = fileIn.readLine();
				for (int j=0; j<9; j++) {
					puzzle.get(i).add(Integer.parseInt(tempLine.substring(0,1)));
					tempLine = tempLine.substring(2);
				}
			}
			for (int i=0; i<9; i++) {
				playingPuzzle.add(new ArrayList<Integer>());
				String tempLine = fileIn.readLine();
				for (int j=0; j<9; j++) {
					playingPuzzle.get(i).add(Integer.parseInt(tempLine.substring(0,1)));
					tempLine = tempLine.substring(2);
				}
			}
			p = new Puzzle(puzzle, playingPuzzle, solve(puzzle), difficulty);
			return p;
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "An error occurred. Starting game with an easy puzzle");
		}
		return generate(Difficulty.EASY);
	}
	
	/**
	 * Generates a puzzle based on difficulty.
	 * 
	 * @param difficulty the difficulty of the desired puzzle
	 * @return the generated puzzle
	 */
	public static Puzzle generate(Difficulty difficulty) {
		ArrayList<ArrayList<Integer>> solvedPuzzle = generateSolvedPuzzle();
		return new Puzzle(generateArrayListPuzzle(solvedPuzzle, difficulty), solvedPuzzle, difficulty);
	}
	
	/**
	 * Solves a given puzzle.
	 * 
	 * @param originalPuzzle the original puzzle
	 * @return the solution
	 */
	public static ArrayList<ArrayList<Integer>> solve(ArrayList<ArrayList<Integer>> originalPuzzle) {
		int iStart = 0;
		int jStart = 0;
		int count;
		boolean advance;
		boolean found = false;
		ArrayList<ArrayList<Integer>> playingPuzzle = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < 9; i++) {
			playingPuzzle.add(new ArrayList<Integer>());
            for (int j = 0; j < 9; j++) {
            	playingPuzzle.get(i).add(originalPuzzle.get(i).get(j));
                if(originalPuzzle.get(i).get(j) == 0 && !found){
                    iStart = i;
                    jStart = j;
                    found = true;
                }
            }
        }
		
		for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                
                advance = false;
                
                count = playingPuzzle.get(i).get(j) + 1;
                
                if (originalPuzzle.get(i).get(j) != 0) {
                    advance = true;
                }
                else {
                    while (!advance) {
                        if (isValid(playingPuzzle, i, j, count) && count < 10) {
                            playingPuzzle.get(i).set(j, count);
                            advance = true;
                        }
                        else if(i == iStart && j == jStart && count > 9){
                            return null;
                        }
                        else {
                            if (count < 9) {
                                count++;
                            }
                            else {
                            	playingPuzzle.get(i).set(j, 0);
                                
                                do {
                                    if (j == 0 && i != 0) {
                                        j = 8;
                                        i--;
                                    }
                                    else {
                                        j--;
                                    }
                                    if(i < 0 || j < 0){
                                        return null;
                                    }
                                    count = playingPuzzle.get(i).get(j) + 1;
                                } while(originalPuzzle.get(i).get(j) != 0);
                                
                            }
                        }
                    }
                }
            }
        }
        return playingPuzzle;
	}
	
	private static boolean isValidRow(ArrayList<ArrayList<Integer>> playingPuzzle, int row, int number) {
		boolean valid = true;
		for (Integer i : playingPuzzle.get(row)) {
			if (i == number) {
				valid = false;
			}
		}
		return valid;
	}
	
	private static boolean isValidCol(ArrayList<ArrayList<Integer>> playingPuzzle, int col, int number) {
		boolean valid = true;
		for (int i=0; i<playingPuzzle.get(0).size(); i++) {
			if (playingPuzzle.get(i).get(col) == number) {
				valid = false;
			}
		}
		return valid;
	}
	
	private static boolean isValidBox(ArrayList<ArrayList<Integer>> playingPuzzle, int row, int col, int number) {
		boolean valid = true;
		int beginRow = (row / 3) * 3;
		int beginCol = (col / 3) * 3;
		for (int i=beginRow; i<beginRow+3; i++) {
			for (int j=beginCol; j<beginCol+3; j++) {
				if (playingPuzzle.get(i).get(j) == number) {
					valid = false;
				}
			}
		}
		return valid;
	}
	
	private static boolean isValid(ArrayList<ArrayList<Integer>> playingPuzzle, int row, int col, int number) {
		return isValidRow(playingPuzzle, row, number) && isValidCol(playingPuzzle, col, number) && isValidBox(playingPuzzle, row, col, number);
	}
	
	private static ArrayList<ArrayList<Integer>> generateSolvedPuzzle() {
		ArrayList<Integer> firstNine = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
		ArrayList<ArrayList<Integer>> puzzle = new ArrayList<ArrayList<Integer>>();
		puzzle.add(new ArrayList<Integer>());
		for (int i=0; i<9; i++) {
			int random = (int)(Math.random()*firstNine.size());
			puzzle.get(0).add(firstNine.get(random));
			firstNine.remove(random);
			if (i < 8) {
				puzzle.add(new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0)));
			}
		}
		
		return solve(puzzle);
	}
	
	private static ArrayList<ArrayList<Integer>> generateArrayListPuzzle(ArrayList<ArrayList<Integer>> solvedPuzzle, Difficulty difficulty) {
		int puzMin = 17;
        int step = 12;
        int numDelete = puzMin + step*difficulty.getDifficulty();
        int row;
        int column;
		ArrayList<ArrayList<Integer>> puzzle = new ArrayList<ArrayList<Integer>>();
		for (int i=0; i<9; i++) {
			puzzle.add(new ArrayList<Integer>());
			for (int j=0; j<9; j++) {
				puzzle.get(i).add(solvedPuzzle.get(i).get(j));
			}
		}
		
		for (int i=0; i<(numDelete/2); i++) {
			do {
                row=(int) (Math.random()*9);
                column=(int) (Math.random()*9);
            } while (puzzle.get(row).get(column) == 0 || puzzle.get(8-row).get(8-column) == 0);
            puzzle.get(row).set(column, 0);
            puzzle.get(8-row).set(8-column, 0);
		}
		
		return puzzle;
	}

}
