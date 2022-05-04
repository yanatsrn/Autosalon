<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Модели машин</title>
    <link rel="stylesheet" href="css/table.css"/>
    <c:url var="compare" value="/compare"/>
</head>
<body>
<table class="table_blur">
    <h1>Модели машин</h1>
    <tr>
        <th>Выбрать</th>
        <th><a class="btn-sort" href="/sortName">Название</a></th>
        <th><a href="/sortYear">Год</a></th>
        <th><a href="/sortDistance">Пробег</a></th>
        <th>Топливо</th>
        <th><a href="/sortFuelConsumption">Расход</a></th>
        <th>Коробка передач</th>
        <th><a href="/sortPrice">Цена</a></th>
        <th>Тип</th>
        <th>Салон</th>
        <th>Цвет</th>
        <th>Помощник</th>
        <c:if test="${currentUser.getUser().getRole() eq 'USER'}">
            <th>Купить</th>
            <th>График роста цен</th>
        </c:if>
        <c:if test="${currentUser.getUser().getRole() eq 'ADMIN'}">
            <th>Изменить</th>
            <th>Удалить</th>
        </c:if>
    </tr>
    <form:form action="${compare}" method="get" class="transparent">
        <c:forEach items="${cars}" var="car">
            <c:if test="${car.bought == false}">
                <tr>
                    <td>
                        <input type="checkbox" name="ids" value="${car.carId}">
                    </td>
                    <td>${car.name}</td>
                    <td>${car.year}</td>
                    <td>${car.distance}</td>
                    <td>${car.fuel}</td>
                    <td>${car.fuelConsumption}</td>
                    <td>${car.transmission}</td>
                    <td>${car.price}</td>
                    <td>${car.typeCar.body}</td>
                    <td>${car.typeCar.salon}</td>
                    <td>${car.typeCar.color}</td>
                    <td>
                        <c:if test="${car.typeCar.parkingHelper}">
                            С парктроником
                        </c:if>
                        <c:if test="${car.typeCar.parkingHelper == false}">
                            Без парктроника
                        </c:if>
                    </td>
                    <c:if test="${currentUser.getUser().getRole() eq 'USER'}">
                        <td><a href="/buyCar?carId=${car.carId}">Купить</a></td>
                        <td><a href="/showGraphic?name=${car.name}">График</a></td>
                    </c:if>
                    <c:if test="${currentUser.getUser().getRole() eq 'ADMIN'}">
                        <td><a href="/updateCar?carId=${car.carId}">Изменить</a></td>
                        <td><a href="/deleteCar?carId=${car.carId}">Удалить</a></td>
                    </c:if>
                </tr>
            </c:if>
        </c:forEach>
        <div class="btns">
            <input type="submit" value="Сравнить" class="btn btn-reg">
            <c:if test="${currentUser.getUser().getRole() eq 'ADMIN'}">
                <a href="/addCar"><input type="button" value="Добавить авто" class="btn btn-reg"></a>
            </c:if>
            <a href="/searchCar"><input type="button" value="Поиск" class="btn btn-reg"></a>
            <a href="/currencyCalculator"><input type="button" value="Конвертер валют" class="btn btn-reg"></a>
            <a href="/creditCalculator"><input type="button" value="Калькулятор цены" class="btn btn-reg"></a>
            <a href="/getCarReport"><input type="button" value="Сохранить каталог" class="btn btn-reg"></a>
            <a href="/index.jsp"><input type="button" value="Вернуться" class="btn btn-reg"></a>
        </div>
    </form:form>
</table>

</body>
</html>
