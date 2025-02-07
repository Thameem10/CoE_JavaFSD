package week2;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
public class WareHouse extends Thread{

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		InventoryManager inventoryManager = new InventoryManager();
		Product p1 = new Product("1","box",5,new Location(10,11,22));
		Product p2 = new Product("2","bag",4,new Location(10,34,31));
		Product p3 = new Product("3","chair",3,new Location(32,25,15));
		inventoryManager.addProduct(p1);
		inventoryManager.addProduct(p2);
		inventoryManager.addProduct(p3);
        List<String> productIDs1 = Arrays.asList("1", "2");
        List<String> productIDs2 = Arrays.asList("1");
        
        Order order1 = new Order("O1", productIDs1, Order.Priority.EXPEDITED);
        Order order2 = new Order("O2", productIDs2, Order.Priority.STANDARD);

        Runnable r1 = () -> inventoryManager.placeOrder(order1);
        Runnable r2 = () -> inventoryManager.placeOrder(order2);

        Runnable r3 = () -> inventoryManager.processOrders();
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		Thread t3 = new Thread(r3);
		t1.start();
		t1.join();
		t2.start();
		t2.join();
		t3.start();
		t3.join();
		 
	}

}
class Product
{
	 private String productID;
	 private String name;
	 private int quantity;
	 private Location location;
	 
	 public Product(String productID, String name, int quantity, Location location) 
	 {
		super();
		this.productID = productID;
		this.name = name;
		this.quantity = quantity;
		this.location = location;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	 	 
}
class Location
{
	private int aisle;
	private int shelf;
	private int bin;
	
	public Location(int aisle, int shelf, int bin) 
	{
		super();
		this.aisle = aisle;
		this.shelf = shelf;
		this.bin = bin;
	}

	public int getAisle() {
		return aisle;
	}

	public void setAisle(int aisle) {
		this.aisle = aisle;
	}

	public int getShelf() {
		return shelf;
	}

	public void setShelf(int shelf) {
		this.shelf = shelf;
	}

	public int getBin() {
		return bin;
	}

	public void setBin(int bin) {
		this.bin = bin;
	}
	
	
}
class InventoryManager
{
	 private Map<String, Product> products;
	    private BlockingQueue<Order> orderQueue;

	    public InventoryManager() {
	        products = new ConcurrentHashMap<>();
	        orderQueue = new PriorityBlockingQueue<>(10, new OrderComparator());
	    }

	    public synchronized void addProduct(Product product) {
	        products.put(product.getProductID(), product);
	        System.out.println("Added product: " + product.getName());
	    }

	    public synchronized void placeOrder(Order order) {
	        orderQueue.add(order);
	        System.out.println("Order placed: " + order.getOrderID());
	    }

	    public void processOrders() {
	        ExecutorService executor = Executors.newFixedThreadPool(3);
	        while (!orderQueue.isEmpty()) {
	            executor.execute(() -> {
	                try {
	                    Order order = orderQueue.poll();
	                    if (order != null) {
	                        fulfillOrder(order);
	                    }
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            });
	        }
	        executor.shutdown();
	    }
	    private synchronized void fulfillOrder(Order order) {
	         System.out.println("Processing order: " + order.getOrderID());
	            for (String productID : order.getProductIDs()) {
	                Product product = products.get(productID);
	                if (product != null && product.getQuantity() > 0) {
	                    product.setQuantity(product.getQuantity() - 1);
	                    System.out.println("Fulfilled: " + product.getName() + " | Remaining: " + product.getQuantity());
	                } else {
	                    System.out.println("Out of stock: " + (product != null ? product.getName() : "Unknown Product"));
	                }
	            }
	        }
		 
	 
}
class OrderComparator implements Comparator<Order> {
    public int compare(Order o1, Order o2) {
        return o1.getPriority().compareTo(o2.getPriority());
    }
}
class Order {
    private String orderID;
    private List<String> productIDs;
    private Priority priority;

    public enum Priority {
        STANDARD, EXPEDITED
    }

    public Order(String orderID, List<String> productIDs, Priority priority) {
        this.orderID = orderID;
        this.productIDs = productIDs;
        this.priority = priority;
    }

    public String getOrderID() {
        return orderID;
    }

    public List<String> getProductIDs() {
        return productIDs;
    }

    public Priority getPriority() {
        return priority;
    }
}
