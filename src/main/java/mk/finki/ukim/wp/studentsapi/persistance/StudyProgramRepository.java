package mk.finki.ukim.wp.studentsapi.persistance;

import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyProgramRepository extends JpaRepository<StudyProgram, Long> {
    Optional<StudyProgram> findStudyProgramByName(String name);
}
