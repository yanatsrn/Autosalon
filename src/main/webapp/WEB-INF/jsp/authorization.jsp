<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap" rel="stylesheet">

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="css/style_users.css">
    <title>Авторизация</title>
    <c:url var="authorization_url" value="/authorization"/>
</head>
<body>
<div class="wrapper">
    <div class="conteiner">
        <div class="window-users">
            <form:form action="${authorization_url}" method="post" modelAttribute="person" class="transparent">
                <div class="title">Авторизация</div>
                <div class="forms">
                    <div class="login">
                        <p>Логин</p>
                        <form:input type="text" class="input" title="от 1 до 20 символов" placeholder="Введите логин" path="user.login"/>
                    </div>
                    <div class="password">
                        <p>Пароль</p>
                        <form:input type="password" class="input" title="1 символ в верхнем и нижнем регистре, больше 8 знаков, со спец символом @#$%^&" placeholder="Введите пароль" path="user.password"/>
                    </div>
                </div>
                <c:if test="${entered == false}">
                    <div class="mistake">
                        <p>Ошибка авторизации!</p>
                    </div>
                </c:if>
                <c:if test="${blocked == true}">
                    <div class="mistake">
                        <p>Вы заблокированы админом!</p>
                    </div>
                </c:if>
                <div class="btns">
                    <input type="submit" value="Войти" class="btn">
                    <a href="/index.jsp"><input type="button" value="Назад" class="btn"></a>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>

</html>



