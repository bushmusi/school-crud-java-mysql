import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;




public class InstructorDAO {
    private Connection connection;

    public InstructorDAO(Connection connection) {
        this.connection = connection;
    }

    public Instructor create(Instructor instructor) throws SQLException {
        String query = "INSERT INTO instructor (fName, lName, dOB, age, gender, dept, position, salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, instructor.getfName());
            statement.setString(2, instructor.getlName());
            statement.setInt(3, instructor.getdOB());
            int age = calculateAge(instructor.getdOB());
            statement.setInt(4, age);
            statement.setString(5, instructor.getGender());
            statement.setString(6, instructor.getDept());
            statement.setString(7, instructor.getPosition());
            statement.setString(8, instructor.getSalary());
            statement.executeUpdate();
    
            // Get the generated ID of the inserted instructor
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    instructor.setId(generatedId);
                }
            }
        }
    
        return instructor;
    }
    

    public List<Instructor> getAll() throws SQLException {
        List<Instructor> instructors = new ArrayList<>();
        String query = "SELECT * FROM instructor";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String fName = resultSet.getString("fName");
                String lName = resultSet.getString("lName");
                int dOB = resultSet.getInt("dOB");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String dept = resultSet.getString("dept");
                String position = resultSet.getString("position");
                String salary = resultSet.getString("salary");
                instructors.add(new Instructor(fName, lName, dOB, age, gender, dept, salary, position));
            }
        }
        return instructors;
    }
    
    public int calculateAge(int yearOfBirth) throws SQLException {
        // get current year as integer and return the age
        int currentYear = 2023;
        int age = currentYear - yearOfBirth;
        return age;
    }
}
