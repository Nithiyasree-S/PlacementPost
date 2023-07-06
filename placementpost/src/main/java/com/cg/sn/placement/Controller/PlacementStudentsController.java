package com.cg.sn.placement.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cg.sn.placement.Service.StudentService;
import com.cg.sn.placement.dto.StudentDTO;

@RestController
@RequestMapping("/api/placement/students")
public class PlacementStudentsController {
    
    private StudentService studentService;

    @Autowired
    public void PlacementController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> addStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO addedStudent = studentService.addStudent(studentDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("data", addedStudent);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getStudentById(@PathVariable("id") Long id) {
        StudentDTO studentDTO = studentService.getStudentById(id);
        if (studentDTO != null) {
            Map<String, Object> response = new HashMap<>();
        response.put("data", studentDTO);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents();
        Map<String, Object> response = new HashMap<>();
        response.put("data", students);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateStudent(@PathVariable("id") Long id, @RequestBody StudentDTO studentDTO) {
        StudentDTO updatedStudent = studentService.updateStudent(id, studentDTO);
        if (updatedStudent != null) {
            Map<String, Object> response = new HashMap<>();
        response.put("data", updatedStudent);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long id) {
        boolean isDeleted = studentService.deleteStudent(id);
        if (isDeleted) {
           
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
