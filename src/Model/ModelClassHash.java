package Model;

import Controller.Interfaces.iGetModel;
import Model.Domain.Student;

import java.util.*;

/**
 * Модель хранит информацию о студентах в виде HashMap, где ключ - это id студента.
 * Основной функционал соответствует другим моделям
 */

public class ModelClassHash extends Model implements iGetModel {
    private Map<Integer, Student> students = new HashMap<>();
    List<Student> studentList = new ArrayList<Student>();


    public ModelClassHash(List<Student> studentList) {
        for (Student student: studentList) {
            this.students.put(student.getId(), student);
        }
    }
    @Override
    public void saveNewStudent(Student student) {
        this.students.put(student.getId(), student);

    }

    @Override
    public List<Student> getAllStudents() {
        for (Map.Entry<Integer, Student> entry : this.students.entrySet()) {
            this.studentList.add(entry.getValue());
        }
        return this.studentList;
    }
    @Override
    public void addAllStudents(List<Student> inputStudentList) {
        this.studentList = inputStudentList;

    }

    /**
     * Метод ищет студента по ключу (id студента)
     * @param id
     * @return Student
     */
    @Override
    public Student studentSearch(Integer id) {
        Student neededStudent = null;
        Set<Integer> studentIdSet = students.keySet(); // Поиск по множеству ключей (быстрее?)
        if(studentIdSet.contains(id)){
            neededStudent = students.get(id);
        }
        return neededStudent;
    }

    /**
     * Удаление нужного студента по ключу (id студента)
     * @param student
     * @return boolean
     */
    @Override
    public boolean studentDeleting(Student student) {
        boolean isDeleting = false;
        if(this.students.remove(student.getId(), student)){
            isDeleting = true;
        }
        return isDeleting;
    }


}
