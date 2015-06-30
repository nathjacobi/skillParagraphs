package skillParagraphs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SpringLayout.Constraints;
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
		mainGUI.add(newStudent, BorderLayout.SOUTH);
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
		checklistPanel.setLayout(new BorderLayout());
		setSelfHelpPanel();
		mainGUI.add(checklistPanel);
	}
	
	private void setSelfHelpPanel() {
		selfHelpPanel = new JPanel();
		JPanel selfHelpLabelPanel = new JPanel();
		selfHelpSection = new LinkedHashMap<JLabel, JRadioButton[]>();

		selfHelpPanel.setLayout(new GridLayout(8, 4, 1, 25));
		selfHelpLabelPanel.setLayout(new GridLayout(8, 0, 1, 25));
		
		JLabel masteredLabel = new JLabel("Mastered");
		JLabel developingLabel = new JLabel("Developing");
		JLabel notYetAbleLabel = new JLabel("Not Yet Able");
		JLabel notIntroducedLabel = new JLabel("Not Introduced");
				
		selfHelpPanel.add(masteredLabel);
		selfHelpPanel.add(developingLabel);
		selfHelpPanel.add(notYetAbleLabel);
		selfHelpPanel.add(notIntroducedLabel);
		
		checklistPanel.add(selfHelpLabelPanel, BorderLayout.WEST);
		
		JLabel selfHelpLabel = new JLabel("Self-Help Skills");
		JLabel buttonSnaps = new JLabel("Buttons, Snaps, Buckles, and Zips");
		JLabel tellsName = new JLabel("Tells name (lirst and last)");
		JLabel writesName = new JLabel("Writes name (first and last)");
		JLabel knowsBirthday = new JLabel("Knows birthday and age");
		JLabel knowsDays = new JLabel("Knows days of the week");
		JLabel knowsMonths = new JLabel("Knows months of the year");
		JLabel takesCare = new JLabel("Takes care of physical needs (clothes and bathroom)");
		
		selfHelpLabelPanel.add(selfHelpLabel);
		selfHelpLabelPanel.add(buttonSnaps);
		selfHelpLabelPanel.add(tellsName);
		selfHelpLabelPanel.add(writesName);
		selfHelpLabelPanel.add(knowsBirthday);
		selfHelpLabelPanel.add(knowsDays);
		selfHelpLabelPanel.add(knowsMonths);
		selfHelpLabelPanel.add(takesCare);
		
		
		setSelfHelpMaps(buttonSnaps);
		setSelfHelpMaps(tellsName);
		setSelfHelpMaps(writesName);
		setSelfHelpMaps(knowsBirthday);
		setSelfHelpMaps(knowsDays);
		setSelfHelpMaps(knowsMonths);
		setSelfHelpMaps(takesCare);
		
		for (JLabel label : selfHelpSection.keySet()) {
			for (JRadioButton button : selfHelpSection.get(label)) {
				selfHelpPanel.add(button);
			}
		}
		selfHelpPanel.setBorder(new EtchedBorder());
		checklistPanel.add(selfHelpPanel);
	}
	
	private void setSelfHelpMaps(JLabel label) {
		JRadioButton masteredButton = new JRadioButton();
		JRadioButton developingButton = new JRadioButton();
		JRadioButton notYetAbleButton = new JRadioButton();
		JRadioButton notIntroducedButton = new JRadioButton();
		abilityLevel = new ButtonGroup();
		abilityLevel.add(masteredButton);
		abilityLevel.add(developingButton);
		abilityLevel.add(notYetAbleButton);
		abilityLevel.add(notIntroducedButton);
		masteredButton.setSelected(true);
		
		JRadioButton[] levelSelect = new JRadioButton[4];
		levelSelect[0] = masteredButton;
		levelSelect[1] = developingButton;
		levelSelect[2] = notYetAbleButton;
		levelSelect[3] = notIntroducedButton;
		
		selfHelpSection.put(label, levelSelect);
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
