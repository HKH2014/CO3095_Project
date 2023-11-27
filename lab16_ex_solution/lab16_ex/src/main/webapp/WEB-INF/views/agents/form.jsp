<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Greeting</title>
</head>
<body>
<form:form action="/addAgent" modelAttribute="agent">
    ID:
    <form:input path="id"/>
    <form:errors path="id"/><br/>


    Name:
    <form:input path="name"/>
    <form:errors path="name"/><br/>

    Country:
    <form:input path="country"/>
    <form:errors path="country"/><br/>

    <input type="submit"/><form:errors/>
</form:form>
</body>
</html>