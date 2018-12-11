package mk.finki.ukim.wp.studentsapi.service;

import mk.finki.ukim.wp.studentsapi.model.StudyProgram;

import java.util.List;
import java.util.Optional;

public interface StudyProgramService {
    Optional<StudyProgram> findById(Long id);

    List<StudyProgram> findAll();

    StudyProgram save(StudyProgram studyProgram);

    Optional<StudyProgram> findByName(String name);

    void delete(StudyProgram studyProgram);
}
