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
</head>
<body>
<jsp:include page="header.jsp"/>

<section class="dashboard-section">
    <div class="container pt-4 pb-4">
        <div class="border-dashed view-height">
            <div class="container">

                <section>
                    <div class="row padding-small">
                        <i class="fas fa-users icon-users"></i>
                        <h1>Przepisy naszych użytkowników:</h1>
                        <hr>
                        <div class="orange-line w-100"></div>
                    </div>
                </section>

                <section class="mr-4 ml-4">
                    <table class="table">
                        <thead>
                        <tr class="d-flex text-color-darker">
                            <th scope="col" class="col-1">ID</th>
                            <th scope="col" class="col-3">NAZWA</th>
                            <th scope="col" class="col-7">OPIS</th>
                            <th scope="col" class="col-1">AKCJE</th>
                        </tr>
                        </thead>
                        <tbody class="text-color-lighter">
                        <c:forEach items="${recipeList}" var="recipe">
                        <tr class="d-flex">
                            <th scope="row" class="col-1">${recipe.id}</th>
                            <td class="col-3">${recipe.name}</td>
                            <td class="col-7">${recipe.description}</td>
                            <td class="col-1"><a href="/recipeDetails?id=${recipe.id}"
                                                 class="btn btn-info rounded-0 text-light">Szczegóły</a></td>
                        </tr>
                        </c:forEach>

                    </table>
                </section>

            </div>
        </div>
    </div>
</section>


<jsp:include page="footer.jsp"/>
</body>
</html>
