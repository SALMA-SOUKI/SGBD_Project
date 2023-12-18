package dao;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminSelection {

	List<String> tables = new ArrayList<String>();
	
	
	public static ArrayList<String> AllUsers() {
		 File directoryPath = new File("C:\\SGBD\\\\");
		 ArrayList<String> contents= new  ArrayList<String>();
		for (String string : directoryPath.list()) {
			contents.add	(string);
		}		 
		 
		 return contents;
	}
	
	
	public static Map<String,List<String>> userDataBase(String login){
		Map<String,List<String>> user_data ;

		List<String> tables = new ArrayList<String>();
		
		  user_data = new HashMap<String,List<String>>();
	      File directoryPath = new File("C:\\SGBD\\\\"+login);
	      String contents[] = directoryPath.list();
	      for(int i=0; i<contents.length; i++) {
	    	  if(!contents[i].endsWith(".txt")) {
	    	  File directoryPath2 = new File("C:\\SGBD\\\\"+login+"\\"+contents[i]);
	    	  
	    	  for(String table:directoryPath2.list()) {
	        		tables.add(table);
	          }
	    	  user_data.put(contents[i],tables);
	    	  }
	    	  
	    	  tables = new ArrayList<String>();

	    	 
	      }
	      
	      return user_data;
		
	}
	
	
	public static Map<String,Map<String,List<String>>> AllUsersDataBases()
	
	{	
		
		
		Map<String,Map<String,List<String>>> l = new HashMap<String,Map<String,List<String>>>();
		
	
	   ArrayList<String> users =AllUsers() ;
		for (String us : users) {
			
			
			l.put(us,userDataBase(us) );
			
			
		}
		
		return l;
		
	}
	
	
	/*public static void main(String[] args) {
		
		Map<String, Map<String, List<String>>> outerMap = AllUsersDataBases();
		for (Map.Entry<String, Map<String, List<String>>> outerEntry : outerMap.entrySet()) {
			String outerKey = outerEntry.getKey();
			Map<String, List<String>> innerMap = outerEntry.getValue();
			for (Map.Entry<String, List<String>> innerEntry : innerMap.entrySet()) {
				String innerKey = innerEntry.getKey();
				List<String> innerValue = innerEntry.getValue();
				System.out.println("user " + outerKey + " its databases : " + innerKey);
			}
		}
		
	}*/

	
	
	

}