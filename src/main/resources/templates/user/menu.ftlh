<!DOCTYPE html>
<html lang="en">
<head>

    <title>User</title>
<!--    <link rel="stylesheet" href="/static/css/style.css">-->

    <link rel="stylesheet" href="../static/css/style.css" th:href="@{/css/style.css}">

</head>

<body>
<h2>Вы вошли как user c логином ${user.login}</h2>
<br>
<a href="/user/menu">тест</a>
<br>
<a href="/user/menu">тест</a>

<br>
<table>
    <thead>
    <tr>
        <th>Список аккаунтов</th>
    </tr>
    </thead>

    <tbody>
    <#list user.accounts as account>
    <tr>
        <th> ${account.id} </th>
    </tr>
    </#list>
    </tbody>
</table>

<br>
<table>
    <thead>
    <tr>
        <th>Изображение</th>
    </tr>
    </thead>

    <tbody>
    <#list user.images as image>
    <tr>
        <th> <img src="/image/${image.id}" width = "100px" height = "100px"> </th>
    </tr>
    </#list>
    </tbody>
</table>

<br>
<form action="/user/add_images" method="post" enctype="multipart/form-data" >
    <input type="hidden" name="userId" value=${user.id}>
    <br>
    фотография <input type="file" name="file">
    <br>
    <input type="submit" value="загрузить фотографию">
    <br>

</body>
</html>