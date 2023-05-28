import java.sql.*;

public class userMain {
    public static void main(String[] args) {
        System.out.println("Hello World");
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "admin", "admin");

            userDAO userDAO = new userDAO(connection);

            User user = new User("root", "pwd");
            userDAO.create(user);
            
            user.setUsername("root");
            user.setPassword("wrong password");
            System.out.println("username: " + user.getUsername() + " User Password: " + user.getPassword());
            userDAO.login(user.getUsername(), user.getPassword());
            user.setUsername("root");
            user.setPassword("pwd");
            System.out.println("User Name: " + user.getUsername() + " User Password: " + user.getPassword());
            userDAO.login(user.getUsername(), user.getPassword());
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
