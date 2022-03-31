package app;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class fileIO 
{
		String fileName = null;
		
		public fileIO(String fn)
		{
			fileName = fn;
		}
		
		public void wrSignUpData(String dataList[]) throws Exception
		{
	        FileWriter fwg = null;
	        try 
	        {
	        	// open the file in append write mode
	        	fwg = new FileWriter(fileName, true);
	        }
	        catch (IOException e)
	        {
	        	System.out.println("[Error] - " + e.getMessage());
	        }
	   	    
	        BufferedWriter bwg = new BufferedWriter(fwg);
	        PrintWriter outg   = new PrintWriter(bwg);
			
	        String timeStamp = new SimpleDateFormat("MM-dd-yyyy HH.mm.ss").format(new Date());
	        outg.println(timeStamp + " : ");
	        
	        for (int i = 0; i <= dataList.length; i++) {
	        	outg.println(dataList);
	        }
	       

	        for (int i = 0; i < dataList.length; i++) {
	        	
	        	fwg.write(dataList[i] + " , ");
	        }
	        
	        	        outg.close();
		}
		
		public void readUserPass(String username, String password)
				throws IOException 
				{    
				    BufferedReader reader = new BufferedReader(new FileReader(fileName));
				    String currentLine = reader.readLine();
				    // Add for loop with if statements seeing if username = current line and password = current line
				    reader.close();
				}
}