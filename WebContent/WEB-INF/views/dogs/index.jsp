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
        <table id="dog_list">
            <tbody>
                <tr>
                   <th>名前</th>
                   <th>犬種</th>
                   <th>年齢</th>
                   <th>性別</th>
                   <th>登録日時</th>
                </tr>
                <c:forEach var="dog" items="${dogs}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${dog.dog_name}" /></td>
                        <td><c:out value="${dog.dog_type}" /></td>
                        <td><c:out value="${dog.age}" /></td>
                        <td><c:out value="${dog.sex}" /></td>
                        <td class="report_date"><fmt:formatDate
                                value='${dog.created_at}' pattern='yyyy-MM-dd' /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

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