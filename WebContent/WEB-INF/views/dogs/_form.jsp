<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="../js/preview.js"></script>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>
    </div>
</c:if>
<label for="dog_name">名前</label><br />
<input type="text" name="dog_name" value="${dog.dog_name}" />
<br /><br />

<label for="dog_type">犬種</label><br />
<input type="text" name="dog_type" value="${dog.dog_type}" />
<br /><br />

<label for="age">年齢</label><br />
<input type="number" name="age" value="${dog.age}" />
<br /><br />

<label for="sex">性別</label><br />
<input type="text" name="sex" value="${dog.sex}" />
<br /><br />

<label for="image">画像</label><br />
<input type="file" name="image" value="${dog.image}" accept="image/png, image/jpeg" onchange = "previewImage(this);"/>
<p>
Preview:<br>
<img id="preview" src="data:image/gif;base64,R0lGODlhAQABAAAAACH5BAEKAAEALAAAAAABAAEAAAICTAEAOw==" style="max-width:200px;">
</p>
<br /><br />


<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録する</button>