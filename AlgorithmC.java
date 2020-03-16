//Michalis Roussos 3150148
//Christos Tasiopoulos 3150170
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class AlgorithmC {

	public static void runC(String data) {
		PrintJob.numPJ=0;
		StringQueueImpl<PrintJob> in = new StringQueueImpl<PrintJob>();
		if(read(data, in)){
			//the write method named sort is only called when the reading process has been successful
			sort(in,data);
		}
		
	}

	static boolean read(String data, StringQueueImpl<PrintJob> input) {
		PrintJob pj;
		int counter = 0;
		boolean correctformat = true;
		BufferedReader reader = null;
		String line = " ";
		StringTokenizer tok;
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
				tok = new StringTokenizer(line);
				//getting the time and size from the file
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
	static int sumofWaitingtimeC;//summary of the waiting times
	static int maxWtimeC;//time of the file that waited the most
	static int maxWfileC;//max waiting file
	static void sort(StringQueueImpl<PrintJob> queue,String data){
		MaxPQ pq = new MaxPQ(5);
		PrintJob pj;
		 sumofWaitingtimeC=0;
		 maxWtimeC=0;
		 maxWfileC=queue.peek().getId();//max waiting file
		 //initializing the T as the arrival time if the first element
		int T=queue.peek().getArrivalTime();
		//inserting element to the priority queue
		pq.insert(queue.get());
		//setting the priority
		pq.peek().setPriority(128-pq.peek().getSize());//priority equals 128 minus the file's size.Thus, we set the priority running according to file's size.
		T=pq.peek().getArrivalTime();
		File f = null;
		BufferedWriter writer = null;
		try	{
			f = new File("writeC"+data);
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
			while(!queue.isEmpty()){
				while(T>=queue.peek().getArrivalTime()){
					queue.peek().setPriority(128-queue.peek().getSize());
					pq.insert(queue.get());	
					if(queue.isEmpty()){break;}
				}
				for(int i=1; i<=pq.size(); i++){
					//setting the priority according to the minimum as instructed:priority + waitingTime-(waitingTime)mod15
					pq.heap[i].setPriority(getMin(127,pq.heap[i].getPriority()+T-pq.heap[i].getArrivalTime()-(T-pq.heap[i].getArrivalTime())%15));
					pq.swim(i);
				}
				if(!pq.isEmpty()){
					//getting the element with the maximum priority
					pj=pq.getMax();
					pj.setWaitingTime(T-pj.getArrivalTime());
					T+=pj.getSize();
					//calculating the summary of the waiting time and finding the max files
					sumofWaitingtimeC+=pj.getWaitingTime();
					if(maxWtimeC<pj.getWaitingTime()){
						maxWtimeC=pj.getWaitingTime();
						maxWfileC=pj.getId();
					}
					
					
					writer.write("t= "+T+", completed file "+pj.getId()+"\n");
				}
				if(queue.size()!=0 && pq.size()==0){
					if(queue.peek().getArrivalTime()>T){
						T=queue.peek().getArrivalTime();
					}
					
				}
			}
			while(!pq.isEmpty()){
				for(int i=1; i<=pq.size(); i++){
					pq.heap[i].setPriority(getMin(127,pq.heap[i].getPriority()+T-pq.heap[i].getArrivalTime()-(T-pq.heap[i].getArrivalTime())%15));
					pq.swim(i);
				}
				pj=pq.getMax();
				pj.setWaitingTime(T-pj.getArrivalTime());
				T+=pj.getSize();
				sumofWaitingtimeC+=pj.getWaitingTime();
				if(maxWtimeC<pj.getWaitingTime()){
					maxWtimeC=pj.getWaitingTime();
					maxWfileC=pj.getId();
				}
				writer.write("t= "+T+", completed file "+pj.getId()+"\n");
				
			}
			writer.write("Average waiting time= "+sumofWaitingtimeC/PrintJob.numPJ+"\nMaximum waiting time= "+ maxWtimeC+" achieved by file "+maxWfileC);
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
	//getting the minimum between two integers
	static int getMin(int a ,int b){
		return  a<b?a:b;
	}
}
