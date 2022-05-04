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
    <title>Добавление авто</title>
</head>

<body>
<c:url var="add_car" value="/addCar"/>
<div class="wrapper">
    <div class="conteiner">
        <div class="window-users">
            <form:form action="${add_car}" method="post" modelAttribute="car" class="transparent">
                <div class="title title-reg">Добавление</div>
                <div class="forms">
                    <div class="field">
                        <p>Название</p>
                        <form:input type="text" class="input input-reg" path="name" required="true"/>
                    </div>
                    <div class="field">
                        <p>Год</p>
                        <form:input type="number" class="input input-reg" path="year" required="true" min="1999"/>
                    </div>
                    <div class="field">
                        <p>Пробег</p>
                        <div class="age">
                            <form:input type="number" class="input input-reg" path="distance" required="true" />
                        </div>
                    </div>
                    <div class="field">
                        <p>Топливо</p>
                        <div class="phone">
                            <form:select class="input input-reg" name="fuelList" path="fuel" >
                                <option>Бензин</option>
                                <option>Дизель</option>
                            </form:select>
                        </div>
                    </div>
                    <div class="field">
                        <p>Расход топлива</p>
                        <div class="mail">
                            <form:input class="input input-reg" path="fuelConsumption" required="true"/>
                        </div>
                    </div>
                    <div class="field">
                        <p>Коробка передач</p>
                        <form:select class="input input-reg" name="transmissionList" path="transmission">
                            <option>Механика</option>
                            <option>Автомат</option>
                        </form:select>
                    </div>
                    <div class="field">
                        <p>Цена</p>
                        <form:input class="input input-reg" path="price" required="required"/>
                    </div>
                    <div class="field hidden">
                        <p>Роль</p>
                        <div class="role">
                            <form:input value="false" hidden="hidden" path="bought"/>
                        </div>
                    </div>
                    <div class="field">
                        <p>Компания</p>
                        <div class="discount">
                            <form:select class="input input-reg" name="companyList" modelAttribute="companies" path="company.companyName">
                                <c:forEach items="${companies}" var="comp">
                                    <option>${comp.companyName}</option>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>
                    <div class="field">
                        <p>Тип</p>
                        <div class="discount">
                            <form:select class="input input-reg" name="typeList" modelAttribute="types" path="typeCar.body">
                                <c:forEach items="${types}" var="type">
                                    <option>${type.typeId} ${type.color} ${type.body}
                                        <c:if test="${type.parkingHelper}">
                                            С парктроником
                                        </c:if>
                                        <c:if test="${type.parkingHelper == false}">
                                            Без парктроника
                                        </c:if>
                                    </option>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>
                    <c:if test="${addCarSuccess}">
                        <div>
                            <p>Авто ${savedCar.name} добавлено!
                            </p>
                        </div>
                    </c:if>
                    <c:if test="${addCarSuccess == false}">
                        <div class="mistake">
                            <p>${carError}</p></div>
                    </c:if>
                    <div class="btns">
                        <input type="submit" value="Добавить" class="btn btn-reg">
                        <a href="/showCompanies"><input type="button" value="Назад" class="btn btn-reg"></a>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>




<%--    <form:input value="1" hidden="hidden" path="typeCar.typeId"/>--%>



</html>
