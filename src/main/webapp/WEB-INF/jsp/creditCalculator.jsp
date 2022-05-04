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
  <title>Калькулятор кредитной стоимости</title>
</head>
<body>
<c:url var="learn_credit_cost" value="/creditCalculator"/>
<div class="wrapper">
  <div class="conteiner">
    <div class="window-users">
      <form:form action="${learn_credit_cost}" method="post" modelAttribute="credit" class="transparent">
        <div class="title title-reg">Калькулятор кредитной стоимости</div>
        <div class="forms">
          <div class="field">
            <p>Цена</p>
            <form:input type="number" min="1" class="input input-reg" path="price"/>
          </div>
          <div class="field">
            <p>Процент</p>
            <form:input type="number" min="1" max="100" class="input input-reg" path="procent"/>
          </div>
          <div class="field">
            <p>Количество лет</p>
            <form:input type="number" min="1" class="input input-reg" path="year"/>
          </div>
          <div class="field">
            <p>Итоговая цена</p>
            <input class="input input-reg" disabled="disabled" value="${newPrice}"/>
          </div>
          <div class="field">
            <p>Месячный взнос</p>
            <input class="input input-reg" disabled="disabled" value="${monthPrice}"/>
          </div>
          <div class="btns">
            <input type="submit" value="Рассчитать" class="btn btn-reg">
            <a href="/index.jsp"><input type="button" value="Назад" class="btn btn-reg"></a>
          </div>
        </div>
      </form:form>
    </div>
  </div>
</div>
</body>
<body>
</body>
</html>
