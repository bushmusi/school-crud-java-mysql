import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;




public class StudentDAO {
    private Connection connection;

    public StudentDAO(Connection connection) {
        this.connection = connection;
    }

    public Student create(Student student) throws SQLException {
        String query = "INSERT INTO student (id, fName, lName, dOB, age, gender, dept) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, student.getId());
            statement.setString(2, student.getfName());
            statement.setString(3, student.getlName());
            statement.setInt(4, student.getdOB());
            int age = calculateAge(student.getdOB());
            statement.setInt(5, age);
            statement.setString(6, student.getGender());
            statement.setString(7, student.getDept());
            statement.executeUpdate();
    
            // Get the generated ID of the inserted student
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    student.setId(generatedId);
                }
            }
        }
    
        return student;
    }
    

    public List<Student> getAll() throws SQLException {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM student";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String fName = resultSet.getString("fName");
                String lName = resultSet.getString("lName");
                int dOB = resultSet.getInt("dOB");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String dept = resultSet.getString("dept");
                students.add(new Student(fName, lName, dOB, age, gender, dept));
            }
        }
        return students;
    }
    
    public int calculateAge(int yearOfBirth) throws SQLException {
        // get current year as integer and return the age
        int currentYear = 2023;
        int age = currentYear - yearOfBirth;
        return age;
    }
}
