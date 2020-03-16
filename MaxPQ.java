//Michalis Roussos 3150148
//Christos Tasiopoulos 3150170
public class MaxPQ {

	// Array based heap representation

	public PrintJob[] heap;

	// The number of objects in the heap

	private int size;
	//constructor
	public MaxPQ(int capacity) {
		//checking the capacity
		if (capacity < 1)
			throw new IllegalArgumentException();
		this.heap = new PrintJob[capacity + 1];
		this.size = 0;

	}
	//getting the size
	public int size() {
		return size;
	}
	//checking whether the priority queue is empty or not
	public boolean isEmpty() {
		return size == 0;
	}
	//double the heap's size 
	public void resize() {
		PrintJob[] newheap = new PrintJob[(heap.length - 1) * 2];
		for (int i = 1; i <= size(); i++) { 
			newheap[i] = heap[i];
		}
		heap = newheap;
	}
	//swapping two elements
	public void swap(int i, int j) {
		PrintJob tmp = heap[i];
		heap[i] = heap[j];
		heap[j] = tmp;
	}
	//checks for the parents and the child's priority and changing their positions
	public void swim(int i) {
        while (i > 1) {  //if i root (i==1) return
            int p = i/2;  //find parent
            int result =heap[i].compareTo(heap[p]);  //compare parent with child
            if (result <= 0) return;    //if child <= parent return
            swap(i, p);                 //else swap and i=p
            i = p;
        }
    }
	//shifting down
	public void sink(int i){
        int left = 2*i, right = left+1, max = left;
        // If 2*i >= size, node i is a leaf
        while (left <= size) {
            // Determine the largest children of node i
            if (right <= size) {
                max = heap[left].compareTo(heap[right]) < 0 ? right : left;
            }
            // If the heap condition holds, stop. Else swap and go on.
            if (heap[i].compareTo(heap[max]) >= 0) return;
            swap(i, max);
            i = max; left = 2*i; right = left+1; max = left;
        }
    }
	//insert a PrintJob object to the priority queue
	public void insert(PrintJob object) {
        // Ensure object is not null
        if (object == null) throw new IllegalArgumentException();
        // Check available space
        if (size > heap.length*75/100) resize();
        // Place object at the next available position
        heap[++size] = object;
        // Let the newly added object swim
        swim(size);
    }
	//gets and removes the element with the max priority
	 public PrintJob getMax() {
	        // Ensure not empty
	        if (size == 0) throw new IllegalStateException();
	        // Keep a reference to the root object
	        PrintJob object = heap[1];
	        // Replace root object with the one at rightmost leaf
	        if (size > 1) heap[1] = heap[size];
	        // Dispose the rightmost leaf
	        heap[size--] = null;
	        // Sink the new root element
	        sink(1);
	        // Return the object removed
	        return object;
	    }
	 //gets an element without removing it from the queue
	 public PrintJob peek() {
			return heap[1];
		}
}
