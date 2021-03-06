/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ladok;

import canvas.CanvasDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jdbc.JdbcCon;

/**
 *
 * @author Joel
 */
public class LadokDAO {
    
    private final String GET_GRADES = "SELECT * FROM ladok";
    private final String GET_GRADES_BY_PERSNR = "SELECT * FROM ladok WHERE persnr=?";
    private final String GET_GRADES_BY_COURSE = "SELECT * FROM ladok WHERE kurskod=?";
    private final String ADD_GRADE = "INSERT INTO ladok (persnr, namn, kurskod, modul, datum, betyg, status)"
    + "VALUES(?, ?, ?, ?, ?, ?, ?) ";
    
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
        public List<LadokDTO> getGrades(){
        
        LadokDTO grade = null;
        List<LadokDTO> grades = new ArrayList<LadokDTO>();
        JdbcCon db = new JdbcCon();
        
        try{
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            con = db.openConnection("ladokdb");
            ps = con.prepareStatement(GET_GRADES);
            rs = ps.executeQuery();
            
            while(rs.next()){
                grade = new LadokDTO();
                grade.setPersonNr(rs.getString(2));
                grade.setNamn(rs.getString(3));
                grade.setKurskod(rs.getString(4));
                grade.setModul(rs.getString(5));
                grade.setDatum(rs.getString(6));
                grade.setBetyg(rs.getString(7));
                grade.setStatus(rs.getString(8));
                grades.add(grade);    
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return grades;
    }
    
    public List <LadokDTO> getGradeByPersNr(String persNr){
        
        LadokDTO grade = null;
        List <LadokDTO> grades = new ArrayList<LadokDTO>();
        JdbcCon db = new JdbcCon();
        
        try{
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            con = db.openConnection("ladokdb");
            ps = con.prepareStatement(GET_GRADES_BY_PERSNR);
            ps.setString(1, persNr);
            rs = ps.executeQuery();
            
            while(rs.next()){
                grade = new LadokDTO();
                grade.setPersonNr(rs.getString(2));
                grade.setNamn(rs.getString(3));
                grade.setKurskod(rs.getString(4));
                grade.setModul(rs.getString(5));
                grade.setDatum(rs.getString(6));
                grade.setBetyg(rs.getString(7));
                grade.setStatus(rs.getString(8));
                grades.add(grade);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        
        return grades;
    }
        public List<LadokDTO> getGradesByCourse(String kurskod){
        
        LadokDTO grade = null;
        List<LadokDTO> grades = new ArrayList<LadokDTO>();
        JdbcCon db = new JdbcCon();
        
        try{
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            con = db.openConnection("ladokdb");
            ps = con.prepareStatement(GET_GRADES_BY_COURSE);
            ps.setString(1, kurskod);
            rs = ps.executeQuery();
            
            while(rs.next()){
                grade = new LadokDTO();
                grade.setPersonNr(rs.getString(2));
                grade.setNamn(rs.getString(3));
                grade.setKurskod(rs.getString(4));
                grade.setModul(rs.getString(5));
                grade.setDatum(rs.getString(6));
                grade.setBetyg(rs.getString(7));
                grade.setStatus(rs.getString(8));
                grades.add(grade);
                  
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return grades;
    }
    
    public LadokDTO addGrade(String persNr, String namn, String kurskod,
        String modul, String datum, String betyg, String status){
        
        LadokDTO grade = null;
        JdbcCon db = new JdbcCon();
        int update = 0;
        
        try{
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            con = db.openConnection("ladokdb");
            ps = con.prepareStatement(ADD_GRADE);
            ps.setString(2, persNr);
            ps.setString(3, namn);
            ps.setString(4, kurskod);
            ps.setString(5, modul);
            ps.setString(6, datum);
            ps.setString(7, betyg);
            ps.setString(8, status);
            update = ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        
        return grade;
    }
}
