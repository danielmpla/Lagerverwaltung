package de.hszg.fei.lagerverwaltung.entity;

public class Person {
	private String vorname;
	private String name;
	
	private boolean darfFahrzeugeErstellen;
	private boolean darfEinkaufen;

	public Person() {
		
	}
	
	public Person(String vorname, String name, boolean darfFahrzeugeErstellen, boolean darfEinkaufen) {
		this.vorname = vorname;
		this.name = name;
		this.darfFahrzeugeErstellen = darfFahrzeugeErstellen;
		this.darfEinkaufen = darfEinkaufen;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDarfFahrzeugeErstellen() {
		return darfFahrzeugeErstellen;
	}

	public boolean isDarfEinkaufen() {
		return darfEinkaufen;
	}
}
