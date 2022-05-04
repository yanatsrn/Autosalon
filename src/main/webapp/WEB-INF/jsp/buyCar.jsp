<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Окно после покупки</title>
    <c:url var="add_purchase" value="/buyCar"/>
    <link rel="stylesheet" href="css/table.css"/>
</head>
<body>
<form:form action="${add_purchase}" method="post" modelAttribute="purchase">
    <h1>Спасибо за покупку ${purchase.car.name} !</h1>
</form:form>
<div class="btns">
    <a href="/showCompanies"><input type="button" value="Вернуться" class="btn btn-reg"></a>
</div>
</body>
</html>
