package prototypeFinal;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.JFileChooser;

public class MyDialog {

	private File NameDir;
	private File NamePath;
	private JFileChooser fc;
	
	public void openDialogBox(){
		fc = new JFileChooser();
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		File f = new File(s.replaceAll("\\\\", "/"));
		
		int Checker;
		fc.setCurrentDirectory(f);
		Checker = fc.showOpenDialog(null);
		if(Checker == JFileChooser.APPROVE_OPTION){
			NameDir = fc.getCurrentDirectory();
			NamePath = fc.getSelectedFile();
		}
	}
	
	@SuppressWarnings("restriction")
	public void playAudioFile(){
		try{
			File fl = fc.getSelectedFile();
			String s = fl.getAbsolutePath();
			InputStream is = new FileInputStream(s);
			AudioStream au = new AudioStream(is);
			AudioPlayer.player.start(au);
			System.out.println(s);
		} 
		catch (Exception e){
			
		}
	}
	
	public File getNameDir(){
		return NameDir;
	}
	
	public File getNamePath(){
		return NamePath;
	}
}
