<%--
  Created by IntelliJ IDEA.
  User: haifei
  Date: 27/8/2018
  Time: 10:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Servlet Async support</title>
</head>
<body>
    <script type="text/javascript" src="assets/js/jquery.js"></script>
    <script type="text/javascript">
        deferred();

        function deferred() {
            $.get('defer',function (data) {
                console.log(data);
                deferred();
            });
        }
    </script>
</body>
</html>
