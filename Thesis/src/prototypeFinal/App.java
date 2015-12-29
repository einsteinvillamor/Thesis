package prototypeFinal;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import net.miginfocom.swing.MigLayout;
import prototypeFinal.ThreadTestGui.guiState;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.JLabel;

public class App extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private MyDialog dialog;
	private LabelDialog lblDialog ;
	private WekaLauncher weka;
	private AudioRecorder recorder;
	private EmotionDialog emoDialog;
	private MatlabLauncher matlab;
	private CsvFileWriter cfw;
	JLabel lblLastRecordedEmotion = new JLabel("Last Recorded Emotion: ");
	
	public static boolean isLabelSet = false;
	public static boolean isModelSet = false;
	
	Path currentRelativePath = Paths.get("");
	/**
	 * Launch the application.
	 */
	
	public static enum guiState{
		Record, Stop
	}
	public static volatile guiState guiStat = guiState.Stop;
	
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
	 * @throws Exception 
	 */
	public App() throws Exception {
		dialog = new MyDialog();
		emoDialog = new EmotionDialog();
		recorder = new AudioRecorder();
		lblDialog = new LabelDialog();
		matlab = new MatlabLauncher(false);
		cfw = new CsvFileWriter();
		String s = currentRelativePath.toAbsolutePath().toString() +"/Files";
		String s1 = currentRelativePath.toAbsolutePath().toString() +"/PermanentFiles";
		File theDir = new File(s.replaceAll("\\\\", "/"));
		File Dir = new File(s1.replaceAll("\\\\", "/"));
		recorder.setFilePath(s.replaceAll("\\\\", "/") +"/Record/");
        if(!Dir.exists()){
        	Dir.mkdir();
        	Dir = new File(s1.replaceAll("\\\\", "/") +"/MatlabCodes");
        	Dir.mkdir();
	        theDir = new File(s.replaceAll("\\\\", "/") +"/Model");
	        theDir.mkdir();
        	System.out.println("DIR created");
        }
		
		if (!theDir.exists()) {
		    System.out.println("creating directory: " + s.replaceAll("\\\\", "/"));
		    boolean result = false;

		    try{
		        theDir.mkdir();
		        theDir = new File(s.replaceAll("\\\\", "/") +"/Record");
		        theDir.mkdir();
		        theDir = new File(s.replaceAll("\\\\", "/") +"/Csv");
		        theDir.mkdir();
		        theDir = new File(s.replaceAll("\\\\", "/") +"/Arff");
		        theDir.mkdir();
		        theDir = new File(s.replaceAll("\\\\", "/") +"/FinalResult");
		        theDir.mkdir();
		        result = true;
		    } 
		    catch(SecurityException se){
		        //handle it
		    }        
		    if(result) {    
		        System.out.println("DIR created");  
		    }
		}else
			System.out.println("DIR exists");
		
		
		weka = new WekaLauncher();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new MigLayout("", "[grow]", "[][grow][][]"));
		setContentPane(contentPane);
		
		JButton btnLoadModel = new JButton("Load Model");
		btnLoadModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.openDialogBox();
				weka.setModelpath(dialog.getNamePath().toString());
				System.out.println(dialog.getNamePath());
				btnLoadModel.setText("Model Loaded");
				isModelSet = true;
			}
		});
		contentPane.add(btnLoadModel, "flowx,cell 0 0");
		
		JButton btnSetLabel = new JButton("Set Label");
		btnSetLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblDialog.start();
				isLabelSet = true;
			}
		});
		contentPane.add(btnSetLabel, "cell 0 0");
		
		JButton btnRecord = new JButton("Record");
		btnRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isModelSet == true && isLabelSet == true){
					btnRecord.setText("Recording");
					guiStat = guiState.Record;
					emoDialog.setEmotionLabel(LabelDialog.labelList);
					
					btnLoadModel.setEnabled(false);
					btnSetLabel.setEnabled(false);
					emoDialog.setLblRecord(lblLastRecordedEmotion);
					//guiStat.notifyAll();
					SwingController();
				}else
					JOptionPane.showMessageDialog(null, "Set model and label first");
			}
		});
		String[] column = {"Time Length", "Agent Assessment", "Detected Emotion"};
		Object[][] data = {};
		table = new JTable(data,column);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, "cell 0 1,grow");
		
		
		contentPane.add(lblLastRecordedEmotion, "cell 0 2");
		contentPane.add(btnRecord, "flowx,cell 0 3");
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRecord.setText("Record");
				guiStat = guiState.Stop;
				btnLoadModel.setEnabled(true);
				btnSetLabel.setEnabled(true);
				cfw.setValuesList(matlab.getResultList());
				//guiStat.notifyAll();
			}
		});
		contentPane.add(btnStop, "cell 0 3");
		
		JButton btnTest = new JButton("Test");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					
			}
		});
		contentPane.add(btnTest, "cell 0 0");
	}
	
	void SwingController(){
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){

			@Override
			protected Void doInBackground() throws Exception {
				// TODO Auto-generated method stub
				while(guiStat == guiState.Record){
					recorder.record();
					emoDialog.start();
					Thread t = new Thread(new Runnable(){

						@Override
						public void run() {
							// TODO Auto-generated method stub
							while(recorder.getFilename().size() != 0){
								matlab.setPath(currentRelativePath.toAbsolutePath().toString() +"/Files/Record/" + recorder.getFilename().get(0));
								recorder.getFilename().remove(0);
								try {
									matlab.runMatlab();
								} catch (MatlabConnectionException | MatlabInvocationException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								System.out.println("done matlab");
							}
						}

					});
					t.start();
					
				}
				return (Void) null;
			}
			
		};
		worker.execute();
	}

}
