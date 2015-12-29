package prototypeFinal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ThreadTestGui extends JFrame {

	private JPanel contentPane;

	public static enum guiState{
		Record, Stop
	}
	private volatile guiState guiStat = guiState.Stop;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThreadTestGui frame = new ThreadTestGui();
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
	Thread t;
	public ThreadTestGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnRecord = new JButton("Record");
		btnRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiStat = guiState.Record;
			t = new Thread(new Runnable() {
                    public void run() {
                            int cnt = 0;

                            while (guiStat == guiState.Record) {
                                    System.out.println(cnt);
                                    ++cnt;
                            }
                    }
            });

            t.start();
			}
		});
		contentPane.add(btnRecord, BorderLayout.WEST);
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				guiStat = guiState.Stop;
				//t.stop();
			}
		});
		contentPane.add(btnStop, BorderLayout.CENTER);
	}

}
