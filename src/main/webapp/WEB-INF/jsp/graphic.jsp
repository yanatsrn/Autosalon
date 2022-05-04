<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>График роста цен</title>
  <link rel="stylesheet" href="css/table.css"/>
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript">
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {

      var data = google.visualization.arrayToDataTable([
        ['Год', 'Цена'],
        <c:forEach items="${statistics}" var="entry">
        ['${entry.key}', ${entry.value}],
        </c:forEach>
      ]);

      var options = {
        title: 'График изменения цены модели',
        curveType: 'function',
        legend: { position: 'right' }
      };

      var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

      chart.draw(data, options);
    }
  </script>
</head>
<body>
<div id="curve_chart" style="width: 900px; height: 500px"></div>
<div class="btns">
  <a href="/index.jsp"><input type="button" value="Вернуться" class="btn btn-reg"></a>
</div>
</body>
</html>