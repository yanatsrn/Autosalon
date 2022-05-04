<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Компании</title>
    <link rel="stylesheet" href="css/table.css"/>
</head>
<body>
<table class="table_blur">
    <h1>Компании</h1>
    <thead>
    <tr>
        <th>Название</th>
        <th>Страна происхождения</th>
        <th>Модели</th>
        <c:if test="${currentUser.getUser().getRole() eq 'ADMIN'}">
        <th>Изменить</th>
        <th>Удалить</th>
        </c:if>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${companies}" var="company">
        <tr>
            <td>${company.companyName}</td>
            <td>${company.companyCountry}</td>
            <td>
                <a href="/showCars?id=${company.companyId}">Перейти к</a>
            </td>
            <c:if test="${currentUser.getUser().getRole() eq 'ADMIN'}">
            <td>
                <a href="/updateCompany?companyId=${company.companyId}">Изменить</a>
            </td>
            <td>
                <a href="/deleteCompany?companyId=${company.companyId}">Удалить</a>
            </td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="btns">
    <c:if test="${currentUser.getUser().getRole() eq 'ADMIN'}">
        <a href="/addCompany"><input type="button" value="Добавить компанию" class="btn btn-reg"></a>
    </c:if>
    <a href="/showDiagram"><input type="button" value="Круговая диаграмма" class="btn btn-reg"></a>
    <a href="/index.jsp"><input type="button" value="Вернуться" class="btn btn-reg"></a>
</div>
</body>
</html>