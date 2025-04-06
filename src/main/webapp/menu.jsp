<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.User" %>
<%
    User user = (User) session.getAttribute("user");
%>
<html>
<head>
    <style>
        .navbar {
            background-color: #c51adc !important;
        }
    </style>

    <link rel="stylesheet" href="./css/style.css">
</head>
<body>
<nav class="navbar">
    <a href="produit" class="logo">Mon E-Commerce</a>
    <ul class="nav-links">
        <li><a href="produit?action=list">Produits</a></li>
        <% if (user == null) { %>
        <li><a href="login.jsp">Connexion</a></li>
        <li><a href="register.jsp">Inscription</a></li>
        <% } else { %>
        <li><span class="welcome">Bonjour, <%= user.getNom() %></span></li>
        <li><a href="logout">Déconnexion</a></li>
        <% } %>
    </ul>
</nav>
</body>
</html>
