package de.hszg.fei.lagerverwaltung.view;

public class LagerAvailabilityView {
    private Long date;
    private int menge;

    public LagerAvailabilityView() {
    }

    public LagerAvailabilityView(Long date, int menge) {
        this.date = date;
        this.menge = menge;
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
