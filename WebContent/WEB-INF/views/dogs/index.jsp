<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>愛犬　一覧</h2>
        <c:choose>
            <c:when test="${dogs != null}">
                <c:forEach var="dog" items="${dogs}" varStatus="status">
                <div class="dog">
                    <img class="dog_image"
                        src="${pageContext.request.contextPath}/dogs/getImage?id=${dog.id}">
                        <table class="dog_int">
                            <tr>
                                <th>名前 </th><td><c:out value="${dog.dog_name}" /></td>
                            </tr>
                            <tr>
                                <th>犬種 </th><td><c:out value="${dog.dog_type}" /></td>
                            </tr>
                            <tr>
                                <th>年齢 </th><td><c:out value="${dog.age}" /></td>
                            </tr>
                            <tr>
                                <th>性別 </th><td><c:out value="${dog.sex}" /></td>
                            </tr>
                            <tr>
                                <th>登録日時</th><td><fmt:formatDate
                                            value='${dog.created_at}' pattern='yyyy-MM-dd' /></td>
                            </tr>
                            <tr>
                                <td colspan = 2><a href="<c:url value='/reports/show?id=${dog.id}' />">日記を見る</a><br /></td>
                            </tr>
                        </table>
                </div>
                </c:forEach>
            </c:when>
        </c:choose>

        <div id="pagination">
            （全 ${dogs_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((dogs_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/dogs/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

    </c:param>
</c:import>