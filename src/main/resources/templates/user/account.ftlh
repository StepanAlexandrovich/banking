<!DOCTYPE html>
<html lang="en">
<head>
    <title>Account</title>
    <link rel="stylesheet" href="/css/style.css">
</head>

<body>
<h2>Данный аккаунт привязан к пользователю с логином ${account.user.login}</h2>
<br>
<h2>Индекс аккаунта ${account.id}</h2>
<br>
<h2>Счёт ${account.amount}</h2>
<br>
<br>
<a href="/logout">выйти из профиля</a>

<br>
<form action="/banking/add_money" method="post" >
    <input type="number" min="0" step="0.01" name="money">
    <br>
    <input type="hidden" name = accountId value="${account.id}">
    <br>
    <input type="submit" value="добавит на счёт">
    <br>
</form>

</body>
</html>