package de.hszg.fei.lagerverwaltung.entity;

import javax.persistence.Entity;

public class Einkaeufer extends Person {

	public Einkaeufer() {
		super();
	}

	public Einkaeufer(String vorname, String name) {
		super(vorname, name, false, true);
	}
	
}
