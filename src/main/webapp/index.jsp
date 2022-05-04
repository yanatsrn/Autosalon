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

    <link rel="stylesheet" href="css/style.css">
    <title>Автосалон Маневр</title>
</head>

<body>
<div class="container">

    <header class="header">
        <div class="heaeder-title">
            <div class="title">Автосалон Маневр</div>
            <div class="content">Мы поможем подобрать машину, подходящую именно вам!</div>
        </div>
    </header>

    <c:if test="${(currentUser.getUser().getRole() != 'ADMIN') and (currentUser.getUser().getRole() != 'USER')}">
    <div class="login">
        <div class="login-title">Выберите действие...</div>
        <div class="login-cards">
            <a class="card" href="/addPerson">
                <div class="registration-logo"></div>
                <div class="card-title ">Зарегистрироваться</div>
                <div class="card-content ">Станьте участником дисконтной программы нашего автосалона.</div>
            </a>
            <a class="card" href="/authorization">
                <div class="authorization-logo"></div>
                <div class="card-title ">Авторизоваться</div>
                <div class="card-content ">Получите доступ к уникальным данным сайта.</div>
            </a>
        </div>
    </div>
    </c:if>
    <c:if test="${(currentUser.getUser().getRole() eq 'ADMIN') or (currentUser.getUser().getRole() eq 'USER')}">
        <div class="login">
            <div class="login-cards">
                <a class="card" href="/showCompanies">
                    <div class="company-logo"></div>
                    <div class="card-title ">Компании</div>
                    <div class="card-content ">Вы можете ознакомиться с компаниями нашего автосалона.</div>
                </a>
                <a class="card" href="/showModels">
                    <div class="car-logo"></div>
                    <div class="card-title ">Модели</div>
                    <div class="card-content ">Все модели нашего автосалона собраны здесь.</div>
                </a>
            </div>
        </div>
        <div class="login">
            <div class="login-cards">
                <c:if test="${(currentUser.getUser().getRole() eq 'USER')}">
                <a class="card" href="/showProfile">
                    <div class="profile-logo"></div>
                    <div class="card-title ">Личный кабинет</div>
                    <div class="card-content ">Вы можете обновить свой профиль.</div>
                </a>
                </c:if>
                <c:if test="${(currentUser.getUser().getRole() eq 'ADMIN')}">
                    <a class="card" href="/showPeople">
                        <div class="profile-logo"></div>
                        <div class="card-title ">Пользователи</div>
                        <div class="card-content ">Есть возможность работы с пользователями.</div>
                    </a>
                </c:if>
                <a class="card" href="/exit">
                    <div class="exit-logo"></div>
                    <div class="card-title ">Покинуть профиль</div>
                    <div class="card-content ">Вы всегда сможете вернуться 💙</div>
                </a>
            </div>
        </div>
    </c:if>
</div>
</body>
</html>
