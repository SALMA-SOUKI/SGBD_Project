package dao;

import java.util.LinkedList;
import java.util.Queue;



public class Fifo {
	
	 private static  Queue file_attente = new LinkedList<File_c>();

	    
	    public  void add(File_c f) {
	    	file_attente.add(f);
	    }
	    
	   
	    public File_c delete() {
	    	File_c f = (File_c) file_attente.remove();
	    	return f;
	    }

		public static  Queue getFile_attente() {
			return file_attente;
		}

		public  void setFile_attente(Queue file_attente) {
			file_attente = file_attente;
		}

		public int size() {
			return file_attente.size();
		}
		
		

}
