package week1;
import java.util.*;
public class TaskManager {
	
	 	private PriorityQueue<AddTask> pq;
		TaskManager()
		{
			pq = new PriorityQueue<>(Comparator.comparingInt(id ->id.priority));
		}
	
		class AddTask
		{
		String id,description;
		int priority;
		AddTask(String id,String description,int priority)
		{
			this.id = id;
			this.description = description;
			this.priority = priority;
		}
		 @Override
	      public String toString() 
		  {
	            return "Task ID: " + id + ", Description: " + description + ", Priority: " + priority;
	      }
	    
		}
		public void addTask(String id, String description, int priority) 
		{
	        pq.add(new AddTask(id, description, priority));
	    }
		
		public void getHighestPriorityTask()
		{
			System.out.println("Highest Priority Task: " +pq.poll() );
		}
		public void removeTask(String id) 
		{
	        for (AddTask task : pq) 
	        {
	            if (task.id.equals(id)) 
	            {
	                pq.remove(task);
	                break;
	            }
	        }
            System.out.println("Task with ID " + id + " removed.");

	    }
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		TaskManager tm = new TaskManager();
		tm.addTask("1", "Task1", 2);
        tm.addTask("2", "Task2", 3);
        tm.addTask("3", "Task3", 1);
		tm.getHighestPriorityTask();
		tm.removeTask("1");
		
		

	}

}
