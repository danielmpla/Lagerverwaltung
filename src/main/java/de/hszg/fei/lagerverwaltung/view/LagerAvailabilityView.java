package de.hszg.fei.lagerverwaltung.view;

import de.hszg.fei.lagerverwaltung.entity.Teil;

public class LagerAvailabilityView {
    private Teil teil;
    private Long date;
    private int menge;

    public LagerAvailabilityView() {
    }

    public LagerAvailabilityView(Teil teil, Long date, int menge) {
        this.teil = teil;
        this.date = date;
        this.menge = menge;
    }

    public Teil getTeil() {
        return teil;
    }

    public void setTeil(Teil teil) {
        this.teil = teil;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }
}
