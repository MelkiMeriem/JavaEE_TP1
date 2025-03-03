package Servlet;

import DAO.ProduitDAO;
import Model.Produit;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(
        name = "ProduitServlet",
        value = {"/produit"}
)
@MultipartConfig
public class ProduitServlet extends HttpServlet {
    private ProduitDAO produitDAO;

    public ProduitServlet() {}

    public void init() {
        this.produitDAO = new ProduitDAO();
        this.produitDAO.testConnexion();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "liste";
        }

        switch (action) {
            case "ajouter" -> this.showAjouterForm(request, response);
            case "modifier" -> this.showModifierForm(request, response);
            case "supprimer" -> this.supprimerProduit(request, response);
            case "details" -> this.showDetails(request, response);
            default -> this.listProduits(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("ajouter".equals(action)) {
            this.ajouterProduit(request, response);
        } else if ("modifier".equals(action)) {
            this.modifierProduit(request, response);
        }
    }

    private void listProduits(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer tous les produits de la base de données
        request.setAttribute("produits", this.produitDAO.getAllProduits());

        // Rediriger vers la page JSP pour afficher les produits
        RequestDispatcher dispatcher = request.getRequestDispatcher("listeProduits.jsp");
        dispatcher.forward(request, response);
    }

    private void showAjouterForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("ajouterProduit.jsp");
        dispatcher.forward(request, response);
    }

    private void showModifierForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Produit produit = this.produitDAO.getProduitById(id);
        request.setAttribute("produit", produit);
        RequestDispatcher dispatcher = request.getRequestDispatcher("modifierProduit.jsp");
        dispatcher.forward(request, response);
    }

    private void supprimerProduit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        this.produitDAO.supprimerProduit(id);
        response.sendRedirect("produit");
    }

    private void showDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Produit produit = this.produitDAO.getProduitById(id);
        request.setAttribute("produit", produit);
        RequestDispatcher dispatcher = request.getRequestDispatcher("detailsProduit.jsp");
        dispatcher.forward(request, response);
    }

    private void ajouterProduit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");
        double prix = Double.parseDouble(request.getParameter("prix"));

        // Handle file upload
        Part imagePart = request.getPart("image");
        String imagePath = saveImage(imagePart);

        // Create the product object
        Produit nouveauProduit = new Produit(0, nom, description, prix, imagePath);
        this.produitDAO.ajouterProduit(nouveauProduit);
        response.sendRedirect("produit");
    }

    private void modifierProduit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");
        double prix = Double.parseDouble(request.getParameter("prix"));
        String image = request.getParameter("image");

        Produit produit = new Produit(id, nom, description, prix, image);
        this.produitDAO.modifierProduit(produit);
        response.sendRedirect("produit");
    }

    // Save the uploaded image to the server
    private String saveImage(Part imagePart) throws IOException {
        // Define the directory to save images
        String imageDir = getServletContext().getRealPath("/images");
        File dir = new File(imageDir);
        if (!dir.exists()) {
            dir.mkdirs(); // Create the directory if it does not exist
        }

        // Extract the file name from the Part object
        String fileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();

        // Define the path to save the image
        File file = new File(dir, fileName);
        imagePart.write(file.getAbsolutePath());

        // Return the relative path to store in the database
        return "images/" + fileName;
    }
}
