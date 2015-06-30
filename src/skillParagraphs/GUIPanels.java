package skillParagraphs;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class GUIPanels {
	JTextField name;
	ParagraphGUI mainGUI;
	Paragraph paragraph;
	JButton newStudent;
	
	JPanel genderPanel;
	JRadioButton female;
	JRadioButton male;
	
	JPanel selfHelpPanel;
	JPanel checklistPanel;
	ButtonGroup abilityLevel;
	Map<JLabel, JRadioButton[]> selfHelpSection;
	Map<String, ButtonGroup> socialSection;
	Map<String, ButtonGroup> fineMotorSelection;
	Map<String, ButtonGroup> communiationLanguageSelection;
	
	public GUIPanels(ParagraphGUI mainGUI, Paragraph paragraph) {
		this.mainGUI = mainGUI;
		this.paragraph = paragraph;
		createNamePanel();
		createGenderButton();
		createNewStudentButton();
		createChecklistPanel();
		addListeners();
	}
	
	private void createNewStudentButton() {
		newStudent = new JButton("New Student");
		mainGUI.add(newStudent);
	}
	
	private void createGenderButton() {
		ButtonGroup genderSelection = new ButtonGroup();
		female = new JRadioButton("Female");
		male = new JRadioButton("Male");
		genderPanel = new JPanel();
		
		genderSelection.add(female);
		genderSelection.add(male);
		
		female.setSelected(true);
		
		genderPanel.add(female);
		genderPanel.add(male);
		
		genderPanel.setBorder(new TitledBorder (new EtchedBorder(), "Gender"));
		
		//genderPanel.add(genderSelection);
		mainGUI.add(genderPanel, BorderLayout.EAST);
	}
	
	private void addListeners() {
		class NewStudentButtonListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				paragraph.setName(name.getText());
				if (female.isSelected())
					paragraph.setGenderPronouns(female.getLabel());
				else
					paragraph.setGenderPronouns(male.getLabel());
				System.out.println(paragraph);
				
			}
			
		}
		newStudent.addActionListener(new NewStudentButtonListener());
	}
	
	private void createChecklistPanel() {
		checklistPanel = new JPanel();
		setSelfHelpPanel();
		setUpMaps();
		
	}
	
	private void setSelfHelpPanel() {
		selfHelpPanel = new JPanel();
		selfHelpSection = new LinkedHashMap<JLabel, JRadioButton[]>();
		
		JRadioButton mastered = new JRadioButton("Mastered");
		JRadioButton developing = new JRadioButton("Developing");
		JRadioButton notYetAble = new JRadioButton("Not Yet Able");
		JRadioButton notIntroduced = new JRadioButton("Not Introduced");
		abilityLevel = new ButtonGroup();
		abilityLevel.add(mastered);
		abilityLevel.add(developing);
		abilityLevel.add(notYetAble);
		abilityLevel.add(notIntroduced);
		mastered.setSelected(true);
		
		JRadioButton[] levelSelect = new JRadioButton[4];
		levelSelect[0] = mastered;
		levelSelect[1] = developing;
		levelSelect[2] = notYetAble;
		levelSelect[3] = notIntroduced;
		
		JLabel buttonSnaps = new JLabel("Buttons, Snaps, Buckles, and Zips");
		JLabel tellsName = new JLabel("Tells name (lirst and last");
		selfHelpSection.put(buttonSnaps, levelSelect);
		selfHelpSection.put(tellsName, levelSelect);
		
		//selfHelpPanel.setLayout(new GridLayout(3, 5));
		
		
		for (JLabel label : selfHelpSection.keySet()) {
			selfHelpPanel.add(label);
			selfHelpPanel.add(mastered);
			selfHelpPanel.add(developing);
			selfHelpPanel.add(notYetAble);
			selfHelpPanel.add(notIntroduced);
		}
		
		
		
		mainGUI.add(selfHelpPanel);
	}
	
	private void setUpMaps() {
		JLabel buttonSnaps = new JLabel("Buttons, Snaps, Buckles, and Zips");
	}
	
	private void createNamePanel() {
		JLabel nameLabel = new JLabel("Name (first only)");
		JPanel namePanel = new JPanel();
		name = new JTextField(30);
		name.setEnabled(true);
		
		namePanel.add(nameLabel);
		namePanel.add(name);
		mainGUI.add(namePanel, BorderLayout.NORTH);
	}
}
