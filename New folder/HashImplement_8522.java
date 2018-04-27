//ELIZABETH OVIEDO cs 435 8522 mp

import java.io.*;
import java.util.Scanner;

public class HashImplement_8522 {

	public static void main(String[] args) {
		File fileName = new File(args[0]);
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(fileName);
			
			int i = -1;
			
			HashCreate_8522[] lexicon = new HashCreate_8522[256];
		
			while(scanner.hasNextLine())
			{   
				int input = scanner.nextInt();
				
				if (input == 14) {
					i++;
					int second = scanner.nextInt();
					lexicon[i] = new HashCreate_8522(second);
				}
				else if(input == 10){
					if (i == -1) {
						System.out.println("You haven't yet created the lexicon.");
						scanner.close();
						return;
					}
					else {
						String word = scanner.next();
						lexicon[i].HashInsert(word);
					}
				}
				else if(input == 11) { 
					if (i == -1) {
						System.out.println("You haven't yet created the lexicon.");
						scanner.close();
						return;
					}
					else {
						String word = scanner.next();
						lexicon[i].HashDelete(word);
					}
				}
				else if(input == 12) {
					if (i == -1) {
						System.out.println("You haven't yet created the lexicon.");
						scanner.close();
						return;
					}
					else {
						String word = scanner.next();
						lexicon[i].HashSearch(word);
					}
				}
				else if(input == 13) {
					if (i == -1) {
						System.out.println("You haven't yet created the lexicon.");
						scanner.close();
						return;
					}
					else {
						lexicon[i].HashPrint();
					}
				}
				else {
					System.out.println("Input not valid. Please put a valid input.");
				}
			}
	    
			scanner.close();
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}