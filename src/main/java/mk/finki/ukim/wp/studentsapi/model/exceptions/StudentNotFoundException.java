package mk.finki.ukim.wp.studentsapi.model.exceptions;

public class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String id) {
        super("Could not find student " + id);
    }
}
