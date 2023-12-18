package fonctionnalite;
import java.io.File;
import java.io.FileWriter;

import org.apache.tomcat.util.http.fileupload.FileUtils; 

//import org.apache.commons.io.FileUtils;


public class delete {
	public String DB;
	public String user;
 
	
	
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
	
	
	
	
	
	public void supprimer(File f) 
	{ 
		try  
		{ 
			if(f.exists())                      //returns Boolean value  
			{  
				FileUtils.deleteDirectory(f);
				 System.out.println(f.getName() + " deleted");   //getting and printing the file name  
			}  
		else  
			{  
			System.out.println("failed");  
			}  
		}  
		catch(Exception e)  
		{  
		e.printStackTrace();  
		}  
		
	}
 
public void deleteDb(String d) 
{        
File f= new File("C://SGBD//"+this.user+"//"+ d+"//");           //file to be delete  
supprimer(f); 
}  




public void deleteTable(String table) 
{     
       
File f= new File("C://SGBD//"+this.user+"//"+ this.DB+"//"+table+"//");           //file to be delete  
supprimer(f); 
}  


//drop database db


	public  void syntaxeDeliteDatabase( String st)
	{
		
		String s = anullerEspacesDoubles(st);
		String[] phrase = s.trim().split(" ");
		String db= phrase[2];
		deleteDb(db) ;
	}
//drop table tb

	public  void syntaxeDeliteTable( String st)
	{
		
		String s = anullerEspacesDoubles(st);
		String[] phrase = s.trim().split(" ");
		String db= phrase[2];
		deleteTable(db) ;
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
	



}