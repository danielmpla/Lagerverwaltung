package de.hszg.fei.lagerverwaltung.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class LagerFahrwerk extends Lager {
    @ManyToOne
    private Fahrwerk fahrwerk;

    public LagerFahrwerk() {
    }

    public LagerFahrwerk(Long id, Long eintreffen, Fahrwerk fahrwerk) {
        super(id, eintreffen);
        this.fahrwerk = fahrwerk;
    }

    public Fahrwerk getFahrwerk() {
        return fahrwerk;
    }

    public void setFahrwerk(Fahrwerk fahrwerk) {
        this.fahrwerk = fahrwerk;
    }
}
