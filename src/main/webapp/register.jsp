<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Inscription</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
<jsp:include page="menu.jsp"/>
<h1>Créer un compte</h1>
<form action="register" method="post">
  <label for="nom">Nom :</label>
  <input type="text" name="nom" required>

  <label for="email">Email :</label>
  <input type="email" name="email" required>

  <label for="password">Mot de passe :</label>
  <input type="password" name="password" required>


  <button type="submit">S'inscrire</button>
</form>
</body>
</html>
