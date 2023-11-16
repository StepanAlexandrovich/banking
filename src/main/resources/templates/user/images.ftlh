<!DOCTYPE html>
<html lang="en">
<head>
    <title>Images</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<form action="/user/add_images" method="post" enctype="multipart/form-data" >
    <input type="hidden" name="userId" value=${user.id}>
    <br>
    фотография <input type="file" name="file">
    <br>
    <input type="submit" value="загрузить фотографию">
    <br>
</form>
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
<a href="/logout">выйти из профиля</a>

</body>
</html>