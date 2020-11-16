/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gradewebservice;

import studentits.StudentItsDAO;
import studentits.StudentItsDTO;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Joel
 */
@Path("/persinfo")
public class StudentItsAPI {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersInfoJson(){
        
        List<StudentItsDTO> modules = new StudentItsDAO().getPersonInfo();
        
        return Response.ok(modules).build();
    }
}
