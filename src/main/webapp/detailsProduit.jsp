<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Détails du Produit</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h1>Détails du Produit</h1>
<div class="product-list">
<div class="product-card">
    <img src="${produit.image}" alt="${produit.nom}">
    <h2>${produit.nom}</h2>
    <p>${produit.description}</p>
    <p class="price">$${produit.prix}</p>

</div>
</div>
<a href="produit">Retour à la liste</a>

</body>
</html>