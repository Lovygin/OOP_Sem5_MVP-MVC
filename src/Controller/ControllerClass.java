package Controller;

import Controller.Interfaces.iGetModel;
import Controller.Interfaces.iGetView;

import Model.Domain.Student;
import Model.Model;
import Model.ModelClassList;
import Model.ModelClassHash;
import Model.ModelClassFile;

import java.util.ArrayList;
import java.util.List;


public class ControllerClass {
    private iGetModel model;
    private iGetView view;
    private List<Student> bufferedStudentList = new ArrayList<Student>();
    /**
     * Список моделей для итерации по ним при поиске
     */
    private List<Model> models = new ArrayList<>();

    public ControllerClass(iGetModel model, iGetView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Принимает на вход модели и формирует из них список для итерации по ним
     *
     * @param mcl
     * @param mch
     * @param mcf
     * @return
     */
    public List<Model> addAllModels(ModelClassList mcl, ModelClassHash mch, ModelClassFile mcf) {
        models.add(mcl);
        models.add(mcf);
        models.add(mch);
        return models;
    }

    public void update() {
        //MVC
        //view.printAllStudent(model.getStudents());

        //MVP
        bufferedStudentList = model.getAllStudents();
        if (testData(bufferedStudentList)) {
            view.printAllStudents(bufferedStudentList);
        } else {
            System.out.println("The list of students is empty!");
            // Может быть другая логика, например поиск других моделей
        }
    }

    private boolean testData(List<Student> students) {
        return students.size() > 0;
    }

    private void getListOfAllStudents() {
        //view.printAllStudents(model.getAllStudents()); // Выводим данные из одной изначально объявленной модели
        for (Model currentModel : models) { // Выводим списки из всех моделей
            this.model = (iGetModel) currentModel;
            view.printAllStudents(model.getAllStudents());
        }
    }

    private boolean studentRemoving(String messageAboutIdTyping, String messageAboutSuccessfullyRemoving
            , String messageAboutDeleteError, String messageStudentNotFound) {
        boolean studentFound = false;
        Integer idOfStudentForRemoving = view.promptForInt(messageAboutIdTyping);
        for (Model currentModel : models) { // Ищем студента во всех имеющихся моделях
                                            // Не написал запрос "Хочет ли пользователь искать в других моделях?"
            this.model = (iGetModel) currentModel;
            Student studentToDelete = model.studentSearch(idOfStudentForRemoving);
            if (studentToDelete != null) {
                if (model.studentDeleting(studentToDelete)) { // При успешном удалении метод возвращает true
                    System.out.println(messageAboutSuccessfullyRemoving);
                    studentFound = true;
                    break;
                } else {
                    System.out.println(messageAboutDeleteError); // При удалении студента произошла ошибка
                }
            }
        }
        if (!studentFound) {
            System.out.println(messageStudentNotFound);
        }
        return studentFound;
    }

    /**
     * Основной фнкцилнал программы, основанный на выборе действия с моделями, хранящими списки студентов.
     * С русскими и английскими командами.
     */
    public void run() {
        Command com = Command.NONE;
        boolean getNewIteration = true;
        while (getNewIteration) {
            System.out.println("LIST/Список - вывести список студентов");
            System.out.println("DELETE/УДАЛИТЬ - удалить студента по id номеру");
            System.out.println("EXIT/ВЫХОД - выйти из программы");
            String command = view.prompt("Type a command/ Введите команду --> ");
            com = Command.valueOf(command.toUpperCase());
            switch (com) {
                case EXIT:
                    getNewIteration = false;
                    System.out.println("Exit the program");
                    break;
                case LIST:
                    getListOfAllStudents();
                    break;
                case DELETE: //Удаление
                    studentRemoving("Type the id number of the student --> "
                            , "Removal of a student from the list has been successfully completed"
                            , "Error occurred while deleting the student"
                            , "This student was not found. He/She cannot be deleted");
                    break;
                case СПИСОК:
                    getListOfAllStudents();
                    break;
                case УДАЛИТЬ:
                    studentRemoving("Введите индивидуальный номер студента --> "
                            , "Удаление студента из списка студентов успешно завершено"
                            , "В процессе удаления студента произошла ошибка"
                            , "Студент не найден. Он/Она не может быть удален(а).");
                    break;
                case ВЫХОД:
                    getNewIteration = false;
                    System.out.println("Выход из программы");
                    break;
            }

        }
    }
}
