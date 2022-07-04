package com.example.spring.data.jpa.repository;

import com.example.spring.data.jpa.entity.Course;
import com.example.spring.data.jpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {
    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher() {
        Course courseDB = Course.builder()
                .title("DB")
                .credit(3)
                .build();

        Course courseArch = Course.builder()
                .title("Architecture")
                .credit(3)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("Mick")
                .lastName("Lat")
//                .courses(List.of(courseDB, courseArch))
                .build();

        teacherRepository.save(teacher);
    }
}