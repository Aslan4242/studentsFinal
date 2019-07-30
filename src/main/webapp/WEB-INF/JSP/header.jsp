<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<div class="logo_wrap">
    <div class="logo" >Система управления студентами и их успеваемостью</div>
    <c:choose>
        <c:when test="${isLogin eq 1}">
            <a href="/login">Logout</a><span><img src="http://pixsector.com/cache/94bed8d5/av3cbfdc7ee86dab9a41d.png" style="width: 40px; vertical-align: top" alt="user"> ${username}</span>
        </c:when>
        <c:otherwise>
            <a href="/login">Login</a>
        </c:otherwise>
    </c:choose>
</div>
