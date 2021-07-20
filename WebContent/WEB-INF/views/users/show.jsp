<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>

        <h2>
            <c:out value="${user.code}" />
            さんのマイページ
        </h2>
        <h3>登録している愛犬一覧</h3>
        <c:choose>
            <c:when test="${dogs != null}">
                <c:forEach var="dog" items="${dogs}" varStatus="status">
                    <div class="dog">
                        <img class="dog_image"
                            src="${pageContext.request.contextPath}/dogs/getImage?id=${dog.id}">
                        <table class="dog_int">
                            <tr>
                                <th>名前</th>
                                <td><c:out value="${dog.dog_name}" /></td>
                            </tr>
                            <tr>
                                <th>犬種</th>
                                <td><c:out value="${dog.dog_type}" /></td>
                            </tr>
                            <tr>
                                <th>年齢</th>
                                <td><c:out value="${dog.age}" /></td>
                            </tr>
                            <tr>
                                <th>性別</th>
                                <td><c:out value="${dog.sex}" /></td>
                            </tr>
                            <tr>
                                <th>登録日時</th>
                                <td><fmt:formatDate value='${dog.created_at}'
                                        pattern='yyyy-MM-dd' /></td>
                            </tr>
                            <tr>
                                <td colspan=2><a
                                    href="<c:url value='/dogs/edit?id=${dog.id}' />">編集する</a><br />
                                    <a href="<c:url value='/reports/new?id=${dog.id}' />">日記を書く</a></td>
                            </tr>
                        </table>
                    </div>
                </c:forEach>
            </c:when>
        </c:choose>
        <div class="link">
            <a href="<c:url value='/users/edit?id=${user.id}' />">ID、パスワードを変更する</a>
            <br /> <a href="<c:url value='/dogs/new?id=${user.id}' />">愛犬を登録する</a>
        </div>
    </c:param>
</c:import>