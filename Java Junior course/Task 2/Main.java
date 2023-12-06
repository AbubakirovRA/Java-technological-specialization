import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = generateEmployeeList(15);
        employees.forEach(System.out::println);

        List<String> uniqueDepartments = employees.stream()
                .map(Employee::getDepartment)
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Unique departments: " + uniqueDepartments);

        employees.stream()
                .filter(employee -> employee.getSalary() < 10_000)
                .forEach(employee -> employee.setSalary(employee.getSalary() * 1.2));

        Map<String, List<Employee>> departmentEmployeesMap = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        departmentEmployeesMap.forEach((department, employeesInDepartment) -> {
            System.out.println("Department: " + department);
            employeesInDepartment.forEach(System.out::println);
            System.out.println("-----------");
        });

        Map<String, Double> departmentAverageSalaryMap = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));

        departmentAverageSalaryMap.forEach((department, averageSalary) -> System.out
                .println("Department: " + department + ", average salary: " + averageSalary));
    }

    private static List<Employee> generateEmployeeList(int size) {
        Random random = new Random();
        return IntStream.range(0, size)
                .mapToObj(i -> new Employee("Employee" + i, random.nextInt(30) + 20,
                        random.nextDouble() * 5000 + 5000, "Department" + (i % 5)))
                .collect(Collectors.toList());
    }
}