package prototypeFinal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import prototypeFinal.App.guiState;

public class AudioRecorder {

	//You need to set the filepath to where to save the audio recorded
    //You need to set the isRunning to know how long it must run
    private String filePath;
    private boolean isRunning = false;
    private int fileInstance = 0;
    private List<String> filename = new ArrayList<>();
    final AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);

    public void record() 
    		throws LineUnavailableException, InterruptedException {
		Random rn = new Random();
    	int randNum = (rn.nextInt(5) + 1) * 1000;
    	DataLine.Info target = new DataLine.Info(TargetDataLine.class, audioFormat);
    	TargetDataLine targetLine = (TargetDataLine)AudioSystem.getLine(target);
    	System.out.println("start record: " + fileInstance + " " + randNum);
    	Thread.sleep(randNum);
        targetLine.open(audioFormat);
        targetLine.start();
        Thread thread = new Thread() {
            @Override
            public void run(){
                AudioInputStream audioStream = new AudioInputStream(targetLine);
                File audioFile = new File(filePath+"record"+fileInstance+".wav");
                filename.add("record"+fileInstance+".wav");
                try {
                    AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE,audioFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        if(App.guiStat == guiState.Record){
        	thread.start();
        	System.out.println("recording" + fileInstance);
        	Thread.sleep(4000);
        	System.out.println("done record" +fileInstance);
        	fileInstance++;
        }
        targetLine.stop();
        targetLine.close();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    public List<String> getFilename(){
    	return filename;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
