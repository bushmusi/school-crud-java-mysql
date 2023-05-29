import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        try {
            // Create a database connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "admin", "admin");

            // Create a StudentDAO instance
            StudentDAO studentDAO = new StudentDAO(connection);
            DeptDAO deptDAO = new DeptDAO(connection);
            InstructorDAO instructorDAO = new InstructorDAO(connection);

            // Create a Scanner object to read user input
            Scanner scanner = new Scanner(System.in);
            
            boolean exit = false;
            
            while (!exit) {
                // Display the menu options
                ListOperationType();
                // Read the user's choice
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        ListStudentOptions();
                        int studentChoice = scanner.nextInt();
                        switch (studentChoice) {
                            case 1:
                                ListStudents(studentDAO);
                                break;
                            case 2:
                                InsertStudent(studentDAO);
                                break;
                            default:
                                System.out.println("Invalid choice!");
                        }
                        break;
                    case 2:
                        ListDeptOptions();
                        int deptChoice = scanner.nextInt();
                        switch (deptChoice) {
                            case 1:
                                LisDepts(deptDAO);
                                break;
                            case 2:
                                InsertDept(deptDAO);
                                break;
                            default:
                                System.out.println("Invalid choice!");
                        }
                        break;
                    case 3:
                        ListInstructorOptions();
                        int instructorChoice = scanner.nextInt();
                        switch (instructorChoice) {
                            case 1:
                                ListInstructors(instructorDAO);
                                break;
                            case 2:
                                InsertInstructor(instructorDAO);
                                break;
                            default:
                                System.out.println("Invalid choice!");
                        }
                        break;
                    case 4:
                        exit = true;  // Set the exit flag to true
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
        
                }

            scanner.close();
            // Close the database connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void InsertStudent(StudentDAO studentDAO) throws SQLException {
                    // Get student data from the user
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("\nEnter first name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter last name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter year of birth (YYYY): ");
                    int dobString = scanner.nextInt();
                    scanner.nextLine(); // Consume the remaining newline character
                    System.out.print("Enter gender: ");
                    String gender = scanner.nextLine();
                    System.out.print("Enter department: ");
                    String department = scanner.nextLine();
        
                    Student student = new Student(firstName, lastName, dobString, 0, gender, department);
        
                    // Create the student in the database
                    Student createdStudent = studentDAO.create(student);
                    System.out.println("Student created with ID: " + createdStudent.getId());
    }

    public static void ListStudents(StudentDAO studentDAO) throws SQLException {
        // Get all students
        List<Student> students = studentDAO.getAll();

        // Print the table header
        System.out.println("\n+------------+-----------+-----+-------------+");
        System.out.println("| First Name | Last Name | age | Department  |");
        System.out.println("+------------+-----------+-----+-------------+");

        // Print student details row by row
        for (Student s : students) {
            System.out.printf("| %-10s | %-9s | %3d | %-11s |%n", s.getfName(), s.getlName(), s.getAge(), s.getDept());
        }

        // Print the table footer
        System.out.println("+------------+-----------+-----+-------------+");
    }
    public static void InsertInstructor(InstructorDAO instructorDAO) throws SQLException {
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("\nEnter first name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter last name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter year of birth (YYYY): ");
                    int dobString = scanner.nextInt();
                    scanner.nextLine(); // Consume the remaining newline character
                    System.out.print("Enter gender: ");
                    String gender = scanner.nextLine();
                    System.out.print("Enter department: ");
                    String department = scanner.nextLine();
                    System.out.print("Enter salary: ");
                    String salary = scanner.nextLine();
                    System.out.print("Enter position: ");
                    String position = scanner.nextLine();
        
                    Instructor instructor = new Instructor(firstName, lastName, dobString, 0, gender, department, salary, position);
        
                    // Create the instructor in the database
                    Instructor createdInstructor = instructorDAO.create(instructor);
                    System.out.println("Instructor created with ID: " + createdInstructor.getId());
    }

    public static void ListInstructors(InstructorDAO instructorDAO) throws SQLException {
        // Get all instructors
        List<Instructor> instructors = instructorDAO.getAll();

        // Print the table header
        System.out.println("\n+------------+-----------+-----+-------------+-----------+---------+");
        System.out.println("| First Name | Last Name | Age | Department  | Position  | Salary  |");
        System.out.println("+------------+-----------+-----+-------------+-----------+---------+");

        // Print instructor details row by row
        for (Instructor s : instructors) {
            System.out.printf("| %-10s | %-9s | %3d | %-11s | %-9s | %-7s |%n", s.getfName(), s.getlName(), s.getAge(), s.getDept(), s.getPosition(), s.getSalary());
        }

        // Print the table footer
        System.out.println("+------------+-----------+-----+-------------+-----------+---------+");
    }

    public static void InsertDept(DeptDAO deptDAO) throws SQLException {
                    // Get student data from the user
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("\nEnter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter location: ");
                    String location = scanner.nextLine();
                    System.out.print("Enter head: ");
                    String head = scanner.nextLine();
        
                    Dept dept = new Dept(name, head, location, phone);
        
                    // Create the dept in the database
                    Dept createdInstructor = deptDAO.create(dept);
                    System.out.println("Instructor created with ID: " + createdInstructor.getId());
    }


    public static void LisDepts(DeptDAO deptDAO) throws SQLException {
        // Get all depts
        List<Dept> depts = deptDAO.getAll();

        // Print the table header
        System.out.println("\n+------------+-----------+----------+-------+");
        System.out.println("| Name       | Phone     | Location | Head  |");
        System.out.println("+------------+-----------+----------+-------+");

        // Print dept details row by row
        for (Dept s : depts) {
            System.out.printf("| %-10s | %-9s | %-8s | %-5s |%n", s.getName(), s.getPhone(), s.getLocation(), s.getHead());
        }

        // Print the table footer
        System.out.println("+------------+-----------+----------+-------+");
    }

    public static void ListStudentOptions() {
        System.out.println("\nStudent Menu:");
        System.out.println("1. List Students");
        System.out.println("2. Insert Student");
        System.out.print("Enter your choice: ");
    }

    public static void ListInstructorOptions() {
        System.out.println("\nInstructor Menu:");
        System.out.println("1. List Instructors");
        System.out.println("2. Insert Instructor");
        System.out.print("Enter your choice: ");
    }

    public static void ListDeptOptions() {
        System.out.println("\nDepartment Menu:");
        System.out.println("1. List Departments");
        System.out.println("2. Insert Department");
        System.out.print("Enter your choice: ");
    }

    public static void ListOperationType() {
        System.out.println("\nMenu:");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Department");
        System.out.println("3. Manage Instructor");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }
}
