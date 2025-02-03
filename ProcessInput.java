package week1;
import java.util.*;

public class ProcessInput {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
		int input1,result;
		Scanner s = new Scanner(System.in);
		System.out.println("enter value");
		input1 = s.nextInt();
		result = 1/input1;
		System.out.println(result);
		}
		catch(ArithmeticException e)
		{
			System.out.println(e.getMessage());
		}

	}

}
