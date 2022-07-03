package com.example.spring.data.jpa.repository;

import com.example.spring.data.jpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // would interact with DB
// @DataJpaTest // would not interact with DB - once we run app, will flush the testing data
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .emailId("john@test.com")
                .firstName("John")
                .lastName("Will")
                .guardianName("Michael")
                .guardianEmail("mic@test.com")
                .guardianMobile("9876543210")
                .build();
        studentRepository.save(student);
    }

    @Test
    public void printAllStudent() {
        List<Student> studentList = studentRepository.findAll();
        System.out.println(studentList);
    }
}