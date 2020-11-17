/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas;

import java.util.List;

/**
 *
 * @author Joel
 */
public class CanvasDTO {
      
    private String studentId;
    private String omdöme;
    private String namn;
    private String kurskod;
    private String modul;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getOmdöme() {
        return omdöme;
    }

    public void setOmdöme(String omdöme) {
        this.omdöme = omdöme;
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
    
}
