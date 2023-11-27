<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Greeting</title>
</head>
<body>
<form:form action="/addReport?agent=${agent}" modelAttribute="report">

    Title:
    <form:input path="title"/>
    <form:errors path="title"/><br/>

    Report:
    <form:textarea path="report"/>
    <form:errors path="report"/><br/>

    <input type="submit"/><form:errors/>
</form:form>
</body>
</html>