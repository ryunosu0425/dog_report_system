<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>犬日記（仮）</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    </head>
    <body>
        <div id="wrapper">
        <div id="header">
            <div id="header_menu">
                <h1>
                    <a href="<c:url value='/' />">犬日記（仮）</a>
                </h1>
                &nbsp;&nbsp;&nbsp; <a href="<c:url value='/dogs/index' />">ワンちゃん一覧</a>&nbsp;
                <a href="<c:url value='/reports/index' />">日記を見る</a>&nbsp; <a
                    href="<c:url value='/follows/index' />">フォロー一覧</a>&nbsp;
            </div>
        </div>
        <div id="content">
                ${param.content}
            </div>
            <div id="footer">
                ryunosuke
            </div>
        </div>
    </body>
</html>