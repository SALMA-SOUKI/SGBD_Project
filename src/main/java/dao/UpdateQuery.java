package dao;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

//import org.apache.commons.lang3.math.NumberUtils;

public class UpdateQuery {
	private String tableName = null;
	private String login;
	private String dataBase;
	private ArrayList<Integer> indColsToUpdate = new ArrayList<Integer>();
	private ArrayList<String> newValues = new ArrayList<String>();
	private ArrayList<Attribut> tableCols = new ArrayList<Attribut>();
	private String condition = null;
	private String[] words;
	
	public UpdateQuery(String query, String login, String dataBase) {
		query = anullerEspacesDoubles(query); 
		words = query.split(" ");
		this.dataBase = dataBase;
		this.login = login;
		extractTableName();
		extractTableCols();
		extractColumnValues();
		extractConditions();
		
	}
	
	
	public void extractTableCols() {
		 File file = new File("C:\\\\SGBD\\" + login + "\\" + dataBase + "\\" + tableName + "\\" +"metaData.csv"); //"+query.getTableName().get(0)+"
	       try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	           String line;
	           String[] Tline;
	           
	           while ((line = reader.readLine()) != null) {
	        	   Tline = line.split(";");
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


	public ArrayList<Attribut> getTableCols() {
		return tableCols;
	}



	public void setTableCols(ArrayList<Attribut> tableCols) {
		this.tableCols = tableCols;
	}



	public void extractTableName(){
		tableName = words[1].split(">")[1];
	}
	
	public String getTableName(){
		return this.tableName;
	}
	
	
	
	public void extractColumnValues(){
		if(words[2].toLowerCase().equals("set")) {
			String[] Tcolv = words[3].split(",");
			for(String colv : Tcolv) {
				if(colv.contains("=")) {
					String[] rl = colv.split("=");
					int indCol = indColumn(rl[0]);
					if(indCol == -1) {
						System.out.println("erreur");
					} else {
						String type = tableCols.get(indCol).getType(); 
						if(type.equals("string")) {
							if((rl[1].charAt(0) == '\'') && rl[1].charAt(rl[1].length()-1) == '\'') {
								rl[1] = new String(Arrays.copyOfRange(rl[1].toCharArray(), 1, rl[1].length()-1));
							} else {
								System.out.println("erreur");
								return;
							}
						} else if(type.equals("int")){
							if(!isInteger(rl[1])) {
								System.out.println("Erreur");
								return;
							}
						} else {
							if(!isFloat(rl[1])) {
								System.out.println("Erreur");
								return;
							}
						}
						indColsToUpdate.add(indCol);
						newValues.add(rl[1]);
					}
				} else {
					System.out.println("Erreur");
				}
			}
		}
	}	
	
	public int indColumn(String colName) {
		for(int i = 0 ; i < tableCols.size() ; i++) {
			if(tableCols.get(i).getNom().equals(colName))
				return i;
		}
		return -1;
	}


	public void extractConditions(){
    	if(words.length >4) {
    	String[] part = Arrays.copyOfRange(words, 4, words.length);
    	words[4] = String.join(" ", part);
    	words = Arrays.copyOfRange(words, 0, 5);
	    	if(words.length == 5) {
	
		    	words[4] = words[4].replace("[", "");
		    	words[4] = words[4].replace("]", "");
		    	for (String word : words[4].split(" ")) {
		        	condition = "" + word;
		      	}
	    	}
    	}
    	
	}

	public String getConditions(){
    	return this.condition;

	}
		
	public int compareString(String right, String left, String operator) {
		if((right.charAt(0) == '\'') && right.charAt(right.length()-1) == '\'') {
			right = new String(Arrays.copyOfRange(right.toCharArray(), 1, right.length()-1));
		} else {
			System.out.println("erreur");
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
	
	
	public static boolean isInteger( String input ) { //Pass in string 
	    try { //Try to make the input into an integer 
	        Integer.parseInt( input ); 
	        return true; //Return true if it works 
	    } 
	    catch( Exception e ) {  
	        return false; //If it doesn't work return false 
	    } 
	}
	
	public static boolean isFloat( String input ) { //Pass in string 
	    try { //Try to make the input into an flaot 
	        Float.parseFloat( input ); 
	        return true; //Return true if it works 
	    } 
	    catch( Exception e ) {  
	        return false; //If it doesn't work return false 
	    } 
	}
	
	public static int compareInt(String rights, String lefts, String operator) {
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
	
	public static int compareFloat(String rights, String lefts, String operator) {
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
	
	public static String anullerEspacesDoubles(String str) {
		str = str.trim();
		
		String nstr = "";
		boolean espaceTrouvee = false;
		boolean StringTrouvee = false;
		char c;
		
		for(int i = 0 ; i < str.length() ; i++) {
			c = str.charAt(i);
			if(c == '\'') {
				StringTrouvee = !StringTrouvee;
			} 
			
			if(c == 32) {
				if(StringTrouvee == true) {
					nstr += c + "";
					espaceTrouvee = false;
				} else {
					if(espaceTrouvee == false) {
						nstr += " ";	
						espaceTrouvee = true;
					} 
				}
			} else {
				nstr += c + "";
				espaceTrouvee = false;
			}
			
		}
		
		return nstr;
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
	
	public static boolean colExists(ArrayList<Attribut> tableCols, String nomCol) {
		for(Attribut atr : tableCols) {
			if(atr.getNom().equals(nomCol))
				return true;
		}
	
		return false;
	}
	
	
	
	public int executer() {
		boolean pkModif = false;
		int count = 0;
		
		try {
			for(int i = 0; i < indColsToUpdate.size() ; i++) {
				if(tableCols.get(indColsToUpdate.get(i)).isPrimaryKey()) {
					pkModif = true;
					BufferedReader rd;
					rd = new BufferedReader(new FileReader("C:\\\\SGBD\\" + login + "\\" + dataBase + "\\" + tableName + "\\" + "Data.csv"));
					String line2;
					String pkValue;
					while((line2 = rd.readLine()) != null) {
						pkValue = line2.split(";")[indColsToUpdate.get(i)];
						switch(tableCols.get(indColsToUpdate.get(i)).getType()) {
						case "string":
							if(compareString("'" + newValues.get(i) + "'", pkValue, "==") == 1) {
								System.out.println("Erreur");
								return -1;
							} else if(compareString("'" + newValues.get(i) + "'", pkValue, "==") == -1){
								System.out.println("Erreur");
								return -1;
							}
							break;
						case "int":
							if(compareInt(newValues.get(i), pkValue, "==") == 1){
								System.out.println("Erreur");
								return -1;
							} else if(compareInt(newValues.get(i), pkValue, "==") == -1){
								System.out.println("Erreur");
								return -1;
							}
							break;
						case "float":
							if(compareFloat(newValues.get(i), pkValue, "==") == 1) {
								System.out.println("Erreur");
								return -1;
							} else if(compareFloat(newValues.get(i), pkValue, "==") == -1){
								System.out.println("Erreur");
								return -1;
							}
							break;
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			 RandomAccessFile raf = new RandomAccessFile("C:\\\\SGBD\\" + login + "\\" + dataBase + "\\" + tableName + "\\"+ "Data.csv", "rw");
	         long position = 0;
	         String line = null;
	         long linelen = 0;
	         String[] record = null;
	         boolean satisfied;
	         while ((line = raf.readLine()) != null) {
	        	linelen = line.getBytes().length + 1;
	            record = line.split(";");
	            
	            satisfied = false;
	            if(condition != null) {
	            	for (Attribut atr : tableCols) {	
            			String cond;
            			cond = condition;
            			String operateur = contientOpComparaison(cond);
            			if(operateur.equals("")) {
            				System.out.println("Erreur");
            				return -1;
            			} else {
    	           				String[] Tc = cond.split(operateur);
    	           				String left = Tc[0];
    	           				String right = Tc[1];
    	           				if(colExists(tableCols, left)) {
    	           					if(atr.getNom().equals(left)) {
    	           						if(atr.isPrimaryKey()) {
    	           							
    	           						} 
        	           					switch(atr.getType()) {
        	           					case "string":
        	           						if(compareString(right, record[tableCols.indexOf(atr)], operateur) == 1) {
        	           							satisfied = true;
        	           						} else if(compareString(right, record[tableCols.indexOf(atr)], operateur) == -1) {
        	           							System.out.println("Erreur");
        	           							return -1;
        	           						}
        	           						break;
        	           					case "int":
        	           						if(compareInt(right, record[tableCols.indexOf(atr)], operateur) == 1) {
        	           							satisfied = true;
        	           						} else if(compareInt(right, record[tableCols.indexOf(atr)], operateur) == -1) {
        	           							System.out.println("Erreur");
        	           							return -1;
        	           						}
        	           						break;
        	           					case "float":
        	           						if(compareFloat(right, record[tableCols.indexOf(atr)], operateur) == 1) {
        	           							satisfied = true;
        	           						} else if(compareFloat(right, record[tableCols.indexOf(atr)], operateur) == -1) {
        	           							System.out.println("Erreur");
        	           							return -1;
        	           						}
        	           						
        	           					}
        	           				}
    	           				} else {
    	           					System.out.println("Erreur");
    	           					return -1;
    	           				}
            			}
	            	}
	            }else {
	            	satisfied = true;
	            }
    			
	            
	            if(satisfied) {
	            	 count++;
	            	 for(int i = 0; i < indColsToUpdate.size() ; i++) {
	            		 for(int j = 0 ; j < tableCols.size() ; j++) {
	            			 if(indColsToUpdate.get(i) == j) {
	            				 record[j] = newValues.get(i);
	            			 }
	            		 }
	            	 }
	            	 
	            	 String recordStr = "";
	            	 for(int i = 0 ; i < record.length - 1 ; i++) {
	            		 recordStr += record[i] + ";";
	            	 }
	            	 recordStr += record[record.length - 1] + "\n";

	                 byte[] remainingBytes = new byte[(int) (raf.length() - raf.getFilePointer())];
	                 raf.read(remainingBytes);
	                 raf.getChannel().truncate(position);
	                 raf.seek(position);
	                 raf.write(recordStr.getBytes());
	                 raf.write(remainingBytes);    
	                 linelen = recordStr.getBytes().length;
	                 raf.seek(position + linelen);
	             }
	            position += linelen;
		     }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if((pkModif == true) && (count > 1)) {
			System.out.println("Erreur");
			return -1;
		}
		
		return count;
	}
	
	public static void main(String[] args) {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter("C:\\\\SGBD\\" + "nouhaila" + "\\" + "Ecommerce" + "\\" + "tb1"  + "\\" + "Data.csv"));
			
			writer.write("1;moncef;21\n");
		    writer.write("2;moncef;30\n");
		    writer.write("3;moncef;20\n");
		    writer.write("4;hicham;30\n");
		    
		    writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		UpdateQuery query = new UpdateQuery("Update >tb1 set nom='moncef' [id==4]", "nouhaila", "Ecommerce");
		System.out.println("Nombre des lignes modifiés : " + query.executer());		
		
	}
	}