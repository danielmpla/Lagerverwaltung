package de.hszg.fei.lagerverwaltung.view;

public class LagerAvailabilityView {
    private Long teileId;
    private Long date;
    private int menge;

    public LagerAvailabilityView() {
    }

    public LagerAvailabilityView(Long teileId, Long date, int menge) {
        this.teileId = teileId;
        this.date = date;
        this.menge = menge;
    }

    public Long getTeileId() {
        return teileId;
    }

    public void setTeileId(Long teileId) {
        this.teileId = teileId;
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
