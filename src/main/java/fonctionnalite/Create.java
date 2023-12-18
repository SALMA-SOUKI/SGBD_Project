package fonctionnalite;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Create {
	public String DB;
	public String user;
	

	//creation dossier initial Apres sign in direcrtement
	
	public void firstCreation()
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
	
	//creation dossier pour un utilisateur 
	
	public void  createUser(String login ,String pw)
	{
		this.user=login;
		String word=login +":"+pw ;
		File theDir = new File("C://SGBD//"+login);
		if (!theDir.exists()){
			theDir.mkdirs();
			 try {
			
			String path = "C://SGBD//"+login+"//yourlogin.txt";
			File f = new File(path);
			f.getParentFile().mkdirs();
			f.createNewFile();
			FileWriter out = new FileWriter("C://SGBD//"+login+"//yourlogin.txt", true);
			out.write(word + "\n");
			out.close();
		   }  catch (Exception e) 
			 {
				e.printStackTrace();
			 }
			
	
		     
		}
		else
		{
			System.out.println("user deja cree");
		}
		
		
	}
	
	
	
	
	
	//Create DataBase School
	
	public  void createDatabase( String st)
	{
		
		String s = anullerEspacesDoubles(st);
		String[] phrase = s.trim().split(" ");
		String db= phrase[2];
		File theDir = new File("C://SGBD//"+this.user+"//"+db);
		if (!theDir.exists()){
		    theDir.mkdirs();
		    
		}
		else
		{
			System.out.println("BD deja cree");
		}
		
	 }
	
	
	
	// use database ;
	public  void  SyntaxeUse(String st)
	{
		String s = anullerEspacesDoubles(st);
		s=s.replace(";","");
		String[] phrase = s.trim().split(" ");
		String db= phrase[1];
		this.DB = db;
		//System.out.println(DB);
	}
	
	
	// Create Etudiant (id,firstname,lastname,age);
	
	public  void  SyntaxeCreateTable (String st )
	{
		
		String str , tableName  ;
		String s = anullerEspacesDoubles(st);
		//System.out.println(s);
		
		String[] phrase = s.trim().split(" ");
	    tableName=phrase[1];
		//System.out.println("TABLENAME    "+tableName);
		createTable(tableName);//////////////////////////Foction deja definit pour creer le data file et le meta data file
		// System.out.println(str);
	    str=    phrase[2].replace("(","");  
	    str=    str.replace(")","");
	  //  System.out.println(str);
	
	//  System.out.println("*********&&&&*********");
	  insertToMetaData(str,tableName);   // insert infos in the meta data
         
        
	}
	
	
	
	public  void createTable( String TableName  )
	{
		try {
			//il faut ajouter le nom utilisateur dans le path 
			String path = "C://SGBD//"+this.user+"//"+ this.DB+"//"+ TableName + File.separator + File.separator +"Data.csv";
		    String metapath = "C://SGBD//"+this.user+"//"+this.DB +"//"+ TableName + File.separator + File.separator +"metaData.csv";
			File f = new File(path);
			File f1 = new File(metapath);
			f.getParentFile().mkdirs();
			f.createNewFile();
			f1.getParentFile().mkdirs();
			f1.createNewFile();
		    } catch (Exception e) {
			e.getMessage();
		}
		
	 }
	
	public  void insertToMetaData (String info , String nameTable)
	{
		
		
		//System.out.println(info);
		 int i=0;
		String[] phrase = info.trim().split(",");
		//System.out.println("-------------------------------------");
		
		 for (String word : phrase) {
	          word = word.replace("[",";").replace("]"," ");
	          
	       
		 try {
  	     	//	String[] types = word.split(";");
			 //	System.out.println(types[1]);
			 	
		    	FileWriter out = new FileWriter("C://SGBD//"+this.user+"//"+this.DB +"\\"+nameTable+"\\metaData.csv",true);
		        out.write(word+"\n");
		    	out.close();
		    }
		    catch (IOException e) {
		      
		        e.printStackTrace();
						 }
		    
		
	   }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//fonction non utilisees  
	
	
	
	

		public  void createFile( String TableName ,String dataBaseName )
		{
			try {
				String path = "C://BigData//"+ dataBaseName +"//"+ TableName + File.separator + File.separator +"Data.csv";
			    String metapath = "C://BigData//"+ dataBaseName +"//"+ TableName + File.separator + File.separator +"metaData.csv";
				// Use relative path for Unix systems
				File f = new File(path);
				File f1 = new File(metapath);
				f.getParentFile().mkdirs();
				f.createNewFile();
				f1.getParentFile().mkdirs();
				f1.createNewFile();
			    } catch (Exception e) {
				e.getMessage();
			    }
			
		 }
	
		
	
	

		
		public void verifierType(String type)
		{
			
			switch(type) {
			 case "int":
				    
				    break;
			  case "float":
			  
			    break;
			  case "String":
			
			    break;
			  default:
			    System.out.println("!!!Type refusee dans ");
			}	
			
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
	
	
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 	//insert >Etudiant (1,hicham,1111)
	public void insertion(String query) {
			String[] firstQuery = query.split(" ", 3);
			String data = (firstQuery[2].replace("(", "")).replace(")", "");
			String nameTable = firstQuery[1].replace(">", "");
		    try {

		    	FileWriter out = new FileWriter("C:\\\\BigData\\\\cafee\\\\Etudiant\\\\Data.csv",true);
		    	out.write(data+"\n");
		    	
		    	System.out.println("Data append successfully");   
		    	out.close(); 
		    }
		    catch (IOException e) {
		    	e.printStackTrace();
		    }
	}
	
	
	
	
	public  void writeInFile(String c ,String d)
	{
		 try{
			 String path = "C://BigData//" + d+ File.separator  + File.separator +c+".csv";
			 
			 FileWriter fileWrt = new FileWriter(path);

		    BufferedWriter bufferWrt = new BufferedWriter(fileWrt);

		    bufferWrt.write("TechGee");

		    bufferWrt.close();

		    System.out.println("File updated successfully!");
		}catch(Exception e){
	          System.out.println(e);
	          }
	          System.out.println("File updated successfully!");
		
		
	}*/
}
