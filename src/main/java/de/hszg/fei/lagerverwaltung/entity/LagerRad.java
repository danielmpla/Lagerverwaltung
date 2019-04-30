package de.hszg.fei.lagerverwaltung.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class LagerRad extends Lager {
    @ManyToOne
    private Rad rad;

    public LagerRad() {
    }

    public LagerRad(Long id, Long eintreffen, Rad rad) {
        super(id, eintreffen);
        this.rad = rad;
    }

    public Rad getRad() {
        return rad;
    }

    public void setRad(Rad rad) {
        this.rad = rad;
    }
}
