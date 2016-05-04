package HomeworkAssignments;
// Nisha Lad, CSE 142, Winter 2016, Section BE -Michelle Cho
// Programming Homework Assignment #7, 26/02/16
// The program allows a user to process data from a Genome file and output information into a file

import java.util.*;
import java.io.*;

public class DNA {
	public static final int MIN_CODON_NUM = 5;
	public static final double CG_PERCENT =  30.0;
	public static final int NUC_PER_CODON = 3;
	public static final int UNIQ_NUM_NUC = 4;
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner console = new Scanner(System.in);
		System.out.println("This program reports information about DNA");
		System.out.println("nucleotide sequences that may encode proteins.");
		System.out.print("Input file name? ");
		Scanner fileScan = new Scanner(new File(console.nextLine()));
		System.out.print("Output file name? ");
		PrintStream outFile = new PrintStream(new File(console.nextLine()));
		while (fileScan.hasNextLine()) {
			String protName = fileScan.nextLine();
			String nucleoSeq = fileScan.nextLine().toUpperCase();
			int[] countArray = countNucleotides(nucleoSeq);
			int[] numOfNucleo = Arrays.copyOf(countArray, 4);
			double[] percent = calcMassPercentage(countArray);
			double [] massPert = Arrays.copyOf(percent, 4);
			String[] codonsList = codons(nucleoSeq);
			printDNAInfo(protName, nucleoSeq, numOfNucleo, massPert, percent, codonsList, outFile);
			proteinCheck(codonsList, percent[1], percent[2], outFile);
		}
	}
	
	// Method prints out the data associated with each DNA sequence
	// Parameters taken: Protein Name, Nucleotide Sequence, count & mass percentage of each 
	// unique Nucleotide, array of codons and PrintStream 
	public static void printDNAInfo(String protName, String nucleoSeq, int[] numOfNucleo, 
			double[] massPert, double[] percent, String[] codonsList, 
			PrintStream outFile) throws FileNotFoundException {
		outFile.println("Region Name: " + protName);
		outFile.println("Nucleotides: " + nucleoSeq);
		outFile.println("Nuc. Counts: " + Arrays.toString(numOfNucleo));
		outFile.println("Total Mass%: " + Arrays.toString(massPert) + " of " + percent[4]);
		outFile.println("Codons List: " + Arrays.toString(codonsList));
	}
	
	// Method returns an array of counts of A's, C's, G's and T's in each nucleotide sequence
	// Parameters taken: Sequence of Nucleotides
	public static int[] countNucleotides(String nucleoSeq) {
		int[] counts = new int[UNIQ_NUM_NUC + 1];
		for (int i = 0; i < nucleoSeq.length(); i++) {
			int index = "ACGT-".indexOf(nucleoSeq.charAt(i));
			if (index >= 0) {
				counts[index]++;
			}
		}
		return counts;
	}
	
	// Returns an array of calculated percentages of each nucleotide in the protein
	// Parameters taken: Array of unique Nucleotide count
	public static double[] calcMassPercentage(int[] array) {
		double[] masses = {135.128, 111.103, 151.128, 125.107, 100.000};
		double[] temporary = new double[UNIQ_NUM_NUC + 1];
		double sum = 0;
		for (int i = 0; i < UNIQ_NUM_NUC + 1; i++) {
			temporary[i] = masses[i] * array[i] * 100;
			sum += masses[i] * array[i];
		}
		for (int i = 0; i < UNIQ_NUM_NUC + 1; i++) {
			temporary[i] = Math.round(temporary[i] * 10.0 / sum) / 10.0;
		}
		double[] massPert = Arrays.copyOf(temporary, UNIQ_NUM_NUC + 1);
		massPert[4] = Math.round(sum * 10.0) / 10.0;
		return massPert;
	}
	
	// Creates an array of codons from the sequence of nucleotides
	// Parameters taken: Nucleotide sequence
	public static String[] codons(String sequence) {
		sequence = sequence.replace("-", "");
		String codonsList[] = new String[sequence.length() / NUC_PER_CODON];
		for (int i = 0; i < sequence.length(); i += NUC_PER_CODON) {
			String trio = sequence.substring(i, i + NUC_PER_CODON);
			codonsList[i / NUC_PER_CODON] = trio; 
		}
		return codonsList;
	}
	
	// Checks whether the protein is valid or not based on tests
	// Parameters taken: array of codons, Cytosine and Guanine mass percentage, PrintStream
	public static void proteinCheck(String[] list, double CytosinePert, double GuaninePert, 
			PrintStream outFile) throws FileNotFoundException {
		double combinedPert = CytosinePert + GuaninePert;
		outFile.print("Is Protein?: ");
		if (combinedPert >= CG_PERCENT && list[0].equals("ATG") && list.length >= MIN_CODON_NUM) {
			if (list[list.length - 1].equals("TAA") || list[list.length - 1].equals("TAG") ||
					list[list.length - 1].equals("TGA")) {
				outFile.println("YES");
			}
		} else {
			outFile.println("NO");
		}
		outFile.println();
	}
}
