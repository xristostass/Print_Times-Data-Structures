//Michalis Roussos 3150148
//Christos Tasiopoulos 3150170
import java.io.*;
import java.util.StringTokenizer;

public class AlgorithmB {
	//method runB
	public static void runB(String data) {
		PrintJob.numPJ=0;
		StringQueueImpl<PrintJob> in = new StringQueueImpl<PrintJob>();
		//the write method named sort is only called when the reading process has been successful
		if(read(data, in)){
			
			sort(in,data);
		}
		
	}
	//reading method for storing the file in the queue
	static boolean read(String data, StringQueueImpl<PrintJob> input) {
		PrintJob pj;
		int counter = 0;
		boolean correctformat = true;
		BufferedReader reader = null;
		String line = " ";
		StringTokenizer tok;
		//the time variable is used later on to check the time's range
		int time1 = -1;
		int MAXSIZE = 128;
		int time;
		int size;
		// try and catch block for successfully opening the file
		try {
			reader = new BufferedReader(new FileReader(data));

		} catch (FileNotFoundException e) {
			System.err.println("Error opening file!");
		}

		try {

			line = reader.readLine();
			while (line != null) {
				//getting time and size from the file
				tok = new StringTokenizer(line);
				time = Integer.parseInt(tok.nextToken());
				if (tok.hasMoreTokens()) {
					size = Integer.parseInt(tok.nextToken());
				} else {
					System.err.println("File's sturcture is not right! \n This line has only one element.");
					correctformat = false;
					break;
				}
				if (tok.hasMoreTokens()) {
					System.err.println("File's sturcture is not right! \n This line has more than two elements.");
					correctformat = false;
					break;
				}
				//checking the time and size restrictions and inserting elements to the queue
				if (time1 <= time && time >= 0 && size > 0 && size <= MAXSIZE) {
					pj = new PrintJob(size, time);
					input.put(pj);
				} else {
					correctformat = false;
					break;
				}
				
				line = reader.readLine();
				counter++;
				
			}

		} catch (IOException e) {
			System.out.println("Error reading line " + counter + ".");
		}
		if(PrintJob.numPJ==0){
			correctformat=false;
		}
		
		return correctformat;
		
	}
	public static int sumofWaitingtimeB;//summary of the waiting time of every file 
	static int maxWtimeB;//time of the file waiting the most
	static int maxWfileB;//max waiting file
	//method to write the files
	static void sort(StringQueueImpl<PrintJob> queue,String data){
		MaxPQ pq = new MaxPQ(5);
		PrintJob pj;
		sumofWaitingtimeB=0;
		maxWtimeB=0;
		maxWfileB=queue.peek().getId();//max waiting file
		//setting T equal to the arrival time of the first element
		int T=queue.peek().getArrivalTime();
		T=queue.peek().getArrivalTime();
		File f = null;
		BufferedWriter writer = null;
		try	{
			f = new File("writeB"+data);
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
			//checking for the queue not to be empty
			while(!queue.isEmpty()){
				//setting the priority and inserting an element to the priority queue
				while(T>=queue.peek().getArrivalTime()){
					queue.peek().setPriority(128-queue.peek().getSize());
					pq.insert(queue.get());	
					
					if(queue.size()==0){break;}
					
				}
				//checking whether the priority queue is empty
				if(!pq.isEmpty()){
					//getting the element with the biggest priority
					pj=pq.getMax();
					pj.setWaitingTime(T-pj.getArrivalTime());
					T+=pj.getSize();
					sumofWaitingtimeB+=pj.getWaitingTime();
					if(maxWtimeB<pj.getWaitingTime()){
						maxWtimeB=pj.getWaitingTime();
						maxWfileB=pj.getId();
					}
					
					writer.write("t= "+T+", completed file "+pj.getId()+"\n");
					
				}
				
				if(queue.size()!=0 && pq.size()==0){
					if(queue.peek().getArrivalTime()>T){
						//setting the T
						T=queue.peek().getArrivalTime();
					}
					
				}
			
			}
			
			while(!pq.isEmpty()){
				
				pj=pq.getMax();
				pj.setWaitingTime(T-pj.getArrivalTime());
				T+=pj.getSize();
				//calculating the sum and finding the max elements
				sumofWaitingtimeB+=pj.getWaitingTime();
				if(maxWtimeB<pj.getWaitingTime()){
					maxWtimeB=pj.getWaitingTime();
					maxWfileB=pj.getId();
				}
				writer.write("t= "+T+", completed file "+pj.getId()+"\n");
				
			}
			writer.write("Average waiting time= "+sumofWaitingtimeB/PrintJob.numPJ+"\nMaximum waiting time= "+ maxWtimeB+" achieved by file "+maxWfileB);
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