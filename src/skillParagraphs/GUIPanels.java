package skillParagraphs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

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
	Map<String, ArrayList<String>> allSkills;

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

	JButton previous;
	JButton next;

	int currentSkill;

	ArrayList<JPanel> skillPanels;
	Map<String, JRadioButton[]> skillMap;
	
	Map<String , Map<String, JRadioButton[]>> skillLevelMap;
	
	public GUIPanels(ParagraphGUI mainGUI, Paragraph paragraph) {
		this.mainGUI = mainGUI;
		this.paragraph = paragraph;
		createNamePanel();
		createGenderButton();
		createNewStudentButton();
		readFile();
		createChecklistPanel();
		addListeners();
	}

	private void readFile() {
		FileReader reader;
		allSkills = new LinkedHashMap<String, ArrayList<String>>();
		try {
			reader = new FileReader("skills.txt");
			Scanner scanner = new Scanner(reader);
			String category = new String();
			ArrayList<String> skillsIn = new ArrayList<String>();
			category = scanner.nextLine();
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.isEmpty()) {
					allSkills.put(category, skillsIn);
					category = scanner.nextLine();
					skillsIn = new ArrayList<String>();
				}
				else {
					skillsIn.add(line);
				}
			}
			allSkills.put(category, skillsIn);

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void createNewStudentButton() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		next = new JButton("Next");
		previous = new JButton("Previous");
		newStudent = new JButton("New Student");

		buttonPanel.add(previous);
		buttonPanel.add(newStudent);
		buttonPanel.add(next);
		mainGUI.add(buttonPanel, BorderLayout.SOUTH);
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
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				paragraph.setName(name.getText());
				paragraph.setSkillLevelMap(skillLevelMap);
				if (female.isSelected())
					paragraph.setGenderPronouns(female.getLabel());
				else
					paragraph.setGenderPronouns(male.getLabel());
				System.out.println(paragraph);
			}
		}

		class NextPanelButtonListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentSkill < skillPanels.size()-1) {
					checklistPanel.remove(skillPanels.get(currentSkill));
					currentSkill++;
					checklistPanel.add(skillPanels.get(currentSkill),
							BorderLayout.WEST);

					mainGUI.validate();
					mainGUI.repaint();
				}			}	
		}

		class PreviousPanelButtonListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentSkill > 0) {
					checklistPanel.remove(skillPanels.get(currentSkill));
					currentSkill--;
					checklistPanel.add(skillPanels.get(currentSkill),
							BorderLayout.WEST);

					mainGUI.validate();
					mainGUI.repaint();
				}
			}

		}

		newStudent.addActionListener(new NewStudentButtonListener());
		next.addActionListener(new NextPanelButtonListener());
		previous.addActionListener(new PreviousPanelButtonListener());
	}

	private void createChecklistPanel() {
		checklistPanel = new JPanel();
		checklistPanel.setLayout(new BorderLayout());

		mainGUI.add(checklistPanel);
		createSkillPanels();

		currentSkill = 0;
		checklistPanel.add(skillPanels.get(currentSkill), BorderLayout.WEST);
	}

	private void createSkillPanels() {
		skillPanels = new ArrayList<JPanel>();
		skillLevelMap = new LinkedHashMap<String, Map<String,JRadioButton[]>>();
		for (String skillSet : allSkills.keySet()) {
			skillMap = new LinkedHashMap<String, JRadioButton[]>();
			JPanel overallSkillPanel = new JPanel();
			overallSkillPanel.setLayout(new BorderLayout());

			JPanel skillLevelPanel = new JPanel();
			JPanel skillLabelPanel = new JPanel();

			skillLevelPanel.setLayout(new GridLayout(allSkills.get(skillSet).size() + 1
					, 4, 1, 25));
			skillLabelPanel.setLayout(new GridLayout(allSkills.get(skillSet).size() + 1
					, 0, 1, 25));

			createButtonGroup(allSkills.get(skillSet), skillMap);

			skillLabelPanel.add(new JLabel(skillSet));

			for (String skill : skillMap.keySet()) {
				JLabel label = new JLabel(skill);
				
				label.setOpaque(true);
				label.setBackground(Color.LIGHT_GRAY);
				
				skillLabelPanel.add(label);
			}

			JLabel masteredLabel = new JLabel("Mastered");
			JLabel developingLabel = new JLabel("Developing");
			JLabel notYetAbleLabel = new JLabel("Not Yet Able");
			JLabel notIntroducedLabel = new JLabel("Not Introduced");

			skillLevelPanel.add(masteredLabel);
			skillLevelPanel.add(developingLabel);
			skillLevelPanel.add(notYetAbleLabel);
			skillLevelPanel.add(notIntroducedLabel);

			for (String skill : skillMap.keySet()) {
				for (JRadioButton button : skillMap.get(skill)) {
					JLabel label = new JLabel(skill);
					label.setOpaque(true);
					label.setBackground(Color.LIGHT_GRAY);
					skillLevelPanel.add(button);
				}
			}
			
			skillLevelMap.put(skillSet, skillMap);
			
			overallSkillPanel.setName(skillSet);
			overallSkillPanel.add(skillLabelPanel, BorderLayout.WEST);
			overallSkillPanel.add(skillLevelPanel);
			skillPanels.add(overallSkillPanel);
		}
	}

	private void createButtonGroup(ArrayList<String> skills, Map<String, JRadioButton[]> map) {
		for (String skill : skills) {
			JRadioButton masteredButton = new JRadioButton();
			JRadioButton developingButton = new JRadioButton();
			JRadioButton notYetAbleButton = new JRadioButton();
			JRadioButton notIntroducedButton = new JRadioButton();

			masteredButton.setOpaque(true);
			developingButton.setOpaque(true);
			notYetAbleButton.setOpaque(true);
			notIntroducedButton.setOpaque(true);
			
			masteredButton.setName("mastered");
			developingButton.setName("developing");
			notYetAbleButton.setName("not yet able");
			notIntroducedButton.setName("not introduced");

			masteredButton.setBackground(Color.LIGHT_GRAY);
			developingButton.setBackground(Color.LIGHT_GRAY);
			notYetAbleButton.setBackground(Color.LIGHT_GRAY);
			notIntroducedButton.setBackground(Color.LIGHT_GRAY);

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

			map.put(skill, levelSelect);
		}
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
