package skillParagraphs;

import java.util.Scanner;

public class Paragraph {
	private String name;
	private String pronoun;
	
	
	public Paragraph() {
		
	}
	
	public void collectInfo() {
		Scanner userInput = new Scanner(System.in);
		
		System.out.println("What is the child's name: ");
		name = userInput.nextLine();
		
		System.out.println("Are they male or female: ");
		pronoun = userInput.nextLine();
		
		if (pronoun.equals("male") || pronoun.equals("Male") 
				|| pronoun.equals("m") || pronoun.equals("M")) {
			pronoun = "He";
		}
		else {
			pronoun = "She";
		}
	}
	
	public static void main(String[] args) {
		Paragraph mainPara = new Paragraph();
		mainPara.collectInfo();
	}

}
