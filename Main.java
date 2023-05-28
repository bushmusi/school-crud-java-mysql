import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        try {
            // Create a database connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "admin", "admin");

            // Create a StudentDAO instance
            StudentDAO studentDAO = new StudentDAO(connection);

            // Create a new student
            Date dob = Date.valueOf("1998-01-01");
            Student student = new Student(1, "John", "Smith", dob.toString(), 23, "Male", "Computer Science");
            studentDAO.create(student);
            System.out.println("Student created with ID: " + student.getId());

            // Get all students
            List<Student> students = studentDAO.getAll();
            for (Student s : students) {
                System.out.println(s.getId() + ", " + s.getfName() + " " + s.getlName() + ", " + s.getAge() + ", " + s.getDept());
            }

            // Update a student
            student.setfName("Jaden");
            studentDAO.update(student);
            System.out.println("Student updated: " + student.getId());

            // Get a student by ID
            Student retrievedStudent = studentDAO.getById(student.getId());
            System.out.println(retrievedStudent.getId() + ", " + retrievedStudent.getfName() + " " + retrievedStudent.getlName()  + ", " + retrievedStudent.getAge() + ", " + retrievedStudent.getDept());

            // calculate age from dob
            studentDAO.calculateAge(student.getId());

            // Delete a student
            studentDAO.delete(student.getId());
            System.out.println("Student deleted: " + student.getId());

            // Close the database connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
