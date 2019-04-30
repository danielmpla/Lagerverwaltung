package de.hszg.fei.lagerverwaltung.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Lager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long eintreffen;

    public Lager() {
    }

    public Lager(Long id, Long eintreffen) {
        this.id = id;
        this.eintreffen = eintreffen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEintreffen() {
        return eintreffen;
    }

    public void setEintreffen(Long eintreffen) {
        this.eintreffen = eintreffen;
    }
}
