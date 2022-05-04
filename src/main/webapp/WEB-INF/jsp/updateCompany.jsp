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
    <title>Обновление компании</title>
</head>
<body>
<c:url var="update_company" value="/updateCompany"/>
<div class="wrapper">
    <div class="conteiner">
        <div class="window-users">
            <form:form action="${update_company}" method="post" modelAttribute="company" class="transparent">
                <div class="title title-reg">Редактирование</div>
                <form:input hidden="true" type="text" path="companyId"/>
                <div class="forms">
                    <div class="field">
                        <p>Название</p>
                        <form:input type="text" class="input input-reg" path="companyName" required="true"/>
                    </div>
                    <div class="field">
                        <p>Страна</p>
                        <form:input type="text" class="input input-reg" path="companyCountry" required="true"/>
                    </div>
                    <c:if test="${updatedCompany}">
                        <div class="mistake">
                            <h1>Компания обновлена!</h1>
                        </div>
                    </c:if>
                    <c:if test="${updatedCompany == false}">
                        <div class="mistake">
                            <h1>${error}</h1>
                        </div>
                    </c:if>
                    <div class="btns">
                        <input type="submit" value="Обновить" class="btn btn-reg">
                        <a href="/showCompanies"><input type="button" value="Отмена" class="btn btn-reg"></a>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>
<body>

</body>
</html>
