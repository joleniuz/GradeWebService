/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentits;

import studentits.StudentItsDTO;
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
public class StudentItsDAO { 
    
    private final String GET_PERS_INFO = "SELECT idPersonNr, StudentId FROM studentits";
    
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public List<StudentItsDTO> getPersonInfo(){
        
        StudentItsDTO personInfo = null;
        List<StudentItsDTO> personInfoList = new ArrayList<StudentItsDTO>();
        JdbcCon db = new JdbcCon();
        
                try{
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            con = db.openConnection("studentitsdb");
            ps = con.prepareStatement(GET_PERS_INFO);
            rs = ps.executeQuery();
            
            while(rs.next()){
                personInfo = new StudentItsDTO();
                personInfo.setPersonNr(rs.getString(1));
                personInfo.setStudentId(rs.getString(2));
                personInfoList.add(personInfo);
                  
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return personInfoList;
    }
}
