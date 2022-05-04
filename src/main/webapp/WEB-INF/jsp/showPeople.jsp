<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Пользователи</title>
    <link rel="stylesheet" href="css/table.css"/>
</head>
<body>
<table class="table_blur">
    <h1>Пользователи</h1>
    <thead>
    <tr>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Возраст</th>
        <th>Телефон</th>
        <th>Почта</th>
        <th>Логин</th>
        <th>Активный</th>
        <th>Скидка,р</th>
        <c:if test="${currentUser.getUser().getRole() eq 'ADMIN'}">
            <th>Изменить</th>
            <th>Удалить</th>
            <th>Заблокировать</th>
            <th>Разблокировать</th>
        </c:if>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${people}" var="person">
        <tr>
            <td>${person.surname}</td>
            <td>${person.name}</td>
            <td>${person.age}</td>
            <td>${person.phone}</td>
            <td>${person.mail}</td>
            <td>${person.user.login}</td>
            <td>
                <c:if test="${person.user.active}">
                    Активный
                </c:if>
                <c:if test="${person.user.active == false}">
                    Заблокирован
                </c:if>
            </td>
            <td>${person.user.discountSum}</td>
            <c:if test="${currentUser.getUser().getRole() eq 'ADMIN'}">
                <td>
                    <a href="/updatePerson?personId=${person.personId}">Изменить</a>
                </td>
                <td>
                    <a href="/deletePerson?id=${person.personId}" style=":visited{text-decoration: none;}">Удалить</a>
                </td>
                <td>
                    <a href="/blockPerson?id=${person.personId}" style=":visited{text-decoration: none;}">Заблокировать</a>
                </td>
                <td>
                    <a href="/unblockPerson?id=${person.personId}" style=":visited{text-decoration: none;}">Разблокировать</a>
                </td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="btns">
    <a href="/getReport" ><input type="button" class="btn btn-reg" value="Получить отчет" ></a>
    <a href="/index.jsp" ><input type="button" class="btn btn-reg" value="Вернуться" ></a>
</div>

</body>
</html>