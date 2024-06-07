package problems;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Hangman {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		if (args.length != 1) {
			System.out.println("Usage: <File name>");
			System.exit(0);
		}
		//creates a scanner object for the user
		Scanner userInput = new Scanner(System.in);
		
		String letter;
		
		ArrayList<String> wordPool = new ArrayList<>();
		
		ArrayList<Integer> indices = new ArrayList<>();
		
		File file = new File(args[0]);
		
		if(!file.exists()) {
			System.out.println("Error: File Does Not Exist");
			System.exit(1);
		}
		
		try (Scanner fileInput = new Scanner(file)){
			while (fileInput.hasNext()) {
				String line = fileInput.nextLine();
				wordCollect(line, wordPool);
			}
			System.out.println(wordPool.toString());
		}
		
		catch (FileNotFoundException ex) {
			
		}
		String hangmanWord = wordPool.get((int)(Math.random()*wordPool.size()));
		StringBuilder hiddenWord = hiddenWord(hangmanWord);
		System.out.println("The word to guess is " + hiddenWord);
		boolean gameStatus = true;
		int triesRemaining = 5;
		while (gameStatus && gameWon(hiddenWord) == false) {
			System.out.println("Guess a letter");
			letter = userInput.next();
			letterCheck(letter);
			if (wordCheck(letter, hangmanWord, indices)) {
			letterReplace(indices, letter, hiddenWord);
			System.out.println("The word to guess is " + hiddenWord);
			System.out.println("Tries remaining: " + triesRemaining );
			}
			else if (!wordCheck(letter, hangmanWord, indices) && triesRemaining != 0) {
				triesRemaining--;
				System.out.println("Incorrect. Tries remaining: " + triesRemaining );
				System.out.println("The word to guess is " + hiddenWord);
			}
			if (triesRemaining == 0) {
				System.out.println("Game over! The word was " + hangmanWord);
				gameStatus = false;
			}
			
			if (gameWon(hiddenWord) == true) {
				System.out.println("Congratulations! The word is " + hiddenWord);
			}
		}
		
	}
	// collects words from a file and transfers them into an array list
	public static void wordCollect(String s, ArrayList<String> wordPool) {
		s = s.trim();
		StringBuilder words = new StringBuilder();
		
		String wordHolder;
		
	
		for (int i = 0; i<s.length(); i++) {
			if (s.charAt(i) != ' ' && s.charAt(i) != '\n') {
				words.append(s.charAt(i));
			}
			else  {
				wordHolder = words.toString();
				wordPool.add(wordHolder);
				words.setLength(0);
			}
			
			
		}
		 	wordHolder = words.toString();
			wordPool.add(wordHolder);
	}
	public static StringBuilder hiddenWord(String hangmanWord) {
		StringBuilder hiddenString = new StringBuilder();
		for (int i = 0; i<hangmanWord.length(); i++) {
			hiddenString.append('*');
		}
		return hiddenString;
	} 
	
	public static void letterCheck(String letter) {
		Scanner userInput = new Scanner(System.in);
		boolean isValid = false;
		
		while (!isValid) {
			if (letter.length() > 1 ) {
				System.out.println("You must only choose one letter");
				letter = userInput.next();
			}
			else {
				isValid = true;
				
			}
		}
		
	}
	public static boolean wordCheck(String letter, String word, ArrayList<Integer> indices) {
			boolean containsLetter = true;
		if (word.contains(letter)) {
			int i = 0;
			while( i < word.length()) {
				if (word.charAt(i) == letter.charAt(0)) {
					indices.add(i);
				}
				i++;
			}
		}
		else {
			containsLetter = false;
		}
		return containsLetter;
	}
	public static void letterReplace(ArrayList<Integer> indices, String letter, StringBuilder hiddenWord) {
		for (int i = 0; i<indices.size(); i++) {
			hiddenWord.setCharAt(indices.get(i), letter.charAt(0));
		}
		indices.clear();
	}
	
	
	public static boolean gameWon(StringBuilder hiddenWord) {
		boolean status = true;
		for (int i = 0; i<hiddenWord.length(); i++) {
			if (hiddenWord.charAt(i) == '*') {
				status = false;
			}
		}
		return status;
	}
	

}
