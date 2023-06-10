package com.example.secondhomework.controller;

import com.example.secondhomework.dto.request.ParentRequest;
import com.example.secondhomework.dto.request.StudentRequest;
import com.example.secondhomework.dto.response.ParentResponse;
import com.example.secondhomework.dto.response.StudentResponse;
import com.example.secondhomework.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    @GetMapping()
    public List<StudentResponse> getAllStudents(){
        return service.getAllStudents();
    }

    @PostMapping("/{mainGroupId}")
    public void createStudent(@PathVariable UUID mainGroupId, @RequestBody StudentRequest request){
        service.createStudent(mainGroupId, request);
    }

    @PutMapping("/{studentId}/{mainGroupId}")
    public void updateStudent(@PathVariable UUID mainGroupId, @PathVariable UUID studentId, @RequestBody StudentRequest request){
        service.updateStudent(mainGroupId, studentId, request);
    }

    @GetMapping("/{studentId}")
    public StudentResponse getStudent(@PathVariable UUID studentId){
        return service.getStudent(studentId);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable UUID studentId){
        service.deleteStudent(studentId);
    }
}
