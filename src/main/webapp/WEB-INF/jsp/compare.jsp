<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Сравнение</title>
    <link rel="stylesheet" href="css/tableForComparison.css"/>
</head>
<c:if test="${count == 0}">
    <h1>Вы не выбрали авто для сравнения</h1>
</c:if>
<c:if test="${count != 0}">
    <div class="table">
    <table class="table_blur">
        <tr>
            <th>Название</th>
        </tr>
        <tr>
            <th>Год</th>
        </tr>
        <tr>
            <th>Пробег</th>
        </tr>
        <tr>
            <th>Топливо</th>
        </tr>
        <tr>
            <th>Расход</th>
        </tr>
        <tr>
            <th>Коробка передач</th>
        </tr>
        <tr>
            <th>Тип</th>
        </tr>
        <tr>
            <th>Салон</th>
        </tr>
        <tr>
            <th>Цвет</th>
        </tr>
        <tr>
            <th>Паркинг</th>
        </tr>
    </table>

    <c:forEach items="${selectedCars}" var="car">
        <table class="table_blur">
            <tr>
                <td>${car.name}</td>
            </tr>
            <tr>
                <td>${car.year}</td>
            </tr>
            <tr>
                <td>${car.distance}</td>
            </tr>
            <tr>
                <td>${car.fuel}</td>
            </tr>
            <tr>
                <td>${car.fuelConsumption}</td>
            </tr>
            <tr>
                <td>${car.transmission}</td>
            </tr>
            <tr>
                <td>${car.typeCar.body}</td>
            </tr>
            <tr>
                <td>${car.typeCar.salon}</td>
            </tr>
            <tr>
                <td>${car.typeCar.color}</td>
            </tr>
            <tr>
                <td>
                    <c:if test="${car.typeCar.parkingHelper}">
                        С парктроником
                    </c:if>
                    <c:if test="${car.typeCar.parkingHelper == false}">
                        Без парктроника
                    </c:if>
                </td>
            </tr>
        </table>
    </c:forEach>
    </div>
</c:if>
<div class="btns">
    <a href="/index.jsp"><input type="button" value="Вернуться" class="btn btn-reg"></a>
</div>
</body>
</html>
