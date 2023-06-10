package com.example.secondhomework.model.users;

import javax.persistence.*;

import com.example.secondhomework.model.SubjectEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@Entity
@Table(name = "teachers")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherEntity extends AbstractUserEntity {

    @ManyToMany
    @JoinTable(
            name = "subject_teacher",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<SubjectEntity> subjects;
}
