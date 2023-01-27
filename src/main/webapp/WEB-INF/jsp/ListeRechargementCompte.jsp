<%@page import="java.util.List"%>
<%@page import="com.example.demo.models.Admin"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%
String token = (String)request.getSession().getAttribute("token");
Admin admin = (Admin) request.getSession().getAttribute("admin");
float chiffreAffaire = (float) request.getAttribute("chiffreAffaire");
List<Object[]> liste = (List<Object[]>) request.getAttribute("listeRechargementCompte");

%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Liste des rechargements de compte</title>
    <link rel="stylesheet" href="assets/General/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i&amp;display=swap">
    <link rel="stylesheet" href="assets/General/fonts/fontawesome-all.min.css">
</head>

<body id="page-top">
<div id="wrapper">
    <nav class="navbar navbar-dark align-items-start sidebar sidebar-dark accordion bg-gradient-primary p-0" style="background: #32be8f;">
        <div class="container-fluid d-flex flex-column p-0">
            <a class="navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0" href="backOffice">
            <div class="sidebar-brand-icon rotate-n-15"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 -64 640 640" width="1em" height="1em" fill="currentColor">
                <!--! Font Awesome Free 6.1.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free (Icons: CC BY 4.0, Fonts: SIL OFL 1.1, Code: MIT License) Copyright 2022 Fonticons, Inc. -->
                <path d="M535 7.03C544.4-2.343 559.6-2.343 568.1 7.029L632.1 71.02C637.5 75.52 640 81.63 640 87.99C640 94.36 637.5 100.5 632.1 104.1L568.1 168.1C559.6 178.3 544.4 178.3 535 168.1C525.7 159.6 525.7 144.4 535 135L558.1 111.1L384 111.1C370.7 111.1 360 101.2 360 87.99C360 74.74 370.7 63.99 384 63.99L558.1 63.1L535 40.97C525.7 31.6 525.7 16.4 535 7.03V7.03zM104.1 376.1L81.94 400L255.1 399.1C269.3 399.1 279.1 410.7 279.1 423.1C279.1 437.2 269.3 447.1 255.1 447.1L81.95 448L104.1 471C114.3 480.4 114.3 495.6 104.1 504.1C95.6 514.3 80.4 514.3 71.03 504.1L7.029 440.1C2.528 436.5-.0003 430.4 0 423.1C0 417.6 2.529 411.5 7.03 407L71.03 343C80.4 333.7 95.6 333.7 104.1 343C114.3 352.4 114.3 367.6 104.1 376.1H104.1zM95.1 64H337.9C334.1 71.18 332 79.34 332 87.1C332 116.7 355.3 139.1 384 139.1L481.1 139.1C484.4 157.5 494.9 172.5 509.4 181.9C511.1 184.3 513.1 186.6 515.2 188.8C535.5 209.1 568.5 209.1 588.8 188.8L608 169.5V384C608 419.3 579.3 448 544 448H302.1C305.9 440.8 307.1 432.7 307.1 423.1C307.1 395.3 284.7 371.1 255.1 371.1L158.9 372C155.5 354.5 145.1 339.5 130.6 330.1C128.9 327.7 126.9 325.4 124.8 323.2C104.5 302.9 71.54 302.9 51.23 323.2L31.1 342.5V128C31.1 92.65 60.65 64 95.1 64V64zM95.1 192C131.3 192 159.1 163.3 159.1 128H95.1V192zM544 384V320C508.7 320 480 348.7 480 384H544zM319.1 352C373 352 416 309 416 256C416 202.1 373 160 319.1 160C266.1 160 223.1 202.1 223.1 256C223.1 309 266.1 352 319.1 352z"></path>
            </svg></div>
            <div class="sidebar-brand-text mx-3"><span>Enchère</span></div>
        </a>
            <hr class="sidebar-divider my-0">
            <ul class="navbar-nav text-light" id="accordionSidebar">
                <li class="nav-item"><a class="nav-link" href="/ListeRechargementCompte"><svg xmlns="http://www.w3.org/2000/svg" viewBox="-64 0 512 512" width="1em" height="1em" fill="currentColor">
                    <!--! Font Awesome Free 6.1.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free (Icons: CC BY 4.0, Fonts: SIL OFL 1.1, Code: MIT License) Copyright 2022 Fonticons, Inc. -->
                    <path d="M336 64h-53.88C268.9 26.8 233.7 0 192 0S115.1 26.8 101.9 64H48C21.5 64 0 85.48 0 112v352C0 490.5 21.5 512 48 512h288c26.5 0 48-21.48 48-48v-352C384 85.48 362.5 64 336 64zM96 392c-13.25 0-24-10.75-24-24S82.75 344 96 344s24 10.75 24 24S109.3 392 96 392zM96 296c-13.25 0-24-10.75-24-24S82.75 248 96 248S120 258.8 120 272S109.3 296 96 296zM192 64c17.67 0 32 14.33 32 32c0 17.67-14.33 32-32 32S160 113.7 160 96C160 78.33 174.3 64 192 64zM304 384h-128C167.2 384 160 376.8 160 368C160 359.2 167.2 352 176 352h128c8.801 0 16 7.199 16 16C320 376.8 312.8 384 304 384zM304 288h-128C167.2 288 160 280.8 160 272C160 263.2 167.2 256 176 256h128C312.8 256 320 263.2 320 272C320 280.8 312.8 288 304 288z"></path>
                    </svg><span>&nbsp;Demande rechargement compte</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/Statistique"><i class="fas fa-sort-amount-up-alt"></i><span>&nbsp;Statistiques</span></a>
                </li>
            </ul>
            <div class="text-center d-none d-md-inline"><button class="btn rounded-circle border-0" id="sidebarToggle" type="button"></button></div>
        </div>
    </nav>
    <div class="d-flex flex-column" id="content-wrapper">
        <div id="content">
            <nav class="navbar navbar-light navbar-expand bg-white shadow mb-4 topbar static-top">
                <div class="container-fluid"><button class="btn btn-link d-md-none rounded-circle me-3" id="sidebarToggleTop" type="button"><i class="fas fa-bars"></i></button><span style="color: rgb(0,0,0);font-weight: bold;">RECHARGEMENT DE COMPTE</span>
                    <ul class="navbar-nav flex-nowrap ms-auto">
<%--                        <li class="nav-item dropdown d-sm-none no-arrow"><a class="dropdown-toggle nav-link" aria-expanded="false" data-bs-toggle="dropdown" href="#"><i class="fas fa-search"></i></a>--%>
<%--                            <div class="dropdown-menu dropdown-menu-end p-3 animated--grow-in" aria-labelledby="searchDropdown">--%>
<%--                                <form class="me-auto navbar-search w-100">--%>
<%--                                    <div class="input-group"><input class="bg-light form-control border-0 small" type="text" placeholder="Search for ...">--%>
<%--                                        <div class="input-group-append"><button class="btn btn-primary py-0" type="button"><i class="fas fa-search"></i></button></div>--%>
<%--                                    </div>--%>
<%--                                </form>--%>
<%--                            </div>--%>
<%--                        </li>--%>
                        <div class="d-none d-sm-block topbar-divider"></div>
                        <li class="nav-item dropdown no-arrow"><a class="nav-link" href="/logout"><i class="fas fa-sign-out-alt"></i></a></li>
                    </ul>
                </div>
            </nav>
            <div class="container-fluid">
                <div class="d-sm-flex justify-content-between align-items-center mb-4">
                    <h3 class="text-dark mb-0" style="color: rgb(0,0,0);">Chiffre d'affaire : <span style="font-weight: bold;"><%= chiffreAffaire  %></span></h3>
                </div>
                <div class="card shadow">
                    <div class="card-header py-3">
                        <p class="text-primary m-0 fw-bold">Liste des rechargements des comptes</p>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th style="color: rgb(0,0,0);">Nom</th>
                                        <th style="color: rgb(0,0,0);">Prenom</th>
                                        <th style="color: rgb(0,0,0);">Moment rechargement</th>
                                        <th style="color: rgb(0,0,0);">Date Heure rechargement</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <% for(int i=0;i<liste.size();i++) { %>
                                <tr>
                                    <td style="color: rgb(0,0,0);"><%= liste.get(i)[2]  %></td>
                                    <td style="color: rgb(0,0,0);"><%= liste.get(i)[3]  %></td>
                                    <td style="color: rgb(0,0,0);"><%= liste.get(i)[4]  %></td>
                                    <td style="color: rgb(0,0,0);"><%= liste.get(i)[5] %></td>
                                    <td style="color: rgb(0,0,0);"><a class="btn btn-primary" href="/Validation/<%= liste.get(i)[0] %>/<%= liste.get(i)[1] %>/<%= liste.get(i)[4] %>">Valider</a></td>
                                </tr>
                                <% } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <footer class="bg-white sticky-footer">
            <div class="container my-auto">
                <div class="text-center my-auto copyright"><span>Copyright © Brand 2023</span></div>
            </div>
        </footer>
    </div><a class="border rounded d-inline scroll-to-top" href="#page-top"><i class="fas fa-angle-up"></i></a>
</div>
<script src="assets/General/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/General/js/theme.js"></script>
</body>

</html>

