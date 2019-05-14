<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CSDN-用户登录</title>
</head>
<body>
    <form id="login" action="http://localhost:8888/user/user/login" method="get">
        <%--tabindex指定tab键的顺序--%>
        <%--target 属性规定在何处打开链接     target="_blank"打开新窗口  --%>
        <input type="text" id="loginName" name="loginName" value="" tabindex="1" placeholder="请输入登录名" />
        <input type="password" id="password" name="password" tabindex="2" placeholder="请输入密码" />
        <label class="fl" for="remember">
            <input type="checkbox" id="remember" value="" checked="checked" name="autoLogin" />
            记住我
        </label>

        <input type="submit" id="submitLogin" value="登 &nbsp; &nbsp; 录" />

    </form>

</body>
</html>
