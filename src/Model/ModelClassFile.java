package Model;

import Controller.Interfaces.iGetModel;
import Model.Domain.Student;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Работа с данным классом выстроена неправильно, но в связи с ограниченными временными ресурсами отправляю как есть.
 */
public class ModelClassFile extends Model implements iGetModel {
    private String fileName;
    private List<Student> studentList; // Планировался для отображения актуальной информации, хранящейся в списке, но id студентов в списке и файле отличаются (Получается разный набор студентов)

    public ModelClassFile(String fileName) {

        this.fileName = fileName;
        try (FileWriter fw = new FileWriter(fileName, true)) {
            fw.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Метод принимает список студентов и сохраняет его в csv документ.
     * Метод делает копию списка для поиска и других действий со студентами.
     * @param students
     */
    public void saveAllStudentToFile(List<Student> students) {
        this.studentList = students;
        try (FileWriter fw = new FileWriter(fileName, true)) {
            for (Student student : students) {
                fw.write(student.getName() + " " + student.getAge() + " " + student.getId());
                fw.append('\n');
            }
            fw.flush(); // "Смывает поток"
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Метод возвращает информацию из файла
     * @return
     */
    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<Student>();
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                String[] param = line.split(" ");
                Student pers = new Student(param[0], Integer.parseInt(param[1]));
                students.add(pers);
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return students;
    }

    /**
     * Метод добавляет(дописывает) нового студента в файл и список студентов
     * @param student
     */

    @Override
    public void saveNewStudent(Student student) {
        this.studentList.add(student);
        try (FileWriter fw = new FileWriter(fileName, true)) {
            fw.write(student.getName() + " " + student.getAge() + " " + student.getId());
            fw.append('\n');
            fw.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Метод ищет студента по id студента в списке студентов (не в файле) - логика сломана, нужно переделывать, так как в файле и и списке "разные" студенты.
     * @param id
     * @return Студента или null.
     */
    @Override
    public Student studentSearch(Integer id) {

        Student neededStudent = null;
        for(Student student: this.studentList){
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

    /**
     * Метод удаляет студента из списка студентов и из файла.
     * Перезаписывает файл информацией, хранящейся в списке студентов.
     * @param student
     * @return
     */
    @Override
    public boolean studentDeleting(Student student) {
        boolean isDeleting = false;
        if(this.studentList.remove(student)){
            isDeleting = true;
        } else {
            isDeleting = false;
        }
        if(isDeleting){
            try{
                Path path = Paths.get(fileName);
                Files.writeString(path,"");
                FileWriter fw = new FileWriter(fileName, true);
                    for (Student stud : studentList) {
                        fw.write(stud.getName() + " " + stud.getAge() + " " + stud.getId());
                        fw.append('\n');
                    }
                    fw.flush();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        return isDeleting;
    }

    /**
     * Альтернатива сохранению информации в файл
     * Прямое добавление списка студентов в класс, без создания нового и дозаполнения старого файлов.
     * Здесь ошибка, так как добавляются те же студенты с новыми id.
     * Для правильной работы лучше удалять уже имеющийся файл и создавать новый перед каждым run-ом.
     * @param inputStudentList
     */
    @Override
    public void addAllStudents(List<Student> inputStudentList) {
        this.studentList= inputStudentList;
    }

}
