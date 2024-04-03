<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<!--
 WEB-INF ?
 파일(jsp)의 경로를 웹브라우저에 입력하면 해당 파일이 호출되고 나타나지는데,
 컨트롤러를 통해서만 파일을 호출하고 싶다면 WEB-INF 아래에 파일을 넣어주면된다. (Rule)
-->
<!-- 상대경로 사용, [현재 URL이 속한 계층 경로 + localhost:9090/servlet-mvc/new-form/save]
     보통은 절대경로를 많이 사용하긴 함 -->
<form action="save" method="post">
    username: <input type="text" name="username" />
    age:      <input type="text" name="age" />
    <button type="submit">전송</button>
</form>

</body>
</html>
