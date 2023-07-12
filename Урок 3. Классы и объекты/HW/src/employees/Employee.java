package employees;

public class Employee {
    private String name;
    private int age;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public static void increaseSalary(Employee[] employees, double amount) {
        for (Employee employee : employees) {
            if (!(employee instanceof Manager)) { // Проверяем, является ли сотрудник руководителем
                double newSalary = employee.getSalary() + amount;
                employee.setSalary(newSalary);
            }
        }
    }
}
