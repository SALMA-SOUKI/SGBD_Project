package dao;

public class File_c {
	private String file_name;
	private String command;
	private String key;
	private String login;


	
	
	public File_c(String file_name, String command, String key, String login) {
		super();
		this.file_name = file_name;
		this.command = command;
		this.key = key;
		this.login = login;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	
	

}
