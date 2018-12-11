package mk.finki.ukim.wp.studentsapi.service;

import mk.finki.ukim.wp.studentsapi.model.Student;
import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudyProgramNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StudentService {
    List<Student> findAll();

    Optional<Student> findById(String index);

    List<Student> findAllByStudyProgram(StudyProgram studyProgram);

    Student save(Student student);

    void update(Student student, Map<String, String> reqBody) throws StudyProgramNotFoundException;

    void delete(Student student);
}
