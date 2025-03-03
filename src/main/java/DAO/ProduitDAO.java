//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package DAO;

import Model.Produit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/ecommerce?useSSL=false&allowPublicKeyRetrieval=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private String jdbcUsername = "mon_utilisateur";
    private String jdbcPassword = "mon_mot_de_passe";
    private static final String SELECT_ALL_PRODUITS = "SELECT * FROM produits";
    private static final String INSERT_PRODUIT = "INSERT INTO produits (nom, description, prix, image) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_PRODUIT = "UPDATE produits SET nom=?, description=?, prix=?, image=? WHERE id=?";
    private static final String DELETE_PRODUIT = "DELETE FROM produits WHERE id=?";
    private static final String SELECT_PRODUIT_BY_ID = "SELECT * FROM produits WHERE id=?";

    public ProduitDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(this.jdbcURL, this.jdbcUsername, this.jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            ((Exception)e).printStackTrace();
        }

        return connection;
    }

    public List<Produit> getAllProduits() {
        List<Produit> produits = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUITS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Produit produit = new Produit();
                produit.setId(rs.getInt("id"));
                produit.setNom(rs.getString("nom"));
                produit.setDescription(rs.getString("description"));
                produit.setPrix(rs.getDouble("prix"));
                produit.setImage(rs.getString("image"));
                produits.add(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Produits récupérés : " + produits.size());
        return produits;
    }

    public void ajouterProduit(Produit produit) {
        try (
                Connection connection = this.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO produits (nom, description, prix, image) VALUES (?, ?, ?, ?)");
        ) {
            preparedStatement.setString(1, produit.getNom());
            preparedStatement.setString(2, produit.getDescription());
            preparedStatement.setDouble(3, produit.getPrix());
            preparedStatement.setString(4, produit.getImage());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void modifierProduit(Produit produit) {
        try (
                Connection connection = this.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE produits SET nom=?, description=?, prix=?, image=? WHERE id=?");
        ) {
            preparedStatement.setString(1, produit.getNom());
            preparedStatement.setString(2, produit.getDescription());
            preparedStatement.setDouble(3, produit.getPrix());
            preparedStatement.setString(4, produit.getImage());
            preparedStatement.setInt(5, produit.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void supprimerProduit(int id) {
        try (
                Connection connection = this.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM produits WHERE id=?");
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Produit getProduitById(int id) {
        Produit produit = null;

        try (
                Connection connection = this.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM produits WHERE id=?");
        ) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String nom = rs.getString("nom");
                String description = rs.getString("description");
                double prix = rs.getDouble("prix");
                String image = rs.getString("image");
                produit = new Produit(id, nom, description, prix, image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produit;
    }

    public void testConnexion() {
        try (Connection connection = this.getConnection()) {
            if (connection != null) {
                System.out.println("Connexion à la base de données réussie !");
            } else {
                System.out.println("Échec de la connexion à la base de données.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
