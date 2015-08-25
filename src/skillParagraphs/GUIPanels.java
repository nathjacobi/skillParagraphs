package skillParagraphs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class GUIPanels {
	Map<String, ArrayList<String>> allSkills;

	JTextField name;
	ParagraphGUI mainGUI;
	Paragraph paragraph;
	JButton writeParagraph;

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
	
	Color skillColor;
	
	public GUIPanels(ParagraphGUI mainGUI) {
		this.mainGUI = mainGUI;
		paragraph = new Paragraph();
		skillColor = Color.LIGHT_GRAY;
		createNamePanel();
		createGenderButton();
		createNavigateAndCreateButtonPanel();
		readFile();
		createChecklistPanel();
		addListeners();
	}

	private void readFile() {
		allSkills = new LinkedHashMap<String, ArrayList<String>>();
		InputStream stream = getClass().getResourceAsStream("skills.txt");
		Scanner scanner = new Scanner(stream);
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
	}

	private void createNavigateAndCreateButtonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		next = new JButton("Next");
		previous = new JButton("Previous");
		writeParagraph = new JButton("Write Paragraph");

		buttonPanel.add(previous);
		buttonPanel.add(writeParagraph);
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
				paragraph.reset();
				paragraph.setName(name.getText());
				paragraph.setSkillLevelMap(skillLevelMap);
				if (female.isSelected())
					paragraph.setGenderPronouns(female.getLabel());
				else
					paragraph.setGenderPronouns(male.getLabel());
				new ParagraphTextBox(paragraph.getParagraphs());
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

		writeParagraph.addActionListener(new NewStudentButtonListener());
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
					, 4, 1, 0));
			skillLabelPanel.setLayout(new GridLayout(allSkills.get(skillSet).size() + 1
					, 0, 1, 0));

			createButtonGroup(allSkills.get(skillSet), skillMap);

			skillLabelPanel.add(new JLabel(skillSet));
			
			if (skillMap.keySet().size() % 2 == 1) {
				pickColor();
			}
			
			for (String skill : skillMap.keySet()) {
				pickColor();
				JLabel label = new JLabel(skill);
				
				label.setOpaque(true);
				label.setBackground(getSkillColor());
				
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
					pickColor();
					JLabel label = new JLabel(skill);
					label.setOpaque(true);
					label.setBackground(getSkillColor());
					skillLevelPanel.add(button);
				}
			}
			
			skillLevelMap.put(skillSet, skillMap);
			
			overallSkillPanel.setName(skillSet);
			overallSkillPanel.setBorder(new EtchedBorder());
			overallSkillPanel.add(skillLabelPanel, BorderLayout.WEST);
			overallSkillPanel.add(skillLevelPanel);
			skillPanels.add(overallSkillPanel);
		}
	}

	private void createButtonGroup(ArrayList<String> skills, Map<String, JRadioButton[]> map) {
		for (String skill : skills) {
			pickColor();
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

			masteredButton.setBackground(getSkillColor());
			developingButton.setBackground(getSkillColor());
			notYetAbleButton.setBackground(getSkillColor());
			notIntroducedButton.setBackground(getSkillColor());

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
	
	private Color getSkillColor() {
		return skillColor;
	}
	
	private Color pickColor() {
		if (skillColor.equals(Color.LIGHT_GRAY)) {
			skillColor = Color.GRAY;
			return skillColor;
		}
		else {
			skillColor = Color.LIGHT_GRAY;
			return skillColor;
		}
	}
}
