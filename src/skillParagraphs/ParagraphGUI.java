package skillParagraphs;

import javax.swing.JFrame;

public class ParagraphGUI extends JFrame{
	private Paragraph paragraph;
	private GUIPanels panels;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ParagraphGUI(){
		this.setTitle("Skill Paragraph Generator");
		this.setSize(640, 720);
		this.setLocation(400, 100);
		paragraph = new Paragraph();
		panels = new GUIPanels(this, paragraph);
	}
	
	public static void main(String[] args) {
		ParagraphGUI mainGUI = new ParagraphGUI();
		mainGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainGUI.setVisible(true);
	}

}
