<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
    <tr>
        <td>编号</td>
        <td>名称</td>
        <td>作者</td>
        <td>操作</td>
    </tr>

    <#list bookList as book>
        <tr>
            <td>${book.id}</td>
            <td>${book.name}</td>
            <td>${book.author}</td>
            <td>
                <a href="/book/preUpdate">添加</a>
                <a href="/book/delete/${book.id}">删除</a>
                <a href="/book/preUpdate/${book.id}">更新</a>
            </td>
        </tr>
    </#list>
</table>
</body>
</html>