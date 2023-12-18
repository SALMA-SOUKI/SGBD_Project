package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
	/*Datav3 db = new Datav3("nouhaila");
		//System.out.println(db.authentification("nouhaila"));
		List<List<String>> auth = new ArrayList<List<String>>();
		auth = 	db.authentification("nouhaila");
		Map<String,List<String>> user_data = new HashMap<String,List<String>>();
		user_data = db.user_data("nouhaila");
		System.out.println(user_data);

		boolean test = true;
		int indice=-1;
		List<Integer> index = new ArrayList<Integer>();
		Map<String,List<String>> droits = new HashMap<String,List<String>>();
		List<String> dr = new ArrayList<String>();
		for(Entry<String, List<String>> mp : user_data.entrySet()) {
			test = true;

			indice = indice+1;
			for(int i=1;i<auth.size();i++) {
				for(int j=2;j<auth.get(i).size();j++) {
					if(auth.get(i).get(j).split(" ")[0].equals(mp.getKey())) {
					   test = false;
						index.add(indice);
						dr = new ArrayList<String>();
						String[] dr_s = auth.get(i).get(j).split(" ")[1].split("/");
						dr_s[0] = dr_s[0].replace("(", "");
						dr_s[dr_s.length-1] = dr_s[dr_s.length-1].replace(")", "");

						for(String s:dr_s) {
							dr.add(s);
						}
						droits.put(mp.getKey(), dr);

					}
				}
			}	
			
			
			if(test) {
				//mp remove
				user_data.remove(mp.getKey());

			}

		}
		
		System.out.println(droits);
		List<Map<String,List<String>>> tables_data_old = new ArrayList<Map<String,List<String>>>();
		List<Map<String,List<String>>> tables_data = new ArrayList<Map<String,List<String>>>();
		tables_data_old = db.tables_data("nouhaila");
		
		
		for(int i=0;i<index.size();i++) {
			tables_data.add(tables_data_old.get(index.get(i)));
		}
		
		
		List<Map<String,List<String>>> tables_metadata_old = new ArrayList<Map<String,List<String>>>();
		List<Map<String,List<String>>> tables_metadata = new ArrayList<Map<String,List<String>>>();
		tables_metadata_old = db.tables_metadata("nouhaila");

		for(int i=0;i<index.size();i++) {
			tables_metadata.add(tables_metadata_old.get(index.get(i)));
		}
		
		//System.out.println(index);
		//System.out.println(tables_metadata);

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//List<Map<String,List<String>>> tables_data = new ArrayList<Map<String,List<String>>>();
		
		
		
		//tables_data = db.tables_data("nouhaila");
		//System.out.println(tables_data.indexOf(tables_data.get(1)));
	//	System.out.println(db.tables_metadata("nouhaila"));
		
		*/
		
		String Username = "nouhaila";
		String DataName = "Simulator";
		String login = "sara";
		String privileges = "get/delete";

		 File directoryPath = new File("C:\\SGBD\\");
	     String contents[] = directoryPath.list();
	     boolean exist = true;
	     String result = null ;
	     boolean existInfile = true;
	     ArrayList<String> records = new ArrayList<>();
	     String  target = null;
		 String line ;

		String word_target = ":" + Username+":"+DataName+" "+"("+privileges+")";

	     
	     for(String s : contents) {
	    	 if(Username.equals(s)){
	    		 exist = false;

		    		File file = new File("C:\\SGBD\\"+login+"\\"+login+".txt");
		    		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
		    		        
						while ((line = reader.readLine()) != null) {

		    		        	if(line.split(":")[0].equals(Username)) {
		    		        		existInfile = false;
		    		        		target = line.toString();
		    		        		

		    		        
		    		        	}else {
		                    		records.add(line);
		    		            	System.out.println(records);

		    		        	}
		    	
		    		        }
		    		        

		    		        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\SGBD\\"+login+"\\"+login+".txt"))) {
		    		            for (String record : records) {
		    		            	System.out.println(record);
		    		                String lineToWrite = record;
		    		                writer.write(lineToWrite);
		    		                writer.newLine();
		    		            }
	    		                String lineToWrite = target +""+word_target;

		    		            writer.write(lineToWrite);
	    		                writer.newLine();
		    		        } catch (IOException e) {
		    		            e.printStackTrace();
		    		        }
		    		}catch (IOException e) {
	    			    e.printStackTrace();
	    			}
	        		


	    		 
	    		 
	    		 
	    		 
	    		 
	    		 
	    		 if(existInfile) {
	    		 
	    		 String word = Username +":"+ Username+":"+DataName+" "+"("+privileges+")";
				 FileWriter out = new FileWriter("C://SGBD//"+login+"//"+login+".txt", true);
				 out.write(word + "\n");
				 out.close();
				 result = "user added";
				 
	    		 }

	    	 }
	     }
	     
	     // already exist in the file
	     if(exist) {
	    	 result = "user dosn't exist";
	     }
		 
		 
		

		


		
	}
		
		

}
