//Michalis Roussos 3150148
//Christos Tasiopoulos 3150170
import java.lang.Comparable;
//class PrintJob
public class PrintJob implements Comparable<PrintJob> {
	static int numPJ = 0;
	int id;
	int size ;
	int waitingTime;
	int arrivalTime;
	int priority;
	
	//constructor
	public PrintJob(int size,int waitingTime,int arrivalTime,int priority){
		id=numPJ++;
		this.size=size;
		this.waitingTime=waitingTime;
		this.arrivalTime=arrivalTime;
		this.priority=priority;
	}
	//default constructor
	public PrintJob(){
		id=numPJ++;
		this.size=0;
		this.waitingTime=0;
		this.arrivalTime=0;
		this.priority=0;
	}
	//third constructor for setting some of the fields
	public PrintJob(int size, int arrivalTime){
		id=numPJ++;
		this.size=size;
		this.waitingTime=0;
		this.arrivalTime=arrivalTime;
		this.priority=0;
	}
	//getters and setters
	 public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	//method compareTo for comparing the priorities between two PrintJob objects
	public int compareTo(PrintJob pj){
		if (priority>pj.priority){return 1;}
		else if(priority==pj.priority){return 0;}
		else{return -1;}
		
	}
}
