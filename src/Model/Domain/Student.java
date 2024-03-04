package Model.Domain;

public class Student extends Person implements Comparable<Student> {
    private int id;
    private static int idGenerator = 1; // static виден всем классам, общий для всех классов

    public Student(String name, int age) {
        super(name, age);
        this.id = idGenerator++;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student{ id: " + id + ", "
                + super.getName() + ", "
                + "age: " + super.getAge() + " }";
    }

    /**
     * Переопределенный метод интерфейса Comparable
     * @apiNote сортировка студентов в группе
     * по 2-м признакам (возраст студента(gen) и id студента)
     * @param o the object to be compared.
     */
    @Override
    public int compareTo(Student o) {
//        System.out.println(this.getName() + " - " + o.getName());
        if (this.getAge() > o.getAge()) return 1;
        else if (this.getAge() < o.getAge()) return -1;
        if(this.getId() > o.getId()) return 1;
        else if(this.getId() < o.getId()) return -1;
        return 0;
    }
}
