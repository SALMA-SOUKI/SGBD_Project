package web;
import dao.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



import fonctionnalite.Create;
import fonctionnalite.delete;


@Controller
public class controller {
	//Selection daoImpl = new Selection();
    Selection2 getQuery ;

	daoImp daoImpl2 = new daoImp();
	DeleteQuery1 deleteQuery ;
	UpdateQuery query;
//	public String executerCommande(@RequestParam(value="request")String command, Model model) {

	 
	@RequestMapping("/executeRequest")
	public String insertionObj(String rqst,Model modele, String login) throws IOException, IOException, InterruptedException{
		String key = null;
		String d ;
		String db ;
		
		String table = "user";

		if(rqst.toUpperCase().contains("GET")) {
			key = "GET";
			table = rqst.split("\\r?\\n")[1].split(">")[1].split(" ")[0];
			
		}
		if(rqst.toUpperCase().contains("DELETE")) {
			key = "DELETE";
		}
		if(rqst.toUpperCase().contains("INSERT")) {
			key = "INSERT";
		}
		if(rqst.toUpperCase().contains("UPDATE")) {
			key = "UPDATE";
		}if(rqst.toUpperCase().contains("DROP")) {
			key = "DROP";
		}
		//String key = //rqst.split(" ")[0];
		System.out.println(login);

		File_c f = new File_c(table,rqst,key,login);

		Thread_f instance = new Thread_f();
		Thread_run th = new Thread_run(instance,f);

		th.start();
		th.join();
		
		
		System.out.println(instance.test);
		
		/*if(instance.test) {
			
		switch(key.toUpperCase()) {
		case "DELETE":
			d = rqst.split("\n")[0];
			db = d.split(" ")[1];
			deleteQuery = new DeleteQuery1(rqst.split("\\r?\\n")[1],login,db.trim());
		    String msg = deleteQuery.deleteRecord();
			modele.addAttribute("fdbb", msg);
			modele.addAttribute("rqst", rqst);
			break;
		case "GET":
			d = rqst.split("\n")[0];
			db = d.split(" ")[1];
			Datav3 dt = new Datav3(login);
			getQuery = new Selection2(rqst.split("\\r?\\n")[1],login,db.trim());
			//String login = "nouhaila";
			modele.addAttribute("fdbb", getQuery.getRecord());
			modele.addAttribute("rqst", rqst);
			break;
			
		case "INSERT":
			d = rqst.split("\n")[0];
			db = d.split(" ")[1];
			String rsp =  new String();
			String fdbb = daoImpl2.insertion(rqst,login);
			System.out.println(rsp);
			modele.addAttribute("fdbb", fdbb);
			modele.addAttribute("rqst", rqst);
			break;
			
		case "UPDATE":
			d = rqst.split("\n")[0];
			db = d.split(" ")[1];
			query = new UpdateQuery(rqst.split("\\r?\\n")[1],login,db.trim());
			modele.addAttribute("fdbb", query.executer());
			modele.addAttribute("rqst", rqst);
			break;
		
		case "DROP":
			d = rqst.split("\n")[0];
			db = d.split(" ")[1];
			delete drop = new delete();
			drop.deleteDb(rqst.split(" ")[1]);
			modele.addAttribute("fdbb", "Database deleted");
			modele.addAttribute("rqst", rqst);
			break;
			
		default:
			modele.addAttribute("fdbb", "syntax error");
			modele.addAttribute("rqst", rqst);
			break;
			
			
			
		}
		}else {
			modele.addAttribute("fdbb", "table is used by another user");
			modele.addAttribute("rqst", rqst);
		}
		*/
		
		modele.addAttribute("fdbb", instance.result );
		modele.addAttribute("rqst", rqst);
//		String rsp =  new String();
//		model.addAttribute("rsp", rsp);
		//fdback fdb = new fdback();
				//fdb.setMsg("succ");
	/*	 Datav2 dt = new Datav2("nouhaila");
 String login = "nouhaila";
		
		modele.addAttribute("fdbb", daoImpl.get(rqst));
		modele.addAttribute("rqst", rqst);
		
		Map<String,List<String>> user_data = new HashMap<String,List<String>>();
		user_data = dt.user_data(login);
		System.out.println(user_data);
		Map<String,List<String>> tables_data = new HashMap<String,List<String>>();
		tables_data = dt.tables_data(login);
		
		Map<String,List<String>> tables_metadata = new HashMap<String,List<String>>();
		tables_metadata = dt.tables_metadata(login);
		
		
		modele.addAttribute("user_data",user_data);
		modele.addAttribute("tables_data",tables_data);
		
		modele.addAttribute("tables_metadata",tables_metadata);
	    modele.addAttribute("login","nouhaila");*/


		//String login = "nouhaila";
		
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
		
		
		modele.addAttribute("user_data",user_data);
		modele.addAttribute("tables_data",tables_data);
		
		modele.addAttribute("tables_metadata",tables_metadata);
	    modele.addAttribute("login",login);
		System.out.println(tables_data);

		
		
		return "VuePrincipal4";
	}
	 
	
	
	@RequestMapping("/executeRequest2")
	public String executeRequest2(String rqst,Model modele, String login, String droits, String password, String adminlogin) throws IOException, IOException, InterruptedException{
		String key = null;
		String table = "user";

		
		String d ;
		String db ;
		System.out.println(droits);
		System.out.println("gtt" + rqst.split("\\r?\\n")[1]);

		if(rqst.toUpperCase().contains("GET")) {
			key = "GET";
			table = rqst.split("\\r?\\n")[1].split(">")[1].split(" ")[0];

		}
		if(rqst.toUpperCase().contains("DELETE")) {
			key = "DELETE";
		}
		if(rqst.toUpperCase().contains("INSERT")) {
			key = "INSERT";
		}
		if(rqst.toUpperCase().contains("UPDATE")) {
			key = "UPDATE";
		}
		if(rqst.toUpperCase().contains("DROP")) {
			key = "DROP";
		}
		//String key = //rqst.split(" ")[0];
		
		
		System.out.println("droits " + droits);
		Map<String,String> droits_edit = new HashMap<String,String>();
		System.out.println("login " + login);
		System.out.println("password" + password);
		
		

		if(login.equals("admin") && password.equals("admin")) {
			d = rqst.split("\n")[0];
			db = d.split(" ")[1];
			droits_edit.put(db.trim(), "(get/delete/insert/update)");
			
			File_c f = new File_c(table,rqst,key,login);

			Thread_f instance = new Thread_f();
			Thread_run th = new Thread_run(instance,f);

			th.start();
			th.join();
			
			
			System.out.println(instance.test);
			
			if(instance.test) {
			
			switch(key.toUpperCase()) {
			case "DELETE":
				d = rqst.split("\n")[0];
				db = d.split(" ")[1];
				deleteQuery = new DeleteQuery1(rqst.split("\\r?\\n")[1],adminlogin,db.trim());
			    String msg = deleteQuery.deleteRecord();
				modele.addAttribute("fdbb", msg);
				modele.addAttribute("rqst", rqst);
				break;
			case "GET":
				d = rqst.split("\n")[0];
				db = d.split(" ")[1];
				System.out.println(adminlogin);
				Datav3 dt = new Datav3(adminlogin);
				getQuery = new Selection2(rqst.split("\\r?\\n")[1],adminlogin,db.trim());
				//String login = "nouhaila";
				modele.addAttribute("fdbb", getQuery.getRecord());
				modele.addAttribute("rqst", rqst);

				break;
				
			case "INSERT":
				d = rqst.split("\n")[0];
				db = d.split(" ")[1];
				String rsp =  new String();
				String fdbb = daoImpl2.insertion(rqst,adminlogin);
				System.out.println(rsp);
				modele.addAttribute("fdbb", fdbb);
				modele.addAttribute("rqst", rqst);

				break;
			
			case "UPDATE":
				d = rqst.split("\n")[0];
				db = d.split(" ")[1];
				query = new UpdateQuery(rqst.split("\\r?\\n")[1],adminlogin,db.trim());
				modele.addAttribute("fdbb", query.executer());
				modele.addAttribute("rqst", rqst);

				break;
				
			case "DROP":
				d = rqst.split("\n")[0];
				db = d.split(" ")[1];
				delete drop = new delete();
				drop.deleteDb(rqst.split(" ")[1]);
				modele.addAttribute("fdbb", "Database deleted");
				modele.addAttribute("rqst", rqst);

				break;
				
			default:
				modele.addAttribute("fdbb", "syntax error");
				modele.addAttribute("rqst", rqst);

				break;
			}
			}
				
			System.out.println("adminlogin " + adminlogin);

			
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
				

				
				modele.addAttribute("auth",auth3);
				modele.addAttribute("user_data",user_data);

				modele.addAttribute("tables_data",tables_data);
				
				modele.addAttribute("tables_metadata",tables_metadata);
			    modele.addAttribute("login",login);
			    modele.addAttribute("adminlogin",adminlogin);
			    modele.addAttribute("password",password);
			    
			    
			    
		return "VuePrincipal6";


		}
		
		String[] dbs;
		String db2 ;
		String commands ;
		if(droits.length()>0) {
		for(String s:droits.split("],")) {
			droits_edit.put(s.trim().split("=")[0].replace("{", ""), s.split("=")[1].replace("}", ""));
		}
		
		
		dbs = droits.split(",");
		db2 = droits.split("=")[0].replace("{", "");
		
		commands = droits.split("=")[1].replace("}", "");
		}
		boolean response = true;
		
		for(Entry<String,String> mp : droits_edit.entrySet()) {
			System.out.println(mp.getKey());
			System.out.println(mp.getValue());

			d = rqst.split("\n")[0];
			db = d.split(" ")[1];
			
			System.out.println(mp.getKey()==db);
			System.out.println(db);
			System.out.println(mp.getKey().contains(db.trim()));
			
		/*File_c f = new File_c(table,rqst,key,login);

			Thread_f instance = new Thread_f();
		Thread_run th = new Thread_run(instance,f);

			th.start();
			th.join();
			
			
			System.out.println(instance.test);*/
			
				
			System.out.println("mp.getValue() "+ mp.getValue().contains("get"));
			System.out.println("mp.getValue() "+ mp.getValue().contains(key.toLowerCase()));
			System.out.println("mp.getValue() "+ mp.getValue().contains(key.toLowerCase()));

		if(mp.getKey().equals(db.trim()) && mp.getValue().contains(key.toLowerCase())) {
			/*if(instance.test) {*/

			File_c f = new File_c(table,rqst,key,login);

			Thread_f instance = new Thread_f();
			Thread_run th = new Thread_run(instance,f);

			th.start();
			th.join();
			
			
			System.out.println(instance.test);
			
			if(instance.test) {
		switch(key.toUpperCase()) {
		case "DELETE":
			d = rqst.split("\n")[0];
			db = d.split(" ")[1];
			deleteQuery = new DeleteQuery1(rqst.split("\\r?\\n")[1],adminlogin,db.trim());
		    String msg = deleteQuery.deleteRecord();
			modele.addAttribute("fdbb", msg);
			modele.addAttribute("rqst", rqst);
			response =false;
			break;
		case "GET":
			d = rqst.split("\n")[0];
			db = d.split(" ")[1];
			System.out.println(adminlogin);
			Datav3 dt = new Datav3(adminlogin);
			getQuery = new Selection2(rqst.split("\\r?\\n")[1],adminlogin,db.trim());
			//String login = "nouhaila";
			modele.addAttribute("fdbb", getQuery.getRecord());
			modele.addAttribute("rqst", rqst);
			response =false;

			break;
			
		case "INSERT":
			d = rqst.split("\n")[0];
			db = d.split(" ")[1];
			String rsp =  new String();
			String fdbb = daoImpl2.insertion(rqst,adminlogin);
			System.out.println(rsp);
			modele.addAttribute("fdbb", fdbb);
			modele.addAttribute("rqst", rqst);
			response =false;

			break;
		
		case "UPDATE":
			d = rqst.split("\n")[0];
			db = d.split(" ")[1];
			query = new UpdateQuery(rqst.split("\\r?\\n")[1],adminlogin,db.trim());
			modele.addAttribute("fdbb", query.executer());
			modele.addAttribute("rqst", rqst);
			response =false;

			break;
			
		case "DROP":
			d = rqst.split("\n")[0];
			db = d.split(" ")[1];
			delete drop = new delete();
			drop.deleteDb(rqst.split(" ")[1]);
			modele.addAttribute("fdbb", "Database deleted");
			modele.addAttribute("rqst", rqst);
			response =false;

			break;
			
		default:
			modele.addAttribute("fdbb", "syntax error");
			modele.addAttribute("rqst", rqst);
			response =false;

			break;
			
			
			
		}}
			/*}*/
		
			//modele.addAttribute("fdbb", instance.result );
			//modele.addAttribute("rqst", rqst);
		}
		
		}
		
		
		
		if(response) {
			
				modele.addAttribute("fdbb", "you are not autorised");
				modele.addAttribute("rqst", rqst);
			
		}
//		String rsp =  new String();
//		model.addAttribute("rsp", rsp);
		//fdback fdb = new fdback();
				//fdb.setMsg("succ");
	/*	 Datav2 dt = new Datav2("nouhaila");
 String login = "nouhaila";
		
		modele.addAttribute("fdbb", daoImpl.get(rqst));
		modele.addAttribute("rqst", rqst);
		
		Map<String,List<String>> user_data = new HashMap<String,List<String>>();
		user_data = dt.user_data(login);
		System.out.println(user_data);
		Map<String,List<String>> tables_data = new HashMap<String,List<String>>();
		tables_data = dt.tables_data(login);
		
		Map<String,List<String>> tables_metadata = new HashMap<String,List<String>>();
		tables_metadata = dt.tables_metadata(login);
		
		
		modele.addAttribute("user_data",user_data);
		modele.addAttribute("tables_data",tables_data);
		
		modele.addAttribute("tables_metadata",tables_metadata);
	    modele.addAttribute("login","nouhaila");*/


		//String login = "nouhaila";
		
		Datav3 dt = new Datav3(adminlogin);
		List<List<String>> auth = new ArrayList<List<String>>();
		auth = 	dt.authentification(adminlogin);
		System.out.println(auth.get(0).get(0));
		System.out.println(auth.get(0).get(1));
		
		
		Map<String,List<String>> user_data = new HashMap<String,List<String>>();
		user_data = dt.user_data(adminlogin);
		boolean test = true;
		int indice=-1;
		List<Integer> index = new ArrayList<Integer>();
		Map<String,List<String>> droit = new HashMap<String,List<String>>();
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
						droit.put(entry.getKey(), dr);

					}
				}
			}	
			
			
			if(test) {
				user_data.remove(entry.getKey());

			}

		}
		
		
	/*	for(Entry<String, List<String>> mp : user_data.entrySet()) {
			indice = indice+1;
			test = true;

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
						droit.put(mp.getKey(), dr);

					}
				}
			}	
			
			
			if(test) {
				user_data.remove(mp.getKey());

			}

		}*/
		
		System.out.println(droits);

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



		
		
		return "VuePrincipal6";
	}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	
	}

