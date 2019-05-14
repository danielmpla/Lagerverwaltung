package de.hszg.fei.lagerverwaltung.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Fahrzeug {
	@Id @GeneratedValue
	private Long id;

    private String name;

	@ManyToOne
	private Karosserie karosserie;
	@ManyToOne
	private Fahrwerk fahrwerk;
	@ManyToOne
	private Innenausstattung innenausstattung;
	@ManyToOne
	private Rad rad;
	
	private int anzahlRaeder;
	
	private int menge;
	private long produktionstermin;

	public Fahrzeug() {
		
	}

    public Fahrzeug(Long id, String name, Karosserie karosserie, Fahrwerk fahrwerk, Innenausstattung innenausstattung, Rad rad, int anzahlRaeder, int menge, long produktionstermin) {
        this.name = name;
		this.id = id;
		this.karosserie = karosserie;
		this.fahrwerk = fahrwerk;
		this.innenausstattung = innenausstattung;
		this.rad = rad;
		this.anzahlRaeder = anzahlRaeder;
		this.menge = menge;
		this.produktionstermin = produktionstermin;
	}

    public Fahrzeug(String name, Karosserie karosserie, Fahrwerk fahrwerk, Innenausstattung innenausstattung, Rad rad, int anzahlRaeder, int menge, long produktionstermin) {
        this.name = name;
		this.karosserie = karosserie;
		this.fahrwerk = fahrwerk;
		this.innenausstattung = innenausstattung;
		this.rad = rad;
		this.anzahlRaeder = anzahlRaeder;
		this.menge = menge;
		this.produktionstermin = produktionstermin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public Karosserie getKarosserie() {
		return karosserie;
	}

	public void setKarosserie(Karosserie karosserie) {
		this.karosserie = karosserie;
	}

	public Fahrwerk getFahrwerk() {
		return fahrwerk;
	}

	public void setFahrwerk(Fahrwerk fahrwerk) {
		this.fahrwerk = fahrwerk;
	}

	public Innenausstattung getInnenausstattung() {
		return innenausstattung;
	}

	public void setInnenausstattung(Innenausstattung innenausstattung) {
		this.innenausstattung = innenausstattung;
	}

	public Rad getRad() {
		return rad;
	}

	public void setRad(Rad rad) {
		this.rad = rad;
	}

	public int getAnzahlRaeder() {
		return anzahlRaeder;
	}

	public void setAnzahlRaeder(int anzahlRaeder) throws Exception {
		if (anzahlRaeder < 2) {
            throw new Exception("Zu wenige Räder");
		}
		this.anzahlRaeder = anzahlRaeder;
	}

	public int getMenge() {
		return menge;
	}

	public void setMenge(int menge) {
		this.menge = menge;
	}

	public long getProduktionstermin() {
		return produktionstermin;
	}

	public void setProduktionstermin(long produktionstermin) {
		this.produktionstermin = produktionstermin;
	}
}
