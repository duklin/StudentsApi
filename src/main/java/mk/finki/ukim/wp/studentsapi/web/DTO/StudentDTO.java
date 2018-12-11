package mk.finki.ukim.wp.studentsapi.web.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import mk.finki.ukim.wp.studentsapi.model.Student;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class StudentDTO {
    @NotNull(message = "'index' is required")
    @Size(min = 6, max = 6, message = "'index' should have exactly 6 digits")
    @Pattern(regexp = "\\d+", message = "'index' only contains digits")
    private String index;

    @NotNull(message = "'name' is required")
    private String name;

    @NotNull(message = "'lastName' is required")
    private String lastName;

    @NotNull(message = "'studyProgram' is required")
    private String studyProgramName;

    public StudentDTO(){ }

    public StudentDTO(Student student) {
        this.index = student.getIndex();
        this.name = student.getName();
        this.lastName = student.getLastName();
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonIgnore
    public String getStudyProgramName() {
        return studyProgramName;
    }

    @JsonProperty
    public void setStudyProgramName(String studyProgramName) {
        this.studyProgramName = studyProgramName;
    }

}
