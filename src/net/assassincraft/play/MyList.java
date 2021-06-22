package net.assassincraft.play;

import org.bukkit.Sound;

public class MyList {
	
	class Node{
		Sound data;
		Node next;
		public Node(Sound data) {
			this.data = data;
			this.next = null;
		}
	}
	
	Node head;
	int counter;
	
	
	public void add(Sound data) {
		if(head == null) {
			head = new Node(data);
			counter = 1;
			return;
		}
		
		Node newNode = new Node(data);
		newNode.next = head;
		head = newNode;
		counter++;
		
		
	}
	
	public int size() {
		return counter;
	}
	
	public Sound get(int num) {
		if(num-1>counter) {
			throw new ArrayIndexOutOfBoundsException();
		}
		int temp = 0;
		Node current = head;
		while(!(temp==num)) {
			current = current.next;
			temp++;
		}
		
		return current.data;
	}
	

}
