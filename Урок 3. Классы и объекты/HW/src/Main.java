import employees.Employee;
import employees.Manager;

public class Main {
    public static void main(String[] args) {
        Employee[] employees = new Employee[3];
        employees[0] = new Employee("Петр", 2000);
        employees[1] = new Manager("Алиса", 3000);
        employees[2] = new Employee("Борис", 2500);

        double salaryIncrease = 1500;
        Employee.increaseSalary(employees, salaryIncrease);

        for (Employee employee : employees) {
            System.out.println("Имя: " + employee.getName() + ", Зарплата: " + employee.getSalary());
        }
    }
}
