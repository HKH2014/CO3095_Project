<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Reports</title>
</head>
<body>
<h1>Reports</h1>
<div th:each="report, iterStat : ${reports}">
    <h2 th:text="${report.title}">Report Title</h2>
    <p>Description: <span th:text="${report.description}"></span></p>
    <p>Report Date: <span th:text="${report.reportDate}"></span></p>
    <p>Status: <span th:if="${report.status}" th:text="${report.status}">Pending</span></p>
    <hr th:if="${!iterStat.last}"/>
</div>
</body>
</html>
