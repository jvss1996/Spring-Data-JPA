package com.example.spring.data.jpa.repository;

import com.example.spring.data.jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    public List<Student> findByFirstName(String firstName);

    public List<Student> findByFirstNameContaining(String name);

    public List<Student> findByLastNameNotNull();

    public List<Student> findByGuardianName(String guardianName);

    // JPQL Query - based on classes we created, not by tables in DB
    @Query("select s from Student s where s.emailId = ?1")
    public Student getStudentByEmailAddress(String emailId);

    @Query("select s.firstName from Student s where s.emailId = ?1")
    public String getStudentFirstNameByEmailAddress(String emailId);

    // Native SQL Query
    @Query(value = "SELECT * FROM tbl_student s where s.email_address = ?1", nativeQuery = true)
    public Student getStudentByEmailAddressNative(String emailId);

    // Native Named Param
    @Query(value = "SELECT * FROM tbl_student s where s.email_address = :emailId", nativeQuery = true)
    public Student getStudentByEmailAddressNativeNamedParam(@Param("emailId") String emailId);
}
