import Controller.ControllerClass;
import Controller.Interfaces.iGetModel;
import Controller.Interfaces.iGetView;
import Model.Domain.Student;
import Model.ModelClassHash;
import Model.ModelClassList;
import View.ViewClassEng;
import Model.ModelClassFile;
import View.ViewClassRussian;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Student> studentsForModelFile = new ArrayList<>();
        Student student1 = new Student("Arkasha", 18);
        Student student2 = new Student("Mirosha", 24);
        Student student7 = new Student("Nikon", 22);
        Student student3 = new Student("Maksusha", 17);
        Student student4 = new Student("Arisha", 21);
        Student student8 = new Student("Sofia", 22);
        Student student5 = new Student("Nikita", 19);
        Student student9 = new Student("Sasha", 24);
        Student student6 = new Student("Lubovia", 22);

        studentsForModelFile.add(student1);
        studentsForModelFile.add(student2);
        studentsForModelFile.add(student7);
        studentsForModelFile.add(student3);
        studentsForModelFile.add(student4);
        studentsForModelFile.add(student8);
        studentsForModelFile.add(student5);
        studentsForModelFile.add(student9);
        studentsForModelFile.add(student6);

        ModelClassFile fmClass = new ModelClassFile("StudentDB.csv"); // База данных студентов в виде файла
        //fmClass.saveAllStudentToFile(studentsForModelFile); // Сохранили список первой группы студентов в файл

        fmClass.addAllStudents(studentsForModelFile); // Добавляем список студентов в класс с файловым способом хранением хранения данных
                                                      // без повторного сохранения в файл


        List<Student> studentsForModelHash = new ArrayList<>();
        studentsForModelHash.add(new Student("Zoya", 16));
        studentsForModelHash.add(new Student("Victor", 19));
        studentsForModelHash.add(new Student("Sasha", 20));
        studentsForModelHash.add(new Student("Kirill", 18));
        studentsForModelHash.add(new Student("Maksim", 24));
        studentsForModelHash.add(new Student("Elvira", 30));
        studentsForModelHash.add(new Student("Rita", 21));
        studentsForModelHash.add(new Student("Nikolay", 34));
        studentsForModelHash.add(new Student("Georgiy", 19));
        ModelClassHash hmClass = new ModelClassHash(studentsForModelHash);


        List<Student> studentsForModelList = new ArrayList<>();
        studentsForModelList.add(new Student("Masha", 18));
        studentsForModelList.add(new Student("Nika", 20));
        studentsForModelList.add(new Student("Kira", 22));
        studentsForModelList.add(new Student("Oleg", 17));
        studentsForModelList.add(new Student("Saveliy", 28));
        studentsForModelList.add(new Student("Gena", 23));
        studentsForModelList.add(new Student("Alan", 19));
        studentsForModelList.add(new Student("Tyler", 18));
        studentsForModelList.add(new Student("Roxy", 21));
        ModelClassList lmClass = new ModelClassList(studentsForModelList);


        /**
         * Функционал предлагает выбрать язык интерфейся (Английский или русский)
         */
        Scanner sc = new Scanner(System.in);
        boolean flag = false;
        iGetView view = new ViewClassEng();
        while (!flag) {
            System.out.println("Выберете язык интерфейса (Русский/English). Введите РУС или ENG -->");
            String lang = sc.nextLine().toUpperCase();
            if (lang.equals("РУС")) {
                view = new ViewClassRussian();
                flag = true;
            } else if (lang.equals("ENG")) {
                flag = true;
            }
        }
        //sc.close();



        iGetModel model = lmClass; // Наделяем интерфейс моделей функционалом модели со списковым способом хранения данных
        ControllerClass controller = new ControllerClass(fmClass, view); // Объявляем контроллер и добавляем интерфейсы
        controller.addAllModels(lmClass, hmClass, fmClass);


        controller.run();


    }
}