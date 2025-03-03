package DAO;

import Model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/ecommerce?useSSL=false&allowPublicKeyRetrieval=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private String jdbcUsername = "mon_utilisateur";
    private String jdbcPassword = "mon_mot_de_passe";

    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String INSERT_USER = "INSERT INTO users (nom, email, password) VALUES (?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE users SET nom=?, email=?, password=? WHERE id=?";
    private static final String DELETE_USER = "DELETE FROM users WHERE id=?";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id=?";
    private static final String SELECT_USER_BY_EMAIL_AND_PASSWORD = "SELECT * FROM users WHERE email=? AND password=?";

    public UserDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Utilisateurs récupérés : " + users.size());
        return users;
    }

    public void ajouterUser(User user) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {

            preparedStatement.setString(1, user.getNom());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierUser(User user) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {

            preparedStatement.setString(1, user.getNom());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerUser(int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int id) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {

            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User verifierLogin(String email, String password) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL_AND_PASSWORD)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void testConnexion() {
        try (Connection connection = getConnection()) {
            if (connection != null) {
                System.out.println("Connexion à la base de données réussie !");
            } else {
                System.out.println("Échec de la connexion à la base de données.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean registerUser(User user) {
        boolean rowInserted = false;
        String sql = "INSERT INTO users (nom, email, password) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getNom());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            rowInserted = stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowInserted;
    }

}
