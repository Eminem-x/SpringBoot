<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>

<h1>iPhone 13 Pro !!! 1元秒杀!!!</h1>

<form id="form" action="${pageContext.request.contextPath}/doSecKill" enctype="application/x-www-form-urlencoded">
    <input type="hidden" id="productId" name="productId" value="0101">
    <input type="button" id="secKillBtn" name="secKillBtn" value="秒杀点我">
</form>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery/jquery-3.1.0.js"></script>
<script type="text/javascript">
    $(function () {
        $("#secKillBtn").click(function () {
            var url = $("#form").attr("action");
            $.post(url, $("#form").serialize(), function (data) {
                if (data == "false") {
                    alert("抢光了!");
                    $("#secKillBtn").attr("disabled", true);
                } else if(data == "true") {
                    alert("抢到了!");
                    $("#secKillBtn").attr("disabled", true);
                }
            });
        })
    })
</script>
</html>