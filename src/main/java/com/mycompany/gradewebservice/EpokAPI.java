/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gradewebservice;

import java.net.URI;
import epok.EpokDAO;
import epok.EpokDTO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Joel
 */
@Path("modules")
public class EpokAPI {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesJson(){
        
        List<EpokDTO> modules = new EpokDAO().getModules();
        
        return Response.ok(modules).build();
    }
}
