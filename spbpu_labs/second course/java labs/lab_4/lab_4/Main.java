package lab_4;

import java.io.File;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the full path to the file with dictionary:");
		String dictPath = in.nextLine();
		File fileDict = new File(dictPath);
		System.out.println("Enter the full path to the file with text:");
		String textPath = in.nextLine();
		File fileText = new File(textPath);
		 in.close();
		 
		Translator translator = new Translator(fileDict);
		System.out.println("\nDictionary:");
		translator.printDictionary();
		System.out.println("\nTranslated text:");
		translator.translateText(fileText);
	}
}
