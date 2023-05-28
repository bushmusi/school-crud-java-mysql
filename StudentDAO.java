import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class StudentDAO {
    private Connection connection;

    public StudentDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Student student) throws SQLException {
        String query = "INSERT INTO student (id, fName, lName, dOB, age, gender, dept) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, student.getId());
            statement.setString(2, student.getfName());
            statement.setString(3, student.getlName());
            statement.setString(4, student.getdOB());
            statement.setInt(5, student.getAge());
            statement.setString(6, student.getGender());
            statement.setString(7, student.getDept());
            statement.executeUpdate();
        }
    }

    public List<Student> getAll() throws SQLException {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM student";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String fName = resultSet.getString("fName");
                String lName = resultSet.getString("lName");
                String dOB = resultSet.getString("dOB");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String dept = resultSet.getString("dept");
                students.add(new Student(id, fName, lName, dOB, age, gender, dept));
            }
        }
        return students;
    }

    public Student getById(int id) throws SQLException {
        String query = "SELECT * FROM student WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String fName = resultSet.getString("fName");
                    String lName = resultSet.getString("lName");
                    String dOB = resultSet.getString("dOB");
                    int age = resultSet.getInt("age");
                    String gender = resultSet.getString("gender");
                    String dept = resultSet.getString("dept");
                    return new Student(id, fName, lName, dOB, age, gender, dept);
                }
            }
        }
        return null;
    }

    public void update(Student student) throws SQLException {
        String query = "UPDATE student SET fName = ?, lName = ?, dOB = ?, age = ?, gender = ?, dept = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, student.getfName());
            statement.setString(2, student.getlName());
            statement.setString(3, student.getdOB());
            statement.setInt(4, student.getAge());
            statement.setString(5, student.getGender());
            statement.setString(6, student.getDept());
            statement.setInt(7, student.getId());
            statement.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String query = "DELETE FROM student WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public void calculateAge(int id) throws SQLException {
        String query = "SELECT DATEDIFF(CURDATE(), dOB) AS age FROM student WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int age = resultSet.getInt("age");
                    System.out.println("Age: " + age);
                }
            }
        }
    }
}
