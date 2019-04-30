package de.hszg.fei.lagerverwaltung.view;

public class FahrzeugView {
    private Long karosserieId;
    private Long fahrwerkId;
    private Long innenausstattungId;
    private Long radId;

    private int anzahlRaeder;

    private int menge;
    private Long produktionsDatum;

    public FahrzeugView() {
    }

    public FahrzeugView(Long karosserieId, Long fahrwerkId, Long innenausstattungId, Long radId, int anzahlRaeder, int menge, Long produktionsDatum) {
        this.karosserieId = karosserieId;
        this.fahrwerkId = fahrwerkId;
        this.innenausstattungId = innenausstattungId;
        this.radId = radId;
        this.anzahlRaeder = anzahlRaeder;
        this.menge = menge;
        this.produktionsDatum = produktionsDatum;
    }

    public Long getKarosserieId() {
        return karosserieId;
    }

    public void setKarosserieId(Long karosserieId) {
        this.karosserieId = karosserieId;
    }

    public Long getFahrwerkId() {
        return fahrwerkId;
    }

    public void setFahrwerkId(Long fahrwerkId) {
        this.fahrwerkId = fahrwerkId;
    }

    public Long getInnenausstattungId() {
        return innenausstattungId;
    }

    public void setInnenausstattungId(Long innenausstattungId) {
        this.innenausstattungId = innenausstattungId;
    }

    public Long getRadId() {
        return radId;
    }

    public void setRadId(Long radId) {
        this.radId = radId;
    }

    public int getAnzahlRaeder() {
        return anzahlRaeder;
    }

    public void setAnzahlRaeder(int anzahlRaeder) {
        this.anzahlRaeder = anzahlRaeder;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    public Long getProduktionsDatum() {
        return produktionsDatum;
    }

    public void setProduktionsDatum(Long produktionsDatum) {
        this.produktionsDatum = produktionsDatum;
    }
}
