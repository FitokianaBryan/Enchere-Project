<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Administrator</title>
    <link rel="stylesheet" type="text/css" href="assets/Login/css/style.css">
    <link href="https://fonts.googleapis.com/css?family=Poppins:600&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a81368914c.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<img class="wave" src="assets/Login/img/wave.png">
<div class="container">
    <div class="img">
        <img src="assets/Login/img/bg.svg">
    </div>
    <div class="login-content">
        <form method="POST" action="/login">
            <img src="assets/Login/img/admin.png">
            <h2 class="title">Welcome admin</h2>
            <div class="input-div one">
                <div class="i">
                    <i class="fas fa-user"></i>
                </div>
                <div class="div">
                    <h5>Username</h5>
                    <input type="text" class="input" name="email" value="${email}" required placeholder="Ex: xxx@gmail.com">
                </div>
            </div>
            <div class="input-div pass">
                <div class="i">
                    <i class="fas fa-lock"></i>
                </div>
                <div class="div">
                    <h5>Password</h5>
                    <input type="password" class="input" placeholder="Mot de passe" name="mdp" value="${mdp}" required>
                </div>
            </div>
            <% if(request.getParameter("error")!=null){ %>
            <br>
                <div class="alert alert-success" role="alert"><span style="color: red;"><strong >!</strong> Connexion échouée, veuillez réessayer</span></div>
            <% } %>
            <input type="submit" class="btn" value="Se connecter">
        </form>
    </div>
</div>
<script type="text/javascript" src="assets/Login/js/main.js"></script>
</body>
</html>
<%--
<html>
<head>
<title>Login administrateur</title>
</head>
<body>




   <h1>Back Office Administrateur</h1>
     <form method="POST" action="/login" class="login-form">
     		      		<div class="form-group">
     		      			<input type="text" class="form-control rounded-left"  name="email" value="${email}" required>
     		      		</div>
                         <div class="form-group d-flex">
                           <input type="password" class="form-control rounded-left" placeholder="Mot de passe" name="mdp" value="${mdp}" required>
                         </div>
                         <div class="form-group">
                             <button type="submit" class="form-control btn btn-primary rounded submit px-3">connexion</button>
                         </div>

                         <div class="form-group d-md-flex">
                             <%
                                 if(request.getParameter("error")!=null){ %>
                                     <p style="color:red;">Connexion échouée, veuillez réessayer</p>
                             <% } %>
                         </div>
     	          </form>
</body>
</html>
--%>
