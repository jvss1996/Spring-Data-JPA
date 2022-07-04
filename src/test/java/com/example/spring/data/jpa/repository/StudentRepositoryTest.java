package com.example.spring.data.jpa.repository;

import com.example.spring.data.jpa.entity.Guardian;
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
//                .guardianName("Michael")
//                .guardianEmail("mic@test.com")
//                .guardianMobile("9876543210")
                .build();
        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian() {
        Guardian guardian = Guardian.builder()
                .email("mic@test.com")
                .name("Michael")
                .mobile("9876543210")
                .build();
        Student student = Student.builder()
                .emailId("bob@test.com")
                .firstName("Bob")
                .lastName("Lett")
                .guardian(guardian)
                .build();
        studentRepository.save(student);
    }

    @Test
    public void printAllStudent() {
        List<Student> studentList = studentRepository.findAll();
        System.out.println(studentList);
    }

    @Test
    public void printStudentByFirstName() {
        List<Student> students = studentRepository.findByFirstName("John");
        System.out.println("Student = " + students);
    }

    @Test
    public void printStudentByFirstNameContaining() {
        List<Student> students = studentRepository.findByFirstNameContaining("Jo");
        System.out.println("Student = " + students);
    }

    @Test
    public void printStudentByLastNameNotNull() {
        List<Student> students = studentRepository.findByLastNameNotNull();
        System.out.println("Student = " + students);
    }

    @Test
    public void printStudentByGuardianName() {
        List<Student> students = studentRepository.findByGuardianName("Michael");
        System.out.println("Student = " + students);
    }

    @Test
    public void getStudentByEmailAddress() {
        Student students = studentRepository.getStudentByEmailAddress("john@test.com");
        System.out.println("Student = " + students);
    }

    @Test
    public void getStudentFirstNameByEmailAddress() {
        String students = studentRepository.getStudentFirstNameByEmailAddress("john@test.com");
        System.out.println("Student = " + students);
    }

    @Test
    public void getStudentByEmailAddressNative() {
        Student students = studentRepository.getStudentByEmailAddressNative("john@test.com");
        System.out.println("Student = " + students);
    }

    @Test
    public void getStudentByEmailAddressNativeNamedParam() {
        Student students = studentRepository.getStudentByEmailAddressNativeNamedParam("john@test.com");
        System.out.println("Student = " + students);
    }

    @Test
    public void updateStudentNameByEmailId() {
        studentRepository.updateStudentNameByEmailId("John Test","john@test.com");
    }
}