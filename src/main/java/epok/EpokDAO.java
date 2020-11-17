/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epok;

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
public class EpokDAO {
    
    private final String GET_MODULES = "SELECT * FROM epok";
    
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public List<EpokDTO> getModules(){
        
        EpokDTO module = null;
        List<EpokDTO> modules = new ArrayList<EpokDTO>();
        JdbcCon db = new JdbcCon();
        
                try{
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            con = db.openConnection("epokdb");
            ps = con.prepareStatement(GET_MODULES);
            rs = ps.executeQuery();
            
            while(rs.next()){
                module = new EpokDTO();
                module.setModulId(rs.getString(1));
                module.setKurskod(rs.getString(2));
                module.setModul(rs.getString(3));
                modules.add(module);
                  
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return modules;
    }
    
    
}
