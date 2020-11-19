/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas;

/**
 *
 * @author Joel
 */
public class StudentGradeDTO {
    
    private String persNr;
    private String studId;
    private String namn;
    private String kurskod;
    private String modul;
    private String omdöme;
    private String betyg;
    private String statusBetyg;

    public String getStudId() {
        return studId;
    }

    public void setStudId(String studId) {
        this.studId = studId;
    }
    
    public String getOmdöme() {
        return omdöme;
    }

    public void setOmdöme(String omdöme) {
        this.omdöme = omdöme;
    }

    public String getStatusBetyg() {
        return statusBetyg;
    }

    public void setStatusBetyg(String statusBetyg) {
        this.statusBetyg = statusBetyg;
    }

    public String getPersNr() {
        return persNr;
    }

    public void setPersNr(String persNr) {
        this.persNr = persNr;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }
    
    public String getKurskod() {
        return kurskod;
    }

    public void setKurskod(String kurskod) {
        this.kurskod = kurskod;
    }

    public String getModul() {
        return modul;
    }

    public void setModul(String modul) {
        this.modul = modul;
    }

    public String getBetyg() {
        return betyg;
    }

    public void setBetyg(String betyg) {
        this.betyg = betyg;
    }
    
}
