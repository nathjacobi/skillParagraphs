package skillParagraphs;

import java.util.Scanner;

import javax.swing.JFrame;

public class Paragraph {
	private String name;
	private String addressingPronoun;
	private String referPronoun;
	private ParagraphGUI mainWindow;
	
	public Paragraph() {
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setGenderPronouns(String gender) {
		if (gender.equals("Female")) {
			addressingPronoun = "her";
			referPronoun = "She";
		}
		else {
			addressingPronoun = "his";
			referPronoun = "He";
		}
	}
	
	@Override
	public String toString() {
		return name + " " + addressingPronoun + " " + referPronoun;
		
	}
}
