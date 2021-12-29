package application;

import entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Locale.setDefault(Locale.US);

        System.out.print("Enter full file path: ");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<Employee> list = new ArrayList<>();

            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = br.readLine();
            }

            System.out.print("Enter salary: ");
            double salary = sc.nextDouble();

            List<String> emails = list.stream()
                    .filter(emp -> emp.getSalary() > salary)
                    .map(emp -> emp.getEmail())
                    .sorted()
                    .collect(Collectors.toList());

            emails.forEach(System.out::println);

            double sum = list.stream()
                    .filter(emp -> emp.getName().charAt(0) == 'M')
                    .map(emp -> emp.getSalary())
                    .reduce(0.0, (x, y) -> x + y);

            System.out.print("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sum));

        } catch (IOException e) {
            System.out.print("Error: " + e.getMessage());
        }
    }
}
