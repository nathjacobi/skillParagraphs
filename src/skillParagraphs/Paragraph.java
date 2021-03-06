package skillParagraphs;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JRadioButton;

public class Paragraph {
	private String name;
	private String addressingPronoun;
	private String referPronoun;
	
	ArrayList <String> paragraphs;
	Map<String , Map<String, JRadioButton[]>> skillLevelMap;
	
	
	public Paragraph() {
		paragraphs = new ArrayList<String>();
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
		
		makeParagraphs();
	}
	
	public void reset() {
		paragraphs = new ArrayList<String>();
		skillLevelMap = new LinkedHashMap<String, Map<String,JRadioButton[]>>();
	}
	
	public void setSkillLevelMap(Map<String, Map<String,JRadioButton[]>> skillLevelMap) {
		this.skillLevelMap = skillLevelMap;
	}

	public void makeParagraphs() {

		for (String set : skillLevelMap.keySet()) {
			String setParagraph = "";
			setParagraph = setParagraph.concat(set+":\n");
			
			ArrayList<String> mastered = new ArrayList<String>();
			ArrayList<String> developing = new ArrayList<String>();
			ArrayList<String> notYetAble = new ArrayList<String>();
			ArrayList<String> notIntroduced = new ArrayList<String>();
			
			
			for (String skill : skillLevelMap.get(set).keySet()) {
				String finalSkill= skill;
				if (skill.contains("their")) {
					finalSkill = skill.replace("their", addressingPronoun);
				}
				for (JRadioButton button : skillLevelMap.get(set).get(skill)) {
					if (button.getName().equals("mastered") && button.isSelected()) {
						mastered.add(finalSkill);
					} 
					else if (button.getName().equals("developing") && button.isSelected()) {
						developing.add(finalSkill);
					}
					else if (button.getName().equals("not yet able") && button.isSelected()) {
						notYetAble.add(finalSkill);
					}
					else if (button.getName().equals("not introduced") && button.isSelected()) {
						String firstWord = finalSkill.substring(0, finalSkill.indexOf(" "));
						String changedFirstWord = firstWord;
						if (firstWord.endsWith("e")) {
							changedFirstWord = changedFirstWord.substring(0, changedFirstWord.length()-1);
						}
						else if (firstWord.endsWith("ie")) {
							changedFirstWord = changedFirstWord.substring(0, changedFirstWord.length()-2);
							changedFirstWord = changedFirstWord.concat("y");
						}
						changedFirstWord = changedFirstWord.concat("ing");
						finalSkill = changedFirstWord.concat(
								finalSkill.substring(firstWord.length(), finalSkill.length()));
						notIntroduced.add(finalSkill);
					}
				}
			}
			
			if (!mastered.isEmpty()) {
				setParagraph = setParagraph.concat(name+" has mastered " + addressingPronoun
						+ " ability to ");
				for (int i = 0; i<mastered.size(); i++) {
					if (i == mastered.size() - 1) {
						if (i > 0) {
							setParagraph = setParagraph.concat("and " + mastered.get(i).toLowerCase() + ".");
						} else {
							setParagraph = setParagraph.concat(mastered.get(i).toLowerCase() + ".");
						}
					}
					else {
						setParagraph = setParagraph.concat(mastered.get(i).toLowerCase());
						if (mastered.get(i).contains(",")){
							setParagraph = setParagraph.concat("; ");
						}
						else {
							setParagraph = setParagraph.concat(", ");
						}
					}
					
				}
			}
			if (!developing.isEmpty()) {
				if (setParagraph.contains(name)) {
					setParagraph = setParagraph.concat(" " + referPronoun + " is developing " + 
				addressingPronoun + " ability to ");
				}
				else {
					setParagraph = setParagraph.concat(name + " is developing " + addressingPronoun
						+ " ability to ");
				}
				for (int i = 0; i<developing.size(); i++) {
					if (i == developing.size() - 1) {
						if (i > 0) {
							setParagraph = setParagraph.concat("and " + developing.get(i).toLowerCase() + ".");
						} else {
							setParagraph = setParagraph.concat(developing.get(i).toLowerCase() + ".");
						}
					}
					else {
						setParagraph = setParagraph.concat(developing.get(i).toLowerCase());
						if (developing.get(i).contains(",")){
							setParagraph = setParagraph.concat("; ");
						}
						else {
							setParagraph = setParagraph.concat(", ");
						}
					}
					
				}
			}
			if (!notYetAble.isEmpty()) {
				if (setParagraph.contains(name)) {
					setParagraph = setParagraph.concat(" " + referPronoun + " is not yet able to ");
				}
				else {
					setParagraph = setParagraph.concat(name + " is not yet able to ");
				}
				for (int i = 0; i<notYetAble.size(); i++) {
					if (i == notYetAble.size() - 1) {
						if (i > 0) {
							setParagraph = setParagraph.concat("and " + notYetAble.get(i).toLowerCase() + ".");
						} else {
							setParagraph = setParagraph.concat(notYetAble.get(i).toLowerCase() + ".");
						}
					}
					else {
						setParagraph = setParagraph.concat(notYetAble.get(i).toLowerCase());
						if (notYetAble.get(i).contains(",")){
							setParagraph = setParagraph.concat("; ");
						}
						else {
							setParagraph = setParagraph.concat(", ");
						}
					}
					
				}
			}
			if (!notIntroduced.isEmpty()) {
				if (setParagraph.contains(name)) {
					setParagraph = setParagraph.concat(" " + referPronoun + " has not been" +
							" introduced to ");
				}
				else {
					setParagraph = setParagraph.concat(name + " has not been introduced to ");
				}				for (int i = 0; i<notIntroduced.size(); i++) {
					if (i == notIntroduced.size() - 1) {
						if (i > 0) {
							setParagraph = setParagraph.concat("and " + notIntroduced.get(i).toLowerCase() + ".");
						} else {
							setParagraph = setParagraph.concat(notIntroduced.get(i).toLowerCase() + ".");
						}
					}
					else {
						setParagraph = setParagraph.concat(notIntroduced.get(i).toLowerCase());
						if (notIntroduced.get(i).contains(",")){
							setParagraph = setParagraph.concat("; ");
						}
						else {
							setParagraph = setParagraph.concat(", ");
						}
					}
					
				}
			}
			setParagraph = setParagraph.concat("\n\n");
			paragraphs.add(setParagraph);
		}
	}
	
	public ArrayList<String> getParagraphs() {
		return paragraphs;
	}
	
	@Override
	public String toString() {
		return name + " " + addressingPronoun + " " + referPronoun;
		
	}
}
