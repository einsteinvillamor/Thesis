package prototypeFinal;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class LabelDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static List<String> labelList = new ArrayList<>();
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			LabelDialog dialog = new LabelDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public void start(){
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	/**
	 * Create the dialog.
	 */
	
	JCheckBox rdbtnHappy = new JCheckBox("Happy");
	JCheckBox rdbtnFear = new JCheckBox("Fear");
	JCheckBox rdbtnSad = new JCheckBox("Sad");
	JCheckBox rdbtnSurprise = new JCheckBox("Surprise");
	JCheckBox rdbtnAngry = new JCheckBox("Angry");
	JCheckBox rdbtnDisgust = new JCheckBox("Disgust");
	public LabelDialog() {
		setBounds(100, 100, 243, 208);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][]", "[][][][]"));
		{
			JLabel lblSetLabelOf = new JLabel("Set Label of Emotions");
			contentPanel.add(lblSetLabelOf, "cell 0 0");
		}
		{
			
			contentPanel.add(rdbtnHappy, "cell 0 1");
		}
		{
			
			contentPanel.add(rdbtnFear, "cell 1 1");
		}
		{
			
			contentPanel.add(rdbtnSad, "cell 0 2");
		}
		{
			
			contentPanel.add(rdbtnSurprise, "cell 1 2");
		}
		{
			
			contentPanel.add(rdbtnAngry, "cell 0 3");
		}
		{
			
			contentPanel.add(rdbtnDisgust, "cell 1 3");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//labelList.add(e);
						if(rdbtnHappy.isSelected() == true){
							labelList.add("happy");
						}
						if(rdbtnSad.isSelected() == true){
							labelList.add("sad");
						}
						if(rdbtnAngry.isSelected() == true){
							labelList.add("angry");
						}
						if(rdbtnFear.isSelected() == true){
							labelList.add("fear");
						}
						if(rdbtnSurprise.isSelected() == true){
							labelList.add("surprise");
						}
						if(rdbtnDisgust.isSelected() == true){
							labelList.add("disgust");
						}

						
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
