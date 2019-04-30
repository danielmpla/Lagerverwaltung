package de.hszg.fei.lagerverwaltung.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class LagerInnenausstattung extends Lager {
    @ManyToOne
    private Innenausstattung innenausstattung;

    public LagerInnenausstattung() {
    }

    public LagerInnenausstattung(Long id, Long eintreffen, Innenausstattung innenausstattung) {
        super(id, eintreffen);
        this.innenausstattung = innenausstattung;
    }

    public Innenausstattung getInnenausstattung() {
        return innenausstattung;
    }

    public void setInnenausstattung(Innenausstattung innenausstattung) {
        this.innenausstattung = innenausstattung;
    }
}
