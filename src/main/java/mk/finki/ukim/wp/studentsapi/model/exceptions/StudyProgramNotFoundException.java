package mk.finki.ukim.wp.studentsapi.model.exceptions;

public class StudyProgramNotFoundException extends Exception {
    public StudyProgramNotFoundException(Long id) {
        super("Could not find study program " + id);
    }

    public StudyProgramNotFoundException(String name) {
        super("Could not find study program " + name);
    }
}
