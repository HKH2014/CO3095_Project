<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>List of Agents</title>
</head>
<body>
<c:forEach items="${agents}" var="agent">
    <p>Name: ${agent.name}, Country:  ${agent.country}
        <a href="/terminate?agent=${agent.id}">[terminate]</a>
        <a href="/reports?agent=${agent.id}">[see reports]</a></p>
</c:forEach>
<p><a href="/newAgent">[Create Agent]</a></p>
</body>
</html>