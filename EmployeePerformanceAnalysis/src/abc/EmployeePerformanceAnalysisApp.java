package abc;

import java.util.*;
import java.util.stream.Collectors;

// Employee class
class Employee {
    private String name;
    private String department;
    private double performanceScore;

    public Employee(String name, String department, double performanceScore) {
        this.name = name;
        this.department = department;
        this.performanceScore = performanceScore;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getPerformanceScore() {
        return performanceScore;
    }

    @Override
    public String toString() {
        return String.format("%s (%s): %.2f", name, department, performanceScore);
    }
}

// PerformanceAnalysis class
class PerformanceAnalysis {
    private List<Employee> employees;

    public PerformanceAnalysis(List<Employee> employees) {
        this.employees = employees;
    }

    // Method to calculate average performance score
    public double calculateAverageScore() {
        return employees.stream()
                .mapToDouble(Employee::getPerformanceScore)
                .average()
                .orElse(0);
    }

    // Method to find top performers
    public List<Employee> findTopPerformers(double threshold) {
        return employees.stream()
                .filter(e -> e.getPerformanceScore() > threshold)
                .sorted(Comparator.comparingDouble(Employee::getPerformanceScore).reversed())
                .collect(Collectors.toList());
    }

    // Method to group employees by department
    public Map<String, List<Employee>> groupByDepartment() {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    // Method to display performance analysis
    public void displayAnalysis() {
        System.out.printf("Average Performance Score: %.2f%n", calculateAverageScore());
        
        System.out.println("Employees grouped by department:");
        groupByDepartment().forEach((department, empList) -> {
            System.out.println(department + ":");
            empList.forEach(emp -> System.out.println("  " + emp));
        });
    }
}

// Main application class
public class EmployeePerformanceAnalysisApp {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", "Sales", 85.5));
        employees.add(new Employee("Bob", "Sales", 90.0));
        employees.add(new Employee("Charlie", "HR", 78.5));
        employees.add(new Employee("Diana", "HR", 88.0));
        employees.add(new Employee("Eve", "IT", 95.0));
        employees.add(new Employee("Frank", "IT", 82.5));

        PerformanceAnalysis analysis = new PerformanceAnalysis(employees);

        // Display the performance analysis
        analysis.displayAnalysis();

        // Find and display top performers
        double threshold = 85.0;
        List<Employee> topPerformers = analysis.findTopPerformers(threshold);
        System.out.printf("Top Performers (score > %.1f):%n", threshold);
        topPerformers.forEach(System.out::println);
    }
}
