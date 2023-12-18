package dao;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Query {
	private ArrayList<String> tableName = new ArrayList<String>();
	private ArrayList<String> columns = new ArrayList<String>();
	private ArrayList<String> conditions = new ArrayList<String>();
	private String query;
	private String login;
	private String db;
	
	public Query(String query,String login, String db) {
		this.query = query;
		this.login = login;
    	this.db = db;
    	
	}
	
	public void TableNames(){
    	String[] words = query.split(" ");
    	words = words[1].split(">");
    	words = words[1].split(",");
    	for (String word : words) {
        	this.tableName.add(word);
      	}
	}
	
	public ArrayList<String> getTableName(){
		return this.tableName;
	}
	
	public void Columns(){
    	String[] words = query.split(" ");
    	words = words[1].split(">");
    	words = words[0].split(",");
    	for (String word : words) {
        	this.columns.add(word);
      	}
	}
	
	public ArrayList<String> getColumns(){
		return this.columns;
	}
	
	
	
	
	public void setColumns(ArrayList<String> columns) {
		this.columns = columns;
	}

	public void Conditions(){
    	String[] words = query.split(" ");
    	if(words.length >2) {
    	String[] part = Arrays.copyOfRange(words, 2, words.length);
    	words[2] = String.join(" ", part);
    	words = Arrays.copyOfRange(words, 0, 3);
    	if(words.length == 3) {
    	//words = words[2].split(" ");
    	// Convert the array to an ArrayList
       // ArrayList<String> list = new ArrayList<>(Arrays.asList(words));
        // Remove an element from the ArrayList

    	words[2] = words[2].replace("[", "");
    	words[2] = words[2].replace("]", "");
        // Convert the ArrayList back to an array
       // words = list.toArray(new String[0]);
    	for (String word : words[2].split(" ")) {
        	this.conditions.add(word);
      	}
    	}
    	
    	
    	
    	}
    	
    	
	}

	public ArrayList<String> getConditions(){
    	return this.conditions;

	}
	public int indexOf(String column) {
        int indice = 0;

        File file = new File("C:\\SGBD\\"+login+"\\"+db+"\\"+ tableName.get(0) +"\\metaData.csv"); //"+query.getTableName().get(0)+"
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = reader.readLine()) != null) {
            	if(line.split(":")[0].equals(column)) {
            		indice = Integer.parseInt(line.split(":")[1]);
            	}
            }
		
		} catch (IOException e) {
		    // Handle any errors that occurred while reading the file
		    e.printStackTrace();
		}
    

		return indice;
	}
	
	public boolean test(String part1, String operator, String part2) {
		int tst = 0;
		boolean result = false;
		
		if(operator.equals("==")) {
			if(part1.equals(part2)) {
				
				tst+=1;
				
			}
		}
		
		if(operator.equals("!=")) {

			if(!part1.equals(part2)) {
				tst+=1;
				
			}
		}
		if(tst == 1) {
			result = true;
		}
		
		return result;
	}
	
	
	public int compareString(String right, String left, String operator) {
		if((right.charAt(0) == '\'') && right.charAt(right.length()-1) == '\'') {
			right = new String(Arrays.copyOfRange(right.toCharArray(), 1, right.length()-1));
		} else {
			return -1;
		}
		
		switch(operator) {
		case "==":
			if(right.equals(left)) {
				return 1;
			} else {
				return 0;
			}
			
		case "!=":
			if(!right.equals(left)) {
				return 1;
			} else {
				return 0;
			}
		case "<":
			if(left.compareTo(right) < 0) {
				return 1;
			} else {
				return 0;
			}
		case ">":
			if(left.compareTo(right) > 0) {
				return 1;
			} else {
				return 0;
			}
		case "<=":
			if(left.compareTo(right) <= 0) {
				return 1;
			} else {
				return 0;
			}
		case ">=":
			if(left.compareTo(right) >= 0) {
				return 1;
			} else {
				return 0;
			}
		default:
			System.out.println("Erreur");
			return -1;
		}
	}
	
	
	public boolean isInteger( String input ) { //Pass in string 
	    try { //Try to make the input into an integer 
	        Integer.parseInt( input ); 
	        return true; //Return true if it works 
	    } 
	    catch( Exception e ) {  
	        return false; //If it doesn't work return false 
	    } 
	}
	
	public boolean isFloat( String input ) { //Pass in string 
	    try { //Try to make the input into an flaot 
	        Float.parseFloat( input ); 
	        return true; //Return true if it works 
	    } 
	    catch( Exception e ) {  
	        return false; //If it doesn't work return false 
	    } 
	}
	
	public int compareInt(String rights, String lefts, String operator) {
		int left = Integer.parseInt(lefts);
		int right;
		if(isInteger(rights)) {
			right = Integer.parseInt(rights);
		} else {
			System.out.println("erreur");
			return -1;
		}
		
		switch(operator) {
		case "==":
			if(right == left) {
				return 1;
			} else {
				return 0;
			}
			
		case "!=":
			if(right != left) {
				return 1;
			} else {
				return 0;
			}
		case "<":
			if(left < right) {
				return 1;
			} else {
				return 0;
			}
		case ">":
			if(left > right) {
				return 1;
			} else {
				return 0;
			}
		case "<=":
			if(left <= right) {
				return 1;
			} else {
				return 0;
			}
		case ">=":
			if(left >= right) {
				return 1;
			} else {
				return 0;
			}
		default:
			System.out.println("Erreur");
			return -1;
		}
	}
	
	public int compareFloat(String rights, String lefts, String operator) {
		float left = Float.parseFloat(lefts);
		float right;
		if(isInteger(rights)) {
			right = Float.parseFloat(rights);
		} else {
			System.out.println("erreur");
			return -1;
		}
		
		switch(operator) {
		case "==":
			if(right == left) {
				return 1;
			} else {
				return 0;
			}
			
		case "!=":
			if(right != left) {
				return 1;
			} else {
				return 0;
			}
		case "<":
			if(left < right) {
				return 1;
			} else {
				return 0;
			}
		case ">":
			if(left > right) {
				return 1;
			} else {
				return 0;
			}
		case "<=":
			if(left <= right) {
				return 1;
			} else {
				return 0;
			}
		case ">=":
			if(left >= right) {
				return 1;
			} else {
				return 0;
			}
		default:
			System.out.println("Erreur");
			return -1;
		}
	}

}
