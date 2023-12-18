package dao;
public class Attribut {
	private String nom;
	private String type;
	private boolean isPrimaryKey;
	public Attribut(String nom, String type, boolean isPrimaryKey) {
		super();
		this.nom = nom;
		this.type = type;
		this.isPrimaryKey = isPrimaryKey;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}
	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}
	
	
}