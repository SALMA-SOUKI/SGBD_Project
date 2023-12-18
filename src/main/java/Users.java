import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Users {
	public static void main(String[] args) {
		
	/*Map<String,List<String>> user_data = new HashMap<String,List<String>>();
	List<String> tables = new ArrayList<String>();
	List<List<String>> authentification = new ArrayList<List<String>>();
	Map<String,List<String>> table_data = new HashMap<String,List<String>>();

		
		 //Creating a File object for directory
	      File directoryPath = new File("C:\\\\nouhaila");
	      //List of all files and directories
	      String contents[] = directoryPath.list();
	      for(int i=0; i<contents.length; i++) {
	    	  if(!contents[i].endsWith(".txt")) {
	    	  File directoryPath2 = new File("C:\\nouhaila\\"+contents[i]);
	    	  for(String table:directoryPath2.list()) {
	        		tables.add(table);
	        		
		    		File file = new File("C:\\nouhaila\\"+ contents[i] + "\\" + table);
		    		
		    		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	    		        String line = reader.readLine();
	    		        List<String> data  =  new ArrayList<String>();
	    		        while (line != null) {
	    		        	data.add(line);
	    		            line = reader.readLine();
	    	
	    		        }
	    		        
	    		        table_data.put(table, data);
	    		        System.out.println(table_data);
	    		    
	    			} catch (IOException e) {
	    			    // Handle any errors that occurred while reading the file
	    			    e.printStackTrace();
	    			}
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	          }
	    	  user_data.put(contents[i],tables);
	    	  System.out.println(user_data);
	    	  }
	    	  

	    	  if(contents[i].equals("nouhaila.txt")) {
	    		  File file = new File("C:\\nouhaila\\"+ contents[i]);
	    		  
	    		  try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	    		        String line = reader.readLine();
	    		        List<String> data ;
	    		        while (line != null) {
    		        		data = new ArrayList<String>();

	    		        	for(String auth:line.split(":")) {
	    		        		data.add(auth);
	    		        	}
	    		        	authentification.add(data);
	    		            line = reader.readLine();
	    	
	    		        }
	    		        
	    		        System.out.println(authentification);

	    		    
	    			} catch (IOException e) {
	    			    // Handle any errors that occurred while reading the file
	    			    e.printStackTrace();
	    			}
	    		  
	    		  
	    	  }
	      }
	      */
		
		
		Data dt = new Data("nouhaila");
		
		System.out.println(dt.user_data("nouhaila"));
		



	}

}
