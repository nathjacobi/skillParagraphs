package skillParagraphs;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ParagraphTextBox extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel textBoxPane;
	private ArrayList<String> paragraphs;
	
	public ParagraphTextBox(ArrayList<String> paragraphs) {
		this.paragraphs = paragraphs;
		
		this.setTitle("Paragraph");
		this.setSize(800, 720);
		this.setLocation(400, 100);
		
		this.setVisible(true);
		
		createtextBox();
	}

	private void createtextBox() {
		textBoxPane = new JPanel();
		textBoxPane.setLayout(new BorderLayout());
		String finalList = new String();
		JTextArea paragraph = new JTextArea();
		paragraph.setLineWrap(true);
		paragraph.setAutoscrolls(true);
		paragraph.setEditable(false);
		
		for (String currentPara : paragraphs) {
			finalList = finalList.concat(currentPara);
		}
		
		paragraph.setText(finalList);
		textBoxPane.add(paragraph, BorderLayout.CENTER);
		this.add(textBoxPane);
	}
}
