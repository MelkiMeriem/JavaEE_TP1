<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter un Produit</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h1>Ajouter un Produit</h1>
<form action="produit" method="post">
    <input type="hidden" name="action" value="ajouter">
    <label for="nom">Nom :</label>
    <input type="text" id="nom" name="nom" required><br>
    <label for="description">Description :</label>
    <textarea id="description" name="description" required></textarea><br>
    <label for="prix">Prix :</label>
    <input type="number" id="prix" name="prix" step="0.01" required><br>
    <label for="image">Image :</label>
    <input type="text" id="image" name="image" required><br>
    <button type="submit">Ajouter</button>
</form>
<a href="produit">Retour à la liste</a>
</body>
</html>