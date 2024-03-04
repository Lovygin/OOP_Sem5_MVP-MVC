package View;

import Controller.Interfaces.iGetView;
import Model.Domain.Student;

import java.util.List;
import java.util.Scanner;
public class ViewClassRussian implements iGetView {
    public void printAllStudents(List<Student> students){
        System.out.println("---------Список студентов---------");
        for(Student student: students){
            System.out.println(student);
        }
        System.out.println("----------------------------------");
    }

    public String prompt(String message){
        Scanner sc = new Scanner(System.in);
        System.out.println(message);
        return sc.nextLine();
    }


    public Integer promptForInt(String message){
        System.out.println(message);
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    @Override
    public void viewRussian() {
        System.out.println("Что-то написано...");
    }




}
