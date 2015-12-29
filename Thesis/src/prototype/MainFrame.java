package prototype;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class MainFrame 
	extends JFrame {

	JPanel panel = new JPanel();
	JPanel panel2 = new JPanel();
	JButton btnLoadModel = new JButton("Load Model");
	JButton btnSetLabel = new JButton("Set Label");
	JButton btnRecord = new JButton("Record");
	JButton btnStop = new JButton("Stop");
	JList<String> myList = new JList<>();
	
	public MainFrame(String text){
		super(text);
		initPanel();
		//getContentPane().add(panel);
		setContentPane(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300,300,550,300);
	}
	
	void initPanel(){
		panel.setLayout(new MigLayout(""));
		panel.add(btnLoadModel);
		panel.add(btnSetLabel, "wrap");
		panel.add(myList, "wrap");
		panel.add(btnRecord);
		panel.add(btnStop,"wrap");

	}
}
