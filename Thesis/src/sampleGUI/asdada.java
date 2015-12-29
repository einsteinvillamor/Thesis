package sampleGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;

public class asdada extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					asdada frame = new asdada();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public asdada() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[][][][grow][][][][]"));
		
		JButton btnNewButton = new JButton("New button");
		contentPane.add(btnNewButton, "flowx,cell 0 0");
		
		JButton btnNewButton_1 = new JButton("New button");
		contentPane.add(btnNewButton_1, "cell 0 0");
		
		JList list = new JList();
		contentPane.add(list, "cell 0 3,grow");
		
		JButton btnNewButton_2 = new JButton("New button");
		contentPane.add(btnNewButton_2, "flowx,cell 0 7");
		
		JButton btnNewButton_3 = new JButton("New button");
		contentPane.add(btnNewButton_3, "cell 0 7");

	}

}
