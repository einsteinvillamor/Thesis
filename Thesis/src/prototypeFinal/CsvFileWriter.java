package prototypeFinal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CsvFileWriter {
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	
	private String filename;
	private List<String> emotionList;
	int c =0;
	private static final String FILE_HEADER = "hfd,katz,hurst,box,dfa";
	Path currentRelativePath = Paths.get("");
	private String path = currentRelativePath.toAbsolutePath().toString() + "/Files/Csv/";
			//"C:/Users/Eihwaz/Desktop/CSV/";
	//private static final String FILE_HEADER_NO_EMO = "hfd,katz,hurst,box,dfa";
	private double[] values;// = new double[34];
	
	
	public void emotionAdd() throws Exception{
		FileWriter fileWriter = null;
		fileWriter = new FileWriter(path + filename, true);
//		
//		for(String i: fileWriter.toString()){
//			
//		}
	}
	
	public void writeTextFile(List<String> list) throws IOException{
		BufferedWriter bw = new BufferedWriter(new FileWriter(currentRelativePath.toAbsolutePath().toString() + "/Files/FinalResult/finalres.txt"));
		for(String i:list){
			bw.write(i);
			bw.append(System.lineSeparator());
		}
		bw.close();
	}
	
	public void reader(String cpath) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(cpath));
		BufferedWriter bw = new BufferedWriter(new FileWriter(currentRelativePath.toAbsolutePath().toString() + "/Files/Arff/newemotest0.arff"));
		//"C:/Users/Eihwaz/Desktop/ArffFile/newemotest0.arff"));
		String strLine, tmp;
		int n = 0;
		int count = 0;
		while ((tmp = br.readLine()) != null){
			if(count< 10){
				System.out.println("hit!!!");
				bw.write(tmp);
				bw.append(NEW_LINE_SEPARATOR);
				count++;
			}
			if(tmp.contains("?")){
				System.out.println("hit ?");
				tmp = tmp.replace("?", emotionList.get(n));
				bw.write(tmp);
				bw.append(NEW_LINE_SEPARATOR);
				n++;
			}
		}
		
		br.close();
		bw.close();
	}
	
	public void writeCsvFile (){
		int count = 0;
		int emoIndex = 0;
		double[] vals = new double[values.length];
		filename = "test"+c+".csv";
//		for(int i = 0; i < 30; i++){
//			values[i] = i;
//		}
		File f = new File(path + filename);
		if(f.delete()){
			System.out.println("file deleted");
		}
		
		FileWriter fileWriter = null;
		
		try{
			fileWriter = new FileWriter(path + filename, true);
			
			fileWriter.append(FILE_HEADER);

			fileWriter.append(NEW_LINE_SEPARATOR);
			for(double i:values){
	        	if(count < 5){
	        	vals[count++] = i;
	        	fileWriter.append(String.valueOf(vals[count - 1]));
	        	if(count < 5){
	        		fileWriter.append(COMMA_DELIMITER);
	        	}
//	        	if(count == 5){
//	        		fileWriter.append(emotionList.get(emoIndex++));
//	        	}
	        	//System.out.println(vals[count - 1]);
	        	}else {
	        		
	        		fileWriter.append(NEW_LINE_SEPARATOR);
	        		count = 0;
	        		vals[count++] = i;
	        		fileWriter.append(String.valueOf(vals[count - 1]));
	        		fileWriter.append(COMMA_DELIMITER);
	        	}
	        }
			System.out.println("CSV file was created successfully !!!");
			//c++;
		} catch(Exception e){
			System.out.println("Error in CsvFileWriter !!!");

			e.printStackTrace();
		} 
		
		try {
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setValuesList(List<Double> valuesList){
    	this.values = new double[valuesList.size()];
    	for(int i =0; i < valuesList.size(); i++){
    		values[i] = valuesList.get(i);
    	}
    }
	
	public String getFilename(){
		return filename;
	}
	
	public String getPath(){
		return path;
	}

	public List<String> getEmotionList() {
		return emotionList;
	}

	public void setEmotionList(List<String> emotionList) {
		this.emotionList = emotionList;
	}

	

	public void writeFile(String path, String filename, List<String> text, List<String> text2) throws IOException{
		FileWriter fileWriter = null;
		
		fileWriter = new FileWriter(path+filename,true);
		System.out.println(text.size() + "size of text");
		System.out.println(text2.size() + "size of text2");
		for(int i = 0; i< text.size(); i++){
			fileWriter.append(text.get(i)+ text2.get(i));
			fileWriter.append(NEW_LINE_SEPARATOR);
			
		}
		
		fileWriter.flush();
		fileWriter.close();
	}
}
