package mk.finki.ukim.wp.studentsapi.web.rest;

import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudyProgramNotFoundException;
import mk.finki.ukim.wp.studentsapi.service.StudyProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/study_programs", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudyProgramResource {
    private StudyProgramService studyProgramService;

    @Autowired
    public StudyProgramResource(StudyProgramService studyProgramService){
        this.studyProgramService = studyProgramService;
    }

    @GetMapping
    public List<StudyProgram> findAllStudyPrograms(){
        return studyProgramService.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> createStudyProgram(@Valid @RequestBody StudyProgram studyProgram){
        StudyProgram savedStudyProgram = studyProgramService.save(studyProgram);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedStudyProgram.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudyProgram(@PathVariable("id") Long id) throws StudyProgramNotFoundException {
        StudyProgram studyProgram = studyProgramService.findById(id).orElseThrow(() -> new StudyProgramNotFoundException(id));
        studyProgramService.delete(studyProgram);
        return ResponseEntity.ok().build();
    }
}
