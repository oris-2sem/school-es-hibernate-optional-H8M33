package com.example.secondhomework.service;

import com.example.secondhomework.dto.request.StudentRequest;
import com.example.secondhomework.dto.response.StudentResponse;
import com.example.secondhomework.model.users.StudentEntity;
import com.example.secondhomework.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    private final MainGroupService mainGroupService;

    private final SubGroupService subGroupService;

    @Override
    public StudentEntity getById(UUID x) {
        return repository.findById(x).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        return repository.findAll().stream().map(x -> StudentResponse.builder()
                .id(x.getId())
                .firstName(x.getFirstName())
                .secondName(x.getSecondName())
                .patronymic(x.getPatronymic())
                .username(x.getUsername())
                .build()).toList();
    }

    @Override
    public void createStudent(UUID mainGroupId, StudentRequest request) {
        repository.save(StudentEntity.builder()
                .firstName(request.getFirstName())
                .secondName(request.getSecondName())
                .patronymic(request.getPatronymic())
                .role("STUDENT")
                .hashPassword(String.valueOf(request.getHashPassword().hashCode()))
                .username(request.getUsername())
                .mainGroup(mainGroupService.getById(mainGroupId))
                .subGroup(request.getSubgroup_id().stream().map(subGroupService::getById).collect(Collectors.toSet()))
                .build());
    }

    @Override
    public void updateStudent(UUID mainGroupId, UUID studentId, StudentRequest request) {
        repository.save(
                repository.findById(studentId)
                        .map(x -> {
                                    x.setFirstName(request.getFirstName());
                                    x.setSecondName(request.getSecondName());
                                    x.setPatronymic(request.getPatronymic());
                                    x.setHashPassword(String.valueOf(request.getHashPassword().hashCode()));
                                    x.setUsername(request.getUsername());
                                    x.setMainGroup(mainGroupService.getById(mainGroupId));
                                    x.setSubGroup(request.getSubgroup_id().stream().map(subGroupService::getById).collect(Collectors.toSet()));
                                    return x;
                                }
                        )
                        .orElseThrow(RuntimeException::new)
        );
    }

    @Override
    public StudentResponse getStudent(UUID studentId) {
        return repository.findById(studentId).map(x -> StudentResponse.builder()
                .id(x.getId())
                .firstName(x.getFirstName())
                .secondName(x.getSecondName())
                .patronymic(x.getPatronymic())
                .username(x.getUsername())
                .build()).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteStudent(UUID studentId) {
        repository.deleteById(studentId);
    }
}
