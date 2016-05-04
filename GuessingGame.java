// Nisha Lad
// The program allows the user to play a game where the program thinks of 
// a random number and accepts guesses from the user until they guess correctly

import java.util.*;

public class GuessingGame {
	public static final int MAX_VALUE = 100;
	
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		Random rand = new Random();		
		haikuIntro();
		int numOfTries = 0;
		char firstLetter = 'y';
		int numOfGames = 0;
		int minNumTries = 1000000;
		// This loop will play the game again or not depending on user input
		while (firstLetter == 'y' || firstLetter == 'Y') {
			int nextSetTries = startGame(console, rand);
			minNumTries = Math.min(minNumTries, nextSetTries);
			numOfTries += nextSetTries;
			numOfGames++;
			firstLetter = playAgain(console);
		}
		printResults(numOfTries, numOfGames, minNumTries);
	}
	
	// Methods prints out the introductory haiku
	public static void haikuIntro() {
		System.out.println("My cat is called:");
		System.out.println("Schrodinger, or is it?");
		System.out.println("We will never know.");
		System.out.println();
	}
	
	// Asks the user if they want to play the game again, method parameters takes in Scanner console user input
	public static char playAgain(Scanner console){
		System.out.print("Do you want to play again? ");
		String response = console.next();
		System.out.println();
		return response.charAt(0);
	}
	
	// Prompts the user to guess a number the program has 'thought' of and compares it
	// Method parameters: scanner console and random number object
	public static int startGame(Scanner console, Random rand) {
		int myRandomNum = rand.nextInt(MAX_VALUE) + 1;
		System.out.println("I'm thinking of a number between 1 and " + MAX_VALUE + "...");
		int guess = 0;
		int tries = 0;
		while (guess != myRandomNum) {
			System.out.print("Your guess? ");
			guess = console.nextInt();
			if (guess > myRandomNum) {
				System.out.println("It's lower.");
			} else if (guess < myRandomNum) {
				System.out.println("It's higher.");
			}
			tries++;
		}
		System.out.print("You got it right in ");
		if (tries > 1) {
			System.out.println(tries + " guesses!");
		} else {
			System.out.println("1 guess!");
		}
		return tries;
	}
	
	// Method prints out overall game statistics
	// Parameters: Number of tries, number of games and minimum number of tries
	public static void printResults(int numOfTries, int numOfGames, int minNumTries) {
		System.out.println("Overall results:");
		System.out.println("Total games   = " + numOfGames);
		System.out.println("Total guesses = " + numOfTries);
		System.out.printf("Guesses/game  = %.1f \n", (double)numOfTries / numOfGames);
		System.out.println("Best game     = " + minNumTries);
	}	
}
