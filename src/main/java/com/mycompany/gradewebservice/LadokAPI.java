/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gradewebservice;

import java.net.URI;
import ladok.LadokDAO;
import ladok.LadokDTO;
import epok.EpokDAO;
import epok.EpokDTO;
import java.net.URISyntaxException;
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
@Path("ladok")
public class LadokAPI {
    
    //exempel: http://localhost:8080/GradeWebService/resources/ladok/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGradesJson(){
        List<LadokDTO> actors = new LadokDAO().getGrades();
        return Response.ok(actors).build();    
    }
    
    //exempel: http://localhost:8080/GradeWebService/resources/ladok/XXXXXXXXXX
    @Path("{persnr}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGradeByPersNr(@PathParam("persnr") String persNr){
        LadokDTO grade = new LadokDAO().getGradeByPersNr(persNr);
        return Response.ok(grade).build();
    }
    
    //Lektion 13, 12:30 POST i JAX-RS
    //Alt ta emot Json, skicka Json Lektion 13 26:00
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newGradeByForm(@FormParam("persnr") String persNr,
            @FormParam("namn") String namn, @FormParam("kurskod") String kurskod,
            @FormParam("modul") String modul, @FormParam("datum") String datum,
            @FormParam("betyg") String betyg, @FormParam("status") String status)throws URISyntaxException{
        if(!(persNr.isEmpty())){
            LadokDTO grade = new LadokDAO().addGrade(persNr, namn, kurskod, modul, datum, betyg, status );      
            return Response.created(URI.create("ladok/"+persNr)).build();
        } else {
            return null; //Response.status(400).entity(new Message("Värden saknas")).build();
        }
        
    }
    
}
