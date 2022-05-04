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
    <title>Обновление пользователя</title>
</head>
<body>
<c:url var="update_person" value="/updatePerson"/>
<div class="wrapper">
    <div class="conteiner">
        <div class="window-users">
            <form:form action="${update_person}" method="post" modelAttribute="person" class="transparent">
                <div class="title title-reg">Редактирование</div>
                <form:input hidden="true" type="number" path="personId"/>
                <!-- фамилия и имя -->
                <div class="forms">
                    <div class="field">
                        <p>Фамилия</p>
                        <form:input type="text" class="input input-reg" path="surname" placeholder="Ваше фамилия" required="true"/>
                    </div>
                    <div class="field">
                        <p>Имя</p>
                        <form:input type="text" class="input input-reg" placeholder="Ваше имя" path="name" required="true"/>
                    </div>
                    <!-- возраст -->
                    <div class="field">
                        <p>Возраст</p>
                        <div class="age">
                            <form:input type="number" class="input input-reg" path="age" placeholder="Ваше возраст" required="true" min="18"/>
                        </div>
                    </div>
                    <!-- телефон -->
                    <div class="field">
                        <p>Телефон</p>
                        <div class="phone">
                            <form:input type="tel" class="input input-reg" placeholder="Ваше телефон" path="phone" required="true"/>
                        </div>
                    </div>
                    <!-- mail -->
                    <div class="field">
                        <p>E-mail</p>
                        <div class="mail">
                            <form:input type="mail" class="input input-reg" placeholder="Ваше e-mail" path="mail" required="true"/>
                        </div>
                    </div>
                    <form:input value="${person.user.userId}" hidden="hidden" path="user.userId"/>
                    <!-- логин и пaроль -->
                    <div class="field">
                        <p>Логин</p>
                        <form:input type="text" class="input input-reg" placeholder="Введиите логин" path="user.login" required="true"/>
                    </div>
                    <div class="field">
                        <p>Пароль</p>
                        <form:input type="password" class="input input-reg" placeholder="Введиите пароль" path="user.password" required="true"/>
                    </div>
                    <!-- скрытый -->
                    <div class="field hidden">
                        <p>Роль</p>
                        <div class="role">
                            <form:input value="USER" hidden="hidden" path="user.role"/>
                        </div>
                    </div>
                    <div class="field hidden">
                        <p>Активность</p>
                        <div class="role">
                            <form:input value="true" hidden="hidden" path="user.active"/>
                        </div>
                    </div>

                    <!-- скидка -->
                    <div class="field">
                        <div class="discount">
                            <form:input hidden="hidden" path="user.discountSum"/>
                        </div>
                    </div>
                    <c:if test="${updatePersonSuccess}">
                        <div >Пользователь ${savedPerson.surname} ${savedPerson.name} добавлен!</div>
                    </c:if>
                    <c:if test="${updatePersonSuccess == false}">
                        <div class="mistake">
                            <p>${personError}</p>
                        </div>
                    </c:if>
                    <div class="btns">
                        <input type="submit" value="Обновить" class="btn btn-reg">
                        <a href="/showPeople"><input type="button" value="Отмена" class="btn btn-reg"></a>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
