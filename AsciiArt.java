/*Nisha Lad, CSE 142, Winter 2016, Section BE -Michelle Cho
*Programming Homework Assignment #2, 01/13/16
*This program produces AsciiArt of the Empire State Building
*/

public class AsciiArt {
	public static void main(String[] args) {
		//calling the methods defined below
		antenna();
		firstTower();
		secondTower();
		bodyTower();
		body();
		bodyTower();
	}
	
	//This method prints out the top antenna of the Empire State
	public static void antenna() {      
		for (int line = 1; line <= 3; line++) {
		      for (int spaces = 1; spaces <= 13; spaces++) {
		    	  System.out.print(" ");
		      }
		      System.out.println("|");	          
		}
		for (int spaces = 1; spaces <= 12; spaces++) {
			System.out.print(" ");
		}
		System.out.println("/#\\"); //NB: escape sequence
	}
	
	//This method prints out a tower height 2, width 3
	public static void firstTower() {
		for (int height = 1; height <= 2; height++) {
			for (int spaces = 1; spaces <= 11; spaces++) {
				System.out.print(" ");
			}
			System.out.print("|");
			for (int width= 1; width <= 3; width++) {
				System.out.print("#");
			}
			System.out.println("|");
		}
	}
	
	//This method prints out a tower height 4, width 7
	public static void secondTower() {
		for (int height = 1; height <= 4; height++) {
			for (int spaces = 1; spaces <= 9; spaces++) {
				System.out.print(" ");
			}
			System.out.print("|");
			for (int width= 1; width <= 7; width++) {
				System.out.print("#");
			}
			System.out.println("|");
		}
	}
	
	//This method prints out a tower height 6, width 11
	public static void bodyTower() {
		for (int height = 1; height <= 6; height++) {
			for (int spaces = 1; spaces <= 7; spaces++) {
				System.out.print(" ");
			}
			System.out.print("|");
			for (int width= 1; width <=11; width++) {
				System.out.print("#");
			}
			System.out.println("|");
		}
	}
	
	//This method prints out the unique statement in my Ascii Art
	public static void body () {
		System.out.println("       |##EMPIRE###|");
		System.out.println("       |###STATE###|");
		System.out.println("       |####NYC####|");
	}
}
