package week1;

import java.io.*;
import java.util.*;

public class LogFileAnalyzer 
{
	    
private static final List<String> KEYWORDS = Arrays.asList("ERROR", "WARNING"); 
	    
	public void analyzeLogFile(String inputFile, String outputFile) 
	{
	        Map<String, Integer> keywordCount = new HashMap<>();
	        
	       	for (String keyword : KEYWORDS) 
	       	{
	            keywordCount.put(keyword, 0);
	        }
	        
	        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
	             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) 
	        {
	            
	            String line;
	            while ((line = reader.readLine()) != null) 
	            {
	                for (String keyword : KEYWORDS) 
	                {
	                    if (line.contains(keyword)) 
	                    {
	                        keywordCount.put(keyword, keywordCount.get(keyword) + 1);
	                        writer.write(line);
	                        writer.newLine();
	                        break; 
	                    }
	                }
	            }
	            
	           
	            writer.write("\nSummary:\n");
	            for (Map.Entry<String, Integer> entry : keywordCount.entrySet()) 
	            {
	                writer.write(entry.getKey() + ": " + entry.getValue());
	                writer.newLine();
	            }
	            
	            System.out.println("Log analysis complete. Results saved to " + outputFile);
	            
	        } 
	        catch (IOException e) 
	        {
	            System.err.println("Error processing the log file: " + e.getMessage());
	        }
	    }
	    
	    public static void main(String[] args) 
	    {
	        if (args.length < 2) 
	        {
	            System.out.println("Usage: java LogFileAnalyzer <inputFile> <outputFile>");
	            return;
	        }
	        
	        LogFileAnalyzer analyzer = new LogFileAnalyzer();
	        analyzer.analyzeLogFile(args[0], args[1]);
	    }
	}


