package week1;
import java.util.*;
public class StringProcessor 
{
	public void ReverseString(String str)
	{
		String result = "";
		for(int i = str.length()-1;i>=0;i--)
		{
			result = result + str.charAt(i);
		}
		System.out.println(result);
	}
	public void CountOccurences(String txt,String sub)
	{
		String result;
		int count = 0;
		for(int i=0;i<txt.length()-sub.length();i++)
		{
			result = txt.substring(i,i+sub.length());
			if(result.equals(sub))
			{
				count++;
			}
		}
		System.out.println(count);
	}
	public void SplitAndCapitalize(String str)
	{
		String[] result;
		result = str.split(" ");
		for(String i : result)
		{
			System.out.println(i.toUpperCase());
		}
		
		
	}
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		StringProcessor sp = new StringProcessor();
		sp.ReverseString("Hello");
		sp.SplitAndCapitalize("hello world");
		sp.CountOccurences("helloxyxhellox", "hello");

	}

}
