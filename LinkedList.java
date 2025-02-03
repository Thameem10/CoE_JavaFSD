package week1;

public class LinkedList {
	
	class node
	{
		int data;
		node next;
		node(int data)
		{
			this.data = data;
			this.next = null;
		}
	}
	public boolean hasCycle(node head)
	{
		if(head == null || head.next == null)
		{
			return false;
		}
		else
		{
			node fast = head;
			node slow = head;
			while(fast!=null && fast.next!=null )
			{
				slow = slow.next;
				fast = fast.next.next;
				if(slow==fast)
				{
					return true;
				}
			}
			
		}
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList l = new LinkedList();
		node n1 = l.new node(1);
		node n2 = l.new node(2);
		node n3 = l.new node(3);
		n1.next = n2;
		n2.next = n3;
		n3.next = n1;
		System.out.println(l.hasCycle(n1));
		
		
		

	}

}
