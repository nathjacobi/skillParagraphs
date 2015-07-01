package skillParagraphs;

import javax.swing.JFrame;

public class ParagraphGUI extends JFrame{
	private GUIPanels panels;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ParagraphGUI(){
		this.setTitle("Skill Paragraph Generator");
		this.setSize(1280, 720);
		this.setLocation(400, 100);
		panels = new GUIPanels(this);
	}
	
	public static void main(String[] args) {
		ParagraphGUI mainGUI = new ParagraphGUI();
		mainGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainGUI.setVisible(true);
	}

}
