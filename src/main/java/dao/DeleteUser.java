package dao;

import java.io.File;

public class DeleteUser {

	 	public String user;

	    public String getUser() {
	        return user;
	    }

	    public void setUser(String user) {
	        this.user = user;
	    }

	    public void deleteUser(String login) {
	        this.user = login;
	        File userDir = new File("C://SGBD//" + login);
	        
	        if (userDir.exists()) {
	        	deleteDirectory(userDir);
	            System.out.println("User " + login + " and their associated database have been deleted.");
	        } else {
	            System.out.println("User " + login + "does not exist or already deleted!");
	        }
	    }
	    
	    public static boolean deleteDirectory(File dir) {
	        if (dir.isDirectory()) {
	            File[] children = dir.listFiles();
	            for (int i = 0; i < children.length; i++) {
	                boolean success = deleteDirectory(children[i]);
	                if (!success) {
	                    return false;
	                }
	            }
	        }
	        return dir.delete();
	    }
	    
	    
}