/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gradewebservice;

import canvas.CanvasDAO;
import canvas.CanvasDTO;
import canvas.StudentGradeDTO;
import java.net.URI;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
    
    //exempel: http://localhost:8080/GradeWebService/resources/gradeform/student?studentid=joeele-8
    @Path("/student")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentByQueryParam(@QueryParam("studid") String studentId){
        StudentGradeDTO student = new CanvasDAO().getStudentById(studentId);
        return Response.ok(student).build();
    }
    
    //exempel: http://localhost:8080/GradeWebService/resources/gradeform/joeele-8
    @Path("{studentid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentById(@PathParam("studentid") String studentId){
        StudentGradeDTO student = new CanvasDAO().getStudentById(studentId);
        return Response.ok(student).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
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
}
