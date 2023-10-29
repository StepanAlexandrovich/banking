<!DOCTYPE html>
<html lang="en">
<head>
    <title>Accounts</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Список аккаутов по id</th>
        <th>Пользователь данного аккаунта</th>
        <th>Блокировка аккаунта</th>
    </tr>
    </thead>

    <tbody>
    <#list accounts as account>
    <tr>
        <th> ${account.id} </th>
        <th> ${account.user.login} </th>
        <th>
            <form action="/admin/ban_account" method="post" >
                <br>
                <input type="hidden" name="accountId" value=${account.id}>
                <input type="submit" value="заблокироать аккаунт">
            </form>
        </th>
    </tr>
    </#list>
    </tbody>
</table>

<br>
<a href="/logout">выйти из профиля</a>

</body>
</html>