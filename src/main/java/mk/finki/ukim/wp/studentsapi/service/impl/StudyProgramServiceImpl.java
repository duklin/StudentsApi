package mk.finki.ukim.wp.studentsapi.service.impl;

import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import mk.finki.ukim.wp.studentsapi.persistance.StudyProgramRepository;
import mk.finki.ukim.wp.studentsapi.service.StudyProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudyProgramServiceImpl implements StudyProgramService {
    private StudyProgramRepository studyProgramRepository;

    @Autowired
    public StudyProgramServiceImpl(StudyProgramRepository studyProgramRepository){
        this.studyProgramRepository = studyProgramRepository;
    }

    @Override
    public Optional<StudyProgram> findById(Long id) {
        return this.studyProgramRepository.findById(id);
    }

    @Override
    public List<StudyProgram> findAll() {
        return studyProgramRepository.findAll();
    }

    @Override
    public StudyProgram save(StudyProgram studyProgram) {
        return studyProgramRepository.save(studyProgram);
    }

    @Override
    public Optional<StudyProgram> findByName(String name) {
        return studyProgramRepository.findStudyProgramByName(name);
    }

    @Override
    public void delete(StudyProgram studyProgram) {
        studyProgramRepository.delete(studyProgram);
    }
}
