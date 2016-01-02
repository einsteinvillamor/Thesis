package prototypeFinal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;

public class WekaLauncher {

	private String filepath = "";//"C:/Users/Eihwaz/Desktop/ArffFile/test0.arff";
	private String filename;
	private List<String> acc = new ArrayList<>();
	private List<String> emotionList = new ArrayList<>();
	private String modelpath = ""; //"C:/Users/Adache/Documents/git/Thesis/PermanentFiles/Model/J48.model";//"C:/Users/Eihwaz/Downloads/KNN.model";
	private Classifier cls;
	Path currentRelativePath = Paths.get("");
//	public WekaLauncher() throws Exception{
//		cls =(Classifier) weka.core.SerializationHelper.read(modelpath.replaceAll("\\\\", "/"));
//	}
	
	public void LoadModel() throws Exception {
		cls = (Classifier) weka.core.SerializationHelper.read(modelpath.replaceAll("\\\\", "/"));
	}
	
	public void start() throws Exception {
		runWeka(cls);
	}
	
	private void runWeka(Classifier cls) throws Exception{
		filename = "result0.arff";
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		Instances test;
		test = new Instances (br);
		test.setClassIndex(test.numAttributes() -1);
		
		br.close();

		Evaluation etest = new Evaluation(test);
		etest.evaluateModel(cls, test);
		
		Instances labeled = new Instances(test);
		
		for (int i = 0 ; i < test.numInstances(); i++){
			double clsLabel = cls.classifyInstance(test.instance(i));
			labeled.instance(i).setClassValue(clsLabel);
		}
		filepath = currentRelativePath.toAbsolutePath().toString() +"/Files/FinalResult/"+filename;
		BufferedWriter bw = new BufferedWriter(new FileWriter(filepath));
		//"C:/Users/Eihwaz/Desktop/PredictedEmotion/"+filename));
		bw.write(labeled.toString());
		bw.close();
		
		emotionList = lastLineReader(filepath);
		System.out.println(labeled.toString());
		System.out.println(etest.toSummaryString());
		accReader(etest.toSummaryString());
		System.out.println(etest.toMatrixString());
		System.out.println(etest.toClassDetailsString());
	}
	
	public void accReader(String text){
		String[] lines = text.split("\n");
		for(String i: lines){
			if(text.contains("Incorrectly")){
				acc.add(i);
				//System.out.println(i);
			}
			else if(text.contains("Correctly")){
				acc.add(i);
				//System.out.println(i);
			}
		}
	}
	
	public List<String> lastLineReader(String path)
			throws Exception{
			boolean emo = true;
			List<String> predicted = new ArrayList<>();
			FileInputStream in=new FileInputStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = null, tmp;
			 
			while ((tmp = br.readLine()) != null)
			{
			   strLine = tmp;
			   //System.out.println(strLine);
			   if(emo == strLine.contains("happy")){
					predicted.add("emotion: happy");
				}
				else if(emo == strLine.contains("sad")){
					predicted.add("emotion: sad");
				}
				else if(emo == strLine.contains("angry")){
					predicted.add("emotion: angry");
				}
				else if(emo == strLine.contains("fear")){
					predicted.add("emotion: fear");
				}
				else if(emo == strLine.contains("surprise")){
					predicted.add("emotion: surprise");
				}
				else if(emo == strLine.contains("disgust")){
					predicted.add("emotion: disgust");
				}
			}
			in.close();
			
			return predicted;
			
		}
	
	public List<String> getEmotionList(){
		return emotionList;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getModelpath() {
		return modelpath;
	}

	public void setModelpath(String modelpath) {
		this.modelpath = modelpath;
	}

	public List<String> getAcc(){
		return acc;
	}
}
