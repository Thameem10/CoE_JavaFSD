package week1;

public class BankAccount extends Thread{
	int amount,bal;
	BankAccount(int bal)
	{
		this.bal = bal;
	}
	public void deposit(int amount)
	{
		bal = bal + amount;
		System.out.println("deposit :"+amount);
		System.out.println("balance :"+bal);
		
	}
	public void withdraw(int amount)
	{
		if(bal!=0)
		{
			bal = bal -amount;
			System.out.println("withdraw :"+amount);
			System.out.println("balance :"+bal);
		}
		else
		{
			System.out.println("no balance");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BankAccount ba = new BankAccount(10000);
		Thread user1 = new Thread(()->
		{
			ba.deposit(10000);
			ba.withdraw(20000);
		},"user1");
		Thread user2 = new Thread(()->
		{
			ba.deposit(10000);
			ba.withdraw(20000);
		},"user2");
		Thread user3 = new Thread(()->
		{
			ba.deposit(10000);
			ba.withdraw(20000);
		},"user3");
		
		user1.start();
        user2.start();
        user3.start();
							

	}

}
