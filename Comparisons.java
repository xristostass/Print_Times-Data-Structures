//Michalis Roussos 3150148
//Christos Tasiopoulos 3150170
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;

public class Comparisons {

	public static void main(String args[]){
		//Creatinginputfiles.inputfiles();/*this method creates new files,if you want to create the new files just remove the comment lines and re-run the program*/
		int TsumofWaitingtimeB;//t stands for total
		int TmaxWtimeB;//total time for file that waited the most
		int TmaxWfileB;//total waiting file for algorithmB
		//same for algorithmC
		int TsumofWaitingtimeC;
		int TmaxWtimeC;
		int TmaxWfileC;
		
		for(int j=0;j<5;j++){//loop for the 5 values we need to create the files
			TsumofWaitingtimeB=0;//t stands for total
			TmaxWtimeB=0;
			TmaxWfileB=0;
			TsumofWaitingtimeC=0;
			TmaxWtimeC=0;
			TmaxWfileC=0;
			for(int k=0;k<10;k++){//loop for every file
				//calling the runB method
				AlgorithmB.runB("input"+j+"_"+k+".txt");
				TsumofWaitingtimeB+=AlgorithmB.sumofWaitingtimeB;
				TmaxWtimeB+=AlgorithmB.maxWtimeB;
				if(TmaxWtimeB<=AlgorithmB.maxWtimeB){
					TmaxWtimeB=AlgorithmB.maxWtimeB;
					TmaxWfileB=AlgorithmB.maxWfileB;
				}
				//calling the runC method
				AlgorithmC.runC("input"+j+"_"+k+".txt");
				TsumofWaitingtimeC+=AlgorithmC.sumofWaitingtimeC;
				TmaxWtimeC+=AlgorithmC.maxWtimeC;
				if(TmaxWtimeC<=AlgorithmC.maxWtimeC){
					TmaxWtimeC=AlgorithmC.maxWtimeC;
					TmaxWfileC=AlgorithmC.maxWfileC;
				}
				File f = null;
				BufferedWriter writer = null;
				try	{
					f = new File("Averages "+j+".txt");
				}
				catch (NullPointerException e) {
					System.err.println ("The file was not found.");
				}

				try	{
					writer = new BufferedWriter(new OutputStreamWriter
						(new FileOutputStream(f)));
				}
				catch (FileNotFoundException e) {
					System.err.println("Error opening file for writing!");
				}
				
				try {
					TsumofWaitingtimeB=TsumofWaitingtimeB/10;
					TsumofWaitingtimeC=TsumofWaitingtimeC/10;
					TmaxWtimeB=TmaxWtimeB/10;
					TmaxWtimeC=TmaxWtimeC/10;
						writer.write("AlgorithmB: \n"+"Average Waiting Time: "+ TsumofWaitingtimeB+"\nAverage Of Max Waiting Time: "+TmaxWtimeB+/*"\nMax Waiting File: "+TmaxWfileB+*/
								"\nAlgorithmC: \n"+"Average Waiting Time:"+TsumofWaitingtimeC+"\nAverage Of Max Waiting Time: "+TmaxWtimeC/*+"\nMax Waiting File: "+TmaxWfileC*/);
						writer.flush();
					}
					
					
				catch (IOException e) {
					System.err.println("Write error!");
				}
					
					
				try{
					writer.close(); //Closes the stream, flushing it first.

				}
				catch (IOException e) {
					System.err.println("Error closing file.");
				}
			}
		}
	}
}
