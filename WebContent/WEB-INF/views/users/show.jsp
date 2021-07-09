<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>
            <c:out value="${user.code}" />
            さんのマイページ
        </h2>
        <h3>登録している愛犬一覧</h3>
        <c:choose>
            <c:when test="${dogs != null}">
                <table>
                    <tbody>
                        <tr>
                            <th>名前</th>
                            <th>犬種</th>
                            <th>年齢</th>
                            <th>性別</th>
                            <th>登録日時</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach var="dog" items="${dogs}" varStatus="status">
                            <tr class="row${status.count % 2}">
                                <td><c:out value="${dog.dog_name}" /></td>
                                <td><c:out value="${dog.dog_type}" /></td>
                                <td><c:out value="${dog.age}" /></td>
                                <td><c:out value="${dog.sex}" /></td>
                                <td class="report_date"><fmt:formatDate
                                        value='${dog.created_at}' pattern='yyyy-MM-dd' /></td>
                                <td><a href="<c:url value='/dogs/destroy?id=${dog.id}' />">削除する</a><br />
                                <a href="<c:url value='/reports/new?id=${dog.id}' />">日記を書く</a></td>

                                <td><img src="${dog.image}"></td>

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
        </c:choose>
        <a href="<c:url value='/users/edit?id=${user.id}' />">ID、パスワードを変更する</a>
        <br />
        <a href="<c:url value='/dogs/new?id=${user.id}' />">愛犬を登録する</a>
    </c:param>
</c:import>