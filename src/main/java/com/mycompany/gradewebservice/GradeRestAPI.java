
package com.mycompany.gradewebservice;

import student.StudentDTO;
import student.StudentDAO;
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
@Path("students")
public class GradeRestAPI {
    
    @GET
    @Path("test")
    public String helloWorld(){
        return "Ni kan ju det här!";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson(){
        List<StudentDTO> actors = new StudentDAO().getStudents();
        return Response.ok(actors).build();    
    }
    
    //exempel: http://localhost:8080/GradeWebService/resources/students/joeele-8
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentById(@PathParam("id") String studentId){
        StudentDTO actor = new StudentDAO().getStudentById(studentId);
        return Response.ok(actor).build();
    }
    
    //exempel: http://localhost:8080/GradeWebService/resources/students/student?id=joeele-8
    @Path("/student")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentByQueryParam(@QueryParam("id") String studentId){
        StudentDTO student = new StudentDAO().getStudentById(studentId);
        return Response.ok(student).build();
    }
    
}
