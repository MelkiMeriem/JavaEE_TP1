package Model;

public class User {
    private int id;
    private String nom;
    private String email;
    private String password;

    // Constructeur par défaut
    public User() {
    }

    // Constructeur avec tous les paramètres
    public User(int id, String nom, String email, String password) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.password = password;
    }

    // Constructeur sans id (pour l'insertion)
    public User(String nom, String email, String password) {
        this.nom = nom;
        this.email = email;
        this.password = password;
    }

    // Getters et setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
