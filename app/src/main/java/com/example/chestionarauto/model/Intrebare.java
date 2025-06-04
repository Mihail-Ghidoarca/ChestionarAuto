package com.example.chestionarauto.model;

import java.util.List;

public class Intrebare {
    private String intrebare;
    private List<String> optiuni;
    private int raspunsCorect;
    private String explicatie;
    private String sectiune;

    public Intrebare(String intrebare, List<String> optiuni, int raspunsCorect, String explicatie, String sectiune) {
        this.intrebare = intrebare;
        this.optiuni = optiuni;
        this.raspunsCorect = raspunsCorect;
        this.explicatie = explicatie;
        this.sectiune = sectiune;
    }

    public String getIntrebare() { return intrebare; }
    public List<String> getOptiuni() { return optiuni; }
    public int getRaspunsCorect() { return raspunsCorect; }
    public String getExplicatie() { return explicatie; }
    public String getSectiune() { return sectiune; }
}
