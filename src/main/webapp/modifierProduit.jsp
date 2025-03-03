<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifier un Produit</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h1>Modifier un Produit</h1>
<form action="produit" method="post">
    <input type="hidden" name="action" value="modifier">
    <input type="hidden" name="id" value="${produit.id}">
    <label for="nom">Nom :</label>
    <input type="text" id="nom" name="nom" value="${produit.nom}" required><br>
    <label for="description">Description :</label>
    <textarea id="description" name="description" required>${produit.description}</textarea><br>
    <label for="prix">Prix :</label>
    <input type="number" id="prix" name="prix" step="0.01" value="${produit.prix}" required><br>
    <label for="image">Image :</label>
    <input type="text" id="image" name="image" value="${produit.image}" required><br>
    <button type="submit">Modifier</button>
</form>
<a href="produit">Retour à la liste</a>
</body>
</html>