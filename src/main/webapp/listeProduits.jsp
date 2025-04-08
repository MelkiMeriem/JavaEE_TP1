<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Produits</title>
    <link rel="stylesheet" href="./css/style.css">
</head>
<body>
<%@ include file="menu.jsp" %>

<h1>Liste des Produits</h1>
<a href="produit?action=ajouter">Ajouter un produit</a>
<div class="product-list">
    <c:forEach var="produit" items="${produits}">
        <div class="product-card">
            <img src="${produit.image}" alt="${produit.nom}">
            <h2>${produit.nom}</h2>
            <p class="price">$${produit.prix}</p>
            <a href="produit?action=modifier&id=${produit.id}">Modifier</a>
            <!-- Confirmation pour supprimer -->
            <a href="produit?action=supprimer&id=${produit.id}" onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce produit ?');">Supprimer</a>
            <a href="produit?action=details&id=${produit.id}">Détails</a>
        </div>
    </c:forEach>
</div>

<!-- Si tu veux personnaliser la boîte de confirmation, tu peux ajouter un script ici -->

<script>
    // Exemple d'une boîte de confirmation personnalisée pour la suppression
    function confirmDelete(event, produitId) {
        event.preventDefault(); // Empêche la redirection avant la confirmation
        const isConfirmed = confirm("Êtes-vous sûr de vouloir supprimer ce produit ?");
        if (isConfirmed) {
            window.location.href = "produit?action=supprimer&id=" + produitId; // Redirection après confirmation
        }
    }
</script>
</body>
</html>
