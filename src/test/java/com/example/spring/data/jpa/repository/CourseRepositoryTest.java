package com.example.spring.data.jpa.repository;

import com.example.spring.data.jpa.entity.Course;
import com.example.spring.data.jpa.entity.Student;
import com.example.spring.data.jpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses() {
        List<Course> courses = courseRepository.findAll();
        System.out.println("Courses = " + courses);
    }

    @Test
    public void saveCourseWithTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Ross")
                .lastName("Sol")
                .build();

        Course course = Course.builder()
                .title("Core Java")
                .credit(3)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    @Test
    public void findAllPagination() {
        Pageable firstPageWithThreeRecords = PageRequest.of(0, 3);

        List<Course> courses = courseRepository.findAll(firstPageWithThreeRecords).getContent();

        long totalElements = courseRepository.findAll(firstPageWithThreeRecords).getTotalElements();

        long totalPages = courseRepository.findAll(firstPageWithThreeRecords).getTotalPages();

        System.out.println("courses = " + courses);

        System.out.println("totalElements = " + totalElements);

        System.out.println("totalPages = " + totalPages);
    }

    @Test
    public void findAllSorting() {
        Pageable sortByTitle = PageRequest.of(0, 3, Sort.by("title"));
        Pageable sortByCreditDesc = PageRequest.of(0, 3, Sort.by("credit").descending());
        Pageable sortByTitleAndCreditDesc = PageRequest.of(0, 3, Sort.by("title").descending()
                .and(Sort.by("credit")));
        List<Course> courses = courseRepository.findAll(sortByTitleAndCreditDesc).getContent();
        System.out.println("courses = " + courses);
    }

    @Test
    public void findByTitleContaining() {
        Pageable firstPageTwoRecords = PageRequest.of(0, 2);
        List<Course> courses = courseRepository.findByTitleContaining("B", firstPageTwoRecords).getContent();
        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Karl")
                .lastName("Max")
                .build();
        Student student = Student.builder()
                .firstName("Leon")
                .lastName("Yukes")
                .emailId("leon@gmail.com")
                .build();
        Course course = Course.builder()
                .title("ML")
                .credit(3)
                .teacher(teacher)
                .build();
        course.addStudent(student);
        courseRepository.save(course);
    }
}