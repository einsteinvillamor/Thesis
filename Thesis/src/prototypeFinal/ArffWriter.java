package prototypeFinal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Add;

public class ArffWriter {
	 //You need to set the list of names of attributes
    private List<String> attList = new ArrayList<>();
    private List<String> nominalLabelList = new ArrayList<>();
    private double[] values;
    private String filename;
    private String filepath;
    int c =0;
    Path currentRelativePath = Paths.get("");
    public void writeArff() throws Exception{
        FastVector attributes = new FastVector();
        Instances data;
        double[] vals;
        int count = 0;
        double inst = 1.0;
        
        filename = "test"+c+".arff";
        for(String i:attList)
            attributes.addElement(new Attribute(i));
        data = new Instances("Test data", attributes, 0);
        vals = new double[data.numAttributes()];

        
        //Instances labeled = new Instances();
        for(double i:values){
        	if(count < 5){
        	vals[count++] = i;
        	//System.out.println(vals[count - 1]);
        	}else {
        		data.add(new Instance(inst++, vals));
        		count = 0;
        		vals[count++] = i;
        	}
        }


        //data.add(new Instance(1.0, vals));
        count = 0;
        
        writeArffFile(data, filename);
        //addAttributeArff();
    }
    
    public void writeArffFile(Instances data, String filename) throws Exception { //Instances data, String path to create

		ArffSaver arffSaverInstance = new ArffSaver();
		arffSaverInstance.setInstances(data);
		arffSaverInstance.setFile(new File(currentRelativePath.toAbsolutePath().toString() +"/Files/Arff/" +filename+""));
		//"C:/Users/Eihwaz/Desktop/ArffFile/"+filename+""));
		arffSaverInstance.writeBatch();
		filepath = currentRelativePath.toAbsolutePath().toString() + "/Files/Arff/"+filename+"" ;
		//"C:/Users/Eihwaz/Desktop/ArffFile/"+filename+"";
	}
    
    public void addAttributeArff(String cfilename) throws Exception { //String path to load
    	filename = "newtest0.arff";
		Instances newData = new Instances(new BufferedReader(new FileReader(cfilename)));
		String nominalLabel = "";
		for(String i: nominalLabelList){
			nominalLabel +=  i+",";
		}
		nominalLabel = nominalLabel.substring(0, nominalLabel.length() -1);
		
		Add filter = new Add();
		filter.setAttributeIndex("last");
		filter.setNominalLabels(nominalLabel);
		filter.setAttributeName("emotion");
		filter.setInputFormat(newData);
		newData = Filter.useFilter(newData, filter);
		writeArffFile(newData, filename);
		
	}
    
    public void setNominalLabelList(List<String> list){
    	nominalLabelList = list;
    }

    public List<String> getAttList() {
        return attList;
    }

    public void setAttList(List<String> attList) {
        this.attList = attList;
    }

    public void setAttList(String att){
        this.attList.add(att);
    }
    
    public void setValuesList(double[] values){
    	this.values = values;
    }
    
    public void setValuesList(List<Double> valuesList){
    	this.values = new double[valuesList.size()];
    	for(int i =0; i < valuesList.size(); i++){
    		values[i] = valuesList.get(i);
    	}
    }

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
}
