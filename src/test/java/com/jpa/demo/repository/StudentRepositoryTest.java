package com.jpa.demo.repository;

import com.jpa.demo.entity.Guardian;
import com.jpa.demo.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void create(){
        Student student = Student.builder()
                .emailId("bharaththermal@gmail.com")
                .firstName("Bharath")
                .lastName("M")
//                .guardianName("Chandrakal B")
//                .guardianEmail("chandrakal@gmail.com")
//                .guardianMobile("9480475982")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void saveWithGuardian(){
        Guardian guardian = Guardian.builder()
                .email("chandrakala@gmail.com")
                .name("Chandrakal B")
                .mobile("9480475982")
                .build();

        Student student = Student.builder()
                .emailId("bharaththermal@gmail.com")
                .firstName("Bharath")
                .lastName("M")
                .guardian(guardian)        //Look here
                .build();

        studentRepository.save(student);
    }

    @Test
    public void getAll(){
        List<Student> s = studentRepository.findAll();
    }

    @Test
    public void getByFirstName(){
        List<Student> students = studentRepository.findByFirstName("Bharath");
        System.out.println(students);
    }

    @Test
    public void getByFirstNameContaining(){
        List<Student> students = studentRepository.findByFirstNameContaining("Bha");
        System.out.println(students);
    }

    @Test
    public void findByGuardian(){
        List<Student> students = studentRepository.findByGuardianName("Chandrakal B");
        System.out.println(students);
    }
}