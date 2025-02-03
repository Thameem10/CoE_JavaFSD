package week1;

import java.util.*;

public class Anagrams 
{
		  public static List<Integer> findAnagrams(String s, String p) 
		  {
		        List<Integer> result = new ArrayList<>();
		        if (s.length() < p.length()) 
		        	return result; 
		        int[] pFreq = new int[26]; 
		        int[] sFreq = new int[26]; 
		        
		        for (int i = 0; i < p.length(); i++) 
		        {
		            pFreq[p.charAt(i) - 'a']++;
		            sFreq[s.charAt(i) - 'a']++;
		        }
		        System.out.println(Arrays.toString(pFreq));
		        System.out.println(Arrays.toString(sFreq));

		       
		        if (Arrays.equals(pFreq, sFreq)) 
		        {
		            result.add(0);
		        }

		       
		        for (int i = p.length(); i < s.length(); i++) 
		        {
		            
		            sFreq[s.charAt(i) - 'a']++;
		            
	
		            sFreq[s.charAt(i - p.length()) - 'a']--;

	
		            if (Arrays.equals(pFreq, sFreq)) 
		            {
		                result.add(i - p.length() + 1);
		            }
		        }

		        return result;
		    }

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		String s = "cbaebabacd";
        String p = "abc";
        List<Integer> anagramIndices = findAnagrams(s, p);
        System.out.println("Anagram Start Indices: " + anagramIndices);
		

	}

}
