package de.hszg.fei.lagerverwaltung.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class LagerKarosserie extends Lager {
    @ManyToOne
    private Karosserie karosserie;

    public LagerKarosserie() {
    }

    public LagerKarosserie(Long id, Long eintreffen, Karosserie karosserie) {
        super(id, eintreffen);
        this.karosserie = karosserie;
    }

    public Karosserie getKarosserie() {
        return karosserie;
    }

    public void setKarosserie(Karosserie karosserie) {
        this.karosserie = karosserie;
    }
}
