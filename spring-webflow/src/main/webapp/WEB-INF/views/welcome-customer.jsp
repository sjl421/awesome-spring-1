<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Ternence
  Date: 2017/10/31
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>customer</title>
</head>
<body>
<h1>欢迎来到Customer页面</h1>
<form:form>
    <input placeholder="请输入手机号码" name="phoneNumber">
    <%--流程执行的key--%>
    <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
    <%--触发事件--%>
    <input type="submit" name="_eventId_phoneEntered" value="查找顾客">
</form:form>
</body>
</html>
