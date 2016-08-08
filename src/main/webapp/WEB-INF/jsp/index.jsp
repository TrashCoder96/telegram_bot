<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<head>
    <title>Команды-сопоставления</title>
    <link>
</head>
<t:genericpage>
    <jsp:body>
        <a>${text}</a>
        <form method="post" action="/add">
            <input type="text" name="request"/>
            <br/>
            <input type="text" name="response"/>
            <br/>
            <input type="submit" value="Создать" />
        </form>
        <table border="1">
            <c:forEach items="${comparisons}" var="comparison">
            <tr>
                <td>${comparison.request}</td>
                <td>${comparison.response}</td>
                <td>
                    <form method="post" action="/delete">
                        <input type="hidden" name="id" value="${comparison.id}" />
                        <input type="submit" value="Удалить" />
                    </form>
                </td>
            </tr>
            </c:forEach>
        </table>
    </jsp:body>
</t:genericpage>