/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

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
public class StudentDAO {
    
    private final String GET_STUDENTS = "SELECT * FROM canvas";
    private final String GET_STUDENTS_BY_ID = "SELECT * FROM canvas WHERE studentid=?";
    
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public List<StudentDTO> getStudents(){
        
        StudentDTO student = null;
        List<StudentDTO> students = new ArrayList<StudentDTO>();
        JdbcCon db = new JdbcCon();
        
        try{
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            con = db.openConnection();
            ps = con.prepareStatement(GET_STUDENTS);
            rs = ps.executeQuery();
            
            while(rs.next()){
                student = new StudentDTO();
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
    
    public StudentDTO getStudentById(String studentId){
        
        StudentDTO student = null;
        JdbcCon db = new JdbcCon();
        
        try{
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            con = db.openConnection();
            ps = con.prepareStatement(GET_STUDENTS_BY_ID);
            ps.setString(1, studentId);
            rs = ps.executeQuery();
            
            if(rs.next()){
                student = new StudentDTO();
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
}
