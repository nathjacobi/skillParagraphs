package skillParagraphs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class GUIPanels {
	JTextField name;
	ParagraphGUI mainGUI;
	Paragraph paragraph;
	JButton newStudent;
	
	
	public GUIPanels(ParagraphGUI mainGUI, Paragraph paragraph) {
		this.mainGUI = mainGUI;
		this.paragraph = paragraph;
		createNamePanel();
		createNewStudentButton();
		addListeners();
	}
	
	private void createNewStudentButton() {
		newStudent = new JButton("New Student");
		mainGUI.add(newStudent);
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
