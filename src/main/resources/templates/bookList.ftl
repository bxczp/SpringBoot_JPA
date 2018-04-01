<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="../jquery/jquery.js"></script>
<script type="text/javascript">

    function findByName(name) {
    	$.post("findByName", {'name': name}, function(data) {
    		console.log(data);
    	})
    }
    function random(limit) {
        $.post("random", {'limit': limit}, function(data) {
            console.log(data);
        })
    }

</script>
<title>Insert title here</title>
</head>
<body>
<table>
    <tr>
        <td>编号</td>
        <td>名称</td>
        <td>作者</td>
        <td>操作</td>
        <td>
            <input type="text" name="name" id="name" />
            <button onclick="findByName($('#name').val())">查詢</button>
        </td>
        <td>
            <input type="number" name="limit" id="limit" />
            <button onclick="random($('#limit').val())">隨機</button>
        </td>
    </tr>
    
    <tr>
    <form action="/book/list2" method="post">
        <td>名称<input type="text"  name="name" /><br/></td>
        <td></td>
        <td>作者<input type="text" name="author" /><br/></td>
        <td></td>
        <td>
        <button type="submit">提交</button>
        </td>
    </form>
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