<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>ID、パスワード変更ページ</h2>
        <p>（パスワードは変更時のみ入力して下さい）</p>
        <form method="POST" action="<c:url value='/users/update' />">
            <c:import url="_form.jsp" />
        </form>
    </c:param>
</c:import>