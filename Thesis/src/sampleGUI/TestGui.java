package sampleGUI;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class TestGui 
	extends JFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TestGui frame = new TestGui();
		frame.setVisible(true);
	}
	JList<String> list = new JList<>();
	JPanel panel = new JPanel();
	
	public TestGui(){
		list.setBackground(Color.BLUE);
		list.setVisibleRowCount(12);
		panel.setLayout(new MigLayout(""));
		panel.add(list);
		
		getContentPane().add(panel);
		setBounds(300,300,550,300);
	}

}
