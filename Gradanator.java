package HomeworkAssignments;

// Nisha Lad, CSE 142, Winter 2016, Section BE -Michelle Cho
// Programming Homework Assignment #4, 01/28/16
// The program generates a student's overall grade report for a certain class

import java.util.*;

public class Gradanator {
	public static final int SECTION_CAP = 30;
	public static final int POINTS_IN_1_SECTION = 5;
	public static final int MAX_EXAM_SCORE = 100;
	
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		Intro();
		double wms = calculateScores(console, "Midterm:"); // weighted midterm
		double wfs = calculateScores(console, "Final:"); // weighted final
		double whs = calculateScores(console, "Homework:"); //weighted homework
		GPA(wms + wfs + whs);
	}
	
	// Method prints the introductory statements of the grade report
	public static void Intro() {
		System.out.println("This program reads exam/homework scores");
		System.out.println("and reports your overall course grade.");
		System.out.println();
	}
	
	// Prompts user for scores and returns the weighted score
	public static double calculateScores(Scanner console, String s) {
		System.out.println(s);
		System.out.print("Weight (0-100)? ");
		int weight = console.nextInt();
		double weightedScore = 0;
		if (s == "Homework:") {
			weightedScore = homeworkScores(console, weight);
		} else {
			weightedScore = examScores(console, weight);
		}
		return weightedScore;
	}
	
	// Continues to prompts user for exam scores and returns the weighted score
	public static double examScores(Scanner console, int weight) {
		System.out.print("Score earned? ");
		int score = console.nextInt();
		System.out.print("Were scores shifted (1=yes, 2=no)? ");
		int binary = console.nextInt();
		if (binary == 1) {
			System.out.print("Shift amount? ");
			int shift = console.nextInt();
			score += shift;
		}
		score = Math.min(score, MAX_EXAM_SCORE); // test scores cap
		printSummary(score, weight, " / " + MAX_EXAM_SCORE, MAX_EXAM_SCORE);
		return score * (double)weight / MAX_EXAM_SCORE;
	}
	
	// Continues to prompts user for homework scores, returns weighted homework score
	public static double homeworkScores(Scanner console, int weight) {
		System.out.print("Number of assignments? ");
		int count = console.nextInt();
		int sum1 = 0;
		int sum2 = 0;
		for (int i = 1; i <= count; i++) {
			System.out.print("Assignment " + i + " score and max? ");
			sum1 += (int) console.nextInt(); // total score
			sum2 += (int) console.nextInt(); // total max score
		}
		System.out.print("How many sections did you attend? ");
		int sectionPoints = POINTS_IN_1_SECTION * console.nextInt();
		sectionPoints = Math.min(sectionPoints, SECTION_CAP);
		System.out.println("Section points = " + sectionPoints + " / " + SECTION_CAP);
		int totalPoints = sum1 + sectionPoints;
		int totalMaxPoints = sum2 + SECTION_CAP;
		totalPoints = Math.min(totalPoints, totalMaxPoints); // Max points available cap
		printSummary(totalPoints, weight, " / " + totalMaxPoints, totalMaxPoints);
		return ((double)(totalPoints) * weight) / totalMaxPoints;
	}
	
	// Method prints out the summary grades and weights for the particular assignments
	public static void printSummary(int score, int weight, String s, double maxPoints) {
		System.out.println("Total points = " + score + s);
		double weightedScore = score * (double)weight / maxPoints;
		System.out.printf("Weighted score = %.1f", weightedScore);
		System.out.println(" / " + weight);
		System.out.println();
	}
	
	// Method calculates overall grade point average
	public static void GPA(double percentage) {
		System.out.printf("Overall percentage = %.1f \n", percentage);
		System.out.print("Your grade will be at least: ");
		if (percentage < 60.0) {
			System.out.println("0.0");
			System.out.println("Please choose another major :-( ");
		} else if (percentage <= 74.99) {
			System.out.println("0.7");
			System.out.println("Try harder next time!");
		} else if (percentage <= 84.99) {
			System.out.println("2.0");
			System.out.println("Pretty good overall grade! Well Done!!");
		} else {
			System.out.println("3.0");
			System.out.println("Awesome grade! Well Done!! :-) ");
		}
	}
}