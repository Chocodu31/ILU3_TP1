package cartes;

public enum Type {
	FEU ("feu"),
	ESSENCE ("essence"),
	CREVAISON ("crevaison"),
	ACCIDENT ("accident");

	private String type;
	
	Type(String type) {
		this.type = type;
	}
	
	public String toString() {
		return type;
	}
	
}
