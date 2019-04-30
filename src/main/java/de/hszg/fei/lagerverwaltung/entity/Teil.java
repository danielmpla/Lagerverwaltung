package de.hszg.fei.lagerverwaltung.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Teil {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int platzbedarf;
	private long seitWann;
	private int lieferzeit;
	private float kosten;
	private String name;

	public Teil() {
	}
	
	public Teil(int platzbedarf, long seitWann, int lieferzeit, float kosten, String name) {
		this.platzbedarf = platzbedarf;
		this.seitWann = seitWann;
		this.lieferzeit = lieferzeit;
		this.kosten = kosten;
		this.name = name;
	}
	
	public Teil(Long id, int platzbedarf, long seitWann, int lieferzeit, float kosten, String name) {
		this.id = id;
		this.platzbedarf = platzbedarf;
		this.seitWann = seitWann;
		this.lieferzeit = lieferzeit;
		this.kosten = kosten;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPlatzbedarf() {
		return platzbedarf;
	}

	public long getSeitWann() {
		return seitWann;
	}

	public int getLieferzeit() {
		return lieferzeit;
	}

	public float getKosten() {
		return kosten;
	}

	public String getName() {
		return name;
	}
	
	public void setPlatzbedarf(int platzbedarf) {
		this.platzbedarf = platzbedarf;
	}
	
	public void setSeitWann(long seitWann) {
		this.seitWann = seitWann;
	}
	
	public void setLieferzeit(int lieferzeit) {
		this.lieferzeit = lieferzeit;
	}
	
	public void setKosten(float kosten) {
		this.kosten = kosten;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
