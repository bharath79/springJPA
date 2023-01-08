package com.jpa.demo.repository;

import com.jpa.demo.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    //    JPA query methods
    List<Student> findByFirstName(String firstName);

    List<Student> findByFirstNameContaining(String name);

    List<Student> findByLastNameNotNull();

    List<Student> findByGuardianName(String guardianName);

    //    jpql
    @Query("select s from Student s where s.emailId= ?1")
    Student getStudentByEmailAddress(String emailId);

    @Query("select s.firstName from Student s where s.emailId= ?1")
    String getStudentFirstNameByEmailAddress(String emailId);

    //    native query
    @Query(value = "select * from tbl_student s where s.email_address= ?1", nativeQuery = true)
    String getStudentByEmailAddressNative(String emailId);

    //    native named param
    @Query(value = "select * from tbl_student s where s.email_address= :emailId", nativeQuery = true)
    String getStudentByEmailAddressNativeNamedParam(@Param("emailId") String emailId);

    @Modifying
    @Transactional
    @Query(value = "update tbl_student set first_name =?1 where s.email_address= ?2", nativeQuery = true)
    int updateStudentByEmailId(String firstName, String emailId);

}
