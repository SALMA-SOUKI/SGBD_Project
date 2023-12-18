package web;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.AdminSelection;
import dao.Create;
import dao.Data;
import dao.Datav2;
import dao.Datav3;
import dao.DeleteUser;

@Controller
public class LoginController {
	
	Create cr = new Create();
	AdminSelection as = new AdminSelection();
	DeleteUser du = new DeleteUser();

 
 
 @RequestMapping(value="/addUser")
 public String addUser(@RequestParam(value="login")String login,@RequestParam(value="password")String password, Model modele) throws ParseException{
		Datav3 dt = new Datav3(login);
		

	 if(dt.uniqueLogin(login)) {
	   try {
		   cr.firstCreation();
		   cr.createUser(login, password);
	       
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	   
	 }else {
		 modele.addAttribute("error","user already exist");
		 return("SignUp");
	 }
	 
     modele.addAttribute("login",login);

	 return("VuePrincipal3");
 }
 

 
 
 

 @RequestMapping(value="/VerifyUser")
 public String updateArticle(@RequestParam(value="login")String login,@RequestParam(value="password")String password, Model modele){
	 Datav3 dt = new Datav3(login);
	List<List<String>> auth = new ArrayList<List<String>>();
	auth = 	dt.authentification(login);
	System.out.println(login);

	System.out.println(auth.get(0).get(0));
	System.out.println(auth.get(0).get(1));
	 List<String> contents= new  ArrayList<String>();
	 Map<String,Map<String,List<String>>> usersdbs = new HashMap<String,Map<String,List<String>>>();
	
	if(login.equals("admin") && password.equals("admin")) {
		
		 contents = as.AllUsers();
		 usersdbs = as.AllUsersDataBases();
		 
	     modele.addAttribute("users", contents);	
	     modele.addAttribute("dbUsers", usersdbs);
	     modele.addAttribute("login", "admin");	

			return("VueAdmin");

	}

	if(!(auth.get(0).get(0).equals(login) && auth.get(0).get(1).equals(password))) {
		if(auth.get(0).get(0).equals("not found")) {
			modele.addAttribute("error","user doesn't exist");
		}else {
			modele.addAttribute("error","wrong credentials");
		}
		return("SignIn");
	}
	

	Map<String,List<String>> user_data = new HashMap<String,List<String>>();
	user_data = dt.user_data(login);
	System.out.println(user_data);
	List<Map<String,List<String>>> tables_data = new ArrayList<Map<String,List<String>>>();
	tables_data = dt.tables_data(login);
	
	List<Map<String,List<String>>> tables_metadata = new ArrayList<Map<String,List<String>>>();
	tables_metadata = dt.tables_metadata(login);
	
	cr.setUser(login);
	
	modele.addAttribute("auth",auth);
	modele.addAttribute("user_data",user_data);

	modele.addAttribute("tables_data",tables_data);
	
	modele.addAttribute("tables_metadata",tables_metadata);
    modele.addAttribute("login",login);
	System.out.println(tables_data);


	 return("VuePrincipal3");
 }
 
 
 
 
 @RequestMapping(value="/console")
 public String console(@RequestParam String db,@RequestParam String table, Model modele){
		
	 System.out.println(db);
	 System.out.println(table);

	 modele.addAttribute("console_status",1);
	 modele.addAttribute("db",db);
	 modele.addAttribute("table",table);
	 
	 
	 
	 
	 return("VuePrincipal3");
 }






 @RequestMapping(value="/creation" )
	 public String creation(@RequestParam(value="name") String name,@RequestParam(value="email") String email,@RequestParam (value="url")String  url,String login,Model modele) 
		{
		 
		 
		 
		 cr.firstCreation();
		 //cr.createUser("Rawaa" ,"password");
		 cr.createDatabase(name,login);
		 cr.SyntaxeUse(email);
		 cr.SyntaxeCreateTable(url,login,name);
		 
		//md.addAttribute("Crquery",name);
		//md.addAttribute("usequery",email);
		//md.addAttribute("tablequery",url); 
		 
		 
		 Datav3 dt = new Datav3(login);
			List<List<String>> auth = new ArrayList<List<String>>();
			auth = 	dt.authentification(login);
			System.out.println(login);

			System.out.println(auth.get(0).get(0));
			System.out.println(auth.get(0).get(1));
			 List<String> contents= new  ArrayList<String>();
			 Map<String,Map<String,List<String>>> usersdbs = new HashMap<String,Map<String,List<String>>>();
			
			

			
			

			Map<String,List<String>> user_data = new HashMap<String,List<String>>();
			user_data = dt.user_data(login);
			System.out.println(user_data);
			List<Map<String,List<String>>> tables_data = new ArrayList<Map<String,List<String>>>();
			tables_data = dt.tables_data(login);
			
			List<Map<String,List<String>>> tables_metadata = new ArrayList<Map<String,List<String>>>();
			tables_metadata = dt.tables_metadata(login);
			
			cr.setUser(login);
			
			modele.addAttribute("auth",auth);
			modele.addAttribute("user_data",user_data);

			modele.addAttribute("tables_data",tables_data);
			
			modele.addAttribute("tables_metadata",tables_metadata);
		    modele.addAttribute("login",login);
			System.out.println(tables_data);


		
		 return ("VuePrincipal3");
		}
 
	 
 
 
 @RequestMapping(value="/addUser2")
 public String addUser2(@RequestParam(value="Username") String Username,@RequestParam(value="DataName") String DataName,@RequestParam(value="privileges") String privileges,String login, Model modele) throws IOException{
	 File directoryPath = new File("C:\\SGBD\\");
     String contents[] = directoryPath.list();
     boolean exist = true;
     String result = null ;
     boolean existInfile = true;
     ArrayList<String> records = new ArrayList<>();
     String  target = null;
	 String line ;
	 boolean over = true;
	 
	String word_target =":"+DataName+" "+"("+privileges+")";

     System.out.println(privileges);
     for(String s : contents) {
    	 if(Username.equals(s)){
    		 exist = false;

	    		File file = new File("C:\\SGBD\\"+login+"\\"+login+".txt");
	    		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	    		        
					while ((line = reader.readLine()) != null) {

	    		        	if(line.split(":")[0].equals(Username) ) {
	    		        		existInfile = false;
	    		        		target = line.toString();
	    		        		
	    		        		if(line.contains(DataName)) {
	    		        			over = false;
		    		        		target = line.toString();

	    		        		}

	    		        
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
	    		            if(over) {
    		                String lineToWrite = target +""+word_target;

	    		            writer.write(lineToWrite);
    		                writer.newLine();
	    		            }else {
	    		            	String word_target2 =DataName+" "+"("+privileges+")";

	    		            	String[] data = target.split(":");
	    		            	
	    		            	for(int i=0;i<data.length;i++) {
	    		            		if(data[i].contains(DataName)) {
	    		            			data[i] = word_target2;
	    		            			System.out.println("pr" + data);
	    		            		}
	    		            	}
	    		            	target ="";
	    		            	for(String dts : data) {
	    		            		target +=dts.concat(":");
		    		            	System.out.print("target " + target);

	    		            	}
	    		            	target = target.substring(0, target.length() - 1); 
	    		            	// = target.repla
	    		            	//for(String dts : data) {
	    		            		String lineToWrite = target;

			    		            writer.write(lineToWrite);
		    		                writer.newLine();
		    		                
		    		      
	    		            	//}
	    		            	
	    		            	
	    		            }
	    		        } catch (IOException e) {
	    		            e.printStackTrace();
	    		        }
	    		}catch (IOException e) {
    			    e.printStackTrace();
    			}
        		


    		 
    		 
    		 
    		 
    		 
    		 
    		 if(existInfile) {
    		 Datav3 dt = new Datav3(Username);
    		 List<List<String>> auth = new ArrayList<List<String>>();
    		 auth = dt.authentification(Username);
    		 String word = Username +":"+ auth.get(0).get(1)+":"+DataName+" "+"("+privileges+")";
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
	 
	 
     Datav3 dt = new Datav3(login);
 	List<List<String>> auth = new ArrayList<List<String>>();
 	auth = 	dt.authentification(login);
 

 	Map<String,List<String>> user_data = new HashMap<String,List<String>>();
 	user_data = dt.user_data(login);
 	System.out.println(user_data);
 	List<Map<String,List<String>>> tables_data = new ArrayList<Map<String,List<String>>>();
 	tables_data = dt.tables_data(login);
 	
 	List<Map<String,List<String>>> tables_metadata = new ArrayList<Map<String,List<String>>>();
 	tables_metadata = dt.tables_metadata(login);
 	
 	cr.setUser(login);
 	
 	modele.addAttribute("auth",auth);
 	modele.addAttribute("user_data",user_data);

 	modele.addAttribute("tables_data",tables_data);
 	
 	modele.addAttribute("tables_metadata",tables_metadata);
     modele.addAttribute("login",login);
 	System.out.println(tables_data);


	 modele.addAttribute("result", result);
	 
	 
	 return("VuePrincipal7");
 }

 
 
 
 
 @RequestMapping(value="/VerifyUser2")
 public String VerifyUser2(@RequestParam(value="adminlogin")String adminlogin,@RequestParam(value="login")String login,@RequestParam(value="password")String password, Model modele){
	System.out.println(adminlogin);
	Datav3 dt = new Datav3(adminlogin);
	List<List<String>> auth = new ArrayList<List<String>>();
	auth = 	dt.authentification(adminlogin);
	System.out.println(auth.get(0).get(0));
	System.out.println(auth.get(0).get(1));
	
	if(login.equals("admin") && password.equals("admin")) {
		
		
	}
	
	for(int i=1;i<auth.size()-1;i++) {

	if(!(auth.get(i).get(0).equals(login) && auth.get(i).get(1).equals(password) && auth.get(0).get(0).equals(adminlogin))) {
		if(i==auth.size()){
			
		if(auth.get(0).get(0).equals("not found")) {
		    modele.addAttribute("error","user doesn't exist");
		}
		if(auth.get(i).get(0) == null) {
		    modele.addAttribute("error","you are not authorised");
		}
		
		

	     Datav3 dt2 = new Datav3(login);
	 	List<List<String>> auth2 = new ArrayList<List<String>>();
	 	auth = 	dt.authentification(login);
	 

	 	Map<String,List<String>> user_data = new HashMap<String,List<String>>();
	 	user_data = dt.user_data(login);
	 	System.out.println(user_data);
	 	List<Map<String,List<String>>> tables_data = new ArrayList<Map<String,List<String>>>();
	 	tables_data = dt.tables_data(login);
	 	
	 	List<Map<String,List<String>>> tables_metadata = new ArrayList<Map<String,List<String>>>();
	 	tables_metadata = dt.tables_metadata(login);
	 	
	 	cr.setUser(login);
	 	
	 	modele.addAttribute("auth",auth);
	 	modele.addAttribute("user_data",user_data);

	 	modele.addAttribute("tables_data",tables_data);
	 	
	 	modele.addAttribute("tables_metadata",tables_metadata);
	     modele.addAttribute("login",login);
	 	System.out.println(tables_data);


		return("VuePrincipal8");

		}

	}

	}
	
	
	System.out.println(auth.get(0).get(0).equals("not found"));
	if(!auth.get(0).get(0).equals("not found")) {
	Map<String,List<String>> user_data = new HashMap<String,List<String>>();
	user_data = dt.user_data(adminlogin);
	boolean test = true;
	int indice=-1;
	List<Integer> index = new ArrayList<Integer>();
	for(Entry<String, List<String>> mp : user_data.entrySet()) {
		test = true;
		indice = indice+1;
		for(int i=1;i<auth.size();i++) {
			for(int j=2;j<auth.get(i).size();j++) {
				if(auth.get(i).get(j).equals(mp.getKey())) {
				   test = false;
					index.add(indice);
				}
			}
		}	
		
		
		if(test) {
			//mp remove
			user_data.remove(mp.getKey());
		}

	}
	
	
	List<Map<String,List<String>>> tables_data_old = new ArrayList<Map<String,List<String>>>();
	List<Map<String,List<String>>> tables_data = new ArrayList<Map<String,List<String>>>();
	tables_data_old = dt.tables_data(adminlogin);
	
	for(int i=0;i<index.size();i++) {
		tables_data.add(tables_data_old.get(index.get(i)));
	}
	
	
	List<Map<String,List<String>>> tables_metadata_old = new ArrayList<Map<String,List<String>>>();
	List<Map<String,List<String>>> tables_metadata = new ArrayList<Map<String,List<String>>>();
	tables_metadata_old = dt.tables_metadata(adminlogin);
	
	for(int i=0;i<index.size();i++) {
		tables_metadata.add(tables_metadata_old.get(index.get(i)));
	}
	
	
	
	
	System.out.println(tables_data);
	
	modele.addAttribute("user_data",user_data);
	modele.addAttribute("tables_data",tables_data);
	
	modele.addAttribute("tables_metadata",tables_metadata);
    modele.addAttribute("login",login);
    modele.addAttribute("bd",null);
    
	 return("VuePrincipal5");

	}else {
		if(auth.get(0).get(0).equals("not found")) {
		    modele.addAttribute("error","user doesn't exist");
		}
		
		
		

	     Datav3 dt2 = new Datav3(login);
	 	List<List<String>> auth2 = new ArrayList<List<String>>();
	 	auth = 	dt.authentification(login);
	 

	 	Map<String,List<String>> user_data = new HashMap<String,List<String>>();
	 	user_data = dt.user_data(login);
	 	System.out.println(user_data);
	 	List<Map<String,List<String>>> tables_data = new ArrayList<Map<String,List<String>>>();
	 	tables_data = dt.tables_data(login);
	 	
	 	List<Map<String,List<String>>> tables_metadata = new ArrayList<Map<String,List<String>>>();
	 	tables_metadata = dt.tables_metadata(login);
	 	
	 	cr.setUser(login);
	 	
	 	modele.addAttribute("auth",auth);
	 	modele.addAttribute("user_data",user_data);

	 	modele.addAttribute("tables_data",tables_data);
	 	
	 	modele.addAttribute("tables_metadata",tables_metadata);
	     modele.addAttribute("login",login);
	 	System.out.println(tables_data);


	}
	return("VuePrincipal8");

 }
 
 
 
 

 @RequestMapping(value="/VerifyUser3")
 public String VerifyUser3(@RequestParam(value="adminlogin")String adminlogin,@RequestParam(value="login")String login,
		 @RequestParam(value="password")String password, Model modele){
	 


	System.out.println(adminlogin);
	 Datav3 dt = new Datav3(adminlogin);
	List<List<String>> auth = new ArrayList<List<String>>();
	auth = 	dt.authentification(adminlogin);
	System.out.println(auth.get(0).get(0));
	System.out.println(auth.get(0).get(1));
	boolean existancetest = false;
	for(List<String> au : auth) {
		if(au.isEmpty()) {
			auth.remove(au);
		}
	}
	
	
	
	if(login.equals("admin") && password.equals("admin")) {
		System.out.println("admin");
		Datav3 dt3 = new Datav3(adminlogin);
		List<List<String>> auth3 = new ArrayList<List<String>>();
		auth3 = dt3.authentification(login);
		
		 List<String> contents= new  ArrayList<String>();
		 Map<String,Map<String,List<String>>> usersdbs = new HashMap<String,Map<String,List<String>>>();
		
		
		

		Map<String,List<String>> user_data = new HashMap<String,List<String>>();
		user_data = dt3.user_data(adminlogin);
		System.out.println(user_data);
		List<Map<String,List<String>>> tables_data = new ArrayList<Map<String,List<String>>>();
		tables_data = dt3.tables_data(adminlogin);
		
		List<Map<String,List<String>>> tables_metadata = new ArrayList<Map<String,List<String>>>();
		tables_metadata = dt3.tables_metadata(adminlogin);
		
		cr.setUser(adminlogin);
		
		modele.addAttribute("auth",auth3);
		modele.addAttribute("user_data",user_data);

		modele.addAttribute("tables_data",tables_data);
		
		modele.addAttribute("tables_metadata",tables_metadata);
	    
	    modele.addAttribute("login",login);
	    modele.addAttribute("adminlogin",adminlogin);
	    modele.addAttribute("password",password);

		
	    return("VuePrincipal5");


	}
	
	
	
	
	if(auth.size()>1 && !login.equals("admin")) {
	for(int i=1;i<=auth.size()-1;i++) {

	if(!(auth.get(i).get(0).equals(login) && auth.get(i).get(1).equals(password) && auth.get(0).get(0).equals(adminlogin))) {
		


		if(i==auth.size()+1){
			System.out.println("okey");

			if(auth.get(0).get(0).equals("not found")) {
			    modele.addAttribute("error","user doesn't exist");
			}
			if(auth.get(i).get(0) == null) {
			    modele.addAttribute("error","you are not authorised");
			}
			
			

		    Datav3 dt2 = new Datav3(login);
		 	List<List<String>> auth2 = new ArrayList<List<String>>();
		 	auth = 	dt.authentification(login);
		 

		 	Map<String,List<String>> user_data = new HashMap<String,List<String>>();
		 	user_data = dt.user_data(login);
		 	System.out.println(user_data);
		 	List<Map<String,List<String>>> tables_data = new ArrayList<Map<String,List<String>>>();
		 	tables_data = dt.tables_data(login);
		 	
		 	List<Map<String,List<String>>> tables_metadata = new ArrayList<Map<String,List<String>>>();
		 	tables_metadata = dt.tables_metadata(login);
		 	
		 	cr.setUser(login);
		 	
		 	modele.addAttribute("auth",auth);
		 	modele.addAttribute("user_data",user_data);

		 	modele.addAttribute("tables_data",tables_data);
		 	
		 	modele.addAttribute("tables_metadata",tables_metadata);
		     modele.addAttribute("login",login);

		 	
			return("VuePrincipal8");

			}

		}
	
	}
	}else {
		
		    modele.addAttribute("error","you are not authorised");
		
		
		System.out.println("okey");

	    Datav3 dt2 = new Datav3(login);
	 	List<List<String>> auth2 = new ArrayList<List<String>>();
	 	auth = 	dt.authentification(login);
	 

	 	Map<String,List<String>> user_data = new HashMap<String,List<String>>();
	 	user_data = dt.user_data(login);
	 	System.out.println(user_data);
	 	List<Map<String,List<String>>> tables_data = new ArrayList<Map<String,List<String>>>();
	 	tables_data = dt.tables_data(login);
	 	
	 	List<Map<String,List<String>>> tables_metadata = new ArrayList<Map<String,List<String>>>();
	 	tables_metadata = dt.tables_metadata(login);
	 	
	 	cr.setUser(login);
	 	
	 	modele.addAttribute("auth",auth);
	 	modele.addAttribute("user_data",user_data);

	 	modele.addAttribute("tables_data",tables_data);
	 	
	 	modele.addAttribute("tables_metadata",tables_metadata);
	     modele.addAttribute("login",login);

	 	
		return("VuePrincipal8");

	}
	
	
	if(!(auth.get(0).get(0).equals("not found")) ) {

		System.out.println("adlmin");
		System.out.println(login);

		System.out.println(!(auth.get(0).get(0).equals("not found")) );
		System.out.println(!(login.equals("admin")));

	Map<String,List<String>> user_data = new HashMap<String,List<String>>();
	user_data = dt.user_data(adminlogin);
	boolean test = true;
	int indice=-1;
	List<Integer> index = new ArrayList<Integer>();
	Map<String,List<String>> droits = new HashMap<String,List<String>>();
	List<String> dr = new ArrayList<String>();
	
	Iterator<Map.Entry<String, List<String>>> itr = user_data.entrySet().iterator();

	while(itr.hasNext())
	{
		Map.Entry<String, List<String>> entry = itr.next();
		
		System.out.println("Key = " + entry.getKey() +
							", Value = " + entry.getValue());
		
		indice = indice+1;
		test = true;

		for(int i=1;i<auth.size();i++) {
			for(int j=2;j<auth.get(i).size();j++) {
				if(auth.get(i).get(j).split(" ")[0].equals(entry.getKey())) {
				   test = false;
					index.add(indice);
					dr = new ArrayList<String>();
					String[] dr_s = auth.get(i).get(j).split(" ");//[1].split("/");
					dr_s[0] = dr_s[0].replace("(", "");
					dr_s[dr_s.length-1] = dr_s[dr_s.length-1].replace(")", "");

					for(String s:dr_s) {
						dr.add(s);
					}
					droits.put(entry.getKey(), dr);

				}
			}
		}	
		
		
		if(test) {
			user_data.remove(entry.getKey());

		}

	}
	System.out.println("droits " + droits);

	List<Map<String,List<String>>> tables_data_old = new ArrayList<Map<String,List<String>>>();
	List<Map<String,List<String>>> tables_data = new ArrayList<Map<String,List<String>>>();
	tables_data_old = dt.tables_data(adminlogin);
	
	for(int i=0;i<index.size();i++) {
		tables_data.add(tables_data_old.get(index.get(i)));
	}
	
	
	List<Map<String,List<String>>> tables_metadata_old = new ArrayList<Map<String,List<String>>>();
	List<Map<String,List<String>>> tables_metadata = new ArrayList<Map<String,List<String>>>();
	tables_metadata_old = dt.tables_metadata(adminlogin);
	
	for(int i=0;i<index.size();i++) {
		tables_metadata.add(tables_metadata_old.get(index.get(i)));
	}
	
	
	
	
	System.out.println(tables_data);
	
	modele.addAttribute("user_data",user_data);
	modele.addAttribute("droits",droits);

	modele.addAttribute("tables_data",tables_data);
	
	modele.addAttribute("tables_metadata",tables_metadata);
    modele.addAttribute("login",login);
    modele.addAttribute("adminlogin",adminlogin);
    modele.addAttribute("password",password);

	}else {
		
		if(!login.equals("admin")) {
		if(auth.get(0).get(0).equals("not found")) {
		    modele.addAttribute("error","user doesn't exist");
		}
		
		
		

	     Datav3 dt2 = new Datav3(login);
	 	List<List<String>> auth2 = new ArrayList<List<String>>();
	 	auth = 	dt.authentification(login);
	 

	 	Map<String,List<String>> user_data = new HashMap<String,List<String>>();
	 	user_data = dt.user_data(login);
	 	System.out.println(user_data);
	 	List<Map<String,List<String>>> tables_data = new ArrayList<Map<String,List<String>>>();
	 	tables_data = dt.tables_data(login);
	 	
	 	List<Map<String,List<String>>> tables_metadata = new ArrayList<Map<String,List<String>>>();
	 	tables_metadata = dt.tables_metadata(login);
	 	
	 	cr.setUser(login);
	 	
	 	modele.addAttribute("auth",auth);
	 	modele.addAttribute("user_data",user_data);

	 	modele.addAttribute("tables_data",tables_data);
	 	
	 	modele.addAttribute("tables_metadata",tables_metadata);
	     modele.addAttribute("login",login);
	 	System.out.println("kj");
		 return("VuePrincipal8");
		}

	}

	 return("VuePrincipal5");
 }
 
 
 
 
 @RequestMapping(value="/addUsers")
 public String addUsers(@RequestParam(value="login")String login,@RequestParam(value="password")String password, Model modele) throws ParseException{
	Datav3 dt = new Datav3(login);
	List<String> contents= new  ArrayList<String>();
	Map<String,Map<String,List<String>>> usersdbs = new HashMap<String,Map<String,List<String>>>();
	

	 if(dt.uniqueLogin(login)) {
	   try {
		   cr.firstCreation();
		   cr.createUser(login, password);
	       
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	   
	 }else {
		 return("VuePrincipal7");
	 }
	 
	 contents = as.AllUsers();
     usersdbs = as.AllUsersDataBases();
	 
     modele.addAttribute("login",login);
     modele.addAttribute("users", contents);	
     modele.addAttribute("dbUsers", usersdbs);

	 return("VueAdmin");
 }
 
 @RequestMapping(value="/deleteUser")
 public String deleteUser(@RequestParam(value="login")String login, Model modele) throws ParseException{
	 List<String> contents= new  ArrayList<String>();
	 Map<String,Map<String,List<String>>> usersdbs = new HashMap<String,Map<String,List<String>>>();
	 

	 
	 try {
		 du.deleteUser(login);

		  
		   
	 } catch(Exception e) {
	         e.printStackTrace();
	 }
	 contents = as.AllUsers();
	 usersdbs = as.AllUsersDataBases();
	 
     modele.addAttribute("login",login);
     modele.addAttribute("users", contents);	
     modele.addAttribute("dbUsers", usersdbs);

	 return("VueAdmin");
 }
 
 
 
 
}
