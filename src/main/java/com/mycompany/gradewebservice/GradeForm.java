/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gradewebservice;

import canvas.CanvasDAO;
import canvas.StudentGradeDTO;
import java.net.URI;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Joel
 */
@Path("gradeform")
public class GradeForm {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGradesJson(){
        List<StudentGradeDTO> grades = new CanvasDAO().getStudentGrades(); 
        return Response.ok(grades).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postStudentGradesForm(@FormParam("persnr") String persNr,
            @FormParam("namn") String namn, @FormParam("kurskod") String kurskod,
            @FormParam("modul") String modul, @FormParam("betyg") String betyg,
            @FormParam("statusBetyg") String statusBetyg, @FormParam("datum") String datum){
        if(!(persNr.isEmpty())){
            StudentGradeDTO grade = new CanvasDAO().addGrade(persNr, namn, kurskod, modul, datum, betyg, statusBetyg);
            return Response.created(URI.create("grades/"+persNr)).build();
        }
        else{
            return null;
        }
    }
    
    public static String stringToJson(String persNr, String namn, String kurskod, String modul, String datum, String betyg, String statusBetyg ) {
        return String.format("{\"persNr\":\"%s\",\"namn\":\"%s\",\"kurskod\":\"%s\",\"modul\":\"%s\",\"datum\":\"%s\",\"betyg\":\"%s\",\"statusBetyg\":\"%s\"}",
                             persNr, namn, kurskod, modul, datum, betyg, statusBetyg);
    }

    /*@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void Response(List<StudentGradeDTO> grades){
            List <StudentGradeDTO> hej = new CanvasDAO().postStudentGrades();
            //return Response.created(URI.create("grades/")).build();
        
    } */
}
