<%--
  Created by IntelliJ IDEA.
  User: haifei
  Date: 26/8/2018
  Time: 11:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html">
    <title>HttpMessageConverter Demo</title>
</head>
<body>
    <div id="resp"></div>
    <input type="button" onclick="req();" value="请求"/>

    <script src="assets/js/jquery.js" type="text/javascript"></script>

    <script>
        function req() {
            $.ajax({
                url: "convert",
                data: "1-haifeiTest",
                type: "POST",
                contentType:"application/x-wisely",
                success: function (data) {
                    $("#resp").html(data)
                }
            });
        }
    </script>
</body>
</html>
