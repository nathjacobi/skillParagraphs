package skillParagraphs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
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
				System.out.println(paragraph);
				
			}
			
		}
		newStudent.addActionListener(new NewStudentButtonListener());
	}
	
	private void createNamePanel() {
		name = new JTextField();
		name.setEnabled(true);
		name.setSize(200, 20);
		mainGUI.add(name);
	}
}
