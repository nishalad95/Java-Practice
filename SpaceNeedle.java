package HomeworkAssignments;
/*Nisha Lad, CSE 142, Winter 2016, Section BE -Michelle Cho
*Programming Homework Assignment #2, 01/13/16
*This program produces an Ascii Art version of the Space Needle in Seattle
*Exploring the use of for loops and nested for loops.
*/

public class SpaceNeedle {
	//declare class constants - globally defined
	public static final int SIZE = 4;

	//main method - recipe to execute Ascii Art
	public static void main(String[] args) {
		//calling the methods defined below
		pipe();
		topHalf();
		bottomHalf();
		pipe();
		tower();
		topHalf();
	}
	
	//Method prints the pipe features of Space Needle
	public static void pipe() {
		//for loop iterates over the repeated part
		for (int height= 1; height <= SIZE; height++) {
			for (int spaces= 1; spaces <=3*SIZE; spaces++) {
				System.out.print(" ");
			}
			System.out.println("||");
		}
	}
	
	//Method prints main tower of Space Needle
	public static void tower() {
		//for loop iterates over the repeated part
		for (int height= 1; height<= SIZE * SIZE; height++) {
			for (int spaces= 1; spaces <=(2*SIZE) + 1; spaces++) {
				System.out.print(" ");
			}
			System.out.print("|");
			for (int per= 1; per<= SIZE - 2; per++) {
				System.out.print("%");
			}
			System.out.print("||");
			for (int per= 1; per<= SIZE - 2; per++) {
				System.out.print("%");
			}
			System.out.println("|");
		}
	}
	
	//Method prints top half of the viewing deck
	public static void topHalf() {
		//for loop iterates over the repeated part
		for (int height= 1; height<= SIZE; height++) {
			for (int spaces= 1; spaces<= (3*SIZE) - (3*height); spaces++) {
				System.out.print(" ");
			}
			System.out.print("__/");
			for (int colon= 1; colon<= 3*height - 3; colon++) {
				System.out.print(":");
			}
			System.out.print("||");
			for (int colon= 1; colon<= 3*height - 3; colon++) {
				System.out.print(":");
			}
			System.out.println("\\__");
		}
		System.out.print("|");
		for (int i= 1; i<= 6*SIZE; i++) {
			System.out.print("\""); //NB: escape sequence
		}
		System.out.println("|");
	}
	
	//Method prints bottom half of the viewing deck
	public static void bottomHalf() {
		//for loop iterates over the repeated part
		for (int height= 1; height<= SIZE; height++) {
			for (int spaces=1; spaces<= 2*height - 2; spaces++) {
				System.out.print(" ");
			}
			System.out.print("\\"); //NB: escape sequence
			System.out.print("_");
			for (int zig=1; zig<= (3*SIZE +1) - (2*height); zig++) {
				System.out.print("/\\"); //NB: escape sequence
			}
			System.out.println("_/");
		}
	}	
}
