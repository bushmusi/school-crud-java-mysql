import java.sql.*;

public class userDAO {
    private Connection connection;
    
    public userDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(User user) throws SQLException {
        String query = "INSERT INTO user (id, username, password) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, user.getId());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
        }
    }

    public User login(String username, String password) throws SQLException {
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("Login Success");
                    return new User(username, password);
                } else {
                    System.out.println("Login Failed");
                }
            }
        }
        return null;
    }
}
