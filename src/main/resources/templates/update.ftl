<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>

<#if (book?exists) >
    <form action="/book/update" method="post">
    名称：<input  type="text" value="${book.name}" name="name" /><br/>
    作者：<input  type="text"  value="${book.author}" name="author"/><br/>
    <input type="hidden" value="${book.id}" name="id" /><br/>
<#else>
    <form action="/book/add" method="post">
    名称：<input  type="text"  name="name"/><br/>
    作者：<input  type="text" name="author" /><br/>
</#if>

<button type="submit">确认</button><br/>
<button type="reset">取消</button><br/>
<a href="javascript:history.back(-1)">点击返回</a><br/>
<button onclick=”javascript:history.back();">返回上一页</button><br/>

</form>

</table>
</body>
</html>