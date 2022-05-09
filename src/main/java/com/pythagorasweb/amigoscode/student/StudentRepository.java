package com.pythagorasweb.amigoscode.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Interface serving as the data source layer
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    //find user by email
    @Query("SELECT s FROM Student s WHERE s.email=?1")
    Optional<Student> findStudentByEmail(String email);
}
