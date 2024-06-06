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

}
