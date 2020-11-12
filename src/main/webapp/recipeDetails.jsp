<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: grzegorz
  Date: 11.11.2020
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Zaplanuj Jedzonko - szczegóły przepisu</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
          rel="stylesheet">
    <link rel="stylesheet" href="./../css/style.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
</head>
<body>

<section class="dashboard-section">
    <div class="container pt-4 pb-4">
        <div class="border-dashed view-height">
            <div class="container">

                <section class="width-medium text-color-darker">
                    <div class="pb-2">
                        <div class="border-dashed view-height w-100">
                            <div class="mt-4 ml-4 mr-4">
                                <div class="row border-bottom border-3">
                                    <div class="col"><h3 class="color-header text-uppercase">Szczegóły przepisu</h3></div>
                                    <div class="col d-flex justify-content-end mb-2"><a href="/recipes" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a></div>

                                </div>

                                <table class="table borderless">
                                    <tbody>
                                    <tr class="d-flex">
                                        <th scope="row" class="col-2">Nazwa Przepisu</th>
                                        <td class="col-7">
                                            ${recipe.name}
                                        </td>
                                    </tr>
                                    <tr class="d-flex">
                                        <th scope="row" class="col-2">Opis przepisu</th>
                                        <td class="col-7">${recipe.description}</td>
                                    </tr>
                                    <tr class="d-flex">
                                        <th scope="row" class="col-2">Przygotowanie (minuty)</th>
                                        <td class="col-7">
                                            ${recipe.preparation_time}
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>

                                <div class="row d-flex">
                                    <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Sposób przygotowania</h3></div>
                                    <div class="col-2"></div>
                                    <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Składniki</h3></div>
                                </div>
                                <div class="row d-flex">
                                    <div class="col-5 p-4">
                                        <p>${recipe.preparation}</p>
                                    </div>
                                    <div class="col-2"></div>
                                    <ul class="col-5 p-4 list-unstyled">
                                        <li>${recipe.ingredients}</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

            </div>
        </div>
    </div>
</section>

</body>
</html>
