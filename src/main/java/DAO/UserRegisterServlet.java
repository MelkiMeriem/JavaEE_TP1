package Servlet;

import DAO.UserDAO;
import Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class UserRegisterServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User newUser = new User();
        newUser.setNom(nom);
        newUser.setEmail(email);
        newUser.setPassword(password);

        boolean registered = userDAO.registerUser(newUser);

        if (registered) {
            response.sendRedirect("login.jsp"); // redirige vers la page de connexion
        } else {
            response.sendRedirect("register.jsp?error=1");
        }
    }
}
