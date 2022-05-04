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
    <link rel="stylesheet" href="css/table.css">
    <title>Поиск авто</title>
</head>

<body class="bg">
<c:url var="search_car" value="/searchCar"/>
<div class="wrapper">
    <div class="conteiner">
        <div class="window-users">
            <form:form action="${search_car}" method="post" class="transparent">
                <div class="title title-reg">Поиск</div>
                <div class="forms">
                    <div class="field">
                        <p>Параметр</p>
                        <div class="discount">
                            <select class="input input-reg" name="selectedParam">
                                <option>Название</option>
                                <option>Год</option>
                                <option>Пробег</option>
                                <option>Расход топлива</option>
                                <option>Цена</option>
                            </select>
                        </div>
                    </div>
                    <div class="field">
                        <p>Значение</p>
                        <input name="input" type="text" class="input input-reg" required="required"/>
                    </div>
                    <div class="btns">
                        <input type="submit" value="Поиск" class="btn btn-reg">
                        <a href="/showCompanies"><input type="button" value="Назад" class="btn btn-reg"></a>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
    <c:if test="${count != null}">
        <c:if test="${count != 0}">
        <div>
            <table class="table_blur">
                <tr>
                    <th>Название</th>
                    <th>Год</th>
                    <th>Пробег</th>
                    <th>Топливо</th>
                    <th>Расход</th>
                    <th>Коробка передач</th>
                    <th>Цена</th>
                    <c:if test="${currentUser.getUser().getRole() eq 'USER'}">
                        <th>Купить</th>
                    </c:if>
                    <c:if test="${currentUser.getUser().getRole() eq 'ADMIN'}">
                        <th>Изменить</th>
                        <th>Удалить</th>
                    </c:if>
                </tr>
                <c:forEach items="${cars}" var="car">
                    <c:if test="${car.bought == false}">
                        <tr>
                            <td>${car.name}</td>
                            <td>${car.year}</td>
                            <td>${car.distance}</td>
                            <td>${car.fuel}</td>
                            <td>${car.fuelConsumption}</td>
                            <td>${car.transmission}</td>
                            <td>${car.price}</td>
                            <c:if test="${currentUser.getUser().getRole() eq 'USER'}">
                                <td><a href="/buyCar?carId=${car.carId}">Купить</a></td>
                            </c:if>
                            <c:if test="${currentUser.getUser().getRole() eq 'ADMIN'}">
                                <td><a href="/updateCar?carId=${car.carId}">Изменить</a></td>
                                <td><a href="/deleteCar?carId=${car.carId}">Удалить</a></td>
                            </c:if>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </div>
        </c:if>
        <c:if test="${count == 0}">
            <div class="mistake">
                <h1>
                    Не найдено таких авто!
                </h1>
            </div>
        </c:if>
    </c:if>
</div>
</body>
</html>
