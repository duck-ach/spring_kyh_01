<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <%--    <li>id=<%=((Member)request.getAttribute("member")).getId()%></li>--%>
    <%--    <li>username=<%=((Member)request.getAttribute("member")).getUsername()%></li>--%>
    <%--    <li>age=<%=((Member)request.getAttribute("member")).getAge()%></li>--%>

    <%--    JSP의 Model이 get property를 지원하여 위와 같은 코드를 아래처럼 줄일 수 있다.    --%>
    <li>id=${member.id}</li>
    <li>username=${member.username}</li>
    <li>age=${member.age}</li>

</ul>
<a href="/">메인</a>
</body>
</html>
