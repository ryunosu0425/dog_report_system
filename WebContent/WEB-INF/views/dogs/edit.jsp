<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>愛犬編集ページ</h2>
        <p>(画像は変更する際に選択して下さい。)</p>
        <form method="POST" action="<c:url value='/dogs/update' />" enctype="multipart/form-data">
            <c:import url="_form.jsp" />
        </form><br />
        <a href= "<c:url value='/dogs/destroy?id=${dog.id}'/> ">削除する</a>
    </c:param>
</c:import>