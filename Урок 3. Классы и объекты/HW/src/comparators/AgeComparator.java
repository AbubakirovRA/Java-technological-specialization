package comparators;
import java.util.Comparator;

import employees.Employee;

class AgeComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee employee1, Employee employee2) {
        return Integer.compare(employee1.getAge(), employee2.getAge());
    }
}
