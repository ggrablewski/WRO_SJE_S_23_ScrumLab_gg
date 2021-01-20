<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: grzegorz
  Date: 11.11.2020
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Zaplanuj Jedzonko - przepisy</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
          rel="stylesheet">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
<%--    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>--%>
<%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>--%>
<%--    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>--%>
    <style>
        /* width */
        ::-webkit-scrollbar {
            width: 20px;
        }
        /* Track */
        ::-webkit-scrollbar-track {
            box-shadow: inset 0 0 5px grey;
            border-radius: 10px;
        }
        /* Handle */
        ::-webkit-scrollbar-thumb {
            background: red;
            border-radius: 10px;
        }
        /* Handle on hover */
        ::-webkit-scrollbar-thumb:hover {
            background: #b30000;
        }
    </style></head>
<body>
<jsp:include page="header.jsp"/>

<section class="dashboard-section">
    <div class="container pt-4 pb-4">
        <div class="border-dashed view-height">
            <div class="container w-100">

                <section>
                    <div class="row padding-small">
                        <i class="fas fa-users icon-users"></i>
                        <h1>Przepisy naszych użytkowników:</h1>
                        <hr>
                        <div class="orange-line w-100"></div>
                    </div>
                </section>

<%--                <nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">--%>
<%--                    <a class="navbar-brand" href="#">Logo</a>--%>
<%--                    <ul class="navbar-nav">--%>
<%--                        <li class="nav-item">--%>
<%--                            <a class="nav-link" href="#">Link</a>--%>
<%--                        </li>--%>
<%--                        <li class="nav-item">--%>
<%--                            <a class="nav-link" href="#">Link</a>--%>
<%--                        </li>--%>
<%--                    </ul>--%>
<%--                </nav>--%>

                <section class="mr-4 ml-4">
                    <table class="table">
                        <nav class="navbar navbar-expand-sm navbar-dark fixed-top">
                        <thead>
                        <tr class="navbar-brand d-flex text-color-darker">
                            <th scope="col" class="col-1">ID</th>
                            <th scope="col" class="col-3">NAZWA</th>
                            <th scope="col" class="col-7">OPIS</th>
                            <th scope="col" class="col-1">AKCJE</th>
                        </tr>
                        </thead>
                        </nav>

<%--                    <tbody class="text-color-lighter">--%>

<%--                        <div class="container-fluid" style="margin-top:80px">--%>
<%--                            <h3>Top Fixed Navbar</h3>--%>
<%--                            <p>A fixed navigation bar stays visible in a fixed position (top or bottom) independent of the page scroll.</p>--%>
<%--                            <h1>Scroll this page to see the effect</h1>--%>
<%--                        </div>--%>

                        <tbody class="container-fluid w-100">
                        <c:forEach items="${recipeList}" var="recipe">
                        <tr class="d-flex">
                            <th scope="row" class="col-1">${recipe.id}</th>
                            <td class="col-3">${recipe.name}</td>
                            <td class="col-7">${recipe.description}</td>
                            <td class="col-1"><a href="/recipe/details?id=${recipe.id}"
                                                 class="btn btn-info rounded-0 text-light">Szczegóły</a></td>
                        </tr>
                        </c:forEach>
                        </tbody>

                    </table>
                </section>

            </div>
        </div>
    </div>
</section>


<jsp:include page="footer.jsp"/>
</body>
</html>
