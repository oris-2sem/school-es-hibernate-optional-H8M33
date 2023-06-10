package com.example.secondhomework.service;

import com.example.secondhomework.dto.request.ParentRequest;
import com.example.secondhomework.dto.response.ParentResponse;
import com.example.secondhomework.model.users.ParentEntity;
import com.example.secondhomework.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

    private final ParentRepository repository;
    private final StudentService studentService;

    @Override
    public List<ParentResponse> getAllParents() {
        return repository.findAll().stream().map(x -> ParentResponse.builder()
                .id(x.getId())
                .firstName(x.getFirstName())
                .secondName(x.getSecondName())
                .patronymic(x.getPatronymic())
                .username(x.getUsername())
                .build()).toList();
    }

    @Override
    public void createParent(UUID studentId, ParentRequest request) {
        repository.save(ParentEntity.builder()
                .student(studentService.getById(studentId))
                .firstName(request.getFirstName())
                .secondName(request.getSecondName())
                .patronymic(request.getPatronymic())
                .role("PARENT")
                .hashPassword(String.valueOf(request.getHashPassword().hashCode()))
                .username(request.getUsername())
                .build());
    }

    @Override
    public void updateParent(UUID parentId, UUID studentId, ParentRequest request) {
        repository.save(
                repository.findById(parentId)
                        .map(x -> {
                                    x.setStudent(studentService.getById(studentId));
                                    x.setFirstName(request.getFirstName());
                                    x.setSecondName(request.getSecondName());
                                    x.setPatronymic(request.getPatronymic());
                                    x.setHashPassword(String.valueOf(request.getHashPassword().hashCode()));
                                    x.setUsername(request.getUsername());
                                    return x;
                                }
                        )
                        .orElseThrow(RuntimeException::new)
        );
    }

    @Override
    public ParentResponse getParent(UUID parentId) {
        return repository.findById(parentId).map(x -> ParentResponse.builder()
                .id(x.getId())
                .firstName(x.getFirstName())
                .secondName(x.getSecondName())
                .patronymic(x.getPatronymic())
                .username(x.getUsername())
                .build()).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteParent(UUID parentId) {
        repository.deleteById(parentId);
    }
}
