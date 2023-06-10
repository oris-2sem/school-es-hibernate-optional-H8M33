package com.example.secondhomework.service;

import com.example.secondhomework.dto.request.SubjectRequest;
import com.example.secondhomework.dto.response.SubjectResponse;
import com.example.secondhomework.model.SubjectEntity;
import com.example.secondhomework.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService{

    private final SubjectRepository repository;

    private final TeacherService teacherService;

    @Override
    public SubjectEntity getByTitle(String subject) {
        return repository.findByTitle(subject).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<SubjectResponse> getAllSubjects() {
        return repository.findAll().stream().map(x -> SubjectResponse.builder()
                .id(x.getId())
                .title(x.getTitle())
                .build()).toList();
    }

    @Override
    public void createSubject(SubjectRequest request) {
        repository.save(SubjectEntity.builder()
                        .title(request.getTitle())
                        .teachers(request.getTeacher_username().stream().map(teacherService::getByUsername).collect(Collectors.toSet()))
                .build());
    }

    @Override
    public void updateSubject(UUID subjectId, SubjectRequest request) {

    }

    @Override
    public SubjectResponse getSubject(UUID subjectId) {
        return repository.findById(subjectId).map(x -> SubjectResponse.builder()
                .id(x.getId())
                .title(x.getTitle())
                .build()).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteSubject(UUID subjectId) {
        repository.deleteById(subjectId);
    }
}
