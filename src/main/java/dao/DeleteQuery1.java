package dao;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
public class DeleteQuery1 {
	

	private String tableName;
	private String login;
	private String db;
    private String column;
    private String columnValue;
    private String[] words;
    private ArrayList<Attribut> tableCols = new ArrayList<Attribut>();
    ArrayList<String[]> records = new ArrayList<>();

    public DeleteQuery1(String query,String login, String db) {
    	this.login = login;
    	this.db = db;
        query = removeExtraSpaces(query);
        words = query.split(" ");
        extractTableName();
        if (words.length > 3) {
        	extractColumn();
            extractColumnValue();
        }
        
        extractTableCols();
    }
    
    
    
    public void extractTableName() {
        tableName = words[2];
    }

    public String getTableName() {
        return this.tableName;
    }

    public void extractColumn() {
        column = words[4].split(contientOpComparaison(words[4]))[0];
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
    
    /*public int getIndexColumn(String column) {
    	ArrayList<String[]> records = new ArrayList<>();
    	String line;
    	int index;
    	try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Ayoub\\sgbdtest\\" + tableName + ".txt"))) {
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                for (int i = 0 ; i < values.length ; i++) {
                	if (values[i].equals(column)) 
                		index = i;
                	return index;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public void extractColumnValue() {
        columnValue = words[4].split(contientOpComparaison(words[4]))[1];
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
    	
    	
		
		if((right.charAt(0) == '\'') && right.charAt(right.length()-1) == '\'') {
			right = new String(Arrays.copyOfRange(right.toCharArray(), 1, right.length()-1));
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
		} else {
			System.out.println("erreur");
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
    	if (query.length == 3) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public String deleteRecord() {
        ArrayList<String[]> records = new ArrayList<>();
        String line;
        int count = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\SGBD\\"+login+"\\"+db+"\\" + tableName + "\\Data.csv"))) {
            while ((line = reader.readLine()) != null) {
            	count++;
                String[] values = line.split(";");
                /*String columnDesignated = getColumn();
                int index = indColumn(columnDesignated);
                
                
                
                String type = tableCols.get(index).getType();*/
                if (!queryWithoutCondition(words)) {
                	String columnDesignated = getColumn();
                    int index = indColumn(columnDesignated);
                    
                    
                    
                    String type = tableCols.get(index).getType();
                	
                	switch (type) {
                    case "string": 
                    	if (compareString(columnValue, values[index], contientOpComparaison(words[4])) == -1 || compareString(columnValue, values[index], contientOpComparaison(words[4])) == 0) {
                    		records.add(values);
                    		count--;
                    	}
                    	break;
                    case "int":
                    	if (compareInt(columnValue, values[index], contientOpComparaison(words[4])) == -1 || compareInt(columnValue, values[index], contientOpComparaison(words[4])) == 0) {
                    		records.add(values);
                    		count--;
                    	}
                    	break;
                    case "float":
                    	if (compareFloat(columnValue, values[index], contientOpComparaison(words[4])) == -1 || compareFloat(columnValue, values[index], contientOpComparaison(words[4])) == 0) {
                    		records.add(values);
                    		count--;
                    	}
                    	break;
                	}
                }
                
                /*switch (type) {
                case "string": 
                	if (compareString(columnValue, values[index], contientOpComparaison(words[4])) == 0) {
                		records.add(values);
                	}
                	break;
                case "int":
                	if (compareInt(columnValue, values[index], contientOpComparaison(words[4])) == 0) {
                		records.add(values);
                	}
                	break;
                case "float":
                	if (compareFloat(columnValue, values[index], contientOpComparaison(words[4])) == 0) {
                		records.add(values);
                	}
                	break;
              
                }*/
            }   
            
        } catch (IOException e) {
            e.printStackTrace();
        }
            
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\SGBD\\"+login+"\\"+db+"\\" + tableName + "\\Data.csv"))) {
            for (String[] record : records) {
                String lineToWrite = String.join(";", record);
                writer.write(lineToWrite);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Nombre des lignes supprimés : " + count;
    }
 
   public static void main(String[] args) {
        String query = "DELETE FROM produit where id==1";
        DeleteQuery1 deleteQuery = new DeleteQuery1(query, "nouhaila","Ecommerce");
        String count = deleteQuery.deleteRecord();
        System.out.println(count);
    }
}
