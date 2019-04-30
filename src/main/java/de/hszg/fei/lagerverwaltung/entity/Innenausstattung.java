package de.hszg.fei.lagerverwaltung.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Innenausstattung extends Teil {
	private String typ;

	public Innenausstattung() {
		super();
	}

	public Innenausstattung(Long id, String typ, int platzbedarf, long seitWann, int lieferzeit, float kosten, String name) {
		super(id, platzbedarf, seitWann, lieferzeit, kosten, name);
		this.typ = typ;
	}
	
	public Innenausstattung(String typ, int platzbedarf, long seitWann, int lieferzeit,	float kosten, String name) {
		super(platzbedarf, seitWann, lieferzeit, kosten, name);
		this.typ = typ;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public void update(Innenausstattung other) {
		if (other.typ != null) {
			this.typ = other.typ;
		}

		if (other.getName() != null) {
			this.setName(other.getName());
		}

		this.setPlatzbedarf(other.getPlatzbedarf());
		this.setSeitWann(other.getSeitWann());
		this.setLieferzeit(other.getLieferzeit());
		this.setKosten(other.getKosten());
	}
}
