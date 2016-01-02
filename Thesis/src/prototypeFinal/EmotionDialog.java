package prototypeFinal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import net.miginfocom.swing.MigLayout;
import prototypeFinal.App.guiState;


public class EmotionDialog 
	extends JDialog{
	
	private final JPanel panel = new JPanel();
	private String emotionString;
	boolean isRunning = false;
	
	JRadioButton rdbtnHappy = new JRadioButton("Happy");
	JRadioButton rdbtnFear = new JRadioButton("Fear");
	JRadioButton rdbtnSad = new JRadioButton("Sad");
	JRadioButton rdbtnSurprise = new JRadioButton("Surprise");
	JRadioButton rdbtnAngry = new JRadioButton("Angry");
	JRadioButton rdbtnDisgust = new JRadioButton("Disgust");
	
	
	JButton btnStop;
	
	ButtonGroup group = new ButtonGroup();
	JButton btn = new JButton("OK");
	
	JLabel lblemo = new JLabel("What are you feeling?");
	JLabel lblrecord = new JLabel("");
	
	private List<String> emotionLabel = new ArrayList<>();
	private List<String> emotionList = new ArrayList<>();

	public void start(){
		if(App.guiStat == guiState.Record){
			btnStop.setEnabled(false);
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setVisible(true);
			emoChecker(emotionLabel);
		}
	}
	
	public EmotionDialog(){
		
		setBounds(300,300,230, 200);
		panel.setLayout(new MigLayout(""));
		panel.add(lblemo, "wrap");
		panel.add(rdbtnHappy);
		panel.add(rdbtnFear, "wrap");
		panel.add(rdbtnSad);
		panel.add(rdbtnSurprise, "wrap");
		panel.add(rdbtnAngry);
		panel.add(rdbtnDisgust, "wrap");
		panel.add(btn);
		
		group.add(rdbtnHappy);
		group.add(rdbtnFear);
		group.add(rdbtnSad);
		group.add(rdbtnSurprise);
		group.add(rdbtnAngry);
		group.add(rdbtnDisgust);
		
		getContentPane().add(panel);
		
		btn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(rdbtnHappy.isSelected() ==true){
					emotionString = "happy";
					emotionSelected();
				}else if (rdbtnSad.isSelected() == true){
					emotionString = "sad";
					emotionSelected();
				}else if (rdbtnAngry.isSelected() == true){
					emotionString = "angry";
					emotionSelected();
				}else if (rdbtnFear.isSelected() == true){
					emotionString = "fear";
					emotionSelected();
				}else if (rdbtnSurprise.isSelected() == true){
					emotionString = "surprise";
					emotionSelected();
				}else if (rdbtnDisgust.isSelected() == true){
					emotionString = "disgust";
					emotionSelected();
				}
				btnStop.setEnabled(true);
			}
			
		});
	}
	
	private void emoChecker(List<String> list){
		rdbtnHappy.setEnabled(false);
		rdbtnSad.setEnabled(false);
		rdbtnAngry.setEnabled(false);
		rdbtnFear.setEnabled(false);
		rdbtnSurprise.setEnabled(false);
		rdbtnDisgust.setEnabled(false);
		
		for(String i:list){
			if(i == "happy"){
				rdbtnHappy.setEnabled(true);
			}
			else if(i == "sad"){
				rdbtnSad.setEnabled(true);
			}
			else if(i == "angry"){
				rdbtnAngry.setEnabled(true);
			}
			else if(i == "fear"){
				rdbtnFear.setEnabled(true);
			}
			else if(i == "surprise"){
				rdbtnSurprise.setEnabled(true);
			}
			else if(i == "disgust"){
				rdbtnDisgust.setEnabled(true);
			}
		}
	}
	
	private void emotionSelected(){
		emotionList.add(emotionString);
		rdbtnHappy.setSelected(false);
		rdbtnSad.setSelected(false);
		rdbtnAngry.setSelected(false);
		rdbtnFear.setSelected(false);
		rdbtnSurprise.setSelected(false);
		rdbtnDisgust.setSelected(false);
		lblrecord.setText("Last Recorded Emotion: " + emotionString);
		dispose();
	}
	
	public void setLblRecord(JLabel lbl){
		this.lblrecord = lbl;
	}
	
	public List<String> getEmotionList(){
		return emotionList;
	}
	
	public void setEmotionLabel(List<String> list){
		this.emotionLabel = list;
	}
	
	public void setBtnStop(JButton btn){
		btnStop = btn;
	}

}
