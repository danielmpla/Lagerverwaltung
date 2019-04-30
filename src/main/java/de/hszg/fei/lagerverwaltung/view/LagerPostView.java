package de.hszg.fei.lagerverwaltung.view;

public class LagerPostView {
    private Long id;
    private Long eintreffen;
    private Integer menge;

    public LagerPostView() {
    }

    public LagerPostView(Long id, Long eintreffen, Integer menge) {
        this.id = id;
        this.eintreffen = eintreffen;
        this.menge = menge;
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

    public Integer getMenge() {
        return menge;
    }

    public void setMenge(Integer menge) {
        this.menge = menge;
    }
}
