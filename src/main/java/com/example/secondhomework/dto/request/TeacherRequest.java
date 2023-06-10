package com.example.secondhomework.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherRequest {
    private String username;
    
    private String hashPassword;
    
    private String firstName;
    
    private String secondName;
    
    private String patronymic;

    private List<String> subjects_title;
}
