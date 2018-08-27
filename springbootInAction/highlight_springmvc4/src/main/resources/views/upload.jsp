<%--
  Created by IntelliJ IDEA.
  User: haifei
  Date: 26/8/2018
  Time: 11:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>

    <div class="upload">
        <form action="upload" enctype="multipart/form-data" method="post">
            <input type="file" name="file">
            <br>
            <input type="submit" value="上传">
        </form>
    </div>
</body>
</html>
