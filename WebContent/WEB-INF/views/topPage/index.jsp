<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
            <c:if test="${flush != null}">
                <div id="flush_success">
                    <c:out value="${flush}"></c:out>
                </div>
            </c:if>

        <h2><img alt="dog-icon" src="../webImage/icon1.png">愛犬日記へようこそ<img alt="dog-icon2" src="/Applications/Eclipse_4.6.3.app/Contents/workspace/dog_report_system/WebContent/webImage/icon2.jpeg"></h2>
    </c:param>
</c:import>