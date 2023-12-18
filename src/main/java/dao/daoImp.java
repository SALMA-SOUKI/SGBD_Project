package dao;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;

import java.io.*;

public class daoImp {
	public static  String DB;
	private static ArrayList<Attribut> tableCols = new ArrayList<Attribut>();
	public static String table;
	public static String nameTable;
	static public String user;
	//insert >Etudiant (1,'hicham',1111)
	public String insertion(String query,String user1) throws IOException {
		int f,h;
		user = user1;
		System.out.println(query);
		String[] query2 = query.split("\n");
		String[] query3 = query2[0].split(" ");
		int pp=1;
		query3[1]= query3[1].strip();
//		DB = query3[];
		
		
		String[] firstQuery = query2[1].split(" ");
		String[] data2 = ((firstQuery[2].replace("(", "")).replace(")", "")).split(";");
		String data = ((firstQuery[2].replace("(", "")).replace(")", "")).replace("'", "");
	    nameTable = firstQuery[1].replace(">", "");

	    DB=query3[1];
	    
	    	// Test The Existing Of DataBase
			
				//FileWriter out = new FileWriter("C:\\SGBD\\"+DB+"\\"+nameTable+"\\Data.csv",true);
				File theDir = new File("C:\\SGBD\\"+user+"\\"+DB);
//				File theDir2 = new File("C:\\SGBD\\"+DB+"\\"+nameTable);
				
				if(!(theDir.exists() &&theDir.isDirectory())) {
					
					return "[Error]: The DataBase '"+DB+"' Not Found";
				}
				
//				if(!(theDir2.exists())) {
//					if(!(theDir.exists()) ) {
//						return "[Error]: The DataBase '"+DB+"' and Table '"+nameTable+"' are Not Found";
//					}
//					return "[Error]: The Table '"+nameTable+"' Not Found";
//				}
				
//				if(!(theDir.exists()) && !(theDir.isDirectory())) {
//					return "[Error]: The DataBase '"+DB+"' Not Found";
//				 }else {
//					 if(!(theDir2.exists()) && !(theDir.isDirectory())) {
//						 return "[Error]: The DataBase '"+DB+"' Not Found";
//				 }
//
//			}
				
					
					// && 
					
					
//	else {
//		
//		if(!(theDir.exists()) && !(theDir.isDirectory())) {
//			return "[Error]: The DataBase '"+DB+"' Not Found";
//		  }else {
//			  if(!(theDir2.exists()) && !(theDir2.isDirectory())) {
//				return "[Error]: The Table '"+nameTable+"' Not Found";
//			}
//		}
				
//				{
//					return "[Error]: The DataBase '"+DB+"' and Table '"+nameTable+"' are Not Found";
//				}
//					
							
		    //Test The Existing Of Table  
//				File theDir2 = new File("C:\\SGBD\\"+DB+"\\"+nameTable);
//				if(theDir.exists() && theDir.isDirectory()) {
//					
//				}else {
//					return "[Error]: The Table '"+nameTable+"' Not Found";
//				}	
				
//			}catch(Exception e) {
//				System.out.println("hicham");
//				return "[Error]: Requested resource does not exist";
//			}
			//Verify existence of primary key -> ! 
			
			extractTableCols();
			
			for( f = 0 ; f < tableCols.size() ; f++) {
				ArrayList<Attribut> att=tableCols;
				String st = data2[f];
				
				switch(tableCols.get(f).getType()) {
				
				case "String":if(isString( st )) {
						break;
					}else {
						h=f;
						f=0;
						return "[Error]: You have an error in your syntax; check the type of "+tableCols.get(h).getNom();
					}
					
				case "int":if(isInteger( st )) {
						break;
					}else {
						h=f;
						f=0;
						return "[Error]: You have an error in your syntax; check the type of "+tableCols.get(h).getNom();
				}
				
				case "Float":if(isFloat( st )) {
					break;
				}else {
					  h=f;
					  f=0;
						return "[Error]: You have an error in your syntax; check the type of "+tableCols.get(h).getNom();
				}
				default:
					break;
				}	
				if(f==2) {
						h=f;
						f = 0;
						break;
				}
			}

		
			for( int j=0 ; j<tableCols.size(); j++) {
				if(tableCols.get(j) != null) {
					
					if((tableCols.get(j)).isPrimaryKey()) {
						
						 
						ArrayList<String[]> dataListe = getDataFromFile("C:\\SGBD\\"+user+"\\"+DB+"\\"+nameTable+"\\Data.csv");
						
						
						    for(int i=0 ; i<dataListe.size();i++) {
							   
									for( int k=0; k<dataListe.size();k++) {
												System.out.println(dataListe.get(k));
												String[] tt=dataListe.get(k)[j].split(";");
												
										     if((tt[0].replace(" ", "")).equals((data2[j].replace(" ", "")).replace("'", ""))) {
											      return "[Error]: Data insertion failed";
										      } 
									 }
						      }	
						    //"C:\\SGBD\\School\\Etudiant\\metaData.csv"
							FileWriter out = new FileWriter("C:\\SGBD\\"+user+"\\"+DB+"\\"+nameTable+"\\Data.csv",true);
							out.write(data+"\n");
							System.out.println("Data append successfully"); 
							out.close();
							return "[Info] :Data inserted successfully"+"\n"+" ID :"+data2[0]+"     Nom :"+data2[1].replace("'", "")+"    Age:"+data2[2];
					 }
				   }	
			   }
			FileWriter out = new FileWriter("C:\\SGBD\\"+user+"\\"+DB+"\\"+nameTable+"\\Data.csv",true);
			out.write(data+"\n");
			System.out.println("Data append successfully"); 
			out.close();	
			return "[Info]:  Data inserted successfully"+"\n"+" ID :"+data2[0]+"     Nom :"+data2[1].replace("'", "")+"    Age:"+data2[2];
}
	
	public void extractTableCols() {
		 	File file = new File("C:\\SGBD\\"+user+"\\"+DB+"\\"+nameTable+"\\metaData.csv"); //"+query.getTableName().get(0)+"
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
	
	//----------------------------------------------------tir-------------------
//    String[] ListFinalMetaData=null; 
//	//System.out.println(listeMetaData.size());
//	for(int i=0 ; i<listeMetaData.size();i++) {
//		ListFinalMetaData=(Arrays.toString(listeMetaData.get(i)).replace("[", "")).replace("]","").split(",");	
//		listeMetaData2.add(ListFinalMetaData);
//	}
//	for( int j=0 ; j<listeMetaData2.size(); j++) {
//		System.out.println(Arrays.toString(listeMetaData2.get(j)));
//		
//	}
	
	
//    	FileWriter out = new FileWriter("C:\\BigData\\School\\"+nameTable+"\\Data.csv",true);
//    	out.write(data+"\n");
//    	
//    	System.out.println("Data append successfully");   
//    	out.close();
	//------------------------------------------------------------------------------------------------------------
	
//	public List<String[]> readLineByLine(Path filePath) throws Exception {
//		
//	    List<String[]> list = new ArrayList<>();
//	    try (Reader reader = Files.newBufferedReader(filePath)) {
//	        try (CSVReader csvReader = new CSVReader(reader)) {
//	            String[] line;
//	            while ((line = csvReader.readNext()) != null) {
//	                list.add(line);
//	            }
//	        }
//	    }
//	    return list;
//	}
	
	public  void  SyntaxeUse(String st)
	{
		String s = anullerEspacesDoubles(st);
		s=s.replace(";","");
		String[] phrase = s.trim().split(" ");
		String db= phrase[1];
		this.DB = db;
		//System.out.println(DB);
	}
	public  String anullerEspacesDoubles(String str) {
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
	 
	public boolean isInteger( String input ) {  
	    try { 
	        Integer.parseInt( input ); 
	        return true;
	    } 
	    catch( Exception e ) {  
	        return false; 
	    } 
	}
	
	public boolean isFloat( String input ) { 
	    try { 
	        Float.parseFloat( input ); 
	        return true;  
	    } 
	    catch( Exception e ) {  
	        return false;
	    } 
	}
	
	public boolean isString(String input) {
		try { 
	if((input.charAt(0) == '\'') && input.charAt(input.length()-1) == '\'') 
		return true;
		
		}catch( Exception e )
	     {  
	    	return false;
	    } 
		return false;
}
	
//
	
	private static ArrayList<String[]> getDataFromFile(String path){
		ArrayList<String[]> listeData = new ArrayList<>(); 
		Path pathToFile = Paths.get(path);
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
        	//read the first line from the text file
        	String line = br.readLine();
        	
        	//loop until all lines are read
        	while(line!= null) {
        		
                String[] attributes = line.split(";");
                listeData.add(attributes);
                line = br.readLine();
        	}
        	
        	
        }catch(IOException ioe) {
        	ioe.printStackTrace();
        }
		return listeData;
	}
	
	//
	
	
//	public ArrayList<String[]> getDataFromFile(String path){
////		ArrayList<String> listeData= new ArrayList<>();
//		ArrayList<String[]> listeData2= new ArrayList<>();
//		try {
//			CSVReader reader = new CSVReader(new FileReader(path));
//			String[] nextLine;
//			
//			while(( nextLine = reader.readNext()) != null) {
//				if(nextLine != null) {
//				    
//					listeData2.add(nextLine);
//				}
//			}
//		}catch(Exception e) {
//			System.out.println(e);
//		}
//		System.out.println("CSV Read complete");
//		return listeData2;
//	}
	
	public void isFolderExists()
	{
		File theDir = new File("C://SGBD");
		if (!theDir.exists()){
		    theDir.mkdirs(); 
		}
		else
		{
			System.out.println("fichier deja cree");
		}
		
		
	}
	
	//Create database
//	
////	public static void createDataBaseAndTable( String TableName ,String dataBaseName )
////	{
////		try {
////			String path = "C://BigData//"+ dataBaseName +"//"+ TableName + File.separator + File.separator +"Data.csv";
////		    	String metapath = "C://BigData//"+ dataBaseName +"//"+ TableName + File.separator + File.separator +"metaData.csv";
////			// Use relative path for Unix systems
////			File f = new File(path);
////			File f1 = new File(metapath);
////			f.getParentFile().mkdirs();
////			f.createNewFile();
////			f1.getParentFile().mkdirs();
////			f1.createNewFile();
////		    } catch (Exception e) {
////			e.getMessage();
////		    }
////	 }
////	
////	public void createCompte(String login , String password) {
////		
////			
////		
////		
////		
////	}
}