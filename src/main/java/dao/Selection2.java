package dao;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Selection2 {
	
	private String tableName;
	private String login;
	private String db;
	private String column;
	private String columnValue;
    private String[] words;
    private ArrayList<Attribut> tableCols = new ArrayList<Attribut>();
    private ArrayList<String> columnsShow = new ArrayList<String>();
    ArrayList<String[]> records = new ArrayList<>();
    ArrayList<List<String>> result = new ArrayList<List<String>>();
    List<String> ligne = new ArrayList<String>();

    
    


	
 	public Selection2(String query,String login, String db) {
    	this.login = login;
    	this.db = db;
        query = removeExtraSpaces(query);
        words = query.split(" ");
        extractTableName();
        if (words.length > 2) {
        	extractColumn();
            extractColumnValue();
        }
        
        extractTableCols();
        extractColumns();

    }
    
	
 	
 
 public void extractTableName() {
     tableName = words[1].split(">")[1];
 }

 public String getTableName() {
     return this.tableName;
 }

 
 public void extractColumn() {
     column = words[2].split(contientOpComparaison(words[2]))[0];
     column = column.replace("[", "");

 }
 public void extractColumns() {
	 if(!words[1].split(">")[0].equals("*")) {
		 for(String column : words[1].split(">")[0].split(",")) {
			 columnsShow.add(column);
		 }
	 }else {
		 File file2 = new File("C:\\SGBD\\"+login+"\\"+db+"\\"+ tableName +"\\metaData.csv"); //"+query.getTableName().get(0)+"
         try (BufferedReader reader2 = new BufferedReader(new FileReader(file2))) {
             String line2;

             while ((line2 = reader2.readLine()) != null) {
            	 columnsShow.add(line2.split(";")[0]);
             }
             
 		
 		} catch (IOException e) {
 		    e.printStackTrace();
 		}
     

	 }

 }

 public String getColumn() {

     return this.column;
 }
 
 
 
 public static String contientOpComparaison(String cdt) {
		char c;
		
		for(int i = 0 ; i < cdt.length() ; i++) {
			c = cdt.charAt(i);
			if(c == '=') {
				if((i < cdt.length() - 1) && (cdt.charAt(i+1) == '=')) {
					return "" + c + cdt.charAt(i+1);
				}
			} else if((c == '>') || c == '<') {
				if((i < cdt.length() - 1) && ((cdt.charAt(i+1) == '='))) {
					return "" + c + cdt.charAt(i+1);
				} else {
					return c + "";
				}
			} else if(c == '!') {
				if((i < cdt.length() - 1) && ((cdt.charAt(i+1) == '='))) {
					return "" + c + cdt.charAt(i+1);
				}
			}
		}
		
		return "";
	}
 
 
 public void extractTableCols() {
	 File file2 = new File("C:\\SGBD\\"+login+"\\"+db+"\\"+ tableName +"\\metaData.csv"); //"+query.getTableName().get(0)+"
       try (BufferedReader reader2 = new BufferedReader(new FileReader(file2))) {
           String line2;
           String[] Tline;

           while ((line2 = reader2.readLine()) != null) {
        	   Tline = line2.split(";");
        	   if(Tline.length == 3)
        		   tableCols.add(new Attribut(Tline[0], Tline[1], true));
        	   else
        		   tableCols.add(new Attribut(Tline[0], Tline[1], false));
           }
           
		
		} catch (IOException e) {
		    // Handle any errors that occurred while reading the file
		    e.printStackTrace();
		}
}

 
 public int indColumn(String colName) {
		for(int i = 0 ; i < tableCols.size() ; i++) {
			if(tableCols.get(i).getNom().equals(colName))
				return i;
		}
		return -1;
	}
 
 
 public void extractColumnValue() {
	 columnValue = words[2].split(contientOpComparaison(words[2]))[1];
	 columnValue = columnValue.replace("]", "");
 }

 public String getColumnValue() {
     return this.columnValue;
 }

 
 public String removeExtraSpaces(String s) {
     String res = s.trim();
     while (res.contains("  ")) {
         res = res.replace("  ", " ");
     }
     return res;
 }

 
 public int compareString(String right, String left, String operator) {
		System.out.println("op " + operator);
		System.out.println("op " + right);
		System.out.println("op " + left);

		//if((right.charAt(0) == '\'') && right.charAt(right.length()-1) == '\'') {
			
			//right = new String(Arrays.copyOfRange(right.toCharArray(), 1, right.length()-1));

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
	//	} else {
		//	System.out.println("erreur");
		//	return -1;
	//	} 
        
		
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
		} else {
			System.out.println("erreur");
			return -1;
		}
		
		
	}
 
 public int compareFloat(String rights, String lefts, String operator) {
		float left = Float.parseFloat(lefts);
		float right;
		if(isInteger(rights)) {
			right = Float.parseFloat(rights);
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
		} else {
			System.out.println("erreur");
			return -1;
		}
		
 }
 
 public boolean queryWithoutCondition(String[] query) {
 	if (query.length == 2) {
 		return true;
 	} else {
 		return false;
 	}
 }
 

 public  ArrayList<List<String>> getRecord() {

     String line;
     int count = 0;
     ArrayList<String[]> records = new ArrayList<>();

     try (BufferedReader reader = new BufferedReader(new FileReader("C:\\SGBD\\"+login+"\\"+db+"\\" + tableName + "\\Data.csv"))) {
         while ((line = reader.readLine()) != null) {
             String[] values = line.split(";");
            
             if (!queryWithoutCondition(words)) {
             	String columnDesignated = getColumn();
                 int index = indColumn(columnDesignated);

                 
                 String type = tableCols.get(index).getType();

             	switch (type) {
                 case "string": 
               		System.out.println("type " +  columnValue.equals( values[index]));

                 	if (compareString(columnValue, values[index], contientOpComparaison(words[2])) == 1 ) {
                 		records.add(values);

                 		count++;
                 	}
                 	break;
                 case "int":
                 	if (compareInt(columnValue, values[index], contientOpComparaison(words[2])) == 1 ) {
                 		records.add(values);

                 		count++;
                 	}
                 	break;
                 case "float":
                 	if (compareFloat(columnValue, values[index], contientOpComparaison(words[2])) == 1) {
                 		records.add(values);
                 		count++;
                 	}
                 	break;
             	}
             	
             	
             	
             	
             }else {
         		count++;
          		records.add(line.split(";"));


          	}
             
            
         }   
         
     } catch (IOException e) {
         e.printStackTrace();
     }
         

		for(String[] a: records) {
			ligne = new ArrayList<String>();
				for(String c:columnsShow) {
					ligne.add(a[indColumn(c)]);
				}
			result.add(ligne);
		 }
		 
System.out.println(result);
     return result;
 }

 
 
 
 
 
 
 
 
 
 
 	
		
    
	public static void main(String[] args) {
		
		 String query = "get *>user";
	     Selection2 deleteQuery = new Selection2(query, "nouhaila", "Ecommerce");
	     System.out.println(deleteQuery.getRecord());
	}

	
}
