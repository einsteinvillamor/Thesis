package sampleGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.JTable;

public class App extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
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
	public App() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][]", "[][][][grow][][][][]"));
		
		JButton btnLoadModel = new JButton("Load Model");
		contentPane.add(btnLoadModel, "flowx,cell 0 0");
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 3,grow");
		
		table = new JTable();
		panel.add(table);
		
		JButton btnRecord = new JButton("Record");
		contentPane.add(btnRecord, "flowx,cell 0 7");
		
		JButton btnStop = new JButton("Stop");
		contentPane.add(btnStop, "cell 0 7");
		
		JButton btnSetLabel = new JButton("Set Label");
		contentPane.add(btnSetLabel, "cell 0 0");
	}

}
