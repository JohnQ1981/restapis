package net.javaguides.srpingboot.controller;

import jakarta.websocket.server.PathParam;
import net.javaguides.srpingboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
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
                "Response Entity-2",
                "Doe"
        );
        //return new ResponseEntity<>(student, HttpStatus.OK);
        return ResponseEntity.ok()
                .header("custom-header","johny")
                .body(student);
    }






    //http://localhost:8080/students
    @GetMapping()
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students= new ArrayList<>();
        students.add(new Student(2,"Salih", "Qosimi"));
        students.add(new Student(3,"Semih","Kuchkarov"));
        students.add(new Student(4,"Zayd", "Kuchkarov"));
        //return students;
        return ResponseEntity.ok(students);
    }
    //Spring Boot REST API with Path VAriable
    //{id} -- UIR template Variable
    //http://localhost:8080/student/1
    @GetMapping("{id}")
    public Student studentPathVariable(@PathVariable("id") int studentId){
        return new Student(studentId, "Hilola","Muhammad");

    }
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable1(@PathVariable("id") int studentId,
                                        @PathVariable("first-name")String firstName,
                                        @PathVariable("last-name") String lastName){
        Student student= new Student(studentId, firstName,lastName);
        return ResponseEntity.ok(student);

    }

    //Spring Boot REST API with Request Param
    // http://localhost:8080/students?id=1
    @GetMapping("student/query")
    public ResponseEntity<Student> studentRequestParam(@RequestParam int id){
        Student student = new Student(id , "Ihsan from Request Param", "Kuchkarov");
        return ResponseEntity.ok(student);
    }
    @GetMapping("query")
    public Student studentRequestParams(@RequestParam int id,
                                        @RequestParam String firstName,
                                        @RequestParam String lastName){
        return new Student(id , firstName, lastName);
    }

    //Spring boot REST API that handles HTTP Post Request
    //create a new resource
    //@PostMapping and @RequestBody
    @PostMapping("student/create")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
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
    public ResponseEntity<String> deleteStudentint (@PathVariable("id") int studentId){
        System.out.println(studentId);
        return ResponseEntity.ok("Student Deleted Successfully");
    }

}
