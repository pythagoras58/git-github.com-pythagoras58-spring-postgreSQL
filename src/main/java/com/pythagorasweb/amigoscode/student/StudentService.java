package com.pythagorasweb.amigoscode.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//Business Logic for the StudentController class file
@Service
public class StudentService {

    // use the repository class
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        //get the email
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        //check if found
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email Already Used");
        }else {
            studentRepository.save(student);
        }
    }

    public void deleteStudent(Long studentId) {
        //studentRepository.findById(studentId);
        boolean exist = studentRepository.existsById(studentId);

        if(!exist){
            throw new IllegalStateException("Student with id:: " + studentId + " Not Found");
        }else{
            studentRepository.deleteById(studentId);
        }
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new IllegalStateException("Student Not FOund"));


        if(name != null && name.length() >0 && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }

        if(email != null && email.length() >0 && !Objects.equals(student.getEmail(),email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

            if(studentOptional.isPresent()){
                throw new IllegalStateException("Email Already Taken");
            }
            student.setEmail(email);
        }

    }



}

