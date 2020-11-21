/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas;

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
public class CanvasDAO {
    
    private final String GET_STUDENTS = "SELECT * FROM canvas";
    private final String GET_STUDENTS_BY_ID = "SELECT * FROM canvas WHERE studentid=?";
    private final String ADD_STUDENT = "INSERT INTO canvas (StudentId, Omdöme, Namn, Kurskod, Modul)"
            + "VALUES(?, ?, ?, ?, ?) ";
    private final String GET_STUDENT_GRADES = "SELECT studentitsdb.studentits.idpersonnr, studentitsdb.studentits.studentid, studentitsdb.studentits.namn, canvasdb.canvas.kurskod, canvasdb.canvas.modul, canvasdb.canvas.omdöme\n" +
"FROM canvasdb.canvas LEFT JOIN studentitsdb.studentits ON studentitsdb.studentits.studentid=canvasdb.canvas.studentid;";
    private final String ADD_STUDENT_GRADE = "INSERT INTO ladokdb.ladok (persnr, namn, kurskod, modul, datum, betyg, status)\n" +
"VALUES(?, ?, ?, ?, ?, ?, ?);";
    private final String GET_STUDENT_GRADES2 = "SELECT studentitsdb.studentits.idpersonnr, studentitsdb.studentits.studentid, studentitsdb.studentits.namn,\n"
            + "canvasdb.canvas.kurskod, canvasdb.canvas.modul, canvasdb.canvas.omdöme,\n"
            + "ladokdb.ladok.Betyg, ladokdb.ladok.Datum, ladokdb.ladok.status FROM canvasdb.canvas LEFT JOIN studentitsdb.studentits ON studentitsdb.studentits.studentid=canvasdb.canvas.studentid\n"
            + "LEFT JOIN ladokdb.ladok ON studentitsdb.studentits.idPersonNr= ladokdb.ladok.PersNr;";
    Connection con = null;
    //Connection con2 = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public List<CanvasDTO> getStudents(){
        
        CanvasDTO student = null;
        List<CanvasDTO> students = new ArrayList<CanvasDTO>();
        JdbcCon db = new JdbcCon();
        
        try{
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            con = db.openConnection("canvasdb");
            ps = con.prepareStatement(GET_STUDENTS);
            rs = ps.executeQuery();
            
            while(rs.next()){
                student = new CanvasDTO();
                student.setStudentId(rs.getString(2));
                student.setOmdöme(rs.getString(3));
                student.setNamn(rs.getString(4));
                student.setKurskod(rs.getString(5));
                student.setModul(rs.getString(6));
                students.add(student);
                  
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return students;
    }
    
    public StudentGradeDTO getStudentById(String studentId){
        
        StudentGradeDTO student = null;
        JdbcCon db = new JdbcCon();
        
        try{
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            con = db.openConnection("canvasdb");
            ps = con.prepareStatement(GET_STUDENTS_BY_ID);
            ps.setString(1, studentId);
            rs = ps.executeQuery();
            
            if(rs.next()){
                student = new StudentGradeDTO();  
                student.setStudId(rs.getString(2));
                student.setOmdöme(rs.getString(3));
                student.setNamn(rs.getString(4));
                student.setKurskod(rs.getString(5));
                student.setModul(rs.getString(6));
            
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        
        return student;
    }
    
    public CanvasDTO addStudent(String studentId, String omdöme, String namn,  String kurskod, String modul){
        
        CanvasDTO student = null;
        JdbcCon db = new JdbcCon();
        int status = 0;
        
        try{
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            con = db.openConnection("canvasdb");
            ps = con.prepareStatement(ADD_STUDENT);
            ps.setString(2, studentId);
            ps.setString(3, omdöme);
            ps.setString(4, namn);
            ps.setString(5, kurskod);
            ps.setString(6, modul);
            
            status = ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
     
        return student;
    }
    
    public List<StudentGradeDTO> getStudentGrades(){
        
        StudentGradeDTO studentGrade = null;
        List<StudentGradeDTO> studentGrades = new ArrayList<StudentGradeDTO>();
        JdbcCon db = new JdbcCon();
        
        try{
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            con = db.openConnection("canvasdb");
            ps = con.prepareStatement(GET_STUDENT_GRADES2);
            rs = ps.executeQuery();
            
            while(rs.next()){
                studentGrade = new StudentGradeDTO();
                studentGrade.setPersNr(rs.getString(1));
                studentGrade.setStudId(rs.getString(2));
                studentGrade.setNamn(rs.getString(3));
                studentGrade.setKurskod(rs.getString(4));
                studentGrade.setModul(rs.getString(5));
                studentGrade.setOmdöme(rs.getString(6));
                studentGrade.setBetyg(rs.getString(7));
                studentGrade.setDatum(rs.getString(8));
                studentGrade.setStatusBetyg(rs.getString(9));
                studentGrades.add(studentGrade);

                  
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
                
        return studentGrades;     
    }
    
        public StudentGradeDTO addGrade(String persNr, String namn, String kurskod, String modul, String datum, String betyg, String betygStatus){
        
        StudentGradeDTO studentGrade = null;
        JdbcCon db = new JdbcCon();
        int status = 0;
        
        try{
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            con = db.openConnection("ladokdb");
            //persnr, namn, kurskod, modul, datum, betyg, status
            ps = con.prepareStatement(ADD_STUDENT_GRADE);
            ps.setString(1, persNr);
            ps.setString(2, namn);
            ps.setString(3, kurskod);
            ps.setString(4, modul);
            ps.setString(5, datum);
            ps.setString(6, betyg);
            ps.setString(7, betygStatus);
            
            status = ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
     
        return studentGrade;
    }
    
    /*public List<StudentGradeDTO> postStudentGrades(String persnr, String namn, String kurskod, String modul, String datum, String betyg, String betygStatus)
    public List<StudentGradeDTO> postStudentGrades(){
        
        StudentGradeDTO studentGrade = null;
        List<StudentGradeDTO> studentGrades = new ArrayList<StudentGradeDTO>();
        JdbcCon db = new JdbcCon();
        int status = 0;
        
        try{
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            con = db.openConnection("ladokdb");
            ps = con.prepareStatement(POST_STUDENT_GRADES);
            
            while(rs.next()){
                studentGrade = new StudentGradeDTO();
                ps.setString(2, studentGrade.getPersNr());
                ps.setString(3, studentGrade.getNamn());
                ps.setString(4, studentGrade.getKurskod());
                ps.setString(5, studentGrade.getModul());
                ps.setString(6, studentGrade.getDatum());
                ps.setString(7, studentGrade.getBetyg());
                ps.setString(8, studentGrade.getStatusBetyg());
                
                status = ps.executeUpdate();
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        return null;
    }*/
}
