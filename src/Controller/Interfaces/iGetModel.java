package Controller.Interfaces;

import Model.Domain.Student;

import java.util.List;

public interface iGetModel {
    public Student studentSearch(Integer id);

    public List<Student> getAllStudents();

    public void saveNewStudent(Student student);
    public boolean studentDeleting(Student student);
    public void addAllStudents(List<Student> inputStudentList);
}
