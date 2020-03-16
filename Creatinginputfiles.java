//Michalis Roussos 3150148
//Christos Tasiopoulos 3150170
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.lang.Math;
	
public class Creatinginputfiles {

		public static void inputfiles (){
			//creating a Random object
			Random generator = new Random();
			//array for storing the random numbers
			int [] sizeofFiles= new int [5];
			int i=0;
			boolean flag;
			//getting the random numbers
			int num=generator.nextInt(49901);
			//storing the random numbers  to the array
			sizeofFiles[i++]=num;
			while(i<5){
				do{
					flag=true;
					//generating random numbers from 0 to 49001.after we are adding 100 to reach the boundaries from 100 to 50.000.
					num=generator.nextInt(49901);
					for(int j=0; j<=i-1;  j++){
						if(Math.abs(sizeofFiles[j]-num)<5000 ){
							flag=false;	
						}
					}
				}while(!flag);
				sizeofFiles[i++]=num;
			}
			File f = null;
			BufferedWriter writer = null;
			
			
			int t1=0,t2=0,s;//t1 stands for the time of the current file,t2 stands for the time of the previous one and s stands for the size.
			
			
			try {
				for(int j=0;j<5;j++){
					//adding 100 so as to reach the boundaries 100 to 50.000 
					sizeofFiles[j]+=100;
					for(int k=0;k<10;k++){
						try	{
							f = new File("input"+j+"_"+k+".txt");
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
						for(int n=0;n<sizeofFiles[j];n++){
							t1=generator.nextInt(129)+t2;//129 here is to restrict the difference between the files' arrivals
							s=generator.nextInt(128)+1;//getting the next size
							writer.write(t1+"\t"+s+"\n");
							t2=t1;
						}
						writer.flush();//flushes the writer
						t1=0;
						t2=0;
//						writer.write("\n");
					}
				}
			}catch (IOException e) {
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

