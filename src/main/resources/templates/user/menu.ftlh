<!DOCTYPE html>
<html lang="en">
<head>
    <title>User</title>
    <link rel="stylesheet" href="/css/style.css">
</head>

<body>
<h2>Вы вошли как user c логином ${user.login}</h2>

<br>
<form action="/user/accounts" method="post" >
    <input type="submit" value="список ваших аккаунтов">
    <br>
</form>

<br>
<form action="/user/images" method="post" >
    <input type="submit" value="фотографии вашего профиля">
    <br>
</form>

<br>
<a href="/logout">выйти из профиля</a>

</body>
</html>