package HomeworkAssignments;
/*Nisha Lad, CSE 142, Winter 2016, Section BE -Michelle Cho
* Programming Homework Assignment #1, 01/06/16
* This program prints out the first 7 verses of a classic Christmas song */

public class Song {
	public static void main(String[] args){
		//calling the methods defined later
		verse1();
		verse2();
		verse3();
		verse4();
		verse5();
		verse6();
		verse7();
	}
	
	//method prints verse 1 of the song
	public static void verse1() {
		System.out.println("On the 1st day of \"Xmas\", my true love gave to me");
		partridge();
	}
	
	//method prints last line of each verse separator blank line
	public static void partridge() {
		System.out.println("a partridge in a pear tree.");
		System.out.println();
	}
	
	//method prints verse 2
	public static void verse2() {
		System.out.println("On the 2nd day of \"Xmas\", my true love gave to me");
		turtle();
	}
	
	//method prints new line and recalls previous method
	public static void turtle() {
		System.out.println("two turtle doves, and");
		partridge();
	}
	
	//method prints verse 3
	public static void verse3() {
		System.out.println("On the 3rd day of \"Xmas\", my true love gave to me");
		hens();
	}
	
	//method prints new line and recalls previous method
	public static void hens() {
		System.out.println("three French hens,");
		turtle();
	}
	
	//method prints verse 4
	public static void verse4() {
		System.out.println("On the 4th day of \"Xmas\", my true love gave to me");
		birds();
	}
	
	//method prints new line and recalls previous method
	public static void birds() {
		System.out.println("four calling birds,");
		hens();
	}
	
	//method prints verse 5
	public static void verse5() {
		System.out.println("On the 5th day of \"Xmas\", my true love gave to me");
		rings();
	}
	
	//method prints new line and recalls previous method
	public static void rings() {
		System.out.println("five golden rings,");
		birds();
	}
	
	//method prints verse 6
	public static void verse6() {
		System.out.println("On the 6th day of \"Xmas\", my true love gave to me");
		geese();
	}
	
	//method prints new line and recalls previous method
	public static void geese() {
		System.out.println("six geese a-laying,");
		rings();
	}
	
	//method prints verse 7
	public static void verse7() {
		System.out.println("On the 7th day of \"Xmas\", my true love gave to me");
		swans();
	}
	
	//method prints new line and recalls previous method
	public static void swans() {
		System.out.println("seven swans are swimmin'");
		geese();
	}
}
