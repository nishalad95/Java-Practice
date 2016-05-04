//Nisha Lad
//The program allows the user to play the Mad Libs game:
//Short stories that have placeholders to be filled in

import java.util.*;
import java.io.*;

public class MadLibs {
	public static void main(String[] args) throws FileNotFoundException{
		Scanner console = new Scanner(System.in);
		printWelcome();
		String response = "start";	
		while (!response.equals("q")) {
			System.out.print("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ");
			response = console.nextLine();
			response = response.toLowerCase();
			if (response.equals("c")) {
				String readInputFile = fileNameInput(console);
				createMadLib(console, readInputFile);
			} else if (response.equals("v")) {
				viewMadLib(console);
			}
		}	
	}
	
	//Prints the introductory statements about the Mad Libs game
	public static void printWelcome() {
		System.out.println("Welcome to the game of Mad Libs.");
		System.out.println("I will ask you to provide various words");
		System.out.println("and phrases to fill in a story.");
		System.out.println("The result will be written to an output file.");
		System.out.println();
	}
	
	//Creates the Mad-Lib by asking for user input
	public static String fileNameInput(Scanner console) throws FileNotFoundException{
		System.out.print("Input file name: ");
		String inputFileName = console.nextLine();
		File file = new File(inputFileName);
		while(!file.exists()) {
			System.out.print("File not found. Try again: ");
			inputFileName = console.nextLine();
			file = new File(inputFileName);
		}
		return inputFileName;
	}
	
	//Creates a new Mad Lib by asking for user input and outputs to a file
	public static void createMadLib(Scanner console, String inputFileName) throws FileNotFoundException{
		File file = new File(inputFileName);
		System.out.print("Output file name: ");
		String outputFileName = console.nextLine();
		System.out.println();
		PrintStream outputFile = new PrintStream(new File(outputFileName));
		//Make the new Mad Lib
		Scanner fileScan = new Scanner(file);
		while (fileScan.hasNextLine()) {
			String line = fileScan.nextLine();
			Scanner lineScan = new Scanner(line);
			while (lineScan.hasNext()) {
				String token = lineScan.next();
				if (token.startsWith("<") && token.endsWith(">")) {
					String word = token.substring(1, token.length() - 1);
					word = word.replace("-", " ");
					String letter1 = word.substring(0, 1);
					if (letter1.equalsIgnoreCase("a") || letter1.equalsIgnoreCase("e") || 
							letter1.equalsIgnoreCase("i") || letter1.equalsIgnoreCase("o") ||
								letter1.equalsIgnoreCase("u")) {
						System.out.print("Please type an " + word + ": ");
					} else {
						System.out.print("Please type a " + word + ": ");
					}
					String replacement = console.nextLine();
					outputFile.print(replacement + " ");
				} else {
					outputFile.print(token + " ");
				}
			}
			outputFile.println();
		}
		System.out.println("Your mad-lib has been created!");
		System.out.println();
	}
	
	//Prints a file to the console, if the file exists
	public static void viewMadLib(Scanner console) throws FileNotFoundException{
		String inputFileName = fileNameInput(console);
		System.out.println();
		Scanner fileScan = new Scanner(new File(inputFileName));
		while (fileScan.hasNextLine()) {
			System.out.println(fileScan.nextLine());
		}
		System.out.println();
	}
}
