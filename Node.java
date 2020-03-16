//Michalis Roussos 3150148
//Christos Tasiopoulos 3150170
public class Node <T> {
	T data;
	Node<T> nextNode;
	
	//constructor
	Node(T data){
		this(data,null);
	}
	//constructor
	Node(T data, Node<T> nextNode){
		this.data=data;
		this.nextNode=nextNode;
		
	}
	//return method to return the node's data
	T getObject() {
		return data;
	}
	//return method to get the next Node
	Node<T> getNext(){
		return nextNode;
	}
	

}
