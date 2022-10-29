package net.javaguides.srpingboot.controller;

import jakarta.websocket.server.PathParam;
import net.javaguides.srpingboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    //http://localhost:8080/student
    @GetMapping("student")
    public Student getStudent(){
        Student student = new Student(
                1,
        "John",
         "Doe"
        );
        return student;
    }
    //using Response Entity
    //http://localhost:8080/student
    @GetMapping("student1")
    public ResponseEntity<Student> getStudent1(){
        Student student = new Student(
                1,
                "Response Entity",
                "Doe"
        );
        //return new ResponseEntity<>(student, HttpStatus.OK);
        return ResponseEntity.ok(student);
    }

    //using header
    @GetMapping("student2")
    public ResponseEntity<Student> getStudent2(){
        Student student = new Student(
                1,
                "Response Entity",
                "Doe"
        );
        //return new ResponseEntity<>(student, HttpStatus.OK);
        return ResponseEntity.ok()
                .header("custom-header","johny")
                .body(student);
    }






    //http://localhost:8080/students
    @GetMapping("students")
    public List<Student> getStudents(){
        List<Student> students= new ArrayList<>();
        students.add(new Student(2,"Salih", "Qosimi"));
        students.add(new Student(3,"Semih","Kuchkarov"));
        students.add(new Student(4,"Zayd", "Kuchkarov"));
        return students;
    }
    //Spring Boot REST API with Path VAriable
    //{id} -- UIR template Variable
    //http://localhost:8080/student/1
    @GetMapping("students/{id}")
    public Student studentPathVariable(@PathVariable("id") int studentId){
        return new Student(studentId, "Hilola","Muhammad");

    }
    @GetMapping("students/{id}/{first-name}/{last-name}")
    public Student studentPathVariable1(@PathVariable("id") int studentId,
                                        @PathVariable("first-name")String firstName,
                                        @PathVariable("last-name") String lastName){
        return new Student(studentId, firstName,lastName);

    }

    //Spring Boot REST API with Request Param
    // http://localhost:8080/students?id=1
    @GetMapping("student/query")
    public Student studentRequestParam(@RequestParam int id){
        return new Student(id , "Ihsan", "Kuchkarov");
    }
    @GetMapping("students/query")
    public Student studentRequestParams(@RequestParam int id,
                                        @RequestParam String firstName,
                                        @RequestParam String lastName){
        return new Student(id , firstName, lastName);
    }

    //Spring boot REST API that handles HTTP Post Request
    //create a new resource
    //@PostMapping and @RequestBody
    @PostMapping("student/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }
    //Spring Boot REST API that handles the PUT Request
    //updating the existing resource
    @PutMapping("student/{id}/update")
    public Student editStudent(@RequestBody Student student,@PathVariable("id") int studentId){
        //System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }
    //REST API that deletes Delete Method-HTTP Delete Request
    @DeleteMapping("student/{id}/delete")
    public String deleteStudentint (@PathVariable("id") int studentId){
        System.out.println(studentId);
        return "Student Deleted Successfully";
    }

}
