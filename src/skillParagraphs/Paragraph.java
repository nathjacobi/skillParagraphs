package skillParagraphs;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JRadioButton;

public class Paragraph {
	private String name;
	private String addressingPronoun;
	private String referPronoun;
	private ParagraphGUI mainWindow;
	Map<String , Map<String, JRadioButton[]>> skillLevelMap;
	
	
	public Paragraph() {
		skillLevelMap = new LinkedHashMap<String, Map<String,JRadioButton[]>>();
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
	
	public void setSkillLevelMap(Map<String, Map<String,JRadioButton[]>> skillLevelMap) {
		this.skillLevelMap = skillLevelMap;
		for (String set : skillLevelMap.keySet()) {
			for (String skill : skillLevelMap.get(set).keySet()) {
				for (JRadioButton button : skillLevelMap.get(set).get(skill)) {
					if (button.isSelected()) {
						System.out.println("The " + skill + " skill is at the " + button.getName()
								+ " level");
					}
				}
			}
		}
	}
	
	@Override
	public String toString() {
		return name + " " + addressingPronoun + " " + referPronoun;
		
	}
}
