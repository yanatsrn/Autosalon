<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap" rel="stylesheet">

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="css/style_users.css">
    <title>Калькулятор стоимости в валюте</title>
</head>
<body>
<c:url var="learn_cost" value="/currencyCalculator"/>
<div class="wrapper">
    <div class="conteiner">
        <div class="window-users">
            <form:form action="${learn_cost}" method="post" modelAttribute="info" class="transparent">
                <div class="title title-reg">Калькулятор стоимости в валюте</div>
                <div class="forms">
                    <div class="field">
                        <p>Название</p>
                        <form:select class="input input-reg" name="currencyList" path="name" required="true">
                            <c:forEach items="${map}" var="currency">
                                <option>${currency.key}</option>
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="field">
                        <p>Цена</p>
                        <form:input class="input input-reg" path="value"/>
                    </div>
                    <div class="field">
                        <p>Новая цена</p>
                        <input class="input input-reg" value="${newPrice}"/>
                    </div>
                    <div class="btns">
                        <input type="submit" value="Рассчитать" class="btn btn-reg">
                        <a href="/index.jsp"><input type="button" value="Назад" class="btn btn-reg"></a>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
<body>
</body>
</html>
