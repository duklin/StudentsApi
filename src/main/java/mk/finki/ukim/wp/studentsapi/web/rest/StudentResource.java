package mk.finki.ukim.wp.studentsapi.web.rest;

import mk.finki.ukim.wp.studentsapi.model.Student;
import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudentNotFoundException;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudyProgramNotFoundException;
import mk.finki.ukim.wp.studentsapi.service.StudentService;
import mk.finki.ukim.wp.studentsapi.service.StudyProgramService;
import mk.finki.ukim.wp.studentsapi.web.DTO.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentResource {
    private StudentService studentService;
    private StudyProgramService studyProgramService;

    @Autowired
    public StudentResource(StudentService studentService, StudyProgramService studyProgramService) {
        this.studentService = studentService;
        this.studyProgramService = studyProgramService;
    }

    @GetMapping
    public List<StudentDTO> findAll() {
        return this.studentService.findAll().stream()
                .map(StudentDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{index}")
    public Student findById(@PathVariable("index") String index) throws StudentNotFoundException {
        return this.studentService.findById(index).orElseThrow(() -> new StudentNotFoundException(index));
    }

    @GetMapping(value = "/by_study_program/{id}")
    public List<Student> findAllByStudyProgram(@PathVariable("id") Long id) throws StudyProgramNotFoundException {
        StudyProgram studyProgram = this.studyProgramService.findById(id).orElseThrow(() -> new StudyProgramNotFoundException(id));
        return this.studentService.findAllByStudyProgram(studyProgram);
    }

    @PostMapping
    public ResponseEntity<Object> createStudent(@Valid @RequestBody StudentDTO student) throws StudyProgramNotFoundException {
        StudyProgram studyProgram = studyProgramService.findByName(student.getStudyProgramName()).orElseThrow(() -> new StudyProgramNotFoundException(student.getStudyProgramName()));
        Student s = new Student(student.getIndex(), student.getName(), student.getLastName(), studyProgram);
        Student savedStudent = studentService.save(s);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedStudent.getIndex()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping(value = "/{index}")
    public ResponseEntity<Object> updateStudent(@PathVariable("index") String index, @RequestBody Map<String, String> reqBody) throws StudentNotFoundException, StudyProgramNotFoundException {
        Student student = studentService.findById(index).orElseThrow(() -> new StudentNotFoundException(index));
        if(reqBody.containsKey("studyProgramName")){
            studyProgramService.findByName(reqBody.get("studyProgramName")).orElseThrow(() -> new StudyProgramNotFoundException(reqBody.get("studyProgramName")));
        }
        studentService.update(student, reqBody);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{index}")
    public ResponseEntity<Object> deleteStudent(@PathVariable("index") String index) throws StudentNotFoundException {
        Student student = studentService.findById(index).orElseThrow(() -> new StudentNotFoundException(index));
        studentService.delete(student);
        return new ResponseEntity(HttpStatus.OK);
    }
}
