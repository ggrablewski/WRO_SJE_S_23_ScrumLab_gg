<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: grzegorz
  Date: 11.11.2020
  Time: 09:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Zaplanuj Jedzonko - dane kontaktowe</title>
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
            <div class="container w-25">
                <c:forEach items="${contact}" var="item">
                    <p>${item.title}<br/>
                    <a href=${item.href}>${item.contents}</a></p><br/>
                </c:forEach>

<%--                <p>Maria Iksińska<br/>--%>
<%--                <a href="ZaplanujJedzonko.pl">ZaplanujJedzonko.pl</a></p><br/>--%>
<%--                <p>Adres e-mail:<br/>--%>
<%--                <a href="mailto:maria.iksinska@zaplanujjedzonko.pl">maria.iksinska@zaplanujjedzonko.pl</a></p><br/>--%>
<%--                <p>Telefon do biura:<br/>--%>
<%--                <a href="tel:+221234567">+22 123 45 67</a></p><br/>--%>
<%--                <p>facebook:<br/>--%>
<%--                <a href="https://www.facebook.com/maria.iksinska">www.facebook.com/maria.iksinska</a></p><br/>--%>
            </div>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp"/>
</body>
</html>
