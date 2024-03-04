package Model;

import Controller.Interfaces.iGetModel;
import Model.Domain.Student;

import java.util.HashMap;
import java.util.List;

public class ModelClassList extends Model implements iGetModel {
    private List<Student> students;

    public ModelClassList(List<Student> students) {
        this.students = students;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    @Override
    public void saveNewStudent(Student student) {
        students.add(student);
    }


    @Override
    public Student studentSearch(Integer id) {
        Student neededStudent = null;
        for(Student student:students){
            if(student.getId() == id){
                neededStudent = student;
            }
        }
        if(neededStudent != null){
            return neededStudent;
        } else {
            return null;
        }
    }

    @Override
    public boolean studentDeleting(Student student) {
        boolean isDeleting = false;
        if(this.students.remove(student)){
            isDeleting = true;
        } else {
            isDeleting = false;
        }
        return isDeleting;
    }

    @Override
    public void addAllStudents(List<Student> inputStudentList) {

    }


}
