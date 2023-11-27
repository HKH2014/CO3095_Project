<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>List of Reports</title>
</head>
<body>
<c:forEach items="${reports}" var="r">
    <h2>${r.title}</h2>
    <p>${r.report}</p>
</c:forEach>
<p><a href="/newReport?agent=${agent}">[Create Report]</a></p>
<p><a href="/agents">[All Agents]</a></p>
</body>
</html>