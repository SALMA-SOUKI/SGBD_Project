package dao;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import dao.Fifo;
import fonctionnalite.delete;

public class Thread_f {
	private static  Queue<File_c> file_attente = new LinkedList<File_c>();
	public static boolean test = false;
	public static String result;
	static Selection2 getQuery ;
	static boolean  response = true;


	static daoImp daoImpl2 = new daoImp();
	static DeleteQuery1 deleteQuery ;
	static UpdateQuery query;
	static String d ;
	static String db ;

	public static synchronized void enter_file(File_c f) throws InterruptedException, IOException {
		Thread.sleep(100);

		if(!file_attente.isEmpty()) {
		for(File_c f1 : file_attente) {
		if(!(f1.getFile_name().equals(f.getFile_name()))) {
			file_attente.add(f);
			test = true;	
			
			switch(f.getKey().toUpperCase()) {
			case "DELETE":
				d = f.getCommand().split("\n")[0];
				db = d.split(" ")[1];
				deleteQuery = new DeleteQuery1(f.getCommand().split("\\r?\\n")[1],f.getLogin(),db.trim());
			    String msg = deleteQuery.deleteRecord();
				result = msg;
				break;
			case "GET":
				d = f.getCommand().split("\n")[0];
				db = d.split(" ")[1];
				Datav3 dt = new Datav3(f.getLogin());
				getQuery = new Selection2(f.getCommand().split("\\r?\\n")[1],f.getLogin(),db.trim());
				//String login = "nouhaila";
			
				result = getQuery.getRecord() + "";
				break;
				
			case "INSERT":
				d = f.getCommand().split("\n")[0];
				db = d.split(" ")[1];
				String rsp =  new String();
				String fdbb = daoImpl2.insertion(f.getCommand(),f.getLogin());
				System.out.println("res " + fdbb);

				result = fdbb;
				break;
				
			case "UPDATE":
				d = f.getCommand().split("\n")[0];
				db = d.split(" ")[1];
				query = new UpdateQuery(f.getCommand().split("\\r?\\n")[1],f.getLogin(),db.trim());
				int nbl = query.executer();
				if(nbl == -1) {
					result = "Une erreur s'est produite";
				} else {
					result = "Le nombre de lignes modifiées est :" + nbl;
				}
				break;
			
			case "DROP":
				d = f.getCommand().split("\n")[0];
				db = f.getCommand().split(" ")[1];
				delete drop = new delete();
				drop.deleteDb(f.getCommand().split(" ")[1]);
				drop.deleteDb(f.getCommand().split(" ")[1]);
				result = "Database deleted";
				
				break;
				
			default:
				result = "syntax error";
				response = false;
				break;
				
				
				
			}
			
		}
		
		}
		}else {
			System.out.println("el");
			file_attente.add(f);
			test = true;	
			switch(f.getKey().toUpperCase()) {
			case "DELETE":
				d = f.getCommand().split("\n")[0];
				db = d.split(" ")[1];
				deleteQuery = new DeleteQuery1(f.getCommand().split("\\r?\\n")[1],f.getLogin(),db.trim());
			    String msg = deleteQuery.deleteRecord();
				result = msg;
				break;
			case "GET":
				d = f.getCommand().split("\n")[0];
				db = d.split(" ")[1];
				Datav3 dt = new Datav3(f.getLogin());
				getQuery = new Selection2(f.getCommand().split("\\r?\\n")[1],f.getLogin(),db.trim());
				//String login = "nouhaila";
			
				result = getQuery.getRecord() + "";
				break;
				
			case "INSERT":
				d = f.getCommand().split("\n")[0];
				db = d.split(" ")[1];
				String rsp =  new String();
				String fdbb = daoImpl2.insertion(f.getCommand(),f.getLogin());
				System.out.println("res " + fdbb);
				result = fdbb;
				break;
				
			case "UPDATE":
				d = f.getCommand().split("\n")[0];
				db = d.split(" ")[1];
				query = new UpdateQuery(f.getCommand().split("\\r?\\n")[1],f.getLogin(),db.trim());
				int nbl = query.executer();
				if(nbl == -1) {
					result = "Une erreur s'est produite";
				} else {
					result = "Le nombre de lignes modifiées est :" + nbl;
				}
				break;
			
			case "DROP":
				d = f.getCommand().split("\n")[0];
				db = f.getCommand().split(" ")[1];
				delete drop = new delete();
				drop.deleteDb(f.getCommand().split(" ")[1]);
				drop.deleteDb(f.getCommand().split(" ")[1]);
				result = "Database deleted";
				
				break;
				
			default:
				result = "syntax error";
				response = false;

				break;
			}
		}
		
		file_attente.remove(f);
		System.out.println("result " + result);
		
		}
		

	


}
