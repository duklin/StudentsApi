package mk.finki.ukim.wp.studentsapi.service.impl;

import mk.finki.ukim.wp.studentsapi.model.Student;
import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudyProgramNotFoundException;
import mk.finki.ukim.wp.studentsapi.persistance.StudentRepository;
import mk.finki.ukim.wp.studentsapi.persistance.StudyProgramRepository;
import mk.finki.ukim.wp.studentsapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;
    private StudyProgramRepository studyProgramRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, StudyProgramRepository studyProgramRepository){
        this.studentRepository = studentRepository;
        this.studyProgramRepository = studyProgramRepository;
    }

    @Override
    public List<Student> findAll() {
        return this.studentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(String index) {
        return this.studentRepository.findById(index);
    }

    @Override
    public List<Student> findAllByStudyProgram(StudyProgram studyProgram) {
        return this.studentRepository.findAllByStudyProgram(studyProgram);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void update(Student student, Map<String, String> reqBody) throws StudyProgramNotFoundException {
        if(reqBody.containsKey("name")){
            student.setName(reqBody.get("name"));
        }
        if(reqBody.containsKey("lastName")){
            student.setLastName(reqBody.get("lastName"));
        }
        if(reqBody.containsKey("studyProgramName")){
            student.setStudyProgram(studyProgramRepository
                    .findStudyProgramByName(reqBody.get("studyProgramName"))
                    .orElseThrow(() -> new StudyProgramNotFoundException(reqBody.get("studyProgramName"))));
        }
        studentRepository.save(student);
    }

    @Override
    public void delete(Student student) {
        studentRepository.delete(student);
    }
}
