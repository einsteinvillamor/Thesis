package prototypeFinal;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

public class CsvToArff {

	  private String completePathwName;
	  Path currentRelativePath = Paths.get("");
	  public void csvToArff(String filename, String path) throws IOException{
		// load CSV
		    CSVLoader loader = new CSVLoader();
		    loader.setSource(new File(path+filename));
		    Instances data = loader.getDataSet();
		    completePathwName = currentRelativePath.toAbsolutePath().toString() +"/Files/Arff/test0.arff";
		    // save ARFF
		    ArffSaver saver = new ArffSaver();
		    saver.setInstances(data);
		    saver.setFile(new File(completePathwName));
		    //"C:/Users/Eihwaz/Desktop/ArffFile/test0.arff"));
		    
		    System.out.println("DONE successfully saved ARFF");
		    //saver.setDestination(new File("C:/Users/Eihwaz/Desktop/Training-Set.arff"));
		    saver.writeBatch();
	  }
	  
	  public String getPathwName(){
		  return completePathwName;
	  }
}
