<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2><c:out value="${user.code}" />さんのマイページ</h2>
        <h3>登録している愛犬一覧</h3>
        <tbody>
            <c:forEach var="dogs" items="${dog}" varStatus="status">


            </c:forEach>
        </tbody>
        <a href="<c:url value='/users/edit?id=${user.id}' />">ID、パスワードを変更する</a>
    </c:param>
</c:import>