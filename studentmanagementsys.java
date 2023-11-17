package CODSOFT;
import java.util.Scanner;

public class studentmanagementsys {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Student[] students = new Student[100]; // Store up to 100 students

        int studentCount = 0; // Keep track of the number of students

        while (true) {
            System.out.println("Student Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    if (studentCount < students.length) {
                        System.out.print("Enter Student Name: ");
                        String name = scanner.nextLine();
                        System.out.println("Enter the moblie number");
                        int moblienumber = scanner.nextInt();
                        System.out.print("Enter Student ID: ");
                        int id = scanner.nextInt();
                        students[studentCount] = new Student(id, name);
                        studentCount++;
                        System.out.println("Student added successfully.");
                    } else {
                        System.out.println("Maximum student capacity reached.");
                    }
                    break;
                case 2:
                    System.out.println("List of Students:");
                    for (int i = 0; i < studentCount; i++) {
                        System.out.println(students[i]);
                    }
                    break;
                case 3:
                    System.out.print("Enter Student ID to search: ");
                    int searchId = scanner.nextInt();
                    boolean found = false;
                    for (int i = 0; i < studentCount; i++) {
                        if (students[i].getId() == searchId) {
                            System.out.println("Student Found: " + students[i]);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Student with ID " + searchId + " not found.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting Student Management System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}

class Student {
    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student ID: " + id + ", Name: " + name;
    }
}