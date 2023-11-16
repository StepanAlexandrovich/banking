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
        <th>Имя аккаунта</th>
        <th>Пользователь данного аккаунта</th>
        <th>Состояние аккаунта</th>
        <th>Сумма</th>
        <th>Блокировка аккаунта</th>
    </tr>
    </thead>

    <tbody>
    <#list accounts as account>
    <tr>
        <th> ${account.name} </th>
        <th> ${account.user.login} </th>
        <th> ${account.getState()} </th>
        <th> ${account.amount} </th>
        <th>
            <form action="/admin/change_state_account" method="post" >
                <br>
                <input type="hidden" name="accountId" value=${account.id}>
                <input type="submit" value="${account.action()}">
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