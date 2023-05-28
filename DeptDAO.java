import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;




public class DeptDAO {
    private Connection connection;

    public DeptDAO(Connection connection) {
        this.connection = connection;
    }

    public Dept create(Dept dept) throws SQLException {
        String query = "INSERT INTO dept (name, phone, location, head) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, dept.getName());
            statement.setString(2, dept.getPhone());
            statement.setString(3, dept.getLocation());
            statement.setString(4, dept.getHead());
            statement.executeUpdate();
    
            // Get the generated ID of the inserted dept
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    dept.setId(generatedId);
                }
            }
        }
    
        return dept;
    }
    

    public List<Dept> getAll() throws SQLException {
        List<Dept> instructors = new ArrayList<>();
        String query = "SELECT * FROM dept";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                String location = resultSet.getString("location");
                String head = resultSet.getString("head");
                instructors.add(new Dept(name, head, location, phone));
            }
        }
        return instructors;
    }
    
}
