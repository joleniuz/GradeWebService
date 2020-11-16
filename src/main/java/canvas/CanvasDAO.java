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
    private final String ADD_STUDENT = "INSERT INTO canvas (StudentId, Omdöme, Namn, Kurskod)"
            + "VALUES(?, ?, ?, ?) ";
    
    Connection con = null;
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
                student.setStudentId(rs.getString(1));
                student.setOmdöme(rs.getString(2));
                student.setNamn(rs.getString(3));
                student.setKurskod(rs.getString(4));
                students.add(student);
                  
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return students;
    }
    
    public CanvasDTO getStudentById(String studentId){
        
        CanvasDTO student = null;
        JdbcCon db = new JdbcCon();
        
        try{
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            con = db.openConnection("canvasdb");
            ps = con.prepareStatement(GET_STUDENTS_BY_ID);
            ps.setString(1, studentId);
            rs = ps.executeQuery();
            
            if(rs.next()){
                student = new CanvasDTO();
                student.setStudentId(rs.getString(1));
                student.setOmdöme(rs.getString(2));
                student.setNamn(rs.getString(3));
                student.setKurskod(rs.getString(4));
            
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        
        return student;
    }
    
    public CanvasDTO addStudent(String studentId, String omdöme, String namn,  String kurskod){
        
        CanvasDTO student = null;
        JdbcCon db = new JdbcCon();
        int status = 0;
        
        try{
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            con = db.openConnection("canvasdb");
            ps = con.prepareStatement(ADD_STUDENT);
            ps.setString(1, studentId);
            ps.setString(2, omdöme);
            ps.setString(3, namn);
            ps.setString(4, kurskod);
            status = ps.executeUpdate();
            
            /*if(rs.next()){
                student = new CanvasDTO();
                student.setStudentId(rs.getString(1));
                student.setOmdöme(rs.getString(2));
                student.setNamn(rs.getString(3));
                student.setKurskod(rs.getString(4));
            
            }*/

        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        
        return student;
    }
}
