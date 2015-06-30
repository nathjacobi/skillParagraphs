package skillParagraphs;

import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	JPanel checklistPanel;
	Map<String, ButtonGroup> selfHelpSection;
	Map<String, ButtonGroup> socialSection;
	Map<String, ButtonGroup> fineMotorSelection;
	Map<String, ButtonGroup> communiationLanguageSelection;
	
	public GUIPanels(ParagraphGUI mainGUI, Paragraph paragraph) {
		this.mainGUI = mainGUI;
		this.paragraph = paragraph;
		createNamePanel();
		createGenderButton();
		createNewStudentButton();

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
		JRadioButton mastered = new JRadioButton("Mastered");
		JRadioButton developing = new JRadioButton("Developing");
		JRadioButton notYetAble = new JRadioButton("Not Yet Able");
		JRadioButton notIntroduced = new JRadioButton("Not Introduced");
		ButtonGroup abilityLevel = new ButtonGroup();
		abilityLevel.add(mastered);
		abilityLevel.add(developing);
		abilityLevel.add(notYetAble);
		abilityLevel.add(notIntroduced);
		setUpMaps();
		
	}
	
	private void setUpMaps() {
		
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
