package prototype;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class MainFrame 
	extends JFrame {

	JPanel panel = new JPanel(new MigLayout(""));
	JLabel lblLoadModel = new JLabel("Load Model");
	JLabel lblSetLabel = new JLabel("Set Label");
	JList myList = new JList();
	
	public MainFrame(String text){
		super(text);
		
		
	}
	
	void initPanel(){
		panel.add(lblLoadModel);
		panel.add(lblSetLabel);
		
	}
}
