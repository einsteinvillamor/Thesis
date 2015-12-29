package prototypeFinal;

import java.util.ArrayList;
import java.util.List;

import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import matlabcontrol.MatlabProxy;
import matlabcontrol.MatlabProxyFactory;
import matlabcontrol.MatlabProxyFactoryOptions;

public class MatlabLauncher {
	private MatlabProxy proxy;
	private String path;
	
	//private List<Double> result = new ArrayList<>();
	private double result;
	private List<Double> resList = new ArrayList<>();
    //private boolean isRunning;

    public MatlabLauncher(boolean isRunning)
            throws MatlabInvocationException, MatlabConnectionException {
        if(isRunning == false){
            start();
            //runningState = true;
        }
    }

    private void start()
            throws MatlabConnectionException, MatlabInvocationException{
        MatlabProxyFactoryOptions options = new MatlabProxyFactoryOptions.Builder()
                .setUsePreviouslyControlledSession(true)
                .build();
        MatlabProxyFactory factory = new MatlabProxyFactory(options);
        proxy = factory.getProxy();
        proxy.eval("addpath(genpath('C:/Users/Adache/Documents/git/Thesis/PermanentFiles/MatlabCodes/'))");
    }
    
    public void runMatlab() 
			throws MatlabConnectionException, MatlabInvocationException { // multiple acess
		fftMatlab(proxy, path); // proxy, pathList.get(0)
		hfd(proxy);
		katz(proxy);
		genhurst(proxy);
		boxcount(proxy);
		dfa(proxy);
		
	}

    private void fftMatlab(MatlabProxy proxy, String path)
            throws MatlabConnectionException, MatlabInvocationException {
        proxy.eval("filename = sprintf('"+path.replaceAll("\\\\", "/")+"');");
        proxy.eval("audiofile = wavread(filename);");
        proxy.eval("myfft = fft(audiofile);");
        Object res = proxy.getVariable("myfft");
    }
    
    private void hfd(MatlabProxy proxy)
            throws MatlabConnectionException, MatlabInvocationException {
        proxy.eval("higuchi = hfd(myfft,5)");
        result = ((double[]) proxy.getVariable("higuchi"))[0];
        resList.add(result);
        //result.add(((Double[]) proxy.getVariable("higuchi"))[0]);
    }

    private void katz(MatlabProxy proxy)
            throws MatlabConnectionException, MatlabInvocationException {
        proxy.eval("katz = myfft(:,1);");
        proxy.eval("katz = KatzFD(katz,1)");
        result = ((double[]) proxy.getVariable("katz"))[0];
        resList.add(result);
        //result.add(((Double[]) proxy.getVariable("katz"))[0]);
    }
    
    private void genhurst(MatlabProxy proxy)
            throws MatlabConnectionException, MatlabInvocationException	{
        proxy.eval("hurst = myfft(:,1);");
        proxy.eval("hurst = genhurst(hurst,1)");
        result = ((double[]) proxy.getVariable("hurst"))[0];
        resList.add(result);
    }

    private void boxcount(MatlabProxy proxy)
            throws MatlabConnectionException, MatlabInvocationException {
        proxy.eval("boxcount = boxdim_classique(myfft)");
        result = ((double[]) proxy.getVariable("boxcount"))[0];
        resList.add(result);
        //result.add(((Double[]) proxy.getVariable("boxcount"))[0]);
    }

    private void dfa(MatlabProxy proxy)
            throws MatlabConnectionException, MatlabInvocationException {
        proxy.eval("audiovalue = audiofile(:,1);");
        proxy.eval("dfa = fastdfa(audiovalue)");
        result = ((double[]) proxy.getVariable("dfa"))[0];
        resList.add(result);
        //result.add(((Double[]) proxy.getVariable("dfa"))[0]);
    }
    
//    public double[] getResult(){
//    	return result;
//    }
//    
//    public void setResultList(double[] result){
//    	this.result = result;
//    }
    
    public List<Double> getResultList(){
    	return resList;
    }
    
    public void setPath(String path){
    	this.path = path;
    }
}
