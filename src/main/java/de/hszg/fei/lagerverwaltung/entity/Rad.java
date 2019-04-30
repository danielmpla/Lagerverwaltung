package de.hszg.fei.lagerverwaltung.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rad extends Teil {
	private String farbe;
	
	public Rad() {
		super();
	}
	
	public Rad(Long id, String farbe, int platzbedarf, long seitWann, int lieferzeit, float kosten, String name) {
		super(id, platzbedarf, seitWann, lieferzeit, kosten, name);
		this.farbe = farbe;
	}
	
	public Rad(String farbe, int platzbedarf, long seitWann, int lieferzeit, float kosten, String name) {
		super(platzbedarf, seitWann, lieferzeit, kosten, name);
		this.farbe = farbe;
	}

	public String getFarbe() {
		return farbe;
	}

	public void setFarbe(String farbe) {
		this.farbe = farbe;
	}

	public void update(Rad other) {
		if (other.farbe != null) {
			this.farbe = other.farbe;
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
