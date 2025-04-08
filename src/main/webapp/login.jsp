<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="./css/style.css">
    <title>Connexion</title>
</head>
<body>
<jsp:include page="menu.jsp"/>
<h2>Connexion</h2>
<form method="post" action="login">
    <label>Email : <input type="text" name="email" required></label><br>
    <label>Mot de passe: <input type="password" name="password" required></label><br>
    <input type="submit" value="Se connecter">
</form>
<p style="color:red;">${errorMessage}</p>
</body>
</html>
