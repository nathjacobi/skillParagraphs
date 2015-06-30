package skillParagraphs;

import java.util.Scanner;

import javax.swing.JFrame;

public class Paragraph {
	private String name;
	private String pronoun;
	private ParagraphGUI mainWindow;
	
	public Paragraph() {
	}
	
	public void setName(String name) {
		this.name = name;
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
	@Override
	public String toString() {
		return name;
		
	}
}
