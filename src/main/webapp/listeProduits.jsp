<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Produits</title>
    <link rel="stylesheet" href="./css/style.css">
</head>
<body>
<h1>Liste des Produits</h1>
<a href="produit?action=ajouter">Ajouter un produit</a>
<div class="product-list">
    <c:forEach var="produit" items="${produits}">
        <div class="product-card">
            <img src="${produit.image}" alt="${produit.nom}">
            <h2>${produit.nom}</h2>
            <p class="price">$${produit.prix}</p>
            <a href="produit?action=modifier&id=${produit.id}">Modifier</a>
            <a href="produit?action=supprimer&id=${produit.id}">Supprimer</a>
            <a href="produit?action=details&id=${produit.id}">Détails</a>
        </div>
    </c:forEach>
</div>
</body>
</html>